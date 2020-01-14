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
package com.count;

import com.connect.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AngryLion
 */
public class CountRowSearch {

    /**
     *
     * @param searchedQuery
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int countQuestion(String searchedQuery) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(*) as count FROM question WHERE lower(question) LIKE ? order by q_id";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchedQuery + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
            sql = "select count(*) as count from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id in (select unique_id from topic where topic_name like ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, searchedQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                count += rs.getInt("count");
            }
            if (count < 50) {
                sql = "select count(*) as count from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id in (select unique_id from topic where topic_name like ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchedQuery + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    count += rs.getInt("count");
                }
            }
            return count;
        } catch (SQLException msg) {
            Logger.getLogger(CountRowSearch.class.getName()).log(Level.SEVERE, searchedQuery, msg);
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
        return 0;
    }

    /**
     *
     * @param searchedQuery
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int countAnswer(String searchedQuery) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "Select count(*)as count  from question q right join answer ans on ans.q_id = q.q_id where lower(answer) LIKE ?";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchedQuery + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException msg) {
            Logger.getLogger(CountRowSearch.class.getName()).log(Level.SEVERE, searchedQuery, msg);
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
        return 0;
    }

    /**
     *
     * @param searchedQuery
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int countTopic(String searchedQuery) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT count(*)as count FROM topic WHERE lower(topic_name) LIKE ?";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchedQuery + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException msg) {
            Logger.getLogger(CountRowSearch.class.getName()).log(Level.SEVERE, searchedQuery, msg);
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
        return 0;
    }

    /**
     *
     * @param searchedQuery
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public int countUser(String searchedQuery) throws SQLException, ClassNotFoundException, Exception {

        DatabaseConnection ds = new DatabaseConnection();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT count(*)as count FROM newuser WHERE lower(firstname) LIKE ?";
            con = ds.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchedQuery + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException msg) {
            Logger.getLogger(CountRowSearch.class.getName()).log(Level.SEVERE, searchedQuery, msg);
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
        return 0;
    }

}