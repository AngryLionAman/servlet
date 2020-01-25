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
package com.admin.question;

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
public class saveQuestion {

    /**
     *
     * @param questionId
     * @param question
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected boolean updateQuestionById(int questionId, String question) throws SQLException, ClassNotFoundException {
        DatabaseConnection connection = new DatabaseConnection();
        String sql = "update question set question = ? where q_id = ?";
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, question);
            ps.setInt(2, questionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(saveQuestion.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;

    }

    /**
     *
     * @param questionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected boolean deleteQuesstionTag(int questionId) throws SQLException, ClassNotFoundException {

        String sql = "delete from question_topic_tag where question_id = ?";
        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(saveQuestion.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;

    }

    /**
     *
     * @param questionId
     * @param question
     * @param questionTag
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void saveQuestionWithIdAndTag(int questionId, String question, String questionTag) throws SQLException, ClassNotFoundException {

        try (Connection con = DatabaseConnection.makeConnection()) {
            //Delete the stored question tag
            boolean status = deleteQuesstionTag(questionId);
            boolean status2 = updateQuestionById(questionId, question);
            if (!status && !status2) {
                /**
                 * ***********
                 * Storing tag, If not present ************
                 */
                String[] arrSplit = questionTag.split(",");
                for (String arrSplit1 : arrSplit) {
                    if (!arrSplit1.isEmpty()) {
                        boolean fountStatus = true;
                        try {
                            String sql = "select topic_name from topic where lower(topic_name) = ?";
                            try (PreparedStatement ps = con.prepareStatement(sql)) {
                                ps.setString(1, arrSplit1.trim().toLowerCase());
                                try (ResultSet rs = ps.executeQuery()) {
                                    if (rs.next()) {
                                        fountStatus = false;
                                    }
                                }

                                if (fountStatus) {
                                    String sql1 = "insert into topic(topic_name) values(?)";
                                    try (PreparedStatement ps1 = con.prepareStatement(sql1)) {
                                        ps1.setString(1, arrSplit1.trim().toLowerCase());
                                        ps1.execute();
                                    }
                                }
                            }

                        } catch (SQLException msg) {
                            Logger.getLogger(saveQuestion.class.getName()).log(Level.SEVERE, null, msg);
                        }
                    }
                }
                /**
                 * ******
                 * Storing tag id with question id ************
                 */
                for (String arrSplit1 : arrSplit) {
                    if (!arrSplit1.isEmpty()) {
                        try {
                            String sql2 = "select unique_id from topic where lower(topic_name) = ?";
                            try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                                ps2.setString(1, arrSplit1.trim().toLowerCase());
                                try (ResultSet rs2 = ps2.executeQuery()) {
                                    if (rs2.next()) {
                                        int topicId = rs2.getInt("unique_id");
                                        String sql3 = "insert into question_topic_tag(question_id,tag_id) values(?,?)";
                                        try (PreparedStatement ps3 = con.prepareStatement(sql3)) {
                                            ps3.setInt(1, questionId);
                                            ps3.setInt(2, topicId);
                                            ps3.execute();
                                        }
                                    }
                                }
                            }

                        } catch (SQLException msg) {
                            Logger.getLogger(saveQuestion.class.getName()).log(Level.SEVERE, null, msg);
                        }
                    }
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(saveQuestion.class.getName()).log(Level.SEVERE, null, msg);
        }
    }
}
