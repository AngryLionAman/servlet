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
package com.mail;

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
public class hideMailClassFile {

    /**
     *
     * @param userId
     * @param action
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean HideMail(int userId, String action) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = DatabaseConnection.getInstance();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dc.getConnection();
            String sql;
            if (action.equalsIgnoreCase("hide")) {
                sql = "UPDATE newuser SET email_s = '1' WHERE id=?";
            } else {
                sql = "UPDATE newuser SET email_s = '0' WHERE id=?";
            }
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            return ps.execute();
        } catch (SQLException msg) {
            Logger.getLogger(hideMailClassFile.class.getName()).log(Level.SEVERE, null, msg);
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