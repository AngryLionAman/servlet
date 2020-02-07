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
package com.password;

import com.connect.DatabaseConnection;
import com.mail.Mail;
import com.string.ValidateWithRegularExpression;
import com.string.validateInput;
import com.user.SupportingFunction;
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
public class ForgetPasswordsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        validateInput input = new validateInput();
        ValidateWithRegularExpression expression = new ValidateWithRegularExpression();
        SupportingFunction supportingFunction = new SupportingFunction();

        String message = null;
        String path = "forgotpassword.jsp";
        DatabaseConnection connection = new DatabaseConnection();
        try (Connection con = DatabaseConnection.makeConnection()){
            String userEmail = input.getInputString(request.getParameter("mail"));
            if (userEmail != null) {
                if (expression.validateEamil(userEmail)) {
                    if (supportingFunction.EmailIsAvaliabe(con,userEmail)) {
                        Mail.sendMail(userEmail, supportingFunction.GetFullNameByEmail(con,userEmail), 8901);
                        message = "Mail has been sent, Please check your inbox";
                    } else {
                        message = "This Email is not available in our database, Please create the new account";
                    }
                } else {
                    message = "Email format is not valid, Please insert the valid Email";
                }
            } else {
                message = "Your input is empty, Please try again";
            }
        } catch (ClassNotFoundException | SQLException msg) {
            Logger.getLogger(ForgetPasswordsServlet.class.getName()).log(Level.SEVERE, null, msg);
        } catch (Exception ex) {
            Logger.getLogger(ForgetPasswordsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.setAttribute("message", message);
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
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ForgetPasswordsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ForgetPasswordsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
