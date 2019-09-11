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

import com.connect.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            try {
                DatabaseConnection dc = DatabaseConnection.getInstance();
                Connection con = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                boolean userValidated = false;
                int userId = 0;
                try {
                    con = dc.getConnection();
                    String sql = "select id, password from newuser where email = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, email);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        userId = rs.getInt("id");
                        String pass = rs.getString("password");
                        if (password.equals(pass)) {
                            userValidated = true;
                        }
                    }

                } catch (SQLException msg) {
                    throw msg;
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException msg) {

                        }
                    }
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (SQLException msg) {

                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException msg) {

                        }
                    }
                    try {
                        if (userValidated) {
                            HttpSession session = request.getSession();
                            session.setAttribute("email", email);
                            session.setAttribute("Session_id_of_user", userId);
                            session.setMaxInactiveInterval(60);
                            try {
                                Cookie usernameCookie = new Cookie("usernamecookie", email);
                                Cookie passwordCookie = new Cookie("passwordcookie", password);
                                usernameCookie.setMaxAge(24 * 60 * 60 * 100);
                                passwordCookie.setMaxAge(24 * 60 * 60 * 100);
                                response.addCookie(usernameCookie);
                                response.addCookie(passwordCookie);
                            } catch (Exception msg) {
                                throw msg;
                            }
                            if (request.getParameter("URL") != null) {
                                request.getRequestDispatcher(request.getParameter("URL")).forward(request, response);
                            } else {
                                response.sendRedirect("index.jsp");
                            }
                        } else {
                            response.sendRedirect("login.jsp?ref=valid");
                        }
                    } catch (Exception msg) {

                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(validate.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
