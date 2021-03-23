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
package com.question;

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
public class SaveQuestionForAdmin extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
       
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        PreparedStatement ps5 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs4 = null;
        try {
            String question = request.getParameter("question").trim();
            String questionTag = request.getParameter("tag_of_question");
            int userId = Integer.parseInt(request.getParameter("userId"));
            out.println(userId);
            if ((question != null && question.length() > 0) && (questionTag != null && questionTag.length() > 0) && userId != 0) {
                String sql = "insert into question(id,question,vote) values(?,?,?)";
                
                con = DatabaseConnection.getInstance().getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setString(2, question);
                ps.setInt(3, 0);
                boolean done = ps.execute();
                if (done) {
                    out.print("Got some problem");
                } else {
                    out.print("saved !!!!");
                }
                /**
                 * *******************************
                 * To get the question id ******************************
                 */
                String sql1 = "select q_id from question where question = ?";
                ps1 = con.prepareStatement(sql1);
                ps1.setString(1, question);
                rs = ps1.executeQuery();
                int currentQuestionId = 0;
                if (rs.next()) {
                    currentQuestionId = rs.getInt("q_id");
                }
                /**
                 * ***********
                 * Storing tag, If not present ************
                 */
                String[] arrSplit = questionTag.split(",");
                for (String arrSplit1 : arrSplit) {
                    boolean fountStatus = true;
                    try {
                        String sql2 = "select topic_name from topic where lower(topic_name) = ?";
                        ps2 = con.prepareStatement(sql2);
                        ps2.setString(1, arrSplit1.trim());
                        rs2 = ps2.executeQuery();
                        if (rs2.next()) {
                            fountStatus = false;
                        }
                        if (fountStatus) {
                            String sql4 = "insert into topic(topic_name) values(?)";
                            ps3 = con.prepareStatement(sql4);
                            ps3.setString(1, arrSplit1.trim());
                            ps3.execute();
                        }

                    } catch (SQLException ms) {
                        out.print(ms);
                        throw ms;
                    }
                }
                /**
                 * ******
                 * Storing tag id with question id ************
                 */
                for (String arrSplit1 : arrSplit) {
                    try {
                        String sql5 = "select unique_id from topic where lower(topic_name) = ?";
                        ps4 = con.prepareStatement(sql5);
                        ps4.setString(1, arrSplit1.trim());
                        rs4 = ps4.executeQuery();
                        if (rs4.next()) {
                            int topicId = rs4.getInt("unique_id");
                            try {
                                String sql6 = "insert into question_topic_tag(question_id,tag_id) values(?,?)";
                                ps5 = con.prepareStatement(sql6);
                                ps5.setInt(1, currentQuestionId);
                                ps5.setInt(2, topicId);
                                ps5.execute();
                            } catch (SQLException msg) {
                                out.println(msg);
                                throw msg;
                            }
                        }

                    } catch (SQLException msg) {
                        out.println(msg);
                        throw msg;
                    }
                }

            } else {
                out.print("bad argument..");
            }
            //response.sendRedirect("Admin/postQuestion.jsp?msg=Question has been stored successfully!!!");
        } catch (NumberFormatException | SQLException msg) {
            try {
                out.println(msg);
                throw msg;
            } catch (NumberFormatException | SQLException ex) {
                Logger.getLogger(SaveQuestionForAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException msg) {

                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                } catch (SQLException msg) {

                }
            }
            if (rs4 != null) {
                try {
                    rs4.close();
                } catch (SQLException msg) {

                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException msg) {

                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException msg) {

                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException msg) {

                }
            }
            if (ps3 != null) {
                try {
                    ps3.close();
                } catch (SQLException msg) {

                }
            }
            if (ps4 != null) {
                try {
                    ps4.close();
                } catch (SQLException msg) {

                }
            }
            if (ps5 != null) {
                try {
                    ps5.close();
                } catch (SQLException msg) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException msg) {

                }
            }
            response.sendRedirect("Admin/postQuestion.jsp?msg=Question has been stored successfully!!!");
        }
    }
}
