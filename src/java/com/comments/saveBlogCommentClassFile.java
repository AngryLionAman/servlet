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

/**
 *
 * @author AngryLion
 */
public class saveBlogCommentClassFile {

    public boolean saveBlog(int bloggerUserId, int sessionUserId, int blogId, String comment) throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        boolean execute = false;
        try {
            con = dc.getConnection();
            String sql = "INSERT INTO comments (user_id,blog_id,comments)VALUES(?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, sessionUserId);
            ps.setInt(2, blogId);
            ps.setString(3, comment);
            boolean value = ps.execute();
            if (bloggerUserId != 0) {
                if (!value) {
                    String sql1 = "INSERT INTO notification (user_id,notification_type,followers_id,blog_id ) VALUES (?,?,?,?)";
                    ps1 = con.prepareStatement(sql1);
                    ps1.setInt(1, bloggerUserId);
                    ps1.setString(2, "comment_on_Blog");
                    ps1.setInt(3, sessionUserId);
                    ps1.setInt(4, blogId);
                    execute = ps1.execute();
                }
            }

        } catch (SQLException msg) {
            throw msg;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {

                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
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

        return execute;
    }
}
