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
import javax.sql.DataSource;

/**
 *
 * @author inquiryhere.com
 */
public class validateUser {

    public boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException, Exception {

        boolean found = false;
        database obj = new database();
        DataSource dataSource = obj.setUpPool();
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String v = "SELECT ID,email,password FROM newuser WHERE email = ?";
            preparedStatement = connection.prepareStatement(v);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String password1 = resultSet.getString("password");
                if (password.equals(password1)) {
                    found = true;
                }
            }

        } catch (SQLException e) {
            throw e;
        }finally{
            if(connection != null){
                try{
                    connection.close();
                }catch(SQLException msg){}
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch(SQLException msg){}
            }
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(SQLException msg){}
            }
        }

        return found;
    }
}
