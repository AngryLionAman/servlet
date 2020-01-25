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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class supportingFunctionTopic {

    /**
     *
     * @param topicId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean isTopicPresentByTopicId(int topicId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT unique_id FROM topic WHERE unique_id = ? LIMIT 1";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, topicId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.first();
            }
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunctionTopic.class.getName()).log(Level.SEVERE, null, msg);
        }
        return false;
    }

    /**
     *
     * @param questionid
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String GetAllTopicByQuestionId(int questionid) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String questionTag = "";
        String sql = "select tag_id as unique_id,(select topic_name from topic where unique_id = question_topic_tag.tag_id)topic_name from question_topic_tag where question_id =?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    questionTag += rs.getString("topic_name") + ",";
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunctionTopic.class.getName()).log(Level.SEVERE, null, msg);
        }
        return questionTag;
    }
}
