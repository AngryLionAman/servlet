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
package com.search;

import com.connect.DatabaseConnection;
import com.index.indexPage;
import com.index.indexPageExtraFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AngryLion
 */
public class search {

    public int set(String val) {
        return 0;
    }

    public int set(Integer val) {
        return val;
    }

    public List<searchUserPojo> getUserByQuearyAndLimit(String query, int Limit) throws SQLException, Exception {
        List<searchUserPojo> list = new ArrayList<>();
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dc.getConnection();
            String sql;
            if (0 == Limit) {
                sql = "select id as userId,username,firstname as fullName,bio,imagepath as imageLink from newuser WHERE lower(firstname) LIKE ? order by userId limit 50";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + query + "%");
            } else {
                sql = "select id as userId,username,firstname as fullName,bio,imagepath as imageLink from newuser WHERE lower(firstname) LIKE ? order by userId limit ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + query + "%");
                ps.setInt(2, Limit);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("username");
                String userFullName = rs.getString("fullName");
                String bio = rs.getString("bio");
                String imageLink = rs.getString("imageLink");
                int totalFollowers = 0;//profile.getTotalFollowersByUserId(userId);//showing an exception error "Operation now allowed result set close"
                list.add(new searchUserPojo(userId, userName, userFullName, bio, imageLink, totalFollowers));
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
        return list;
    }

    public List<searchTopicPojo> getTopicByQuearyAndLimit(String query, int Limit) throws SQLException, Exception {
        indexPageExtraFunction function = new indexPageExtraFunction();
        List<searchTopicPojo> list = new ArrayList<>();
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql;
            con = dc.getConnection();
            if (Limit == 0) {
                sql = "select unique_id as topicid,topic_name as topicname,image_url as imagelink from topic WHERE lower(topic_name) LIKE ? order by topicid limit 100";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + query + "%");
            } else {
                sql = "select unique_id as topicid,topic_name as topicname,image_url as imagelink from topic WHERE lower(topic_name) LIKE ? order by topicid limit ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + query + "%");
                ps.setInt(2, Limit);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                int topicId = rs.getInt("topicid");
                String topic = rs.getString("topicname");
                int totalrelatedQuestion = function.totalRelatedQuestion(topicId);
                String imageLink = rs.getString("imagelink");
                list.add(new searchTopicPojo(topicId, topic, totalrelatedQuestion, imageLink));
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
        return list;
    }

    public List<searchAnswerPojo> getAnswerByQuearyAndLimit(String query, int Limit) throws SQLException, Exception {
        indexPage page = new indexPage();
        List<searchAnswerPojo> list = new ArrayList<>();
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql;
            con = dc.getConnection();
            if (Limit == 0) {
                sql = "Select q.question as question,q.total_view as totalview,q.q_id as questionid,ans.answer as answer from question q right join answer ans on ans.q_id = q.q_id where lower(answer) LIKE ? and question is not null limit 50";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + query + "%");
            } else {
                sql = "Select q.question as question,q.total_view as totalview,q.q_id as questionid,ans.answer as answer from question q right join answer ans on ans.q_id = q.q_id where lower(answer) LIKE ? and question is not null limit ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + query + "%");
                ps.setInt(2, Limit);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("questionid");
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                int totalViewOfQuestion = rs.getInt("totalview");
                int totalAnswerOfQuestion = page.totalAnswer(questionId);
                list.add(new searchAnswerPojo(questionId, question, answer, totalViewOfQuestion, totalAnswerOfQuestion));
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
        return list;
    }

