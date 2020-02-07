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
public class FunClassFile {

    /**
     *
     * @param con
     * @param recordPerPage
     * @return
     * @throws Exception
     */
    public int totalNumberOfpage(Connection con, int recordPerPage) throws Exception {

        float totalNumberOfpage = 0;

        try (PreparedStatement ps = con.prepareStatement("select count(*) as cnt from fun");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                totalNumberOfpage = rs.getInt("cnt");
            }
            totalNumberOfpage = totalNumberOfpage / recordPerPage;
            int newnumber = (int) totalNumberOfpage;
            if (totalNumberOfpage > newnumber) {
                totalNumberOfpage = totalNumberOfpage + 1;
            }
            return (int) totalNumberOfpage;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return 0;
    }

    /**
     *
     * @param con
     * @param based_on
     * @return
     * @throws Exception
     */
    public List<funPojo> getFunDataByBasedOn(Connection con, String based_on) throws Exception {

        List<funPojo> list = new ArrayList<>();

        String sql = "SELECT * FROM fun WHERE based_on = ? ORDER BY RAND()";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, based_on);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param con
     * @param type_
     * @return
     * @throws Exception
     */
    public List<funPojo> getFunDataByType(Connection con, String type_) throws Exception {

        List<funPojo> list = new ArrayList<>();

        String sql = "SELECT * FROM fun WHERE type = ? ORDER BY RAND()";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, type_);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param con
     * @param category_
     * @return
     * @throws Exception
     */
    public List<funPojo> getFunDataByCategory(Connection con, String category_) throws Exception {

        List<funPojo> list = new ArrayList<>();

        String sql = "SELECT * FROM fun WHERE category = ? ORDER BY RAND()";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category_);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }

    /**
     *
     * @param con
     * @param pageNo
     * @param recordPerPage
     * @return
     * @throws Exception
     */
    public List<funPojo> getRandomFunData(Connection con, int pageNo, int recordPerPage) throws Exception {

        List<funPojo> list = new ArrayList<>();

        if (pageNo < 1) {
            pageNo = 1;
        }
        int startPage = (pageNo * recordPerPage) - recordPerPage;
        String sql = "SELECT * FROM fun ORDER BY RAND() LIMIT ?,?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, startPage);
            ps.setInt(2, recordPerPage);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
            return list;
        } catch (SQLException msg) {
            Logger.getLogger(FunClassFile.class.getName()).log(Level.SEVERE, null, msg);
        }
        return null;
    }
}
