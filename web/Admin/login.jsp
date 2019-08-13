<%-- 
    Document   : login
    Created on : 28 Feb, 2019, 6:48:50 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="page" class="com.security.validateUser"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Admin</title>
    </head>
    <body>

    <center>
        <h1>Hello Admin 
            <c:if test="${param.msg ne null and not empty param.msg}">
                : ${param.msg}
            </c:if>
        </h1>
        <form action="login.jsp" method="post">
            <table>
                <tr>
                    <th>UserName</th>
                    <th><input type="email" name="email"></th>
                </tr>
                <tr>
                    <th>Password</th>
                    <th><input type="password" name="password"></th>
                </tr>
                <tr>
                    <th></th>
                    <th><input type="submit" value="Login"></th>
                </tr>

            </table>
        </form>        
    </center>
</body>
</html>
<%
    if ((request.getParameter("email") != null && !request.getParameter("email").isEmpty()) && (request.getParameter("password") != null && !request.getParameter("password").isEmpty())) {
        String eMail = request.getParameter("email").trim();
        String passWord = request.getParameter("password");
        user.validateAdminUser(eMail, passWord, request, response);
    }
%>
