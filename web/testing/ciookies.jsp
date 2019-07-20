<%-- 
    Document   : ciookies
    Created on : Jul 17, 2019, 11:28:02 AM
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
        <h1>Hello World! bro</h1>
        
            <c:set value="${4 eq 5}" var="status"/>
        <c:if test="${status ne null}">
            <c:out value="status is not null"/>
        </c:if>
        <c:if test="${status eq null}">
            <c:out value="status is null"/>
        </c:if>
        <c:if test="${empty status}">
            <c:out value="status is empty"/>
        </c:if>
        <c:if test="${not empty status}">
            <c:out value="status is not empty"/>
        </c:if>
        <c:out value="${status}"/>
    </body>
</html>
