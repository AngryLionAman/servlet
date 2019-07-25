<%-- 
    Document   : xml
    Created on : Jul 22, 2019, 11:12:51 AM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:catch var="ex">
            <sql:query var="name" dataSource="jdbc/mydatabase">
                select * from newuser;
            </sql:query>
            <c:forEach var="n" items="${name.rows}" end="10" > 
                <c:out value="${n.firstname}"/><br>
            </c:forEach>
        </c:catch>
        <c:if test="${ex eq null}">
            <c:out value="${ex}"/>
        </c:if>

    </body>
</html>
