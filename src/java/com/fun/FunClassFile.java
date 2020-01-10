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
package com.fun;

import com.connect.SecondPoolConnection;
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
public class FunClassFile {

    /**
     *
     * @param recordPerPage
     * @return
     * @throws Exception
     */
    public int totalNumberOfpage(int recordPerPage) throws Exception {

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        float totalNumberOfpage = 0;

        try {
            con = ds.getConnection();
            String sql = "select count(*) as cnt from fun";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalNumberOfpage = rs.getInt("cnt");
            }
            totalNumberOfpage = totalNumberOfpage / recordPerPage;
            int newnumber = (int) totalNumberOfpage;
            if (totalNumberOfpage > newnumber) {
                totalNumberOfpage = totalNumberOfpage + 1;
            }
            rs.close();
            ps.close();
            con.close();
            return (int) totalNumberOfpage;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignore) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {

                }
            }
        }
        return 0;
    }

    /**
     *
     * @param based_on
     * @return
     * @throws Exception
     */
    public List<funPojo> getFunDataByBasedOn(String based_on) throws Exception {

        List<funPojo> list = new ArrayList<>();

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();

            String sql = "SELECT * FROM fun WHERE based_on = ? ORDER BY RAND()";
            ps = con.prepareStatement(sql);
            ps.setString(1, based_on);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("unique_id");
                int postedBy = rs.getInt("posted_by_id");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                String category = rs.getString("category");
                String basedOn = rs.getString("based_on");
                String type = rs.getString("type");
                list.add(new funPojo(id, postedBy, title, desc, category, basedOn, type));
            }
            rs.close();
            ps.close();
            con.close();
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignore) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {

                }
            }
        }
        return null;
    }

    /**
     *
     * @param type_
     * @return
     * @throws Exception
     */
    public List<funPojo> getFunDataByType(String type_) throws Exception {

        List<funPojo> list = new ArrayList<>();

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();

            String sql = "SELECT * FROM fun WHERE type = ? ORDER BY RAND()";
            ps = con.prepareStatement(sql);
            ps.setString(1, type_);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("unique_id");
                int postedBy = rs.getInt("posted_by_id");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                String category = rs.getString("category");
                String basedOn = rs.getString("based_on");
                String type = rs.getString("type");
                list.add(new funPojo(id, postedBy, title, desc, category, basedOn, type));
            }
            rs.close();
            ps.close();
            con.close();
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignore) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {

                }
            }
        }
        return null;
    }

    /**
     *
     * @param category_
     * @return
     * @throws Exception
     */
    public List<funPojo> getFunDataByCategory(String category_) throws Exception {

        List<funPojo> list = new ArrayList<>();

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();

            String sql = "SELECT * FROM fun WHERE category = ? ORDER BY RAND()";
            ps = con.prepareStatement(sql);
            ps.setString(1, category_);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("unique_id");
                int postedBy = rs.getInt("posted_by_id");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                String category = rs.getString("category");
                String basedOn = rs.getString("based_on");
                String type = rs.getString("type");
                list.add(new funPojo(id, postedBy, title, desc, category, basedOn, type));
            }
            rs.close();
            ps.close();
            con.close();
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignore) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {

                }
            }
        }
        return null;
    }

    /**
     *
     * @param pageNo
     * @param recordPerPage
     * @return
     * @throws Exception
     */
    public List<funPojo> getRandomFunData(int pageNo, int recordPerPage) throws Exception {

        List<funPojo> list = new ArrayList<>();

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (pageNo == 0) {
            pageNo = 1;
        }
        int startPage = (pageNo * recordPerPage) - recordPerPage;

        try {
            con = ds.getConnection();

            String sql = "SELECT * FROM fun ORDER BY RAND() LIMIT ?,?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, startPage);
            ps.setInt(2, recordPerPage);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("unique_id");
                int postedBy = rs.getInt("posted_by_id");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                String category = rs.getString("category");
                String basedOn = rs.getString("based_on");
                String type = rs.getString("type");
                list.add(new funPojo(id, postedBy, title, desc, category, basedOn, type));
            }
            rs.close();
            ps.close();
            con.close();
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignore) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {

                }
            }
        }
        return null;
    }
}
