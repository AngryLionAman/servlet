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
public class FunHelpingFunction {

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public List<String> getFunCategory() throws SQLException, ClassNotFoundException, Exception {

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        List<String> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            String sql = "SELECT DISTINCT category FROM fun WHERE category IS NOT NULL ORDER BY RAND()";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                list.add(category);
            }
            rs.close();
            ps.close();
            con.close();
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunHelpingFunction.class.getName()).log(Level.SEVERE, null, msg);
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

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> getFunType() throws SQLException, ClassNotFoundException, Exception {

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        List<String> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            String sql = "SELECT DISTINCT type FROM fun WHERE type IS NOT NULL ORDER BY RAND()";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                list.add(type);
            }
            rs.close();
            ps.close();
            con.close();
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunHelpingFunction.class.getName()).log(Level.SEVERE, null, msg);
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

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> getFunBasedOn() throws SQLException, ClassNotFoundException, Exception {

        SecondPoolConnection pc = new SecondPoolConnection();
        DataSource ds = pc.setUpPool();

        List<String> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            String sql = "SELECT DISTINCT based_on FROM fun WHERE based_on IS NOT NULL ORDER BY RAND()";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String basedOn = rs.getString("based_on");
                list.add(basedOn);
            }
            rs.close();
            ps.close();
            con.close();
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunHelpingFunction.class.getName()).log(Level.SEVERE, null, msg);
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
