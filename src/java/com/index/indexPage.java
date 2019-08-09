/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inquiryhere.com
 */
public class indexPage {

    public List<recentQuestionPojo> questionYouMayLike() throws SQLException, Exception {
        indexPageExtraFunction function = new indexPageExtraFunction();
        DatabaseConnection connection = new DatabaseConnection();
        List<recentQuestionPojo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time as date,"
                    + "user.id,user.firstname,user.username,user.higher_edu from question q inner join newuser user "
                    + "on user.id = q.id ORDER BY RAND() limit 10";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int totalView = rs.getInt("q.total_view");
                String date = rs.getString("date");
                int questionId = rs.getInt("q.q_id");
                String question = rs.getString("q.question");
                int vote = rs.getInt("q.vote");
                String fullName = rs.getString("user.firstname");
                String userName = rs.getString("user.username");
                String higherEdu = rs.getString("user.higher_edu");
                int userId = rs.getInt("user.id");
                int totalAnswer = totalAnswer(questionId);
                function.updateQuestionView(questionId);
                recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, questionId, question, vote, fullName, userName, higherEdu, userId, totalAnswer);
                list.add(recentQuestionPojo);
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

    public List<recentQuestionPojo> recentPostQuestion() throws Exception {
        indexPageExtraFunction function = new indexPageExtraFunction();
        DatabaseConnection connection = new DatabaseConnection();
        List<recentQuestionPojo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT q.total_view,date(q.posted_time) as date,q.q_id,q.question,q.vote,user.firstname,user.username,user.higher_edu,user.ID as u_ide FROM question q RIGHT JOIN newuser user ON user.id = q.id WHERE q.q_id IS NOT NULL AND q.question IS NOT NULL ORDER BY q_id DESC LIMIT 10";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int totalView = rs.getInt("q.total_view");
                String date = rs.getString("date");
                int questionId = rs.getInt("q.q_id");
                String question = rs.getString("q.question");
                int vote = rs.getInt("q.vote");
                String fullName = rs.getString("user.firstname");
                String userName = rs.getString("user.username");
                String higherEdu = rs.getString("user.higher_edu");
                int userId = rs.getInt("u_ide");
                int totalAnswer = totalAnswer(questionId);
                function.updateQuestionView(questionId);
                recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, questionId, question, vote, fullName, userName, higherEdu, userId, totalAnswer);
                list.add(recentQuestionPojo);
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

    public List<recentQuestionPojo> relatedQuestion(int user_Id) throws Exception {
        indexPageExtraFunction function = new indexPageExtraFunction();
        DatabaseConnection connection = new DatabaseConnection();
        List<recentQuestionPojo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select DISTINCT q.id as userid,user.firstname as fullname,user.username as username,"
                    + "user.higher_edu,q.q_id as questionId,q.total_view as totalView,"
                    + "q.question,q.posted_time as date,q.vote from question q inner join "
                    + "question_topic_tag qtt on q.q_id = qtt.question_id inner join newuser user "
                    + "on user.id = q.id where tag_id IN "
                    + "(select t.unique_id from topic t inner join topic_followers_detail de on t.unique_id = de.topic_id where user_or_followers_id = ?) "
                    + "and q.id is not null and q.q_id is not null and q.question is not null ORDER BY RAND() limit 10";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_Id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int totalView = rs.getInt("totalView");
                String date = rs.getString("date");
                int questionId = rs.getInt("questionId");
                String question = rs.getString("q.question");
                int vote = rs.getInt("q.vote");
                String fullName = rs.getString("fullname");
                String userName = rs.getString("username");
                String higherEdu = rs.getString("user.higher_edu");
                int userId = rs.getInt("userid");
                int totalAnswer = totalAnswer(questionId);
                function.updateQuestionView(questionId);
                recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, questionId, question, vote, fullName, userName, higherEdu, userId, totalAnswer);
                list.add(recentQuestionPojo);
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

    public int totalAnswer(int questionId) throws SQLException, Exception {
        int totalAnswer = 0;
        DatabaseConnection connection = new DatabaseConnection();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(a_id)as cnt FROM answer WHERE q_id = ? group by q_id";
            con = connection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalAnswer = rs.getInt("cnt");
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
        return totalAnswer;
    }

}
