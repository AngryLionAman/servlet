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
package com.admin.question;

import java.io.IOException;
import java.io.PrintWriter;
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
public class updateQuestion extends HttpServlet {

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
        saveQuestion save = new saveQuestion();
        PrintWriter pw = response.getWriter();
        try {
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            String question = request.getParameter("question").trim();
            String questionTag = request.getParameter("tag_of_question").trim();
            questionTag = questionTag.toLowerCase();
            try {
                save.saveQuestionWithIdAndTag(questionId, question, questionTag);
            } catch (SQLException ex) {
                pw.print(ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(updateQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            pw.print("<br>Question has been updated!!!!");
            response.sendRedirect("Admin/modifyQuestion.jsp?p="+pageNumber+"&msg=Question has been successfully updated");
        } catch (NumberFormatException msg) {
            //pw.print(msg);
            throw msg;
        }
    }
}
