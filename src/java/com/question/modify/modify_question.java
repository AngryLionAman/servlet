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
package com.question.modify;

import com.connect.DatabaseConnection;
import com.question.Question;
import com.string.validateInput;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
@WebServlet(name = "modify_question", urlPatterns = {"/modify_q"})
public class modify_question extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        Question ques = new Question();

        String message = null;
        String path = "question";

        int questionId = 0;
        int currentUserId = 0;
        String question = null;
        int userIdWhoPostedQuestion = 0;

        try {

            questionId = input.getInputInt(request.getParameter("q_id"));
            currentUserId = input.getInputInt(request.getParameter("u_id"));
            question = input.getInputString(request.getParameter("q"));
            userIdWhoPostedQuestion = input.getInputInt(request.getParameter("q_u_id"));

            if (questionId != 0) {
                DatabaseConnection connection = new DatabaseConnection();
                try (Connection con = DatabaseConnection.makeConnection()) {
                    if (ques.IsQuestionPresentByQuestionId(con, questionId)) {
                        path = "modify_question.jsp";
                    } else {
                        message = "The question id you are looking for is not availabe in database, please try again";
                    }
                }

            } else {
                message = "Hitting the invalid argumet, Please try again";
            }

        } catch (ClassNotFoundException | SQLException msg) {
            Logger.getLogger(modify_question.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("message", message);

            request.setAttribute("id", questionId);
            request.setAttribute("currentUserId", currentUserId);
            request.setAttribute("question", question);
            request.setAttribute("userIdWhoPostedQuestion", userIdWhoPostedQuestion);

            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
