/*
 * Copyright 2019 AngryLion.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author AngryLion
 */
public class NormalizeURL {

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        Scanner in = new Scanner(System.in);
        while(true){
        System.out.println("Enter the url :- ");
        String taintedURL = in.nextLine();
        //String taintedURL = "http://localhost:8084/inquiryhere/Answer.jsp?q=%E0%A4%85%E0%A4%82%E0%A4%A4%E0%A4%B0%E0%A5%8D%E0%A4%B0%E0%A4%BE%E0%A4%B7%E0%A5%8D%E0%A4%9F%E0%A5%8D%E0%A4%B0%E0%A5%80%E0%A4%AF-%E0%A4%B8%E0%A4%B9%E0%A4%AF%E0%A5%8B%E0%A4%97-%E0%A4%94%E0%A4%B0-%E0%A4%B8%E0%A4%A6%E0%A4%AD%E0%A4%BE%E0%A4%B5-%E0%A4%95%E0%A5%87-%E0%A4%B0%E0%A4%BE%E0%A4%B7%E0%A5%8D%E0%A4%9F%E0%A5%8D%E0%A4%B0%E0%A5%80%E0%A4%AF-%E0%A4%B2%E0%A4%95%E0%A5%8D%E0%A4%B7%E0%A5%8D%E0%A4%AF-%E0%A4%95%E0%A5%80-%E0%A4%AD%E0%A4%BE%E0%A4%B0%E0%A4%A4%E0%A5%80%E0%A4%AF-%E0%A4%B8%E0%A4%82%E0%A4%B5%E0%A4%BF%E0%A4%A7%E0%A4%BE%E0%A4%A8-%E0%A4%AE%E0%A5%87%E0%A4%82-%E0%A4%85%E0%A4%AD%E0%A4%BF%E0%A4%B5%E0%A5%8D%E0%A4%AF%E0%A4%95%E0%A5%8D%E0%A4%A4%E0%A4%BF-%E0%A4%95%E0%A5%88%E0%A4%B8%E0%A5%87-%E0%A4%B9%E0%A5%81%E0%A4%88&Id=4090";
        String new_url = normalize(taintedURL);
        System.out.println(new_url);
        }
    }

    public static String normalize(final String taintedURL) throws MalformedURLException, URISyntaxException {
        final URL url;
        try {
            url = new URI(taintedURL).normalize().toURL();
        } catch (URISyntaxException e) {
            throw new MalformedURLException(e.getMessage());
        }

        final String path = url.getPath().replace("/$", "");
        final SortedMap<String, String> params = createParameterMap(url.getQuery());
        final int port = url.getPort();
        final String queryString;

        if (params != null) {
            // Some params are only relevant for user tracking, so remove the most commons ones.
            for (Iterator<String> i = params.keySet().iterator(); i.hasNext();) {
                final String key = i.next();
                if (key.startsWith("utm_") || key.contains("session")) {
                    i.remove();
                }
            }
            queryString = "?" + canonicalize(params);
        } else {
            queryString = "";
        }

        return url.getProtocol() + "://" + url.getHost()
                + (port != -1 && port != 80 ? ":" + port : "")
                + path + queryString;
    }

    /**
     * Takes a query string, separates the constituent name-value pairs, and
     * stores them in a SortedMap ordered by lexicographical order.
     *
     * @return Null if there is no query string.
     */
    private static SortedMap<String, String> createParameterMap(final String queryString) {
        if (queryString == null || queryString.isEmpty()) {
            return null;
        }

        final String[] pairs = queryString.split("&");
        final Map<String, String> params = new HashMap<String, String>(pairs.length);

        for (final String pair : pairs) {
            if (pair.length() < 1) {
                continue;
            }

            String[] tokens = pair.split("=", 2);
            for (int j = 0; j < tokens.length; j++) {
                try {
                    tokens[j] = URLDecoder.decode(tokens[j], "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
            switch (tokens.length) {
                case 1: {
                    if (pair.charAt(0) == '=') {
                        params.put("", tokens[0]);
                    } else {
                        params.put(tokens[0], "");
                    }
                    break;
                }
                case 2: {
                    params.put(tokens[0], tokens[1]);
                    break;
                }
            }
        }

        return new TreeMap<String, String>(params);
    }

    /**
     * Canonicalize the query string.
     *
     * @param sortedParamMap Parameter name-value pairs in lexicographical
     * order.
     * @return Canonical form of query string.
     */
    private static String canonicalize(final SortedMap<String, String> sortedParamMap) {
        if (sortedParamMap == null || sortedParamMap.isEmpty()) {
            return "";
        }

        final StringBuffer sb = new StringBuffer(350);
        final Iterator<Map.Entry<String, String>> iter = sortedParamMap.entrySet().iterator();

        while (iter.hasNext()) {
            final Map.Entry<String, String> pair = iter.next();
            sb.append(percentEncodeRfc3986(pair.getKey()));
            sb.append('=');
            sb.append(percentEncodeRfc3986(pair.getValue()));
            if (iter.hasNext()) {
                sb.append('&');
            }
        }

        return sb.toString();
    }

    /**
     * Percent-encode values according the RFC 3986. The built-in Java
     * URLEncoder does not encode according to the RFC, so we make the extra
     * replacements.
     *
     * @param string Decoded string.
     * @return Encoded string per RFC 3986.
     */
    private static String percentEncodeRfc3986(final String string) {
        try {
            return URLEncoder.encode(string, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            return string;
        }
    }
}
