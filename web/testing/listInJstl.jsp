<%-- 
    Document   : listInJstl
    Created on : 17 Oct, 2019, 11:55:27 AM
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
        <h1>Hello World!</h1>
        <c:set value="${null}" var="list_s"/>
        <jsp:useBean id="list_s" class="java.util.ArrayList" scope="session" />
        <a href="jstlv.jsp">Click here</a>
        <c:catch var="m">

            <c:set var="n" value="${list_s.add(6)}" />
            <c:set var="ny" value="${list_s.add(7)}" />
            <c:set var="nd" value="${list_s.add(8)}" />
            <c:set var="nd" value="${list_s.add(9)}" />
            <c:set var="nd" value="${list_s.add(10)}" />
            <c:set var="nd" value="${list_s.add(11)}" />

            <c:if test="${not empty list_s}">
                <c:if test="${not list_s.contains(6)}">
                    ${list_s.add(e)}
                </c:if>
            </c:if>
            Session scope:-     <c:out value="${list_s}"/><br><br>

        </c:catch>
        <c:if test="${m ne null}">
            ${m}
        </c:if>

    </body>
</html>
