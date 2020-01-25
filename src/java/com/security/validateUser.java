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

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT id FROM newuser WHERE email = ? AND password = ? LIMIT 1";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.first();
            }
        } catch (SQLException e) {
            Logger.getLogger(validateUser.class.getName()).log(Level.SEVERE, username, e);
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

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "select id,username from newuser where email = ? and password = ?";

        int foundUserId = 0;
        String userName = null;

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, eMail);
            ps.setString(2, passWord);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    foundUserId = rs.getInt("id");
                    userName = rs.getString("username");
                }
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
        }
    }
}
