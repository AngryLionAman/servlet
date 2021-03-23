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

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author AngryLion
 */
public class OperationWithPassword {

    /**
     *
     * @param userId
     * @param pass
     * @return
     * @throws SQLException
     */
    public boolean changePassword(int userId, String pass) throws SQLException {

        String sql = "UPDATE newuser SET password = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pass);
            ps.setInt(2, userId);
            return ps.executeUpdate() == 1;
        }
    }

    /**
     *
     * @param pass
     * @param userId
     * @return
     * @throws SQLException
     */
    public boolean isPasswordMatched(String pass, int userId) throws SQLException {
        String sql = "SELECT password FROM newuser WHERE password = ? AND id = ? LIMIT 1";
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pass);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.first();
            }
        }
    }
}
