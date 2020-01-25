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
import com.followmoretopic.totalQuestion;
import com.index.indexPageExtraFunction;
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
public class allTopic {

    /**
     *
     * @param pageNo
     * @param recordPerPage
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<allTopicPojo> topic(int pageNo, int recordPerPage) throws SQLException, Exception {

        totalQuestion q = new totalQuestion();
        indexPageExtraFunction function = new indexPageExtraFunction();
        List<allTopicPojo> list = new ArrayList<>();

        int startPage = (pageNo * recordPerPage) - recordPerPage;

        String sql = "select unique_id,topic_name,image_url from topic limit ?,?";

        DatabaseConnection connection = new DatabaseConnection();

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, startPage);
            ps.setInt(2, recordPerPage);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int topicId = rs.getInt("unique_id");
                    String topicName = rs.getString("topic_name") == null ? "Unknown Topic" : rs.getString("topic_name");
                    String imageUrl = rs.getString("image_url");
                    int totalQuestion = q.totalQestion(topicId);
                    int totalFollowers = function.totalFollowersOfTopic(topicId);
                    list.add(new allTopicPojo(topicId, topicName, imageUrl, totalQuestion, totalFollowers));
                }
                return list;
            }

        } catch (SQLException msg) {
            Logger.getLogger(allTopic.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
