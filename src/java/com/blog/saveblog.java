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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String blog_sub = getInputString(request.getParameter("blog_subject"));
        String blog_description = getInputString(request.getParameter("blog_description"));
        int id_of_user = getInputInt(request.getParameter("Session_id_of_user"));
        
        saveBlogClass bc = new saveBlogClass();
        
        if (blog_sub != null && blog_description != null && id_of_user != 0) {
            try {
                boolean result =  bc.saveBlog(blog_sub, blog_description, id_of_user);
                if(!result){
                    request.setAttribute("message", "Blog has been saved");
                }else{
                    request.setAttribute("message", "Got some error, Blog not saved");
                }
                request.getRequestDispatcher("blog").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(saveblog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
