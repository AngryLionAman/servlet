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
package com.admin.question;

import com.connect.DatabaseConnection;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
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
public class updateQuestion extends HttpServlet {

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
        String message = null;
        int pageNumber = 0;
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            saveQuestion save = new saveQuestion();
            validateInput input = new validateInput();

            pageNumber = input.getInputInt(request.getParameter("pageNumber"));
            int questionId = input.getInputInt(request.getParameter("questionId"));
            String question = input.getInputString(request.getParameter("question"));
            String questionTag = input.getInputString(request.getParameter("tag_of_question"));

            if (questionId != 0 && question != null && questionTag != null) {
                DatabaseConnection connection = new DatabaseConnection();
                try (Connection con = DatabaseConnection.makeConnection()) {

                    save.saveQuestionWithIdAndTag(con, questionId, question, questionTag);
                    message = "Question update successfully";

                } catch (Exception msg) {
                    message = "Question not updated, Got some probelm, Contact to administrator";
                    Logger.getLogger(updateQuestion.class.getName()).log(Level.SEVERE, null, msg);
                }
            } else {
                message = "Question id is zero, Or question is null or question tag is empty";
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(updateQuestion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setAttribute("message", message);
            request.setAttribute("pageNumber", pageNumber);
            request.getRequestDispatcher("Admin/modifyQuestion.jsp").forward(request, response);
        }
    }
}
