package com.tech.facebook;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
public class CreateFBConnection {
    public static final String APP_ID = "484385559013956";
    public static final String APP_SECRET = "b9354b9069915d00fa557314e15e84d4";
    public static final String REDIRECT_URI = "http://localhost:8080/inquiryhere/fbhome.jsp";
    String accessToken = "";
    public String getFBAuthUrl() {
        String fbLoginUrl = "";
        try {
            fbLoginUrl = "http://www.facebook.com/dialog/oauth?"
                    + "client_id="
                    + CreateFBConnection.APP_ID
                    + "&redirect_uri="
                    + URLEncoder.encode(CreateFBConnection.REDIRECT_URI,
                            "UTF-8") + "&scope=email";
        } catch (UnsupportedEncodingException e) {
        }
        return fbLoginUrl;
    }
    public String getFBGraphUrl(String code) {
        String fbGraphUrl = "";
        try {
            fbGraphUrl = "https://graph.facebook.com/oauth/access_token?"
                    + "client_id="
                    + CreateFBConnection.APP_ID
                    + "&redirect_uri="
                    + URLEncoder.encode(CreateFBConnection.REDIRECT_URI,
                            "UTF-8") + "&client_secret=" + APP_SECRET
                    + "&code=" + code;
        } catch (UnsupportedEncodingException e) {
        }
        return fbGraphUrl;
    }
    public String getAccessToken(String code) {
        URL fbGraphURL;
        try {
            fbGraphURL = new URL(getFBGraphUrl(code));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid code received " + e);
        }
        URLConnection fbConnection;
        StringBuffer b = null;
        try {
            fbConnection = fbGraphURL.openConnection();
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(
                    fbConnection.getInputStream()));
            String inputLine;
            b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to connect with Facebook " + e);
        }
        accessToken = b.toString();
        if (accessToken.startsWith("{")) {
            throw new RuntimeException("ERROR: Access Token Invalid: "
                    + accessToken);
        }
        return accessToken;
    }
}