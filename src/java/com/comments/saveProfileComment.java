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

/**
 *
 * @author AngryLion
 */
public class saveProfileComment extends HttpServlet {

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
        onProfileCommentClass commentClass = new onProfileCommentClass();
        CreateNotification notification = new CreateNotification();

        int OnCommentUserId = 0;
        String message = null;

        try {
            String comments = input.getInputString(request.getParameter("comments"));
            int userId = input.getInputInt(request.getParameter("session_userid"));
            OnCommentUserId = input.getInputInt(request.getParameter("OnCommentUserId"));

            if (OnCommentUserId != 0 && comments != null) {
                if (userId != 0) {
                    if (!commentClass.SaveCommentOfProfie(userId, OnCommentUserId, comments, true)) {
                        if (!notification.CreateNotificationOfProfileComment(userId, OnCommentUserId)) {
                            message = "Comment has been Posted and notification has been sent to user";
                        } else {
                            message = "Comment has been saved, But sending notification to user is failed";
                        }
                    } else {
                        message = "Comment not saved, Please report to admin";
                    }
                } else {
                    if (!commentClass.SaveCommentOfProfie(userId, OnCommentUserId, comments, false)) {
                        message = "Comment has been saved and this will appear admin approved";
                    } else {
                        message = "Comment save operaion failed, Please try again";
                    }
                }
            } else {
                message = "userId is zero or comment is empty. Pelase try agina or report to admin";
            }
        } catch (ClassNotFoundException | SQLException msg) {
            Logger.getLogger(saveProfileComment.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("message", message);
            request.setAttribute("id", OnCommentUserId);
            request.getRequestDispatcher("profile").forward(request, response);
        }
    }
}
