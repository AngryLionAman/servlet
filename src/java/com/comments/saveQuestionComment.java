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
import javax.servlet.http.HttpSession;

/**
 *
 * @author AngryLion
 */
public class saveQuestionComment extends HttpServlet {

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

        HttpSession session = request.getSession();
        SaveQuestionCommentClassFile file = new SaveQuestionCommentClassFile();
        CreateNotification notification = new CreateNotification();
        validateInput input = new validateInput();

        int session_active_user_id = input.getInputInt(request.getParameter("session_active_user_id"));
        int id_of_user_who_posted_question = input.getInputInt(request.getParameter("id_of_user_who_posted_question"));
        int question_id = input.getInputInt(request.getParameter("question_id"));
        String comments = input.getInputString(request.getParameter("comments"));
        String question = input.getInputString(request.getParameter("question"));

        String message = null;
        if (session_active_user_id != 0 && question_id != 0 && comments != null && question != null) {
            try {
                if (!file.SaveQuestionComment(session_active_user_id, question_id, comments)) {
                    if (session.getAttribute("userIdForNotification") != null) {
                        String allUserId = String.valueOf(session.getAttribute("userIdForNotification"));
                        if (!notification.CreateNotificationOfQuestionForAllRealtedUsers(allUserId, session_active_user_id, question_id)) {
                            message = "Commnted has been posted and notification has been sent to all related users";
                        } else {
                            message = "Comment has been saved, but notification not generated";
                        }
                    } else {
                        if (id_of_user_who_posted_question != 0) {
                            if (!notification.CreateNotificationForQuestionComment(id_of_user_who_posted_question, session_active_user_id, question_id)) {
                                message = "Comment has been posted and notification has been sent to user";
                            } else {
                                message = "Comment has been posted, Notification not sent to user";
                            }
                        } else {
                            message = "Comment has been saved, Notification will not generate for the guest post";
                        }
                    }
                } else {
                    message = "Comment not posted";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(saveQuestionComment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(saveQuestionComment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Comment not saved due to bad argument, Please try agian";
        }
        request.setAttribute("id", question_id);
        request.setAttribute("q", question);
        request.setAttribute("message", message);
        request.getRequestDispatcher("questions").forward(request, response);
    }

}
