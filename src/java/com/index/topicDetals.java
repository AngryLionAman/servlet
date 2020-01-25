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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inquiryhere.com
 */
public class topicDetals {

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List<topicPojo> userFollowedTopic(int userId) throws Exception {

        indexPageExtraFunction function = new indexPageExtraFunction();
        List<topicPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT t.unique_id,t.topic_name FROM topic t INNER JOIN topic_followers_detail de ON t.unique_id = de.topic_id WHERE user_or_followers_id = ?  AND t.unique_id IS NOT NULL AND t.topic_name IS NOT NULL AND t.topic_name != '' LIMIT 15";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String topicName = rs.getString("t.topic_name");
                    int topicId = rs.getInt("t.unique_id");
                    int totalFollowers = function.totalFollowersOfTopic(topicId);
                    int relatedQuestion = function.totalRelatedQuestion(topicId);
                    list.add(new topicPojo(topicName, topicId, totalFollowers, relatedQuestion));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(topicDetals.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param Limit
     * @return
     * @throws Exception
     */
    public List<topicPojo> randomTopic(int Limit) throws Exception {

        indexPageExtraFunction function = new indexPageExtraFunction();
        List<topicPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT unique_id,topic_name FROM topic WHERE crawl = 1 AND unique_id IS NOT NULL AND topic_name IS NOT NULL AND topic_name != '' ORDER BY RAND() LIMIT ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String topicName = rs.getString("topic_name");
                    int topicId = rs.getInt("unique_id");
                    int totalFollowers = function.totalFollowersOfTopic(topicId);
                    int relatedQuestion = function.totalRelatedQuestion(topicId);
                    list.add(new topicPojo(topicName, topicId, totalFollowers, relatedQuestion));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(topicDetals.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
