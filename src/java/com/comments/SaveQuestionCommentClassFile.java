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
package com.comments;

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
public class SaveQuestionCommentClassFile {

    /**
     *
     * @param userId
     * @param content_id
     * @param comment
     * @param approved_by_admin
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean SaveQuestionComment(int userId, int content_id, String comment, boolean approved_by_admin) throws SQLException, ClassNotFoundException {
        DatabaseConnection connection = new DatabaseConnection();

        String sql = "INSERT INTO comments (user_id,content_id,comments,comment_type,approved_by_admin)VALUES(?,?,?,?,?)";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, content_id);
            ps.setString(3, comment);
            ps.setString(4, "commet_on_question");
            ps.setBoolean(5, approved_by_admin);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(SaveQuestionCommentClassFile.class.getName()).log(Level.SEVERE, comment, msg);
        }
        return true;
    }
}
