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
package com.question.update;

import com.connect.DatabaseConnection;
import com.question.user.questionClass;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
@WebServlet(name = "updateQuestionByUser", urlPatterns = {"/update_q"})
public class updateQuestionByUser extends HttpServlet {

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

        validateInput input = new validateInput();
        updateQuestionClassByUser user = new updateQuestionClassByUser();
        questionClass tag_class = new questionClass();

        String question = input.getInputString(request.getParameter("question"));
        int question_id = input.getInputInt(request.getParameter("question_id"));
        String tag = input.getInputString(request.getParameter("tag"));

        String message = null;

        if (question != null && question_id != 0) {
            try{
                DatabaseConnection connection = new DatabaseConnection();
                try(Connection con  = DatabaseConnection.makeConnection()) {
                    if (!user.UpdateQuestionByQuestionId(con,question, question_id)) {
                        if (tag != null) {
                            if (!tag_class.SaveTag(con,tag)) {
                                if (!tag_class.SaveTagWithQuestionId(con,question_id, tag)) {
                                    message = "Question and tag has been updated successfully";
                                } else {
                                    message = "Question has been updated but not integrated with topic";
                                }
                            } else {
                                message = "Question has been updatd but tag not updated, please try again";
                            }
                        } else {
                            message = "Question has been updated but tag is still empty";
                        }
                    } else {
                        message = "question not updated,Got some problme.Please try again";
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(updateQuestionByUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(updateQuestionByUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(updateQuestionByUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Got Some error , please try again";
        }
        request.setAttribute("message", message);
        request.setAttribute("id", question_id);
        request.getRequestDispatcher("questions").forward(request, response);
    }
}
