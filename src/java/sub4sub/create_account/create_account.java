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
package sub4sub.create_account;

import com.string.validateInput;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
@WebServlet(name = "create_account", urlPatterns = {"/create_account"})
public class create_account extends HttpServlet {

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
        validateInput input = new validateInput();

        String fullName = input.getInputString(request.getParameter("fullname"));
        String what_you_are = input.getInputString(request.getParameter("what_you_are"));

        String account_name = input.getInputString(request.getParameter("account_name"));
        String account_link = input.getInputString(request.getParameter("account_link"));
        String connection_methode = input.getInputString(request.getParameter("connection_methode"));
        String conteact_detail = input.getInputString(request.getParameter("contact_detail"));
        String country = input.getInputString(request.getParameter("country"));
        String password = request.getParameter("password");
    }
}
