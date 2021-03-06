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

import com.answer.user.saveanswer.SaveAnswer;
import com.connect.DatabaseConnection;
import com.notifications.CreateNotification;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
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
        validateInput input = new validateInput();
        SaveAnswer saveAnswer = new SaveAnswer();
        CreateNotification notification = new CreateNotification();
        String message = null;
        String path = "Admin/unanswerQuestion.jsp";
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {

            int userId = input.getInputInt(request.getParameter("userId"));
            int questionId = input.getInputInt(request.getParameter("qId"));
            int id_of_user_who_posted_question = input.getInputInt(request.getParameter("postedById"));
            String answer = input.getInputString(request.getParameter("answer"));

            if (questionId != 0 && answer != null) {
                if (userId != 0) {
                    if (!saveAnswer.SaveAnswerByQuestionIdAndIdUserId(userId, questionId, answer, true)) {
                        if (id_of_user_who_posted_question != 0) {
                            if (!notification.UserGotAnswerOfQuestion(userId, id_of_user_who_posted_question, questionId)) {
                                message = "Answer has been posted and notification has been successfully sent to user";
                            } else {
                                message = "Answer has been posted but notification not sent to user";
                            }
                        } else {
                            message = "Answer has been posted, Notification will not generate for the guest post";
                        }
                    } else {
                        message = "Answer not saved, Please try agina or report to admin";
                    }
                } else {
                    if (!saveAnswer.SaveAnswerByQuestionIdAndIdUserId(userId, questionId, answer, false)) {
                        message = "Dear user, Your answer has been saved. it will visible after admin approval";
                    } else {
                        message = "Dear Guest user, Your answer not saved. Please try again or report to admin";
                    }
                }
            } else {
                message = "Question id is zero, or Answer is empty. Please try agian or contact to adminstrator";
            }
        } catch (Exception msg) {
            Logger.getLogger(SaveAnswerAdminModule.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
