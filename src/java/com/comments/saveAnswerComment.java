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
package com.comments;

import com.connect.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class saveAnswerComment extends HttpServlet {

    private int getInputInt(String option) {
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

    private String getInputString(String parameter) {
        String val;
        if (parameter.isEmpty()) {
            val = null;
        } else {
            val = parameter.trim();
        }
        return val;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int SessionActiveUserId = getInputInt(request.getParameter("session_active_user_id"));
        int answer_id = getInputInt(request.getParameter("answer_id"));
        int question_id = getInputInt(request.getParameter("question_id"));
        int id_of_user_who_posted_question = getInputInt(request.getParameter("id_of_user_who_posted_question"));
        String question = getInputString(request.getParameter("question"));
        String comments = getInputString(request.getParameter("comments"));
        if (SessionActiveUserId != 0 && answer_id != 0 && question_id != 0 && question != null && comments != null
                && id_of_user_who_posted_question != 0) {
            try {
                DatabaseConnection dc = DatabaseConnection.getInstance();
                Connection con = null;
                PreparedStatement ps = null;
                PreparedStatement ps1 = null;
                try {
                    con = dc.getConnection();
                    String sql = "INSERT INTO comments (user_id,ans_id,comments)VALUES(?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, SessionActiveUserId);
                    ps.setInt(2, answer_id);
                    ps.setString(3, comments);
                    boolean value = ps.execute();
                    if (!value) {
                        //Followered id = who created the notification
                        //user id = who posted the question
                        String sql1 = "INSERT INTO notification (user_id,notification_type,followers_id,question_id,ans_id ) VALUES (?,?,?,?,?)";
                        ps1 = con.prepareStatement(sql1);
                        ps1.setInt(1, id_of_user_who_posted_question);
                        ps1.setString(2, "comment_on_Answer");
                        ps1.setInt(3, SessionActiveUserId);
                        ps1.setInt(4, question_id);
                        ps1.setInt(5, answer_id);
                        boolean saved = ps1.execute();
                        if (!saved) {
                            request.setAttribute("Id", question_id);
                            request.setAttribute("q", question);
                            request.getRequestDispatcher("Answer.jsp").forward(request, response);
                        }
                    }
                } catch (SQLException msg) {
                    throw msg;
                } finally {
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
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException msg) {

                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(saveAnswerComment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }
}
