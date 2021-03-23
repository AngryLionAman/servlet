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
package com.blog;

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
public class saveblog extends HttpServlet {

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

        validateInput input = new validateInput();

        String blog_sub = input.getInputString(request.getParameter("blog_subject"));
        String blog_description = input.getInputString(request.getParameter("blog_description"));
        int id_of_user = input.getInputInt(request.getParameter("Session_id_of_user"));

        saveBlogClass blog = new saveBlogClass();

        String message = null;

        if (blog_sub != null && blog_description != null && id_of_user != 0) {
            try {
                if (!blog.saveBlog(blog_sub, blog_description, id_of_user)) {
                    message = "Blog has been saved";
                    //Send notification to all followers
                } else {
                    message = "Blog not saved, Got some unknown error. Please try again";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(saveblog.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Blog not saved due to bad argument, Please try again";
        }
        request.setAttribute("message", message);
        request.setAttribute("id", id_of_user);
        request.setAttribute("tab", "blog");
        request.getRequestDispatcher("profile").forward(request, response);
    }
}
