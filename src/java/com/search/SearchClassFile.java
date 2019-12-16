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
import com.count.CountRowSearch;
import com.index.indexPage;
import com.index.indexPageExtraFunction;
import com.string.WordFormating;
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
public class SearchClassFile {

    /**
     *
     * @param query
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<CountSearchPojo> GetCountRowSearch(String query) throws SQLException, ClassNotFoundException, Exception {
        CountRowSearch cRow = new CountRowSearch();
        int countQuestion = cRow.countQuestion(query);
        int countAnswer = cRow.countAnswer(query);
        int countTopic = cRow.countTopic(query);
        int countUser = cRow.countUser(query);
        List<CountSearchPojo> list = new ArrayList<>();
        list.add(new CountSearchPojo(countQuestion, countAnswer, countTopic, countUser));
        return list;
    }

    /**
     *
     * @param query
     * @param Limit
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<searchUserPojo> getUserByQuearyAndLimit(String query, int Limit) throws SQLException, Exception {
        List<searchUserPojo> list = new ArrayList<>();

        DatabaseConnection ds = new DatabaseConnection();
        
        WordFormating formating = new WordFormating();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
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
                String userFullName = formating.convertStringUpperToLower(rs.getString("fullName"));
                String bio = rs.getString("bio");
                String imageLink = rs.getString("imageLink");
                int totalFollowers = 0;//profile.getTotalFollowersByUserId(userId);//showing an exception error "Operation now allowed result set close"
                list.add(new searchUserPojo(userId, userName, userFullName, bio, imageLink, totalFollowers));
            }
             return list;
        } catch (SQLException msg) {
            Logger.getLogger(SearchClassFile.class.getName()).log(Level.SEVERE, query, msg);
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

    /**
     *
     * @param query
     * @param Limit
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<searchTopicPojo> getTopicByQuearyAndLimit(String query, int Limit) throws SQLException, Exception {
        indexPageExtraFunction function = new indexPageExtraFunction();
        List<searchTopicPojo> list = new ArrayList<>();

        DatabaseConnection ds = new DatabaseConnection();

        WordFormating formating = new WordFormating();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql;
            con = ds.getConnection();
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
                String topic = formating.convertStringUpperToLower(rs.getString("topicname"));
                int totalrelatedQuestion = function.totalRelatedQuestion(topicId);
                String imagePath = rs.getString("imagelink");
                list.add(new searchTopicPojo(topicId, topic, totalrelatedQuestion, imagePath));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(SearchClassFile.class.getName()).log(Level.SEVERE, query, msg);
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

    /**
     *
     * @param query
     * @param Limit
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<searchAnswerPojo> getAnswerByQuearyAndLimit(String query, int Limit) throws SQLException, Exception {
        indexPage page = new indexPage();
        List<searchAnswerPojo> list = new ArrayList<>();

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql;
            con = ds.getConnection();
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
            Logger.getLogger(SearchClassFile.class.getName()).log(Level.SEVERE, query, msg);
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

    /**
     *
     * @param query
     * @param Limit
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<searchQuestionPojo> getQuestionByQueryAndLimit(String query, int Limit) throws SQLException, Exception {
        indexPage page = new indexPage();
        List<searchQuestionPojo> list = new ArrayList<>();

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql;
            con = ds.getConnection();
            if (Limit == 0) {
                sql = "SELECT q_id as questionid,question,total_view as totalview FROM question WHERE lower(question) LIKE ? order by 1 desc limit 30";
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
            Logger.getLogger(SearchClassFile.class.getName()).log(Level.SEVERE, query, msg);
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
}
