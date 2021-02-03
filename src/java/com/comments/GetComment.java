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

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class GetComment {

    /**
     *
     * @param blogId
     * @param con
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<BlogCommentPojoFile> getCommentOfBlogByBlogId(int blogId, Connection con) throws ClassNotFoundException, SQLException {

        List<BlogCommentPojoFile> list = new ArrayList<>();

        String sql = "SELECT c.comments AS comment, c.time AS date, u.id AS userid,u.username AS username, "
                + "u.firstname AS fullname, u.user_type AS usertype from comments c INNER JOIN newuser u ON c.user_id = u.id "
                + "WHERE c.content_id = ? AND approved_by_user = 1 AND approved_by_admin = 1 ORDER BY unique_id DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String comment = rs.getString("comment");
                    Date date = rs.getDate("date");
                    int userId = rs.getInt("userid");
                    String userName = rs.getString("username");
                    String fullName = rs.getString("fullname");
                    String userType = rs.getString("usertype");
                    list.add(new BlogCommentPojoFile(comment, date, userId, userName, fullName, userType));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(GetComment.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param con
     * @param profileId
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<ProfileCommentsPojo> GetCommentByProfileId(Connection con, int profileId) throws SQLException, ClassNotFoundException {

        List<ProfileCommentsPojo> list = new ArrayList<>();

        String sql = "SELECT user_id,comments,time,user_type,username,firstname from comments INNER JOIN newuser ON comments.user_id = newuser.id "
                + "WHERE content_id = ? AND comment_type='comment_on_user_profile' "
                + "AND approved_by_user = 1 AND approved_by_admin = 1 ORDER BY time DESC LIMIT 10";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, profileId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String userType = rs.getString("user_type");
                    String userName = rs.getString("username");
                    String fullName = rs.getString("firstname");
                    String comment = rs.getString("comments");
                    Date date = rs.getDate("time");
                    list.add(new ProfileCommentsPojo(userId, userType, userName, fullName, comment, date));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(GetComment.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
