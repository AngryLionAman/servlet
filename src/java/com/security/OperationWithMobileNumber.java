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
public class OperationWithMobileNumber {

    /**
     *
     * @param con
     * @param userId
     * @return
     * @throws SQLException
     */
    public int getMobileNumberByuserId(Connection con, int userId) throws SQLException {
        
        String sql = "SELECT data FROM extra_user_details WHERE data_type = 'mobile_number' AND user_id = ? ORDER BY unique_id DESC LIMIT 1";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("data");
                }
            }
        }
        return 0;
    }

    /**
     *
     * @param con
     * @param userid
     * @param mob
     * @return
     * @throws SQLException
     */
    public boolean addMobileNumber(Connection con, int userid, String mob) throws SQLException {
        String sql = "INSERT INTO extra_user_details(user_id,data_type,data)VALUES(?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userid);
            ps.setString(2, "mobile_number");
            ps.setString(3, mob);
            return ps.executeUpdate() == 1;
        }
    }

    /**
     *
     * @param con
     * @param mob
     * @return
     * @throws SQLException
     */
    public boolean isMobileNumberAvaliable(Connection con, String mob) throws SQLException {
        String sql = "SELECT * FROM extra_user_details WHERE data = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mob);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.first();
            }
        }
    }
}
