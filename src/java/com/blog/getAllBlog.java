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
package com.blog;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AngryLion
 */
public class getAllBlog {

    public List<blogPojo> blogByLimit(int limit) throws SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        List<blogPojo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
         try {
            con = dc.getConnection();
            String sql = "SELECT unique_id,subject,posted_by as userId,username,firstname FROM blog LEFT JOIN newuser ON newuser.id = blog.posted_by order by rand() limit ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("username");
                String fullName = rs.getString("firstname");
                int unique_id = rs.getInt("unique_id");
                String subject = rs.getString("subject");
                list.add(new blogPojo(userId, userName, fullName, unique_id, subject));
            }
        } catch (SQLException msg) {
            throw msg;
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
        return list;

    }
    public List<blogPojoById> blogByBlogId(int blogid) throws SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        List<blogPojoById> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dc.getConnection();
            String sql = "SELECT b.subject,b.view,b.unique_id,b.desc,user.firstname,user.username,user.id FROM blog b left join newuser user on b.posted_by = user.Id WHERE unique_id =?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, blogid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int uniqueId = rs.getInt("b.unique_id");
                String subject = rs.getString("b.subject");
                String desc = rs.getString("b.desc");
                int view = rs.getInt("b.view");
                int userId = rs.getInt("user.id");
                String userName = rs.getString("user.username");
                String fullName = rs.getString("user.firstname");
                list.add(new blogPojoById(uniqueId, subject, desc, view, userId, userName, fullName));
            }
        } catch (SQLException msg) {
            throw msg;
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
        return list;

    }

    public List<blogPojo> blog() throws SQLException {
        DatabaseConnection dc = new DatabaseConnection();
        List<blogPojo> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dc.getConnection();
            String sql = "SELECT unique_id,subject,posted_by as userId,username,firstname FROM blog LEFT JOIN newuser ON newuser.id = blog.posted_by";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("username");
                String fullName = rs.getString("firstname");
                int unique_id = rs.getInt("unique_id");
                String subject = rs.getString("subject");
                list.add(new blogPojo(userId, userName, fullName, unique_id, subject));
            }
        } catch (SQLException msg) {
            throw msg;
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
        return list;
    }
}
