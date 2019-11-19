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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validateInput input = new validateInput();
        getAllBlog blog = new getAllBlog();
        supportingFunction function = new supportingFunction();
        int blogId = input.getInputInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
        if (blogId != 0) {
            try {
                List<blogPojoById> blogByBlogId = blog.blogByBlogId(blogId);
                request.setAttribute("blogByBlogId", blogByBlogId);
                /* increase the view count value by one 1*/
                function.increateBlogViewByBlogId(blogId);
                List<blogPojo> blogByLimit = blog.blogByLimit(10);
                request.setAttribute("blogByLimit", blogByLimit);
                request.getRequestDispatcher("D_Blog.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(blog.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {            
            try {
                List<blogPojo> blog1 = blog.blog();
                request.setAttribute("blogList", blog1);
                request.getRequestDispatcher("blog.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(blog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
