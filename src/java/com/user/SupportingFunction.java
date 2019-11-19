/*
 * Copyright 2019 AngryLion.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.user;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AngryLion
 */
public class SupportingFunction {

    public int GetUserIdByEmail(String email) throws SQLException {
        
        DatabaseConnection dc = DatabaseConnection.getInstance();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
                
        try {
            con = dc.getConnection();
            String sql = "SELECT id FROM newuser WHERE email = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");                
            }
        } catch (SQLException msg) {
            Logger.getLogger(SupportingFunction.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return 0;
    }

    public String CreateUsername(String username) throws SQLException {
        String finalUsername = "";

        DatabaseConnection dc = DatabaseConnection.getInstance();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "select username from newuser where username = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            boolean usernameFound = false;
            while (rs.next()) {
                usernameFound = true;
            }
            if (usernameFound) {
                Random rand = new Random();
                int number = rand.nextInt(100);
                finalUsername = username + number;
                CreateUsername(finalUsername);
            } else {
                finalUsername = username;
            }
        } catch (SQLException msg) {
            Logger.getLogger(SupportingFunction.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return finalUsername;
    }

    public boolean EmailIsAvaliabe(String args) throws SQLException {

        DatabaseConnection dc = DatabaseConnection.getInstance();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dc.getConnection();
            String sql = "SELECT email FROM newuser WHERE email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, args);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException msg) {
            Logger.getLogger(SupportingFunction.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return false;
    }
}
