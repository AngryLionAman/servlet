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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
     * @param profileId
     * @return 
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<ProfileCommentsPojo> GetCommentByProfileId(int profileId) throws SQLException, ClassNotFoundException {

        DatabaseConnection dc = new DatabaseConnection();

        List<ProfileCommentsPojo> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dc.getConnection();
            String sql = "SELECT user_id,comments,time,username,firstname from comments inner join newuser on comments.user_id = newuser.id "
                    + "where userprofileid = ? order by time desc limit 10";
            ps = con.prepareStatement(sql);
            ps.setInt(1, profileId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("username");
                String fullName = rs.getString("firstname");
                String comment = rs.getString("comments");
                Date date = rs.getDate("time");
                list.add(new ProfileCommentsPojo(userId, userName, fullName, comment, date));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(GetComment.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {
                }
            }
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
        return null;
    }
}
