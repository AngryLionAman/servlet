<%-- 
    Document   : returnList
    Created on : Jul 31, 2019, 11:06:51 AM
    Author     : inquiryhere.com
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.test.totalUser" %>
<%@page import="com.test.saveUser" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Total user detail !!!!!!</h1>
        <%
            try {
                totalUser obj1 = new totalUser();
                for (saveUser obj : obj1.totalUserDetails()) {
                    
                    out.println("<br><br>userEmail ->"+obj.getEmail());
                    out.println("<br>UserName ->"+obj.getUserName());
                    out.println("<br>UserId ->"+obj.getUserId());
                }
            } catch (Exception msg) {
                out.println(msg);
            }

        %>

    </body>
</html>
