<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("usernamecookie")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("passwordcookie")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
%>
<%
    session.setAttribute("email", null);
    session.setAttribute("Session_id_of_user", null);
    session.invalidate();
    response.sendRedirect("login.jsp?ref=logout");
%>
