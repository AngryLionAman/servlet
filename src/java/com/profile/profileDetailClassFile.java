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
package com.profile;

import com.connect.DatabaseConnection;
import com.connect.PoolConnection;
import com.string.WordFormating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author AngryLion
 */
public class profileDetailClassFile {

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int GetUserIdByUserName(String username) throws SQLException, ClassNotFoundException, Exception {

        PoolConnection pc = new PoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            String sql = "select id from newuser where not user_type <=> 'guest' and firstname is not null and username = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException msg) {
            Logger.getLogger(profileDetailClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return 0;
    }

    /**
     *
     * @param userid
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<profileDetialPojoFile> GetUserDetailByUserId(int userid) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<profileDetialPojoFile> list = new ArrayList<>();

        WordFormating word = new WordFormating();

        try {
            con = ds.getConnection();
            String sql = "select id,username,firstname,email,email_s,higher_edu,best_achievement,bio,imagepath,total_view "
                    + "from newuser where not user_type <=> 'guest' and firstname is not null and id = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String username = rs.getString("username");
                String fullName = word.convertStringUpperToLower(rs.getString("firstname"));
                String email = rs.getString("email");
                int email_s = rs.getInt("email_s");
                String higherEdu = rs.getString("higher_edu");
                String bestAchivement = rs.getString("best_achievement");
                String bio = rs.getString("bio");
                String imagePath = rs.getString("imagepath");
                int totalView = rs.getInt("total_view");
                list.add(new profileDetialPojoFile(userId, username, fullName, email, email_s, higherEdu, bestAchivement, bio, imagePath, totalView));
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(profileDetailClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

    /**
     *
     * @param userId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public boolean IsUserPresent(int userId) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            String sql = "SELECT username FROM newuser where not user_type <=> 'guest' and firstname is not null and id = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            return rs.first();
        } catch (SQLException msg) {
            Logger.getLogger(profileDetailClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
        return false;
    }

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public boolean IsUserPresent(String username) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            String sql = "SELECT username FROM newuser where not user_type <=> 'guest' and firstname is not null and username = ? limit 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.first();
        } catch (SQLException msg) {
            Logger.getLogger(profileDetailClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {

                }
            }
        }
        return false;
    }
}
