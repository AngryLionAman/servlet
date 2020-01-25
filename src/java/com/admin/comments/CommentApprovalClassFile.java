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
package com.admin.comments;

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
public class CommentApprovalClassFile {

    /**
     *
     * @param comment_id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean changePermissionOfComment(int comment_id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE comments SET approved_by_admin = 1 WHERE unique_id = ?";
        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, comment_id);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(CommentApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }
}
