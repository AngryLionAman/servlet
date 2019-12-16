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

/**
 *
 * @author inquiryhere.com
 */
public class uQuestion {

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<uQuestionPojo> question() throws SQLException, ClassNotFoundException {
        List<uQuestionPojo> list = new ArrayList<>();
        DatabaseConnection con = new DatabaseConnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select q_id as qId,posted_time as Date,question,(SELECT COUNT(a_id)as cnt FROM answer WHERE q_id = question.q_id group by q_id)as cnt,newuser.id as userId,firstname as userFullName from question inner join newuser on newuser.id = question.id group by q_id having cnt is null order by q_id desc";
            connection = con.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int questionId = resultSet.getInt("qId");
                String question = resultSet.getString("question");
                String date = resultSet.getString("Date");
                int postedById = resultSet.getInt("userId");
                String postedByName = resultSet.getString("userFullName");
                list.add(new uQuestionPojo(questionId, question, date, postedById, postedByName));
            }

        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException msg) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException msg) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException msg) {
                }
            }
        }
        return list;
    }
}
