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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class getQuestionByTopicId {

    /**
     *
     * @param topicId
     * @param pageNo
     * @param recordPerPage
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public HashMap<Integer, String> getAllQuestionByTopicId(int topicId, int pageNo, int recordPerPage) throws SQLException, ClassNotFoundException {

        HashMap<Integer, String> map = new HashMap<>();
        
        DatabaseConnection dc = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int startPage = (pageNo * recordPerPage) - recordPerPage;

        try {
            String sql = "select q.question as question,q.q_id as questionid from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id = ? limit ?,?";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.setInt(2, startPage);
            ps.setInt(3, recordPerPage);

            rs = ps.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("questionid");
                String question = rs.getString("question");
                map.putIfAbsent(questionId, question);
            }
            return map;
        } catch (SQLException msg) {
            Logger.getLogger(getQuestionByTopicId.class.getName()).log(Level.SEVERE, null, msg);
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
        return null;
    }
}
