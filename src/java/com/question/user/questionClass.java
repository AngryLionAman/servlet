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
package com.question.user;

import com.connect.DatabaseConnection;
import com.string.validateInput;
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
public class questionClass {

    public void SaveTagIdAndQuestionId(int tagid, int questionid) throws SQLException {
        
        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dc.getConnection();
            String sql = "insert into question_topic_tag(question_id,tag_id) values(?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionid);
            ps.setInt(2, tagid);
            ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(questionClass.class.getName()).log(Level.SEVERE, null, msg);
        } finally {            
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
    }

    public static int GetTopicIdByTopicName(String topicname) throws SQLException {
        
        DatabaseConnection dc = new DatabaseConnection();

        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "select unique_id from topic where lower(topic_name) = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, input.getInputString(topicname).toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("unique_id");
            }
        } catch (SQLException msg) {
            Logger.getLogger(questionClass.class.getName()).log(Level.SEVERE, null, msg);
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

        return 0;
    }

    public boolean SaveTagWithQuestionId(int questionid, String complete_tag) throws SQLException {

        String[] tag = complete_tag.split(",");
        for (String obj : tag) {
            int topicid = GetTopicIdByTopicName(obj);
            if (topicid != 0) {
                SaveTagIdAndQuestionId(topicid, questionid);
            } else {
                //We will never get the zero value
            }
        }
        return false;
    }

    public static boolean IsTopicIsPresent(String topic) throws SQLException {

        DatabaseConnection dc = new DatabaseConnection();

        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "select topic_name from topic where lower(topic_name) = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, input.getInputString(topic).toLowerCase());
            rs = ps.executeQuery();
            return rs.first();
        } catch (SQLException msg) {
            Logger.getLogger(questionClass.class.getName()).log(Level.SEVERE, null, msg);
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
        return true;
    }

    public static boolean SaveTopic(String topic) throws SQLException {

        DatabaseConnection dc = new DatabaseConnection();

        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dc.getConnection();
            String sql = "insert into topic(topic_name) values(?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, input.getInputString(topic).toLowerCase());
            return ps.execute();
        } catch (SQLException msg) {

            Logger.getLogger(questionClass.class.getName()).log(Level.SEVERE, null, msg);

        } finally {
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
        return true;
    }

    public boolean SaveTag(String tag) throws SQLException {
        String[] arrSplit = tag.split(",");
        for (String obj : arrSplit) {
            if (!IsTopicIsPresent(obj)) {
                SaveTopic(obj);
            }
        }
        return false;
    }

    public int GetQuestionIdByQuestion(String question) throws SQLException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "select q_id from question where question = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, question);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("q_id");
            }
        } catch (SQLException msg) {
            Logger.getLogger(questionClass.class.getName()).log(Level.SEVERE, null, msg);
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
        return 0;
    }

    public boolean SaveQuestionByQuestionAndTagandUserId(int userid, String question) throws SQLException {

        DatabaseConnection dc = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dc.getConnection();
            String sql = "insert into question(id,question) values(?,?)";
            /* In MySql
            @id - active session userid
             */
            ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            ps.setString(2, question);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(questionClass.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
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
        return true;
    }
}
