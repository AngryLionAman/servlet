/*
 * Copyright 2019 inquiryhere.com.
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
package com.admin.unasweredQuestion;

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
 * @author inquiryhere.com
 */
public class SaveAnswerAdminModule extends HttpServlet {
    //this methode is used for admin panel

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
        
        String answer = request.getParameter("answer");
        int questionId = Integer.parseInt(request.getParameter("qId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int PostedByuserId = Integer.parseInt(request.getParameter("postedById"));

        /**
         * ***********************************
         */
        DatabaseConnection connection = null;
        try {
            connection = new DatabaseConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SaveAnswerAdminModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        try {
            con = connection.getConnection();
            String sql = "insert into answer(q_id,answer,Answer_by_id,vote) values(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            ps.setString(2, answer);
            ps.setInt(3, userId);
            ps.setInt(4, 0);
            ps.execute();
            /**
             * ****save the comment**********
             */
            String sql_notification = "INSERT INTO notification (user_id,notification_type,followers_id,question_id)VALUES(?,?,?,?)";
            ps1 = con.prepareStatement(sql_notification);
            ps1.setInt(1, PostedByuserId);
            ps1.setString(2, "got_answer_of_a_question");
            ps1.setInt(3, userId);
            ps1.setInt(4, questionId);
            ps1.execute();

            response.sendRedirect("Admin/unanswerQuestion.jsp?msg=answer has been successfully saved!!");

        } catch (SQLException msg) {
            try {
                throw msg;
            } catch (SQLException ex) {
                Logger.getLogger(SaveAnswerAdminModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    //out.println("Exception in closing preparedStatement " + e);
                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    //out.println("Exception in closing preparedStatement " + e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    //out.println("Exception in closing connection " + e);
                }
            }
        }
    }
}
