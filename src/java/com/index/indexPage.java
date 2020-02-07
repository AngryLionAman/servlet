/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.index;

import com.answer.time;
import com.connect.DatabaseConnection;
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
 * @author inquiryhere.com
 */
public class indexPage {

    /**
     *
     * @param qId
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<recentQuestionPojo> getQuestion(int qId) throws SQLException, Exception {
        time time = new time();
        List<recentQuestionPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "select q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time as date,"
                + "user.id,user.firstname,user.username,user.user_type,user.higher_edu from question q inner join newuser user "
                + "on user.id = q.id WHERE q.q_id = ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int totalView = rs.getInt("q.total_view");
                    String date = rs.getString("date");
                    int questionId = rs.getInt("q.q_id");
                    int days = time.showTime(con, questionId);
                    String question = rs.getString("q.question");
                    int vote = rs.getInt("q.vote");
                    String fullName = rs.getString("user.firstname");
                    String userName = rs.getString("user.username");
                    String userType = rs.getString("user.user_type");
                    String higherEdu = rs.getString("user.higher_edu");
                    int userId = rs.getInt("user.id");
                    int totalAnswer = 0;//totalAnswer(questionId); Getting null pointer exception here
                    recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, days, questionId, question, vote, fullName, userName, userType, higherEdu, userId, totalAnswer);
                    list.add(recentQuestionPojo);
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPage.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param con
     * @return @throws SQLException
     * @throws Exception
     */
    public List<recentQuestionPojo> questionYouMayLike(Connection con) throws SQLException, Exception {

        indexPageExtraFunction function = new indexPageExtraFunction();

        time time = new time();
        List<recentQuestionPojo> list = new ArrayList<>();

        String sql = "select q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time as date,"
                + "user.id,user.firstname,user.username,user.user_type,user.higher_edu from question q inner join newuser user "
                + "on user.id = q.id ORDER BY RAND() limit 10";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int totalView = rs.getInt("q.total_view");
                String date = rs.getString("date");
                int questionId = rs.getInt("q.q_id");
                int days = time.showTime(con, questionId);
                String question = rs.getString("q.question");
                int vote = rs.getInt("q.vote");
                String fullName = rs.getString("user.firstname");
                String userName = rs.getString("user.username");
                String userType = rs.getString("user.user_type");
                String higherEdu = rs.getString("user.higher_edu");
                int userId = rs.getInt("user.id");
                int totalAnswer = totalAnswer(con, questionId);
                function.updateQuestionView(con, questionId);
                recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, days, questionId, question, vote, fullName, userName, userType, higherEdu, userId, totalAnswer);
                list.add(recentQuestionPojo);
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPage.class.getName()).log(Level.SEVERE, null, msg);
        }
        return list;
    }

    /**
     *
     * @return @throws Exception
     */
    public List<recentQuestionPojo> recentPostQuestion() throws Exception {

        indexPageExtraFunction function = new indexPageExtraFunction();

        time time = new time();
        List<recentQuestionPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT q.total_view,date(q.posted_time) as date,q.q_id,q.question,q.vote,user.firstname,user.username,user.user_type,user.higher_edu,user.ID as u_ide FROM question q RIGHT JOIN newuser user ON user.id = q.id WHERE q.q_id IS NOT NULL AND q.question IS NOT NULL ORDER BY q_id DESC LIMIT 20";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int totalView = rs.getInt("q.total_view");
                String date = rs.getString("date");
                int questionId = rs.getInt("q.q_id");
                int days = time.showTime(con, questionId);
                String question = rs.getString("q.question");
                int vote = rs.getInt("q.vote");
                String fullName = rs.getString("user.firstname");
                String userName = rs.getString("user.username");
                String userType = rs.getString("user.user_type");
                String higherEdu = rs.getString("user.higher_edu");
                int userId = rs.getInt("u_ide");
                int totalAnswer = totalAnswer(con, questionId);
                function.updateQuestionView(con, questionId);
                recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, days, questionId, question, vote, fullName, userName, userType, higherEdu, userId, totalAnswer);
                list.add(recentQuestionPojo);
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPage.class.getName()).log(Level.SEVERE, null, msg);
        }
        return list;

    }

    /**
     *
     * @param con
     * @param user_Id
     * @return
     * @throws Exception
     */
    public List<recentQuestionPojo> relatedQuestion(Connection con, int user_Id) throws Exception {

        time time = new time();
        List<recentQuestionPojo> list = new ArrayList<>();
        String sql = "select DISTINCT q.id as userid,user.firstname as fullname,user.username as username,user.user_type as usertype,"
                + "user.higher_edu,q.q_id as questionId,q.total_view as totalView,"
                + "q.question,q.posted_time as date,q.vote from question q inner join "
                + "question_topic_tag qtt on q.q_id = qtt.question_id inner join newuser user "
                + "on user.id = q.id where tag_id IN "
                + "(select t.unique_id from topic t inner join topic_followers_detail de on t.unique_id = de.topic_id where user_or_followers_id = ?) "
                + "and q.id is not null and q.q_id is not null and q.question is not null ORDER BY RAND() limit 10";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, user_Id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int totalView = rs.getInt("totalView");
                    String date = rs.getString("date");
                    int questionId = rs.getInt("questionId");
                    int days = time.showTime(con, questionId);
                    String question = rs.getString("q.question");
                    int vote = rs.getInt("q.vote");
                    String fullName = rs.getString("fullname");
                    String userName = rs.getString("username");
                    String userType = rs.getString("usertype");
                    String higherEdu = rs.getString("user.higher_edu");
                    int userId = rs.getInt("userid");
                    int totalAnswer = totalAnswer(con, questionId);
                    recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, days, questionId, question, vote, fullName, userName, userType, higherEdu, userId, totalAnswer);
                    list.add(recentQuestionPojo);
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPage.class.getName()).log(Level.SEVERE, null, msg);
        }
        return list;
    }

    /**
     *
     * @param con
     * @param questionId
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public int totalAnswer(Connection con, int questionId) throws SQLException, Exception {

        String sql = "SELECT COUNT(a_id)as cnt FROM answer WHERE q_id = ? group by q_id";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(indexPage.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

}
