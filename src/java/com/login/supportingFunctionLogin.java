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
package com.login;

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
public class supportingFunctionLogin {

    /**
     *
     * @param con
     * @param email
     * @param password
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int GetUserIdByEmailAndPassword(Connection con, String email, String password) throws SQLException, ClassNotFoundException, Exception {

        String sql = "select id from newuser where email = ? and password = ? limit 1";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunctionLogin.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param con
     * @param email
     * @param password
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean IsUserIsPresent(Connection con, String email, String password) throws SQLException, ClassNotFoundException, Exception {

        String sql = "select id from newuser where email = ? and password = ? limit 1";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.first();
            }
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunctionLogin.class.getName()).log(Level.SEVERE, null, msg);
        }
        return false;
    }
}
