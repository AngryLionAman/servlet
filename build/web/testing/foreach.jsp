<%-- 
    Document   : foreach
    Created on : Jul 18, 2019, 3:20:36 PM
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
        <c:set var="name">aman,kumar,bhadaura muer</c:set>
        <c:set var="count" value="0" scope="page" />
        <c:forEach var="name" varStatus="loop" items="${name}" >
            <c:out value="${name}"/>
            <c:out value="${loop.count}"/>
            <c:set var="count" value="${loop.count}" scope="page" />
        </c:forEach>
        <c:if test="${count gt 0}">
            <c:out value="loop execuated ${count} times"/>
        </c:if>
        <c:if test="${count eq 0}">
            <c:out value="loop execuated ${count} times"/>
        </c:if>
    </body>
</html>
