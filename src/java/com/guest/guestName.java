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
package com.guest;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author AngryLion
 */
public class guestName {

    public String genreateGuestName(String guestName) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String finalGuestName = "";
        try {
            String sql = "select username from newuser where username = ?";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, guestName);
            rs = ps.executeQuery();
            boolean found = false;
            if (rs.next()) {
                found = true;
            }
            if (found) {
                Random rand = new Random();
                int number = rand.nextInt(1000);
                finalGuestName = guestName + number;
                genreateGuestName(finalGuestName);
            } else {
                finalGuestName = guestName;
            }
        } catch (SQLException msg) {
            throw msg;
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
        return finalGuestName;
    }
}
