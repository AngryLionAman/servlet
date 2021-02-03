<%-- 
    Document   : session
    Created on : 21 Dec, 2019, 5:47:37 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Session !</h1>
        
    <c:if test="${sessionScope.Session_id_of_user eq null}">
        Session is null<br> next line <br>
        <c:set value="1" scope="session" var="Session_id_of_user"/>
        Session is null
    </c:if>
    <c:if test="${sessionScope.Session_id_of_user ne null}">
        Session is not null
    </c:if>
    </body>
</html>
