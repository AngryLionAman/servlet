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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author inquiryhere.com
 */
public class validateUser {

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String v = "SELECT id FROM newuser WHERE email = ? AND password = ? LIMIT 1";
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(v);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            return resultSet.first();

        } catch (SQLException e) {
            Logger.getLogger(validateUser.class.getName()).log(Level.SEVERE, username, e);
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

        return false;
    }

    /**
     *
     * @param eMail
     * @param passWord
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public void validateAdminUser(String eMail, String passWord, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, Exception {
        //HttpServletRequest request = null;
        HttpSession session = request.getSession(false);
        
        DatabaseConnection ds = new DatabaseConnection();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select id,username from newuser where email = ? and password = ?";
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eMail);
            preparedStatement.setString(2, passWord);
            resultSet = preparedStatement.executeQuery();
            int foundUserId = 0;
            String userName = null;
            if (resultSet.next()) {
                foundUserId = resultSet.getInt("id");
                userName = resultSet.getString("username");
            }
            if (foundUserId != 0) {
                session.setAttribute("adminUserId", foundUserId);
                session.setAttribute("userName", userName);
                session.setMaxInactiveInterval(600);
                request.getRequestDispatcher("adminModule.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("visit.jsp").forward(request, response);
            }

        } catch (SQLException msg) {
            Logger.getLogger(validateUser.class.getName()).log(Level.SEVERE, eMail, msg);
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
    }
}
