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
package com.admin.answer;

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
public class AnswerApprovalAdminClassFile {

    /**
     *
     * @param answer_id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean changeApprovaByAdmin(int answer_id) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dc.getConnection();

            String sql = "UPDATE answer SET approved_by_admin = 1 WHERE a_id = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, answer_id);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(AnswerApprovalAdminClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
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
        return true;
    }
}
