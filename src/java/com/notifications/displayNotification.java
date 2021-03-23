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
package com.notifications;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class displayNotification {

    /**
     *
     * @param userId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int countNotificationByUserId(int userId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT COUNT(*) AS cnt FROM notification WHERE user_id = ? AND seen = 0";
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            } catch (SQLException msg) {
                Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    private static void updateSeenStatus(int notification_id) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE notification SET seen = 1 WHERE unique_id = ?";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, notification_id);
                ps.executeUpdate();
            } catch (SQLException msg) {
                Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
    }

    /**
     *
     * @param UserId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<notificationPojo> notification(int UserId) throws SQLException, ClassNotFoundException {
        List<notificationPojo> list = new ArrayList<>();

        String sql = "SELECT unique_id,user_id,creater_id,content_id,notification_type,seen FROM notification WHERE user_id = ? ORDER BY 1 DESC LIMIT 20";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, UserId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int comment_id = rs.getInt("unique_id");
                        int user_id = rs.getInt("user_id");
                        int notificationCreatedBy = rs.getInt("creater_id");
                        int content_id = rs.getInt("content_id");
                        String notification_type = rs.getString("notification_type");
                        boolean seen = rs.getBoolean("seen");
                        list.add(new notificationPojo(comment_id, user_id, notificationCreatedBy, content_id, notification_type, seen));
                        try {
                            updateSeenStatus(comment_id);
                        } catch (ClassNotFoundException | SQLException msg) {
                            Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
                        }
                    }
                } catch (SQLException msg) {
                    Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
                }
            } catch (SQLException msg) {
                Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(displayNotification.class.getName()).log(Level.SEVERE, null, msg);
        }
        return list;
    }
}
