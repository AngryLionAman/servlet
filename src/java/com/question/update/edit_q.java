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
package com.question.update;

import com.connect.DatabaseConnection;
import com.string.validateInput;
import com.topic.supportingFunctionTopic;
import java.io.IOException;
import java.sql.Connection;
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
public class edit_q extends HttpServlet {

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
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            
            validateInput input = new validateInput();
            
            int questionid = input.getInputInt(request.getParameter("id"));
            String question = input.getInputString(request.getParameter("q"));
            
            supportingFunctionTopic function = new supportingFunctionTopic();
            
            String message = null;            
            String GetAllTopicByQuestionId = null;
            
            DatabaseConnection connection = new DatabaseConnection();
            if (question != null && questionid != 0) {
                try(Connection con = DatabaseConnection.makeConnection()) {
                    GetAllTopicByQuestionId = function.GetAllTopicByQuestionId(con,questionid);
                    message = "Update your question and tag as well";
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(edit_q.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                message = "May question is null and question id is zero";
            }
            request.setAttribute("topic", GetAllTopicByQuestionId);
            request.setAttribute("question", question);
            request.setAttribute("questionId", questionid);
            request.setAttribute("message", message);
            request.getRequestDispatcher("edit_q.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(edit_q.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
