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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class GuestUser {

    /**
     *
     * @param guestName
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public String GenreateGuestName(String guestName) throws SQLException, ClassNotFoundException {

        if (guestName == null) {
            return "NoString";
        }
        if (guestName.equals("")) {
            return "NoString";
        }

        String sql = "SELECT LOWER(username) FROM newuser WHERE username =? LIMIT 1";

        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, guestName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    Random rand = new Random();
                    int number = rand.nextInt(1000);
                    return GenreateGuestName(guestName + number);
                } else {
                    return guestName;
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(GuestUser.class.getName()).log(Level.SEVERE, guestName, msg);
        }
        return null;
    }
}
