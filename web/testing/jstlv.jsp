<%-- 
    Document   : jstlv
    Created on : 17 Oct, 2019, 1:04:28 PM
    Author     : AngryLion
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is new page</h1>
        <%!
            public String remove(String word) {
                word = word.trim();
                word = word.replaceAll("[^0-9]", "");
                return word;
            }

        %>

        <%

            String name = String.valueOf(session.getAttribute("userIdForNotification"));
            out.println(session.getAttribute("userIdForNotification").toString().replaceAll("[^0-9]", "").isEmpty());
            out.println("<br><br>");
            out.println(name);
            out.println("<br><br>");
            String[] name1 = name.split(" ");
            for (String obj : name1) {
                out.print(remove(obj));
                out.print("<br>");
            }


        %>
    </body>
</html>
