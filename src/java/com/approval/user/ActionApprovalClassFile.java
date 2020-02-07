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
package com.approval.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class ActionApprovalClassFile {

    /**
     *
     * @param con
     * @param newQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean deleteModificationQuestionByAdmin(Connection con, int newQuestionId) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM modified_question_table WHERE modified_question IS NOT NULL AND unique_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newQuestionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(ActionApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param con
     * @param newQuestionId
     * @param message
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean questionRequestRejectedByAdmin(Connection con, int newQuestionId, String message) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE modified_question_table SET rejected_by_admin = 1, message = ? WHERE unique_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, message);
            ps.setInt(2, newQuestionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(ActionApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param con
     * @param newQuestionId
     * @param message
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean questionRequestRejectedByUser(Connection con, int newQuestionId, String message) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE modified_question_table SET rejected_by_user = 1, message = ? WHERE unique_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, message);
            ps.setInt(2, newQuestionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(ActionApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param con
     * @param newQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int whoModifiedTheQuestion(Connection con, int newQuestionId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT modified_question_by AS userid FROM modified_question_table WHERE unique_id = ? LIMIT 1";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newQuestionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("userid");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(ActionApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param con
     * @param oldQuestionId
     * @param newQuestionId
     * @param message
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean replaceOldQuestionWithNewQuestionAndNewQuestionWithNullAndChangePermission(Connection con, int oldQuestionId, int newQuestionId, String message) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE question q INNER JOIN modified_question_table n ON q.q_id = n.question_id SET q.question = n.modified_question , n.modified_question = NULL,n.approved_by_user = 1,n.message = ? WHERE q.q_id = ?  AND n.unique_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, message);
            ps.setInt(2, oldQuestionId);
            ps.setInt(3, newQuestionId);
            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(ActionApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param con
     * @param newQuestionId
     * @param message
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean changePermissionOfQuestionByAdmin(Connection con, int newQuestionId, String message) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE modified_question_table SET approved_by_admin = 1,message = ? WHERE unique_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, message);
            ps.setInt(2, newQuestionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(ActionApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param con
     * @param newQuestionId
     * @param message
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean changePermissionOfQuestionByUser(Connection con, int newQuestionId, String message) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE modified_question_table SET approved_by_user = 1,message = ? WHERE unique_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, message);
            ps.setInt(2, newQuestionId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(ActionApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return true;
    }

    /**
     *
     * @param con
     * @param newQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean isApprovedByAdmin(Connection con, int newQuestionId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT approved_by_admin AS permission FROM modified_question_table WHERE unique_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newQuestionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getBoolean("permission");
                }
            }
        } catch (SQLException msg) {
            Logger.getLogger(ActionApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return false;
    }
}