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

    private int getUserId(Connection con, String userName, String email) throws SQLException, ClassNotFoundException, Exception {

        String sql;
        if (email != null) {
            sql = "select id from newuser where username = ? and email = ?";
        } else {
            sql = "select id from newuser where username = ? and email is ?";
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(newUser.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param con
     * @param userName
     * @param userFullName
     * @param email
     * @param userType
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int saveNewGuestUser(Connection con, String userName, String userFullName, String email, String userType) throws SQLException, ClassNotFoundException, Exception {

        String sql = "insert into newuser(username,firstname,user_type,email)values(?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, userFullName);
            ps.setString(3, userType);
            ps.setString(4, email);
            ps.execute();
            //Get the userId
            return getUserId(con, userName, email);
        } catch (SQLException msg) {
            Logger.getLogger(newUser.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }
}
