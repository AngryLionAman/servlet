/*
 * Copyright 2019 inquiryhere.com.
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
package com.admin.unasweredQuestion;

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
public class uQuestion {

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<uQuestionPojo> question() throws SQLException, ClassNotFoundException {
        List<uQuestionPojo> list = new ArrayList<>();

        String sql = "SELECT q_id as qId,posted_time AS Date,question,newuser.id AS userId,firstname AS userFullName "
                + "FROM question INNER JOIN newuser ON newuser.id = question.id WHERE q_id NOT IN(SELECT q_id FROM answer) ORDER BY q_id DESC";

        
        try (Connection con = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int questionId = rs.getInt("qId");
                String question = rs.getString("question");
                String date = rs.getString("Date");
                int postedById = rs.getInt("userId");
                String postedByName = rs.getString("userFullName");
                list.add(new uQuestionPojo(questionId, question, date, postedById, postedByName));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(uQuestion.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
