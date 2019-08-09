/*
 * Copyright 2019 inquiryhere.com.
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
package com.index;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inquiryhere.com
 */
public class topicDetals {

    public List<topicPojo> userFollowedTopic(int userId) throws Exception {
        indexPageExtraFunction function = new indexPageExtraFunction();
        List<topicPojo> list = new ArrayList<>();
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select t.unique_id,t.topic_name from topic t right join topic_followers_detail de on t.unique_id = de.topic_id where user_or_followers_id =? and t.unique_id is not null and t.topic_name is not null limit 15";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String topicName = rs.getString("t.topic_name");
                int topicId = rs.getInt("t.unique_id");
                int totalFollowers = function.totalFollowersOfTopic(topicId);
                int relatedQuestion = function.totalRelatedQuestion(topicId);
                list.add(new topicPojo(topicName, topicId, totalFollowers, relatedQuestion));
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
            if (con != null && !con.isClosed()) {
                if (!con.getAutoCommit()) {
                    con.commit();
                    con.setAutoCommit(true);
                }
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return list;
    }

    public List<topicPojo> randomTopic() throws Exception {
        indexPageExtraFunction function = new indexPageExtraFunction();
        List<topicPojo> list = new ArrayList<>();
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT unique_id,topic_name FROM topic where unique_id IS NOT NULL AND topic_name IS NOT NULL ORDER BY RAND() LIMIT 15";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String topicName = rs.getString("topic_name");
                int topicId = rs.getInt("unique_id");
                int totalFollowers = function.totalFollowersOfTopic(topicId);
                int relatedQuestion = function.totalRelatedQuestion(topicId);
                list.add(new topicPojo(topicName, topicId, totalFollowers, relatedQuestion));
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
            if (con != null && !con.isClosed()) {
                if (!con.getAutoCommit()) {
                    con.commit();
                    con.setAutoCommit(true);
                }
                try {
                    con.close();
                } catch (SQLException msg) {
                }
            }
        }
        return list;
    }
}