    public List<searchQuestionPojo> getQuestionByQueryAndLimit(String query, int Limit) throws SQLException, Exception {
        indexPage page = new indexPage();
        List<searchQuestionPojo> list = new ArrayList<>();
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {
            String sql;
            con = dc.getConnection();
            if (Limit == 0) {
                sql = "SELECT q_id as questionid,question,total_view as totalview FROM question WHERE lower(question) LIKE ? limit 30";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + query + "%");
                rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    i++;
                    int questionId = rs.getInt("questionid");
                    String question = rs.getString("question");
                    int totalView = rs.getInt("totalview");
                    int totalAnswer = page.totalAnswer(questionId);
                    if (list.isEmpty()) {
                        list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                    } else {
                        if (!list.contains(new searchQuestionPojo(questionId, question, totalView, totalAnswer))) {
                            list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                        }
                    }
                }
                if (i < 50) {
                    //    To get the effective result we are fetching the question from topic
                    //    Overrideing
                    int limitForSecondQuery = 50 - i;
                    sql = "select q.q_id as questionid,q.question as question,q.total_view as totalview from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id in (select unique_id from topic where topic_name like ?) limit ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, query);
                    ps.setInt(2, limitForSecondQuery);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        i++;
                        int questionId = rs.getInt("questionid");
                        String question = rs.getString("question");
                        int totalView = rs.getInt("totalview");
                        int totalAnswer = page.totalAnswer(questionId);
                        if (list.isEmpty()) {
                            list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                        } else {
                            if (!list.contains(new searchQuestionPojo(questionId, question, totalView, totalAnswer))) {
                                list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                            }
                        }
                    }
                }
                if (i < 50) {//if still not got the result
                    //    To get the effective result we are fetching the question from topic
                    //    Overrideing
                    int limitForSecondQuery = 50 - i;
                    sql = "select q.q_id as questionid,q.question as question,q.total_view as totalview from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id in (select unique_id from topic where topic_name like ?) limit ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + query + "%");
                    ps.setInt(2, limitForSecondQuery);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        i++;
                        int questionId = rs.getInt("questionid");
                        String question = rs.getString("question");
                        int totalView = rs.getInt("totalview");
                        int totalAnswer = page.totalAnswer(questionId);
                        if (list.isEmpty()) {
                            list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                        } else {
                            if (!list.contains(new searchQuestionPojo(questionId, question, totalView, totalAnswer))) {
                                list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                            }
                        }
                    }
                }
            } else {
                sql = "SELECT q_id as questionid,question,total_view as totalview FROM question WHERE lower(question) LIKE ? limit ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + query + "%");
                ps.setInt(2, Limit);
                rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    i++;
                    int questionId = rs.getInt("questionid");
                    String question = rs.getString("question");
                    int totalView = rs.getInt("totalview");
                    int totalAnswer = page.totalAnswer(questionId);
                    if (list.isEmpty()) {
                        list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                    } else {
                        if (!list.contains(new searchQuestionPojo(questionId, question, totalView, totalAnswer))) {
                            list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                        }
                    }
                }
                if (i < 5) {
                    //    To get the effective result we are fetching the question from topic
                    //    Overrideing
                    int limitForSecondQuery = 5 - i;
                    sql = "select q.q_id as questionid,q.question as question,q.total_view as totalview from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id in (select unique_id from topic where topic_name like ?) limit ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, query);
                    ps.setInt(2, limitForSecondQuery);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        int questionId = rs.getInt("questionid");
                        String question = rs.getString("question");
                        int totalView = rs.getInt("totalview");
                        int totalAnswer = page.totalAnswer(questionId);
                        if (list.isEmpty()) {
                            list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                        } else {
                            if (!list.contains(new searchQuestionPojo(questionId, question, totalView, totalAnswer))) {
                                list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                            }
                        }
                    }
                }
                if (i < 5) { // still not got the answer then
                    //    To get the effective result we are fetching the question from topic
                    //    Overrideing
                    int limitForSecondQuery = 5 - i;
                    sql = "select q.q_id as questionid,q.question as question,q.total_view as totalview from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id in (select unique_id from topic where topic_name like ?) limit ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + query + "%");
                    ps.setInt(2, limitForSecondQuery);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        int questionId = rs.getInt("questionid");
                        String question = rs.getString("question");
                        int totalView = rs.getInt("totalview");
                        int totalAnswer = page.totalAnswer(questionId);
                        if (list.isEmpty()) {
                            list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                        } else {
                            if (!list.contains(new searchQuestionPojo(questionId, question, totalView, totalAnswer))) {
                                list.add(new searchQuestionPojo(questionId, question, totalView, totalAnswer));
                            }
                        }
                    }
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
        return list;

    }

    public int countQuestion(String searchedQuery) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(*) as count FROM question WHERE lower(question) LIKE ? order by q_id";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchedQuery + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            sql = "select count(*) as count from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id in (select unique_id from topic where topic_name like ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, searchedQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                count += rs.getInt("count");
            }
            if (count < 50) {
                sql = "select count(*) as count from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id in (select unique_id from topic where topic_name like ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchedQuery + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    count += rs.getInt("count");
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
        return count;
    }

    public int countAnswer(String searchedQuery) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "Select count(*)as count  from question q right join answer ans on ans.q_id = q.q_id where lower(answer) LIKE ?";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchedQuery + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
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
        return count;
    }

    public int countTopic(String searchedQuery) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(*)as count FROM topic WHERE lower(topic_name) LIKE ?";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchedQuery + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
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
        return count;
    }

    public int countUser(String searchedQuery) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(*)as count FROM newuser WHERE lower(firstname) LIKE ?";
            con = dc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchedQuery + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
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
        return count;
    }

}
