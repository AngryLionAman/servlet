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
public class updateAnswer extends HttpServlet {

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

        String question = input.getInputString(request.getParameter("q"));
        int question_id = input.getInputInt(request.getParameter("q_id"));
        int answer_id = input.getInputInt(request.getParameter("ans_id"));
        //int answer_by_id = input.getInputInt(request.getParameter("ans_by_id"));

        String message = null;
        String answer = null;

        updateAnswerClass update = new updateAnswerClass();

        if (question_id != 0 && answer_id != 0) {
            try {
                DatabaseConnection connection = new DatabaseConnection();
                try (Connection con = DatabaseConnection.makeConnection()) {
                    //get answer of selected the id
                    answer = update.GetAnswerByAnswerid(con, answer_id);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(updateAnswer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(updateAnswer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(updateAnswer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Invalid argument";
        }
        request.setAttribute("answer", answer);
        request.setAttribute("id", question_id);
        request.setAttribute("question", question);
        request.setAttribute("answerid", answer_id);
        request.setAttribute("message", message);

        request.getRequestDispatcher("edit_a.jsp").forward(request, response);
    }
}
