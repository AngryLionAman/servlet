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

    /**
     *
     * @param tagid
     * @param questionid
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void SaveTagIdAndQuestionId(int tagid, int questionid) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
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

    /**
     *
     * @param topicname
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static int GetTopicIdByTopicName(String topicname) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
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

    /**
     *
     * @param tagId
     * @param questionId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean IsTagIdIntegratedWithQuestionId(int tagId, int questionId) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            String sql = "SELECT unique_id FROM question_topic_tag WHERE question_id = ? AND tag_id = ? LIMIT 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            ps.setInt(2, tagId);
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

    /**
     *
     * @param questionid
     * @param complete_tag
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean SaveTagWithQuestionId(int questionid, String complete_tag) throws SQLException, ClassNotFoundException, Exception {

        String[] tag = complete_tag.split(",");
        for (String obj : tag) {
            int topicid = GetTopicIdByTopicName(obj);
            if (topicid != 0) {
                if (!IsTagIdIntegratedWithQuestionId(topicid, questionid)) {
                    SaveTagIdAndQuestionId(topicid, questionid);
                } else {
                    //Do Nothing
                }
            } else {
                //We will never get the zero value
            }
        }
        return false;
    }

    /**
     *
     * @param topic
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean IsTopicIsPresent(String topic) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
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
        return false;
    }

    /**
     *
     * @param topic
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean SaveTopic(String topic) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        validateInput input = new validateInput();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ds.getConnection();
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

    /**
     *
     * @param tag
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean SaveTag(String tag) throws SQLException, ClassNotFoundException, Exception {
        String[] arrSplit = tag.split(",");
        for (String obj : arrSplit) {
            if (!IsTopicIsPresent(obj)) {
                SaveTopic(obj);
            }
        }
        return false;
    }

    /**
     *
     * @param question
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int GetQuestionIdByQuestion(String question) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
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

    /**
     *
     * @param userid
     * @param question
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean SaveQuestionByQuestionAndTagandUserId(int userid, String question) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ds.getConnection();
            String sql = "INSERT INTO question(id,question) VALUES(?,?)";
            /* In MySql
             * @id - active session userid
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
