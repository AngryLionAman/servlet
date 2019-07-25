/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inquiryhere.com
 */
public class database {

    String URL;
    String userName;
    String passWord;
    Connection connection ;

    public database() {
        URL = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
        userName = "root";
        passWord = null;
    }

    public Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, userName, passWord);
        } catch (ClassNotFoundException ex) {
            System.err.println("Problem in loading driver" + ex);
        }
        return connection;
    }
    public void disconnect (){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
