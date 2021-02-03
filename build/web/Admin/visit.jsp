<%-- 
    Document   : login
    Created on : 28 Feb, 2019, 6:48:50 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Login Admin</title>
    </head>
    <body>

    <center>
        <h1>Hello Admin<br>
            <font style="color: red;">
            <c:if test="${message ne null}">
                ${message}
            </c:if>
            </font>           
        </h1>
        <form action="<%=request.getContextPath()%>/login" method="post">
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
                    <th>Security code</th>
                    <th><input type="password" name="code"></th>
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

