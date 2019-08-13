/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.security;

import com.connect.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author inquiryhere.com
 */
public class validateUser {

    public boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException, Exception {

        boolean found = false;
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String v = "SELECT ID,email,password FROM newuser WHERE email = ?";
            connection =con.getConnection();
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
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException msg) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException msg) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException msg) {
                }
            }

        }

        return found;
    }
    public void validateAdminUser(String eMail,String passWord,HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException{
        //HttpServletRequest request = null;
        HttpSession session = request.getSession();
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String sql = "select id from newuser where email = ? and password = ?";
            connection = con.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eMail);
            preparedStatement.setString(2, passWord);
            resultSet = preparedStatement.executeQuery();
            int foundUserId = 0;
            if(resultSet.next()){
                foundUserId = resultSet.getInt("id");
            }
            if(foundUserId != 0){
                session.setAttribute("adminUserId", foundUserId);
                response.sendRedirect("adminModule.jsp");
            }else{
                response.sendRedirect("visit.jsp?msg=invalid crenditial");
            }
            
        }catch(SQLException msg){
            throw msg;
        }finally{
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException msg) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException msg) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException msg) {
                }
            }
        }
    }
}
