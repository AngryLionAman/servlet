<%-- 
    Document   : sharedSameConnection
    Created on : Aug 2, 2019, 2:40:52 PM
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
        <c:catch var="ex" >
            <sql:transaction dataSource="jdbc/mydatabase">
                <sql:query var="name" >
                    select * from newuser limit 5;
                </sql:query>
                <sql:query var="user">
                    select * from newuser limit 10,5;
                </sql:query>

                <c:forEach var="a" items="${name.rows}">
                    ${a.id}-${a.username}
                </c:forEach><br><br>
                <c:forEach var="b" items="${user.rows}">
                    ${b.id}-${b.username}-${b.firstname}
                </c:forEach>
            </sql:transaction>
        </c:catch>
        <c:if test="${ex ne null}">
            ${ex}
        </c:if>

    </body>
</html>
