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
public class onProfileCommentClass {

    /**
     *
     * @param session_user_id
     * @param on_comment_user_id
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean CreateNotificationOfProfileComment(int session_user_id, int on_comment_user_id) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = DatabaseConnection.getInstance();

        Connection con = null;

        PreparedStatement ps = null;

        try {

            con = dc.getConnection();

            String sql = "INSERT INTO notification (user_id,notification_type,followers_id) VALUES (?,?,?)";

            ps = con.prepareStatement(sql);

            ps.setInt(1, on_comment_user_id);

            ps.setString(2, "comment_on_Profile");

            ps.setInt(3, session_user_id);

            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(onProfileCommentClass.class.getName()).log(Level.SEVERE, null, msg);
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

    /**
     *
     * @param session_user_id
     * @param comment_on_id
     * @param comment
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public boolean SaveCommentOfProfie(int session_user_id, int comment_on_id, String comment) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = DatabaseConnection.getInstance();

        Connection con = null;

        PreparedStatement ps = null;

        try {

            con = dc.getConnection();

            String sql = "INSERT INTO comments (user_id,userprofileid,comments)VALUES(?,?,?)";

            ps = con.prepareStatement(sql);

            ps.setInt(1, session_user_id);

            ps.setInt(2, comment_on_id);

            ps.setString(3, comment);

            return ps.execute();

        } catch (SQLException msg) {
            Logger.getLogger(onProfileCommentClass.class.getName()).log(Level.SEVERE, null, msg);
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
