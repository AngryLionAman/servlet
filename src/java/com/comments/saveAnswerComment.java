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
import com.string.WordFormating;
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
public class saveAnswerComment extends HttpServlet {

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

        WordFormating word = new WordFormating();
        validateInput input = new validateInput();
        HttpSession session = request.getSession();
        SaveAnswerCommentClassFile file = new SaveAnswerCommentClassFile();
        CreateNotification notification = new CreateNotification();

        int SessionActiveUserId = input.getInputInt(request.getParameter("session_active_user_id"));
        int answer_id = input.getInputInt(request.getParameter("answer_id"));
        int question_id = input.getInputInt(request.getParameter("question_id"));
        int id_of_user_who_posted_question = input.getInputInt(request.getParameter("id_of_user_who_posted_question"));
        String question = input.getInputString(request.getParameter("question"));
        String comments = word.RemoveWhiteSpace(input.getInputString(request.getParameter("comments")));

        String message = null;

        if (SessionActiveUserId != 0 && answer_id != 0 && question_id != 0 && question != null && comments != null) {
            try {
                if (!file.SaveAnswerComment(SessionActiveUserId, answer_id, comments)) {

                    //Followered id = who created the notification
                    //user id = who posted the question
                    if (session.getAttribute("userIdForNotification") != null) {
                        String allUserId = String.valueOf(session.getAttribute("userIdForNotification"));
                        if (!notification.CreateNotificationOfAnswerCommentForAllReatedUsers(allUserId, SessionActiveUserId, question_id, answer_id)) {
                            message = "Comment has been posted and notification has been sent to all related user";
                        } else {
                            message = "Comment has been posted but got some problem to sent notification to all users";
                        }
                    } else {
                        if (id_of_user_who_posted_question != 0) {
                            if (!notification.CreateNotificationOfAnswerComment(id_of_user_who_posted_question, SessionActiveUserId, question_id, answer_id)) {
                                message = "Comment has been posted and notification has been successfully sent to user";
                            } else {
                                message = "Comment has been posted but got some error to send notification to " + id_of_user_who_posted_question;
                            }
                        } else {
                            message = "Comment has been posted, Notification will not create for the guest post";
                        }
                    }
                } else {
                    message = "Comment not posted, Please try again";
                }
            } catch (SQLException ex) {
                Logger.getLogger(saveAnswerComment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(saveAnswerComment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(saveAnswerComment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Bad argument, Comment not saved. Please try again";
        }
        request.setAttribute("id", question_id);
        request.setAttribute("message", message);
        request.getRequestDispatcher("questions").forward(request, response);
    }
}
