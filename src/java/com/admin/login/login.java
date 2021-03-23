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
package com.admin.login;

import com.connect.DatabaseConnection;
import com.string.validateInput;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.user.SupportingFunction;

/**
 *
 * @author AngryLion
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String message = null;
        String path = "Admin/visit.jsp";
        HttpSession session = request.getSession(false);
        validateInput input = new validateInput();
        AdminLoginClassFile file = new AdminLoginClassFile();
        SupportingFunction function = new SupportingFunction();
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {

            String email = input.getInputString(request.getParameter("email"));
            String password = input.getInputString(request.getParameter("password"));
            String code = input.getInputString(request.getParameter("code"));

            if (email != null && password != null && code != null) {
                if (code.equals("sampur")) {
                    if (file.validateAdminUser(con, email, password)) {

                        session.setAttribute("adminUserId", function.GetUserIdByEmail(email));
                        session.setAttribute("userName", function.GetUserNameByEmail(email));
                        session.setMaxInactiveInterval(600);
                        path = "Admin/adminModule.jsp";
                    } else {
                        message = "UserName and Password not valid";
                    }
                } else {
                    message = "Security code not valid, Contact to admin";
                }
            } else {
                message = "All field value required";
            }

        } catch (Exception msg) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, msg);
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher(path).forward(request, response);
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
