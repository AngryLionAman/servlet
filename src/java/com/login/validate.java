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
package com.login;

import com.string.validateInput;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AngryLion
 */
public class validate extends HttpServlet {

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

        validateInput input = new validateInput();
        supportingFunctionLogin login = new supportingFunctionLogin();

        String email = input.getInputString(request.getParameter("email"));
        String password = request.getParameter("password");

        String message = null;

        if (email != null && password != null && !password.isEmpty()) {
            try {
                if (login.IsUserIsPresent(email, password)) {
                    int userId = login.GetUserIdByEmailAndPassword(email, password);
                    if (userId != 0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("Session_id_of_user", userId);
                        session.setMaxInactiveInterval(600);
                        try {
                            Cookie[] cookies = request.getCookies();
                            if (cookies != null) {

                            } else {
                                Cookie usernameCookie = new Cookie("usernamecookie", email);
                                Cookie passwordCookie = new Cookie("passwordcookie", password);
                                usernameCookie.setMaxAge(24 * 60 * 60 * 100);
                                passwordCookie.setMaxAge(24 * 60 * 60 * 100);
                                response.addCookie(usernameCookie);
                                response.addCookie(passwordCookie);
                            }

                        } catch (Exception msg) {
                            Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, msg);
                        }
                        request.getRequestDispatcher("index").forward(request, response);
                        return;
                    } else {
                        message = "Your Email and Password is valid but got some unknown problem, Please contact to administrator";
                    }
                } else {
                    message = "Email or Password is not valid, Please try again";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            message = "Email or password is empty, Please try with valid input";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
