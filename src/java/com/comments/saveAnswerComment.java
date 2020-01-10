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
        
        String message = null;
        int question_id = 0;
        
        try {
            
            int userId = input.getInputInt(request.getParameter("session_active_user_id"));
            int answer_id = input.getInputInt(request.getParameter("answer_id"));
            question_id = input.getInputInt(request.getParameter("question_id"));
            int id_of_user_who_posted_question = input.getInputInt(request.getParameter("id_of_user_who_posted_question"));
            //String question = input.getInputString(request.getParameter("question"));
            String comments = word.RemoveWhiteSpace(input.getInputString(request.getParameter("comments")));
            
            if (answer_id != 0 && comments != null) {
                if (userId != 0) {
                    if (!file.SaveAnswerComment(userId, answer_id, comments, true)) {
                        if (session.getAttribute("userIdForNotification") != null) {
                            String allUserId = String.valueOf(session.getAttribute("userIdForNotification"));
                            if (!notification.CreateNotificationOfAnswerCommentForAllReatedUsers(allUserId, userId, question_id, answer_id)) {
                                message = "Comment has been posted and notification has been sent to all related user";
                            } else {
                                message = "Comment has been posted but got some problem to sent notification to all users";
                            }
                        } else {
                            if (id_of_user_who_posted_question != 0) {
                                if (!notification.CreateNotificationOfAnswerComment(id_of_user_who_posted_question, userId, question_id, answer_id)) {
                                    message = "Comment has been posted and notification has been successfully sent to user";
                                } else {
                                    message = "Comment has been posted but got some error to send notification to " + id_of_user_who_posted_question;
                                }
                            } else {
                                message = "Comment has been posted, Notification will not create for the guest post";
                            }
                        }
                    } else {
                        message = "Save comment is failed, please try agian";
                    }
                } else {
                    if (!file.SaveAnswerComment(userId, answer_id, comments, false)) {
                        message = "Dear guest user, Comment has been posted. Your comment will display after admin approval ";
                    } else {
                        message = "Dear guest user, Your commet on answer is not saved. please contact to administrator";
                    }
                }
            } else {
                message = "Comment is empty or you are hitting the bad url";
            }
        } catch (Exception msg) {
            Logger.getLogger(saveAnswerComment.class.getName()).log(Level.SEVERE, message, msg);
        } finally {
            request.setAttribute("id", question_id);
            request.setAttribute("message", message);
            request.getRequestDispatcher("questions").forward(request, response);
        }
        
    }
}
