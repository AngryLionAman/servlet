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

/**
 *
 * @author AngryLion
 */
public class getTotalnumberOfColum {

    public int totalNumberOfPageOfTopicByTopicId(int topicId, int recordPerPage) throws SQLException {

        DatabaseConnection connection = new DatabaseConnection();

        Connection com = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        float totalNumberOfpage = 0;

        try {
            com = connection.getConnection();
            String sql = "select count(*) as cnt from question q right join question_topic_tag qtt on qtt.question_id = q.q_id where tag_id = ?";
            ps = com.prepareStatement(sql);
            ps.setInt(1, topicId);
            rs = ps.executeQuery();

            while (rs.next()) {
                totalNumberOfpage = rs.getInt("cnt");
            }

            totalNumberOfpage = totalNumberOfpage / recordPerPage;
            int newnumber = (int) totalNumberOfpage;
            if (totalNumberOfpage > newnumber) {
                totalNumberOfpage = totalNumberOfpage + 1;
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (com != null) {
                try {
                    com.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
        }
        return (int) totalNumberOfpage;
    }

    public int totalNumberOfColumnOfTopic(int recordPerPage) throws SQLException {

        DatabaseConnection connection = new DatabaseConnection();

        Connection com = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        float totalNumberOfpage = 0;

        try {
            com = connection.getConnection();
            String sql = "select count(*) as cnt from topic";
            ps = com.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                totalNumberOfpage = rs.getInt("cnt");

            }
            totalNumberOfpage = totalNumberOfpage / recordPerPage;
            int newnumber = (int) totalNumberOfpage;
            if (totalNumberOfpage > newnumber) {
                totalNumberOfpage = totalNumberOfpage + 1;
            }
        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (com != null) {
                try {
                    com.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlex) {
                    // ignore -- as we can't do anything about it here
                }
            }
        }
        return (int) totalNumberOfpage;
    }

}
