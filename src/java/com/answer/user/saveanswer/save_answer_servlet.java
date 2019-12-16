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

import com.notifications.CreateNotification;
import com.string.validateInput;
import java.io.IOException;
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
        SaveAnswer saveAnswer = new SaveAnswer();
        CreateNotification notification = new CreateNotification();

        int sessionUserId = input.getInputInt(request.getParameter("_id_of_user"));
        int questionId = input.getInputInt(request.getParameter("q_id"));
        int id_of_user_who_posted_question = input.getInputInt(request.getParameter("id_of_user_who_posted_question"));
        String answer = input.getInputString(request.getParameter("answer"));

        String message = null;

        if (sessionUserId != 0 && questionId != 0 && answer != null) {
            try {
                if (!saveAnswer.SaveAnswerByQuestionIdAndIdUserId(sessionUserId, questionId, answer)) {
                    if (id_of_user_who_posted_question != 0) {
                        if (!notification.UserGotAnswerOfQuestion(sessionUserId, id_of_user_who_posted_question, questionId)) {
                            message = "Answer has been posted and notification has been successfully sent to user";
                        } else {
                            message = "Answer has been posted but notification not sent to user";
                        }
                    } else {
                        message = "Answer has been posted, Notification will not generate for the guest post";
                    }
                } else {
                    message = "Answer not saved, Got unknown error";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(save_answer_servlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(save_answer_servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Bad argument, Answer not posted. Please try again";
        }

        request.setAttribute("message", message);
        request.setAttribute("id", questionId);
        request.getRequestDispatcher("questions").forward(request, response);
    }
}
