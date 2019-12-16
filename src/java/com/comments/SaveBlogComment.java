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

        int sessionUserId = input.getInputInt(request.getParameter("sessionUserId"));
        int userIdWhoPosted = input.getInputInt(request.getParameter("bloggerUserId"));
        int blogId = input.getInputInt(request.getParameter("blog_id"));
        String comment = input.getInputString(request.getParameter("comments"));

        String message = null;

        if (blogId != 0 && comment != null) {
            if (sessionUserId != 0) {
                try {
                    if (!blogClassFile.SaveBlogComment(sessionUserId, blogId, comment)) {
                        if (userIdWhoPosted != 0) {
                            if (!createNotification.CreateNotificationForBlogComment(sessionUserId, userIdWhoPosted, blogId)) {
                                message = "Comment has been saved and notification has been sent to the user";
                            } else {
                                message = "Comment has been posted, but got some probelm createing notification";
                            }
                        } else {
                            message = "This blog is posted by guest user, So comment has been posted but notification will not generate";
                        }
                    } else {
                        message = "Command not saved, Please try again";
                    }
                } catch (SQLException |ClassNotFoundException  ex) {
                    Logger.getLogger(SaveBlogComment.class.getName()).log(Level.SEVERE, null, ex);
                }catch (Exception ex) {
                    Logger.getLogger(SaveBlogComment.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                message = "Guest user not allowed to post the comment";
            }
        } else {
            message = "Bad argument, May comment is without word. Please try again with valid input";
        }
        request.setAttribute("message", message);
        request.setAttribute("id", blogId);
        request.getRequestDispatcher("blog").forward(request, response);
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
