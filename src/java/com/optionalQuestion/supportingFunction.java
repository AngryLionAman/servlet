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
package com.optionalQuestion;

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
 * @author AngryLion
 */
public class supportingFunction {

    /**
     *
     * @param questionId
     * @param vote
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public int showPrecentage(int questionId, int vote) throws SQLException, ClassNotFoundException {

        String sql = "select sum(vote)as sum from option_of_question where question_id = ?";

        int precentege = 0;
        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            try (ResultSet rs = ps.executeQuery()) {
                int sum = 0;
                while (rs.next()) {
                    sum = rs.getInt("sum");
                }
                if (sum != 0) {
                    precentege = (vote * 100) / sum;
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunction.class.getName()).log(Level.SEVERE, null, msg);
        }
        return precentege;
    }

    /**
     *
     * @param qId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<optionalQuestionAnswerPojo> optionOfQuestinById(int qId) throws SQLException, ClassNotFoundException {
        List<optionalQuestionAnswerPojo> list = new ArrayList<>();

        String sql = "select unique_id,option_value,vote from option_of_question where question_id = ?";

        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int aId = rs.getInt("unique_id");
                    String option = rs.getString("option_value");
                    int vote = rs.getInt("vote");
                    list.add(new optionalQuestionAnswerPojo(aId, option, vote));
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunction.class.getName()).log(Level.SEVERE, null, msg);
        }
        return list;
    }

    /**
     *
     * @param con
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> onTopicName(Connection con) throws SQLException, ClassNotFoundException {
        List<String> list = new ArrayList<>();

        String sql = "SELECT DISTINCT on_topic FROM optional_question WHERE on_topic <> 'uncategorized'";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String onTopic = rs.getString("on_topic");
                list.add(onTopic);
            }
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunction.class.getName()).log(Level.SEVERE, null, msg);
        }
        return list;
    }

    /**
     *
     * @param con
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Integer> totalNumberOfOption(Connection con) throws SQLException, ClassNotFoundException {
        List<Integer> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement("select distinct total_option from optional_question");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int count = rs.getInt("total_option");
                list.add(count);
            }
        } catch (SQLException msg) {
            Logger.getLogger(supportingFunction.class.getName()).log(Level.SEVERE, null, msg);
        }
        return list;
    }
}
