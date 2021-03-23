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

import com.answer.time;
import com.connect.DatabaseConnection;
import com.index.indexPage;
import com.index.recentQuestionPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class Question {

    /**
     *
     * @param questionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean IsQuestionPresentByQuestionId(int questionId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT q_id FROM question WHERE q_id = ? LIMIT 1";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, questionId);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.first();
                }
            } catch (SQLException msg) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (Exception msg) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, msg);
        }
        return false;
    }

    /**
     *
     * @return @throws Exception
     */
    public List<RecentPostQUestionHavingAtLeastOneAnswerPojo> RecentPostQUestionHavingAtLeastOneAnswer() throws Exception {

        DateAndTime day = new DateAndTime();

        List<RecentPostQUestionHavingAtLeastOneAnswerPojo> list = new ArrayList<>();

        String sql = "SELECT "
                + "u.username AS userName,"
                + "u.firstname AS fullName,"
                + "u.user_type AS userType,"
                + "u.higher_edu AS higherEdu,"
                + "q.id AS userId,"
                + "q.q_id AS questionId,"
                + "q.question AS question,"
                + "q.total_view AS questionView,"
                + "q.vote AS questionVote,"
                + "q.updated_time AS lastModify,"
                + "count(a.a_id) as ansCount "
                + "FROM "
                + "question q,"
                + "answer a,"
                + "newuser u "
                + "WHERE "
                + "q.q_id = a.q_id "
                + "AND "
                + "u.id = q.id "
                + "GROUP BY "
                + "a.q_id "
                + "ORDER BY "
                + "q.q_id "
                + "DESC LIMIT 20;";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String userName = rs.getString("userName");
                    String fullName = rs.getString("fullName");
                    String userType = rs.getString("userType");
                    String higherEdu = rs.getString("higherEdu");
                    int userId = rs.getInt("userId");
                    int questionId = rs.getInt("questionId");
                    String question = rs.getString("question");
                    int questionView = rs.getInt("questionView");
                    int questionVote = rs.getInt("questionVote");
                    Date lastModify = rs.getDate("lastModify");
                    int ansCount = rs.getInt("ansCount");
                    int days = 55;//day.GetDaysByQuestionId(questionId);
                    list.add(new RecentPostQUestionHavingAtLeastOneAnswerPojo(userName, fullName, userType, higherEdu, userId, questionId, question, questionView, questionVote, lastModify, ansCount, days));
                }
                return list;
            } catch (SQLException msg) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (Exception msg) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<recentQuestionPojo> UnAnsweredQuestion() throws SQLException, ClassNotFoundException, Exception {

        List<recentQuestionPojo> list = new ArrayList<>();

        time time = new time();
        indexPage index = new indexPage();

        String sql = " SELECT q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time AS date,user.id,user.firstname,"
                + "user.username,user.user_type,user.higher_edu FROM question q INNER JOIN newuser user ON user.id = q.id "
                + "WHERE q.q_id NOT IN(SELECT q_id FROM answer) ORDER BY q.q_id DESC";

        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int totalView = rs.getInt("q.total_view");
                    String date = rs.getString("date");
                    int questionId = rs.getInt("q.q_id");
                    int days = 13;//time.showTime(questionId);
                    String question = rs.getString("q.question");
                    int vote = rs.getInt("q.vote");
                    String fullName = rs.getString("user.firstname");
                    String userName = rs.getString("user.username");
                    String userType = rs.getString("user.user_type");
                    String higherEdu = rs.getString("user.higher_edu");
                    int userId = rs.getInt("user.id");
                    int totalAnswer = 0;//index.totalAnswer(questionId);
                    recentQuestionPojo recentQuestionPojo = new recentQuestionPojo(totalView, date, days, questionId, question, vote, fullName, userName, userType, higherEdu, userId, totalAnswer);
                    list.add(recentQuestionPojo);
                }
                return list;
            } catch (Exception msg) {
                Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, msg);
            }
        } catch (Exception msg) {
            Logger.getLogger(Question.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
