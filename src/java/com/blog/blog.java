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
import java.util.List;
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
public class blog extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        validateInput input = new validateInput();
        getAllBlog blog = new getAllBlog();
        SupportingFunctionBlog function = new SupportingFunctionBlog();

        int blogId;
        String page = "blog.jsp";
        String gotException = null;

        if (request.getAttribute("id") != null) {
            blogId = (int) request.getAttribute("id");
        } else {
            blogId = input.getInputInt(request.getParameter("id"));
        }

        String message = null;

        if (request.getAttribute("message") != null) {
            message = (String) request.getAttribute("message");
        }

        List<blogPojoById> blogByBlogId = null;
        List<blogPojo> blogByLimit = null;

        List<blogPojo> blog1 = null;
        try {
            
            if (blogId != 0) {
                if (function.IsBlogPresentByBlogId(blogId)) {

                    blogByBlogId = blog.blogByBlogId(blogId);
                    function.increateBlogViewByBlogId(blogId);
                    blogByLimit = blog.blogByLimit(10);

                    page = "D_Blog.jsp";

                } else {
                    message = "The blog you are looking for has been removed or delete. Sorry for the inconvenience";
                    blog1 = blog.blog();

                    page = "blog.jsp";
                }
            } else {
                blog1 = blog.blog();

                page = "blog.jsp";
            }
        } catch (Exception msg) {
            gotException = "not null";
            Logger.getLogger(blog.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("gotException", gotException);

            request.setAttribute("message", message);

            request.setAttribute("blogByLimit", blogByLimit);
            request.setAttribute("blogByBlogId", blogByBlogId);

            request.setAttribute("blogList", blog1);

            request.getRequestDispatcher(page).forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(blog.class.getName()).log(Level.SEVERE, null, ex);
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

        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(blog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
