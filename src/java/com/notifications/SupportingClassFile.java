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
package com.notifications;

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
public class SupportingClassFile {

    /**
     *
     * @param notificationId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean deleteNotificationByNotificationId(int notificationId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "DELETE FROM notification WHERE unique_id = ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, notificationId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(SupportingClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param questionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<QuestionForApprovalPojo> getQuestionForApprobval(int questionId) throws SQLException, ClassNotFoundException {

        DatabaseConnection connection = new DatabaseConnection();

        String sql = "SELECT q.q_id AS old_question_id,q.question AS old_question,qt.unique_id AS new_question_id,qt.modified_question AS new_question FROM question q INNER JOIN modified_question_table qt ON q.q_id = qt.question_id WHERE q.q_id = ? AND qt.modified_question IS NOT NULL";

        List<QuestionForApprovalPojo> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, questionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int oldQuestionId = rs.getInt("old_question_id");
                    String oldQuestion = rs.getString("old_question");
                    int newQuestionId = rs.getInt("new_question_id");
                    String newQuestion = rs.getString("new_question");

                    list.add(new QuestionForApprovalPojo(oldQuestionId, oldQuestion, newQuestionId, newQuestion));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(SupportingClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } 
        return null;
    }
}
