/*
 * Copyright 2020 AngryLion.
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
package com.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class OperationWithEmail {

    /**
     *
     * @param con
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean isEmailAvailabe(Connection con, String email) throws SQLException {
        String sql = "SELECT email FROM newuser WHERE email = ? LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.first();
            }
        }
    }

    /**
     *
     * @param con
     * @param userId
     * @param data
     * @return
     * @throws SQLException
     */
    public boolean addEmail(Connection con, int userId, String data) throws SQLException {

        String sql = "INSERT INTO extra_user_details(user_id,data_type,data)VALUES(?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, "email");
            ps.setString(3, data);
            return ps.execute();
        }
    }
}
