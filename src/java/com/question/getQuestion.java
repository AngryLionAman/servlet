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
package com.question;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author AngryLion
 */
public class getQuestion {

    public HashMap<Integer, String> getRandomQuestion() throws SQLException {
        HashMap<Integer, String> map = new HashMap<>();
        DatabaseConnection dc = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select q_id as questionid,question from question order by rand() limit 20";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("questionid");
                String question = rs.getString("question");
                map.putIfAbsent(questionId, question);
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
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {

                }
            }
        }
        return map;

    }

    public HashMap<Integer, String> getRelatedQuestionById(int qId) throws SQLException {
        HashMap<Integer, String> map = new HashMap<>();
        DatabaseConnection dc = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT DISTINCT q.question as question,q.q_id as questionid FROM question q RIGHT JOIN question_topic_tag qtt ON qtt.question_id=q.q_id WHERE tag_id IN (SELECT DISTINCT(tag_id) AS tag_id FROM question_topic_tag WHERE question_id = ?) AND q_id IS NOT NULL LIMIT 20";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, qId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("questionid");
                String question = rs.getString("question");
                if (questionId != qId) {
                    map.putIfAbsent(questionId, question);
                }
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
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {

                }
            }
        }
        return map;
    }
}
