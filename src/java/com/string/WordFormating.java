/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.string;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inquiryhere.com
 */
public class WordFormating {

    /**
     *
     * @param str
     * @return
     */
    public String firstName(String str) {
        try {
            if (str != null && str.length() > 0 && !(str.trim()).equals("null") && !str.equals("") && !str.equals(" ")) {

                String[] final_word = str.trim().split("\\s");
                return final_word[0].substring(0, 1).toUpperCase() + final_word[0].substring(1).toLowerCase();
            } else {
                return "Empty Name";
            }

        } catch (Exception msg) {
            Logger.getLogger(WordFormating.class.getName()).log(Level.SEVERE, str, msg);
        }
        return "Empty Name";
    }

    /**
     *
     * @param sentence
     * @return
     */
    public String convertStringUpperToLower(String sentence) {
        if (sentence.isEmpty()) {
            return "String Not Found";
        }
        String finalSentenct = "";
        try {
            sentence = sentence.trim();
            char[] c = sentence.toCharArray();
            String str1 = "";
            //If you don't use the '=' (equals) then will missed the last char
            for (int i = 0; i < sentence.length(); i++) {//If String having the extra white space
                if ((c[i] == ' ' && c[i + 1] != ' ') || (c[i] != ' ')) {
                    str1 += c[i];
                }
            }
            //Spliting the sentence into words
            String[] word = str1.split(" ");
            //Captlizing the every word
            for (int i = 0; i < word.length; i++) {
                word[i] = word[i].substring(0, 1).toUpperCase() + word[i].substring(1).toLowerCase();
                finalSentenct += word[i] + " ";
            }
            finalSentenct = finalSentenct.trim();

//end of the script
        } catch (Exception msg) {
            finalSentenct = msg.toString();
        }
        return finalSentenct;
    }

    /**
     *
     * @param sentence
     * @return
     */
    public String RemoveWhiteSpace(String sentence) {

        try {
            sentence = sentence.trim().replaceAll("\n", " ");
            char[] c = sentence.toCharArray();
            String str1 = "";
            for (int i = 0; i < sentence.length(); i++) {//If String having the extra white space
                if ((c[i] == ' ' && c[i + 1] != ' ') || (c[i] != ' ')) {
                    str1 += c[i];
                }
            }
            return str1.trim();
        } catch (Exception msg) {
            Logger.getLogger(WordFormating.class.getName()).log(Level.SEVERE, sentence, msg);
        }
        return null;
    }

    /**
     *
     * @param url
     * @return
     */
    public String UrlFormat(String url) {
        try {
            return RemoveWhiteSpace(url.replaceAll("[-+.^:,#%()@$*<>;'_{}=\"!|/?\\\\\\[\\]&]", " ")).replaceAll(" ", "-");
        } catch (Exception msg) {
            Logger.getLogger(WordFormating.class.getName()).log(Level.SEVERE, url, msg);
        }
        return null;
    }
}
