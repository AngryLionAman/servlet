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
package com.answer;

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
public class time {

    /**
     *
     * @param questionId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int showTime(int questionId) throws SQLException, ClassNotFoundException, Exception {

        String sql = "SELECT DATEDIFF(CURDATE(), posted_time) AS Date FROM question WHERE q_id = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, questionId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("Date");
                    }
                }
            } catch (Exception msg) {
                Logger.getLogger(time.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (Exception msg) {
            Logger.getLogger(time.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }
}
