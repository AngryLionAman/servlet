/*
 * Copyright 2020 AngryLion.
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
package com.mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author AngryLion
 */
public class Mail {

    public static void sendMail(String reciepent, String userFullName, int OTP) throws Exception {
        // System.out.println("Setting up the mail\n");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "mail.inquiryhere.com");
        properties.put("mail.smtp.port", "2525");

        String myAccountEmail = "aman@inquiryhere.com";
        String myPassword = "pa.......il1";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myPassword);
            }
        });

        Message message = preparedMessage(session, myAccountEmail, reciepent,userFullName,OTP);
        Runnable r = () -> {
            try {
                //  System.out.println("Mail Sending");
                Transport.send(message);
            } catch (MessagingException ex) {
                Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        new Thread(r).start();
        //System.out.println("\nMail sent successfully");
    }

    private static Message preparedMessage(Session session, String myAccountEmail, String reciepent, String fullName, int OTP) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reciepent));
            message.setSubject("Change password | inquiryhere.com");

            /*This is used for simple text message*/
            //message.setText("This is first");

            /*This is used for HTML text message*/
            String htmlText = "<h1>Dear "+fullName+"</h1>"
                    + "Thanks for joinig our community <br><br>"
                    +"Your one time password is - > <b>"+OTP+"</b><br><br>"
                    +"<b>Note:</b>If you need any help, Please mail us on help@gmail.com<br><br>"
                    +"<h3>Thanks Again</h3>"
                    +"<h4>inquiryhere Team</h4>";

            message.setContent(htmlText, "text/html");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
