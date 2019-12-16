/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.security;

import com.connect.DatabaseConnection;
import com.connect.PoolConnection;
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
import javax.sql.DataSource;

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
        HttpSession session = request.getSession();
        
        DatabaseConnection ds = new DatabaseConnection();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select id from newuser where email = ? and password = ?";
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, eMail);
            preparedStatement.setString(2, passWord);
            resultSet = preparedStatement.executeQuery();
            int foundUserId = 0;
            if (resultSet.next()) {
                foundUserId = resultSet.getInt("id");
            }
            if (foundUserId != 0) {
                session.setAttribute("adminUserId", foundUserId);
                response.sendRedirect("adminModule.jsp");
            } else {
                response.sendRedirect("visit.jsp?msg=invalid crenditial");
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
