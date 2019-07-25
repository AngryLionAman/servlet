/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.connect.database;

/**
 *
 * @author inquiryhere.com
 */
public class validateUser {

    public boolean validateUser(String username, String password) throws SQLException {

        boolean found = false;
        database obj = new database();
        try (Connection connection = obj.connect()) {
            try {
                String v = "SELECT ID,email,password FROM newuser WHERE email = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(v)) {
                    preparedStatement.setString(1, username);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            String password1 = resultSet.getString("password");
                            if (password.equals(password1)) {
                                found = true;
                            }
                        }
                    }
                }

            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        obj.disconnect();
        return found;
    }
}
