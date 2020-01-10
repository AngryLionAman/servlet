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

    /**
     *
     * @param UserId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<notificationPojo> notification(int UserId) throws SQLException, ClassNotFoundException {
        List<notificationPojo> list = new ArrayList<>();
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            con = dc.getConnection();
            String sql = "SELECT n.unique_id AS unique_id,n.user_id AS user_id,n.notification_type AS notification_type,n.followers_id AS followers_id"
                    + ",n.approval_for_question AS approval_for_question,newuser.firstname AS firstname,n.question_id AS question_id,q.question AS question"
                    + ",n.blog_id AS blog_id,n.time AS time FROM notification n INNER JOIN newuser ON newuser.id = n.followers_id "
                    + "LEFT JOIN question q ON q.q_id = n.question_id WHERE n.user_id = ? ORDER BY n.unique_id DESC LIMIT 20";
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
                int approval_for_question = resultSet.getInt("approval_for_question");
                list.add(new notificationPojo(comment_id, question_id, blog_id, question, notificationCreatedBy, userFirstName, notification_type, approval_for_question));
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
