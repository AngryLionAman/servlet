<%-- 
    Document   : this
    Created on : Jul 15, 2019, 11:35:21 AM
    Author     : inquiryhere.com
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
        <h1>Hello World!</h1>
        <br><c:out  value="this message is printed by jstl out tag"/>
        <jsp:useBean id="fun" class="com.math.add" scope="page"/><br>
        <c:out value="${fun.add(10, 2)}"/>
    </body>
</html>
