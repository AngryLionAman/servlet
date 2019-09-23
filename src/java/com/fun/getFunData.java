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

import com.connect.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
public class getFunData extends HttpServlet {

    private String getInputString(String parameter) {
        String val;
        if (parameter.isEmpty()) {
            val = "all";
        } else {
            val = parameter.trim();
        }
        return val;
    }

    private int getInput(String pageNo) {
        int pageno = 1;
        if (pageNo == null) {
            return 1;
        }
        if (pageNo.isEmpty()) {
            return 1;
        }
        if (!pageNo.isEmpty()) {
            pageno = Integer.parseInt(pageNo);
        }
        return pageno;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DatabaseConnection dc = DatabaseConnection.getInstance();
            List<funPojo> list = new ArrayList<>();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            int pageNo = getInput(request.getParameter("p"));
            String _category = getInputString(request.getParameter("category") == null ? "" : request.getParameter("category"));
            String _type = getInputString(request.getParameter("type") == null ? "" : request.getParameter("type"));
            String _basedOn = getInputString(request.getParameter("basedOn") == null ? "" : request.getParameter("basedOn"));
            int recordPerPage = 30;
            float totalNumberOfpage = 0;
            int startPage = (pageNo * recordPerPage) - recordPerPage;

            try {
                con = dc.getConnection();
                String sql;
                if (_category.equalsIgnoreCase("all") && _type.equalsIgnoreCase("all") && _basedOn.equalsIgnoreCase("all")) {
                    sql = "select * from fun order by rand() limit "+startPage+","+recordPerPage;
                } else {
                    sql = "select * from fun where";
                    if (!_category.equalsIgnoreCase("all")) {
                        sql += " category = '" + _category + "'";
                    }
                    if (!_type.equalsIgnoreCase("all")) {
                        sql += " type = '" + _type + "'";
                    }
                    if (!_basedOn.equalsIgnoreCase("all")) {
                        sql += " based_on = '" + _basedOn + "'";
                    }
                }

                ps = con.prepareStatement(sql);
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
                sql = "select count(*) as cnt from fun";
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
                request.setAttribute("list", list);
                request.setAttribute("totalNumberOfpage", (int) totalNumberOfpage);
                request.getRequestDispatcher("fun.jsp").forward(request, response);
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
        } catch (SQLException ex) {
            Logger.getLogger(getFunData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
