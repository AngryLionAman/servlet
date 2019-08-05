<%-- 
    Document   : poolTesting
    Created on : Aug 3, 2019, 5:24:55 PM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.test.usePool" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <br><br>
        <jsp:useBean class="com.index.comments" id="comm"/>
        <c:catch var="ex">
            <c:forEach items="${comm.commentsOnQuestion(764)}" var="c">
                this :-
                ${c.commentId}<br>
                ${c.comment} <br>
                ${c.time} <br>
                ${c.userId} <br>
                ${c.userFullName} <br>
                ${c.userUserName} <br>
            </c:forEach>
        </c:catch>
        <c:if test="${ex ne null}">
            ${ex}
        </c:if>
    </body>
</html>
