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
        <meta name="robots" content="noindex, nofollow" />
        <title>Login Admin</title>
    </head>
    <body>

    <center>
        <h1>Hello Admin 
            <c:choose>
                <c:when test="${message ne null}">
                    <br><font style="color: red;">${message}</font>
                </c:when>
                <c:when test="${param.msg}">
                    <br><font style="color: red;">${param.msg}</font>
                </c:when>
            </c:choose>
        </h1>
        <h1>
            <%
                if ((request.getParameter("email") != null
                        && !request.getParameter("email").isEmpty())
                        && (request.getParameter("password") != null
                        && !request.getParameter("password").isEmpty())
                        && (request.getParameter("code") != null
                        && !request.getParameter("code").isEmpty())) {
                    String eMail = request.getParameter("email").trim();
                    String passWord = request.getParameter("password");
                    String code = request.getParameter("code");
                    if (code.equals("sampur")) {
                        user.validateAdminUser(eMail, passWord, request, response);
                    } else {
                        out.print("<font style='color: red';>Invalid credential</font>");
                    }
                } else {
                    //out.print("All input required");
                }
            %>
        </h1>
        <form action="<%=request.getContextPath()%>/Admin/visit.jsp" method="post">
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

