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
public class SaveBlogComment extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

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
        SaveBlogCommentClassFile blogClassFile = new SaveBlogCommentClassFile();
        CreateNotification createNotification = new CreateNotification();

        String message = null;
        int blogId = 0;

        try {

            int userId = input.getInputInt(request.getParameter("sessionUserId"));
            int userIdWhoPostedBog = input.getInputInt(request.getParameter("bloggerUserId"));
            blogId = input.getInputInt(request.getParameter("blog_id"));
            String comment = input.getInputString(request.getParameter("comments"));

            if (blogId != 0 && comment != null) {
                if (userId != 0) {
                    if (!blogClassFile.SaveBlogComment(userId, blogId, comment, true)) {
                        if (userIdWhoPostedBog != 0) {
                            if (!createNotification.CreateNotificationForBlogComment(userId, userIdWhoPostedBog, blogId)) {
                                message = "Comment has been saved and notification has been sent to user";
                            } else {
                                message = "Comment has been posted, but got some probelm in createing notification";
                            }
                        } else {
                            message = "This blog is posted by guest user, So comment has been posted but notification will not generate";
                        }
                    } else {
                        message = "Storing the comment operation faield, Please try again";
                    }
                } else {
                    if (!blogClassFile.SaveBlogComment(userId, blogId, comment, false)) {
                        message = "Dear Guest user, Your comment has been saved. Will display after admin approval";
                    } else {
                        message = "Dear user, Your comment is not saved. Please try again or report to admin";
                    }
                }
            } else {
                message = "Blog id is zero, Or comment is empty. Plase try again or report to admin";
            }
        } catch (Exception msg) {
            Logger.getLogger(SaveBlogComment.class.getName()).log(Level.SEVERE, message, msg);
        } finally {
            request.setAttribute("message", message);
            request.setAttribute("id", blogId);
            request.getRequestDispatcher("blog").forward(request, response);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
