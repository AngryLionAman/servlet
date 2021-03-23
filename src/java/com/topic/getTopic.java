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
package com.topic;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class getTopic {

    /**
     *
     * @param topicid
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public HashMap<Integer, String> getTopicDetailByRefId(int topicid) throws SQLException, Exception {

        HashMap<Integer, String> map = new HashMap<>();

        String sql = "SELECT DISTINCT t.unique_id,t.topic_name FROM topic t INNER JOIN question_topic_tag qtt ON t.unique_id=qtt.tag_id WHERE question_id IN (SELECT question_id FROM question_topic_tag WHERE tag_id = ? ) AND t.unique_id IS NOT NULL AND t.topic_name IS NOT NULL AND crawl = 1 GROUP BY t.unique_id LIMIT 30";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, topicid);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String topicName = rs.getString("t.topic_name").trim();
                        int topicId = rs.getInt("t.unique_id");
                        map.putIfAbsent(topicId, topicName);
                    }
                    return map;
                } catch (SQLException msg) {
                    Logger.getLogger(getTopic.class.getName()).log(Level.SEVERE, null, msg);
                }
            } catch (SQLException msg) {
                Logger.getLogger(getTopic.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (SQLException msg) {
            Logger.getLogger(getTopic.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
