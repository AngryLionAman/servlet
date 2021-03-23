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
package com.answer.update;

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
public class saveUpdatedAnswer extends HttpServlet {

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

        int answerId = input.getInputInt(request.getParameter("answer_id"));
        int questionId = input.getInputInt(request.getParameter("question_id"));
        String answer = input.getInputString(request.getParameter("answer"));

        saveUpdatedAnswerClass answerClass = new saveUpdatedAnswerClass();

        String message = null;

        if (answerId != 0 && questionId != 0 && answer != null) {
            try (Connection con = DatabaseConnection.getInstance().getConnection()) {
                if (!answerClass.SaveupdatedAnswerByAnswerId(con, answerId, answer)) {
                    message = "Answer has been successfully updated";
                } else {
                    message = "Answer not update, Please try again";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(saveUpdatedAnswer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(saveUpdatedAnswer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Empty value error, plase try again";
        }
        request.setAttribute("id", questionId);
        request.setAttribute("message", message);
        request.getRequestDispatcher("questions").forward(request, response);
    }
}
