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
package com.question.modify;

import com.notifications.CreateNotification;
import com.string.validateInput;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "save_modified_question", urlPatterns = {"/save_modified_question"})
public class save_modified_question extends HttpServlet {

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
        validateInput input = new validateInput();
        ModifiedQuestionClassFile file = new ModifiedQuestionClassFile();
        CreateNotification notification = new CreateNotification();

        String message = null;
        String path = "questions";

        int questionId = 0;

        try {

            questionId = input.getInputInt(request.getParameter("question_id"));
            int currentUserId = input.getInputInt(request.getParameter("currentUserId"));
            int userIdWhoPostedQuestion = input.getInputInt(request.getParameter("userIdWhoPostedQuestion"));
            String old_question = input.getInputString(request.getParameter("old_question"));
            String modified_question = input.getInputString(request.getParameter("modified_question"));

            if (questionId != 0 && old_question != null && modified_question != null) {
                if (!old_question.equalsIgnoreCase(modified_question)) {
                    if (!file.saveModifiedQuestion(currentUserId, questionId, modified_question, userIdWhoPostedQuestion)) {
                        if (userIdWhoPostedQuestion != 0) {
                            if (!notification.modificationOfQuestionRequest(userIdWhoPostedQuestion, currentUserId, questionId)) {
                                message = "Your question approvel is panding by user, If user not respond within five working day then approval will be handover to the admin";
                            } else {
                                message = "Notification not created for the user, Got some probelm. Please report to administrator";
                            }
                        } else {
                            message = "Question is posted by Guest user. Now admin will take care of your modification";
                        }
                    } else {
                        message = "Your modified question is not saved, Please try agina or report to administrator";
                    }
                } else {
                    message = "Old question and the Modified question is same, Question not updated";
                }
            } else {
                message = "Question id can't be zero, Old question can't be empty, or new question can't be empty.If you are getting this message Please report to admin";
            }

        } catch (ClassNotFoundException | SQLException msg) {
            Logger.getLogger(save_modified_question.class.getName()).log(Level.SEVERE, path, msg);
        } finally {
            request.setAttribute("id", questionId);
            request.setAttribute("message", message);

            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
