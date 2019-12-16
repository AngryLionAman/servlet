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

/**
 *
 * @author AngryLion
 */
public class SupportingFunction {

    /**
     *
     * @param email
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int GetUserIdByEmail(String email) throws SQLException, ClassNotFoundException {
        
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

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String CreateUsername(String username) throws SQLException, ClassNotFoundException {
        
        DatabaseConnection dc = DatabaseConnection.getInstance();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "SELECT username FROM newuser WHERE username = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.first()){
                Random rand = new Random();
                int number = rand.nextInt(100);
                CreateUsername(username + number);
            }else{
                return username;
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
        return null;
    }

    /**
     *
     * @param args
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean EmailIsAvaliabe(String args) throws SQLException, ClassNotFoundException {

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
            return rs.first();
           
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
