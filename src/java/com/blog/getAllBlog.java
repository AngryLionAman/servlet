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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class getAllBlog {

    /**
     *
     * @param limit
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<blogPojo> blogByLimit(int limit) throws SQLException, ClassNotFoundException {

        List<blogPojo> list = new ArrayList<>();

        DatabaseConnection connection = new DatabaseConnection();
        String sql = "SELECT unique_id,subject,posted_by as userId,username,firstname FROM blog LEFT JOIN newuser ON newuser.id = blog.posted_by order by rand() limit ?";

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int userId = rs.getInt("userId");
                    String userName = rs.getString("username");
                    String fullName = rs.getString("firstname");
                    int unique_id = rs.getInt("unique_id");
                    String subject = rs.getString("subject");
                    list.add(new blogPojo(userId, userName, fullName, unique_id, subject));
                }
                return list;
            }
        } catch (SQLException msg) {
            Logger.getLogger(getAllBlog.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param blogid
     * @return
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<blogPojoById> blogByBlogId(int blogid) throws SQLException, ClassNotFoundException {

        List<blogPojoById> list = new ArrayList<>();
        String sql = "SELECT b.subject,b.view,b.unique_id,b.desc,user.firstname,user.username,user.id FROM blog b left join newuser user on b.posted_by = user.Id WHERE unique_id =?";

        DatabaseConnection connection = new DatabaseConnection();

        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogid);
            try (ResultSet rs = ps.executeQuery()) {
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
                return list;
            }

        } catch (SQLException msg) {
            Logger.getLogger(getAllBlog.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;

    }

    /**
     *
     * @return @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<blogPojo> blog() throws SQLException, ClassNotFoundException {

        List<blogPojo> list = new ArrayList<>();

        String sql = "SELECT unique_id,subject,posted_by as userId,username,firstname FROM blog LEFT JOIN newuser ON newuser.id = blog.posted_by";

        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("username");
                String fullName = rs.getString("firstname");
                int unique_id = rs.getInt("unique_id");
                String subject = rs.getString("subject");
                list.add(new blogPojo(userId, userName, fullName, unique_id, subject));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(getAllBlog.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
