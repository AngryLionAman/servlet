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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inquiryhere.com
 */
public class validateUser {

    /**
     *
     * @param con
     * @param username
     * @param password
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public boolean validateUser(Connection con, String username, String password) throws SQLException, ClassNotFoundException, Exception {

        String sql = "SELECT id FROM newuser WHERE email = ? AND password = ? LIMIT 1";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
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
}
