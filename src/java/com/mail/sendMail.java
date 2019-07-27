package com.mail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author inquiryhere.com
 */
public class sendMail {
    public boolean sendMail(String mailAdd,String pass) {
        String host = "mail.inquiryhere.com";
        final String user = "help@inquiryhere.com";//change accordingly  
        final String password = "";//change accordingly  

        String to = mailAdd;//change accordingly  

        //Get the session object  
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        //Compose the message  
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("inquiryhere.com : Password");
            message.setText("Please dont share with anyone,Your password is:-"+pass);

            //send the message  
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {
            System.out.println(e.fillInStackTrace());
        }
        return true;
    }
}
