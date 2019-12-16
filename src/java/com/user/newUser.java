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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class newUser {

    private int getUserId(String userName, String email) throws SQLException, ClassNotFoundException, Exception {
        
        DatabaseConnection ds = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            String sql;
            if(email != null){
                sql = "select id from newuser where username = ? and email = ?";
            }else{
                sql = "select id from newuser where username = ? and email is ?";
            }            
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException msg) {
            Logger.getLogger(newUser.class.getName()).log(Level.SEVERE, null, msg);
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
     * @param userName
     * @param userFullName
     * @param email
     * @param userType
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int saveNewGuestUser(String userName,String userFullName, String email,String userType) throws SQLException, ClassNotFoundException, Exception {
       
        DatabaseConnection ds = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ds.getConnection();
            String sql = "insert into newuser(username,firstname,user_type,email)values(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userFullName);
            ps.setString(3, userType);
            ps.setString(4, email);
            ps.execute();
            //Get the userId
            return getUserId(userName, email);
        } catch (SQLException msg) {
            Logger.getLogger(newUser.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
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
}
