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
package com.question.modify;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class ModifiedQuestionClassFile {

    /**
     *
     * @param userId
     * @param questionId
     * @param modified_question
     * @param questionPostedBy
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean saveModifiedQuestion(int userId, int questionId, String modified_question, int questionPostedBy) throws SQLException, ClassNotFoundException {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;

        boolean approvelByUser = false;

        if (questionPostedBy == 0) {
            approvelByUser = true;
        }

        try {

            con = ds.getConnection();

            String sql = "INSERT INTO modified_question_table (question_id,modified_question,modified_question_by,approved_by_user)VALUES(?,?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            ps.setString(2, modified_question);
            ps.setInt(3, userId);
            ps.setBoolean(4, approvelByUser);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(ModifiedQuestionClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
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
        return true;
    }
}
