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

/**
 *
 * @author AngryLion
 */
public class displayNotification {

    public List<notificationPojo> notification(int UserId) throws SQLException {
        List<notificationPojo> list = new ArrayList<>();
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = dc.getConnection();
            String sql = "SELECT unique_id,user_id,user_email,notification_type,followers_id,(SELECT firstname FROM newuser WHERE id = notification.followers_id)AS firstname,question_id,(SELECT question FROM question WHERE q_id = notification.question_id)AS QUESTION,ans_id,blog_id,time FROM notification WHERE user_id = ? ORDER BY unique_id DESC LIMIT 10";
            ps = con.prepareStatement(sql);
            ps.setInt(1, UserId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int comment_id = resultSet.getInt("unique_id");
                int question_id = resultSet.getInt("question_id");
                int blog_id = resultSet.getInt("blog_id");
                String question = resultSet.getString("question");
                int notificationCreatedBy = resultSet.getInt("followers_id");//who created the notification
                String userFirstName = resultSet.getString("firstname");
                String notification_type = resultSet.getString("notification_type");
                list.add(new notificationPojo(comment_id, question_id, blog_id, question, notificationCreatedBy, userFirstName, notification_type));
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
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
        return list;
    }
}
