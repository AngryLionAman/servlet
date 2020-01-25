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
public class getTotalnumberOfColum {

    /**
     *
     * @param topicId
     * @param recordPerPage
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int totalNumberOfPageOfTopicByTopicId(int topicId, int recordPerPage) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        float totalNumberOfpage = 0;

        String sql = "select count(*) as cnt from question q right join question_topic_tag qtt on qtt.question_id = q.q_id where tag_id = ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, topicId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    totalNumberOfpage = rs.getInt("cnt");
                }

                totalNumberOfpage = totalNumberOfpage / recordPerPage;
                int newnumber = (int) totalNumberOfpage;
                if (totalNumberOfpage > newnumber) {
                    totalNumberOfpage = totalNumberOfpage + 1;
                }
                return (int) totalNumberOfpage;
            }
        } catch (SQLException msg) {
            Logger.getLogger(getTotalnumberOfColum.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param recordPerPage
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int totalNumberOfColumnOfTopic(int recordPerPage) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection connection = new DatabaseConnection();

        float totalNumberOfpage = 0;

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement("select count(*) as cnt from topic");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                totalNumberOfpage = rs.getInt("cnt");
            }
            totalNumberOfpage = totalNumberOfpage / recordPerPage;
            int newnumber = (int) totalNumberOfpage;
            if (totalNumberOfpage > newnumber) {
                totalNumberOfpage = totalNumberOfpage + 1;
            }
            return (int) totalNumberOfpage;
        } catch (SQLException msg) {
            Logger.getLogger(getTotalnumberOfColum.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }
}
