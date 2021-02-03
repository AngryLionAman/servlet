<%-- 
    Document   : jstl session
    Created on : Jul 10, 2019, 2:58:36 PM
    Author     : inquiryhere.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@include file="bd.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <script>
            function confirmGo(m,u) {
                if ( confirm(m) ) {
                    window.location = u;
                }
            }
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:if test="${sessionScope.Session_id_of_user ne null}">
           This is session variale number  <c:out value="${sessionScope.Session_id_of_user}"></c:out><br>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.Session_id_of_user ne null}">
            <sql:query dataSource="${dbsource}" var="result">
                SELECT * FROM 
                newuser where id = ?;
                <sql:param value="${sessionScope.Session_id_of_user}"></sql:param>
            </sql:query>
        </c:when>
        <c:otherwise>
            <br><c:out value="User session is not active"></c:out>
        </c:otherwise>
    </c:choose>
    <table border="1" width="40%">
        <caption>Userlist List</caption>
        <tr>
            <th>User ID</th>
            <th>User Name</th>
            <th>email</th>
            <th colspan="2">Action</th>
        </tr>
         <tr>
                <td><c:out value="${row.id}"/>1</td>
                <td><c:out value="${row.firstname}"/></td>
                <td><c:out value="${row.email}"/></td>
                <td><a href="update.jsp?id=<c:out value="${row.id}"/>">Update</a></td>
                <td><a href="javascript:confirmGo('Sure to delete this record?','deletedb.jsp?id=<c:out value="${row.id}"/>')">Delete</a></td>

            </tr>
        <c:forEach var="row" items="${result.rows}">
            <tr>
                <td><c:out value="${row.id}"/>1</td>
                <td><c:out value="${row.firstname}"/></td>
                <td><c:out value="${row.email}"/></td>
                <td><a href="update.jsp?id=<c:out value="${row.id}"/>">Update</a></td>
                <td><a href="javascript:confirmGo('Sure to delete this record?','deletedb.jsp?id=<c:out value="${row.id}"/>')">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</body>
</html>
