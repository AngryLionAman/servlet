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
public class saveOptionalQuestion extends HttpServlet {

    private String getInputString(String parameter) {
        String val;
        if (parameter.isEmpty()) {
            val = null;
        } else {
            val = parameter.trim();
        }
        return val;
    }

    private int getInput(String option) {
        int id = 0;
        if (option == null) {
            return 0;
        }
        if (option.isEmpty()) {
            return 0;
        }
        if (!option.isEmpty()) {
            id = Integer.parseInt(option);
        }
        return id;
    }

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
        PrintWriter pw = response.getWriter();
        String question = getInputString(request.getParameter("o_questin"));
        String onTopic = getInputString(request.getParameter("onTopic").equalsIgnoreCase("others") ? request.getParameter("onTopic1") : request.getParameter("onTopic"));
        int postedBy = getInput(request.getParameter("userId"));
        int numberOfOption = getInput(request.getParameter("nuberOfOption"));
        String[] opt = request.getParameterValues("option[]");
        String correctAnswer = getInputString(request.getParameter("correctAnswer"));
        String msg = "";
        if (question == null) {
            msg += "<font style='color: red'>Question can't be empty</font><br>";
        }
        if (onTopic == null) {
            msg += "Topic can't be empty<br>";
        }
        if (question != null && onTopic != null) {
            try {
                DatabaseConnection dc = DatabaseConnection.getInstance();
                Connection con = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    con = dc.getConnection();
                    String sql = "insert into optional_question(question,answer,on_topic,posted_by,total_option)values(?,?,?,?,?);";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, question);
                    ps.setString(2, correctAnswer);
                    ps.setString(3, onTopic);
                    ps.setInt(4, postedBy);
                    ps.setInt(5, numberOfOption);
                    Boolean value = ps.execute();
                    if (value) {

                    } else {
                        int questionId = 0;
                        try {

                            sql = "select id from optional_question where question = ?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, question);
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                questionId = rs.getInt("id");
                            }
                        } catch (SQLException expIngettigTheQuestionId) {
                            throw expIngettigTheQuestionId;
                        }
                        sql = "insert into option_of_question(question_id,option_value)values(?,?)";
                        for (String obj : opt) {
                            ps = con.prepareStatement(sql);
                            ps.setInt(1, questionId);
                            ps.setString(2, obj.trim());
                            boolean r_value = ps.execute();
                            if (!r_value) {
                                msg = "Question has been posted<br>";
                            } else {
                                msg += "Got some Problem<br>";
                            }
                        }
                    }
                } catch (SQLException err) {
                    try {
                        throw err;
                    } catch (SQLException ex) {
                        Logger.getLogger(saveOptionalQuestion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException err) {

                        }
                    }
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException err) {

                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException err) {

                        }
                    }

                }
                response.sendRedirect("optionalquestion?msg=" + msg);
            } catch (SQLException ex) {
                Logger.getLogger(saveOptionalQuestion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(saveOptionalQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("optional.jsp?msg=" + msg);
        }
    }

}
