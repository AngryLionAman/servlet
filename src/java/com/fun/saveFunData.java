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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class saveFunData extends HttpServlet {

    private String getInputString(String parameter) {
        String val;
        if (parameter.isEmpty()) {
            val = null;
        } else {
            val = parameter.trim();
        }
        return val;
    }

    private int getInput(String userId) {
        int id = 0;
        if (userId == null) {
            return 0;
        }
        if (userId.isEmpty()) {
            return 0;
        }
        if (!userId.isEmpty()) {
            id = Integer.parseInt(userId);
        }
        return id;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter pw = response.getWriter();
            int userId = getInput(request.getParameter("userId"));
            String category = getInputString(request.getParameter("category").equalsIgnoreCase("others") ? request.getParameter("category1") : request.getParameter("category"));
            String type = getInputString(request.getParameter("type").equalsIgnoreCase("others") ? request.getParameter("type1") : request.getParameter("type"));
            String basedon = getInputString(request.getParameter("basedon").equalsIgnoreCase("others") ? request.getParameter("basedon1") : request.getParameter("basedon"));
            String title = getInputString(request.getParameter("title"));
            String description = request.getParameter("description").trim();
            pw.print("<br>");
            pw.print(category);
            pw.print("<br>");
            pw.print(type);
            pw.print("<br>");
            pw.print(basedon);
            pw.print("<br>");
            pw.print(title);
            pw.print("<br>");
            pw.print(description);
            pw.print("<br>");
            pw.print(userId);
            DatabaseConnection dc = DatabaseConnection.getInstance();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                con = dc.getConnection();
                String sql = "insert into fun(posted_by_id,title,description,category,based_on,type)value(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, description);
                ps.setString(4, category);
                ps.setString(5, basedon);
                ps.setString(6, type);
                boolean val = ps.execute();
                if (val) {
                    pw.print("Not inserted");
                } else {
                    pw.print("Inserted sucesfully");
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
                response.sendRedirect("fun?msg=Data has been saved");
                //request.setAttribute("msg", "");
                //request.getRequestDispatcher("fun").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(saveFunData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
