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
package com.admin.approval;

import com.approval.user.ActionApprovalClassFile;
import com.connect.DatabaseConnection;
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
public class AdminApprovalClassFile {

    /**
     *
     * @param newQuestionId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean isApprovedByUser(int newQuestionId) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();

            String sql = "SELECT approved_by_user AS permission FROM modified_question_table WHERE unique_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, newQuestionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("permission");
            }
        } catch (SQLException msg) {
            Logger.getLogger(AdminApprovalClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException msg) {

            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException msg) {

            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException msg) {

            }
        }
        return false;
    }
}