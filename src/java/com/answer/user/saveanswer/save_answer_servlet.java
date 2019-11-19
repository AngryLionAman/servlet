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
package com.answer.user.saveanswer;

import com.string.validateInput;
import java.io.IOException;
import java.io.PrintWriter;
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
public class save_answer_servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter pw = response.getWriter();

        validateInput input = new validateInput();

        SaveAnswer saveAnswer = new SaveAnswer();

        supportingFunction function = new supportingFunction();

        String message = null;

        try {
            //String question = input.getInputString(request.getParameter("question"));
            int id_of_user = input.getInputInt(request.getParameter("_id_of_user"));
            int q_id = input.getInputInt(request.getParameter("q_id"));
            int id_of_user_who_posted_question = input.getInputInt(request.getParameter("id_of_user_who_posted_question"));
            String answer = input.getInputString(request.getParameter("answer"));
            message = "Got some unknown error";
            if (!saveAnswer.saveAnswerByQuestionAndIdUserId(id_of_user, q_id, answer)) {
                message = "Answer has been submited";
                /*Create the creating notification function*/
                if (!function.CreateNotification(id_of_user, id_of_user_who_posted_question, q_id)) {
                    //Notification created
                }
                request.setAttribute("message", message);

            } else {
                
                message = "Answer not saved,please try again";
                request.setAttribute("message", message);

            }
            request.setAttribute("Id", q_id);
            request.getRequestDispatcher("Answer.jsp").forward(request, response);
        } catch (IOException | SQLException | ServletException msg) {
            Logger.getLogger(save_answer_servlet.class.getName()).log(Level.SEVERE, message, msg);
        }
    }
}
