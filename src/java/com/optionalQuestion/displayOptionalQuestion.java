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
package com.optionalQuestion;

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
public class displayOptionalQuestion extends HttpServlet {

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

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DatabaseConnection dc = new DatabaseConnection();
            List<optionalQuestionPojo> list = new ArrayList<>();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            int pageNo = getInput(request.getParameter("p"));
            String _numofOption = getInputString(request.getParameter("option") == null ? "" : request.getParameter("option"));
            String _basedOn = getInputString(request.getParameter("onTopic") == null ? "" : request.getParameter("onTopic"));
            int recordPerPage = 30;
            float totalNumberOfpage = 0;
            int startPage = (pageNo * recordPerPage) - recordPerPage;

            try {
                con = dc.getConnection();
                String sql;
                if (_numofOption.equalsIgnoreCase("all") && _basedOn.equalsIgnoreCase("all")) {
                    sql = "select id,question,answer,on_topic,posted_by,total_option from optional_question order by rand() limit "+startPage+","+recordPerPage;
                } else {
                    sql = "select id,question,answer,on_topic,posted_by,total_option from optional_question";
                    if (!_numofOption.equalsIgnoreCase("all")) {
                        sql += " where total_option = '" + _numofOption + "'";
                    }
                    if (!_basedOn.equalsIgnoreCase("all")) {
                        sql += " where on_topic = '" + _basedOn + "'";
                    }
                }
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String question = rs.getString("question");
                    String correctAnswer = rs.getString("answer");
                    String onTopic = rs.getString("on_topic");
                    int postedBy = rs.getInt("posted_by");
                    int totalOption = rs.getInt("total_option");
                    list.add(new optionalQuestionPojo(id, question, correctAnswer, onTopic, postedBy, totalOption));
                }
                sql = "select count(*) as cnt from optional_question";
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
                if (_numofOption.equalsIgnoreCase("all") && _basedOn.equalsIgnoreCase("all")) {
                    request.setAttribute("totalNumberOfpage", (int) totalNumberOfpage);
                }
                request.getRequestDispatcher("optionalq.jsp").forward(request, response);
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
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(displayOptionalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
