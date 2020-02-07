/*
 * Copyright 2020 AngryLion.
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
package com.admin.answer;

import com.connect.DatabaseConnection;
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
@WebServlet(name = "admin_answer_approval", urlPatterns = {"/admin_answer_approval"})
public class admin_answer_approval extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        validateInput input = new validateInput();
        AnswerApprovalAdminClassFile file = new AnswerApprovalAdminClassFile();

        String message = null;
        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection()) {
            int answerId = input.getInputInt(request.getParameter("answer_id"));
            String action = input.getInputString(request.getParameter("action"));

            if (answerId != 0 && action != null) {
                if (action.equalsIgnoreCase("accept")) {
                    if (!file.changeApprovaByAdmin(con, answerId)) {
                        message = "Answer approved";
                    } else {
                        message = "Changing permission failed";
                    }
                } else if (action.equalsIgnoreCase("delete")) {
                    if (!file.deleteAnswerApprovalById(con, answerId)) {
                        message = "Answer delete successfully";
                    } else {
                        message = "Answer Not deleted, Please try again";
                    }
                } else {
                    message = "There is no any other option";
                }
            } else {
                message = "Answer id is zero or action is null";
            }
        } catch (ClassNotFoundException | SQLException msg) {
            throw msg;
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("Admin/approva_by_admin_answer.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(admin_answer_approval.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(admin_answer_approval.class.getName()).log(Level.SEVERE, null, ex);
        }
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
