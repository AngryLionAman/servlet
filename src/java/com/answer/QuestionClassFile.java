/*
 * Copyright 2020 AngryLion.
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
package com.answer;

import com.connect.DatabaseConnection;
import com.index.recentQuestionPojo;
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
public class QuestionClassFile {

    public List<recentQuestionPojo> getQuestion(int qId) throws SQLException, Exception {

        time time = new time();
        List<recentQuestionPojo> list = new ArrayList<>();

        String sql = "select q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time as date,"
                + "user.id,user.firstname,user.username,user.user_type,user.higher_edu from question q inner join newuser user "
                + "on user.id = q.id WHERE q.q_id = ?";

        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int totalView = rs.getInt("q.total_view");
                    String date = rs.getString("date");
                    int questionId = rs.getInt("q.q_id");
                    int days = time.showTime(questionId);
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
            Logger.getLogger(QuestionClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } 
        return null;
    }

}
