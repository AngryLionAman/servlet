/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author inquiryhere.com
 */
public class validateUser {

    String DB_URL_ = "jdbc:mysql://localhost/bharat";
    String DB_USERNAME_ = "root";
    String DB_PASSWORD_ = null;

    public boolean validateUser(String username, String password) throws SQLException {

        boolean found = false;
        try {
            String cookiesMail = username;
            String cookiesPassword = password;

            Connection connection = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);

            try {

                String v = "SELECT ID,email,password FROM newuser WHERE email = ?";

                preparedStatement = connection.prepareStatement(v);
                preparedStatement.setString(1, cookiesMail);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String password1 = resultSet.getString("password");
                    if (cookiesPassword.equals(password1)) {
                        found = true;
                    }
                }
                connection.close();
                resultSet.close();
                preparedStatement.close();

            } catch (SQLException e) {
            }
        } catch (ClassNotFoundException msg) {

        }
        return found;
    }

}
