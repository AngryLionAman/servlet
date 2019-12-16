<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="com.login.supportingFunctionLogin"%>
<%@page import="com.string.validateInput"%>
<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>

<%
    try {
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
                        session.setAttribute("Session_id_of_user", userId);
                        session.setMaxInactiveInterval(60);
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
                            out.println(msg);
                            Logger.getLogger("login validate").log(Level.SEVERE, null, msg);
                        }
                        //request.getRequestDispatcher("index").forward(request, response);
                        //return;
                    } else {
                        message = "Your Email and Password is valid but got some unknown problem, Please contact to administrator";
                    }
                } else {
                    message = "Email or Password is not valid, Please try again";
                }
            } catch (Exception msg) {
                out.println(msg);
            }
        } else {
            message = "Email or password is empty, Please try with valid input";
        }
        // request.setAttribute("message", message);
        //request.getRequestDispatcher("login.jsp").forward(request, response);

    } catch (Exception msg) {
        out.print(msg);
    }

%>