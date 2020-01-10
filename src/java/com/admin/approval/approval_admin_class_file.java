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

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AngryLion
 */
public class approval_admin_class_file {

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<QuestionForApprovalPojo> approvalForQuestion() throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<QuestionForApprovalPojo> list = new ArrayList<>();

        try {
            con = dc.getConnection();

            String sql = "SELECT q.q_id AS old_question_id,q.question AS old_question,o.unique_id AS new_question_id,o.modified_question AS new_question,"
                    + "o.approved_by_user,o.approved_by_admin,o.rejected_by_user,o.rejected_by_admin,o.message,o.time "
                    + "FROM modified_question_table o INNER JOIN question q ON o.question_id = q.q_id  WHERE o.modified_question IS NOT NULL";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int old_question_id = rs.getInt("old_question_id");
                String old_question = rs.getString("old_question");
                int new_question_id = rs.getInt("new_question_id");
                String new_question = rs.getString("new_question");
                boolean approve_by_user = rs.getBoolean("o.approved_by_user");
                boolean approve_by_admin = rs.getBoolean("o.approved_by_admin");
                boolean rejected_by_user = rs.getBoolean("o.rejected_by_user");
                boolean rejected_by_admin = rs.getBoolean("o.rejected_by_admin");
                String message = rs.getString("o.message");
                Date date = rs.getDate("o.time");
                list.add(new QuestionForApprovalPojo(old_question_id, old_question, new_question_id, new_question, approve_by_user, approve_by_admin, rejected_by_user, rejected_by_admin, message, date));
            }
            return list;
        } catch (SQLException msg) {
            throw msg;
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
    }
}
