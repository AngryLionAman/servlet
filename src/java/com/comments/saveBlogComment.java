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
public class saveBlogComment extends HttpServlet {

    private int getInputInt(String option) {
        int id = 0;
        if (option == null) {
            return 0;
        }
        if (option.isEmpty()) {
            return 0;
        }
        if (!option.isEmpty()) {
            id = Integer.parseInt(option);
        }
        return id;
    }

    private String getInputString(String parameter) {
        String val;
        if (parameter.isEmpty()) {
            val = null;
        } else {
            val = parameter.trim();
        }
        return val;
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int sessionUserId = getInputInt(request.getParameter("sessionUserId"));
        int userIdWhoPosted = getInputInt(request.getParameter("bloggerUserId"));
        int blogId = getInputInt(request.getParameter("blog_id"));
        String comment = getInputString(request.getParameter("comments"));
        if (blogId != 0 && comment != null) {
            try {
                saveBlogCommentClassFile save = new saveBlogCommentClassFile();
                boolean saveBlog = save.saveBlog(userIdWhoPosted, sessionUserId, blogId, comment);
                if (!saveBlog) {
                    request.setAttribute("message", "Comment has been saved");
                    request.getRequestDispatcher("blog?id=" + blogId).forward(request, response);
                } else {
                    request.setAttribute("message", "Comment not saved, Try again");
                    request.getRequestDispatcher("blog?id=" + blogId).forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(saveBlogComment.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //comment and blog id can't be empty
        }
    }

}
