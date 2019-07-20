<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page isErrorPage="true" errorPage="error.jsp" %>
<%@include file="bd.jsp" %>
 
<html>
    <head>
        <title>SELECT Operation</title>
        <script>
            function confirmGo(m,u) {
                if ( confirm(m) ) {
                    window.location = u;
                }
            }
        </script>
    </head>
    <body
        <sql:query dataSource="${dbsource}" var="result">
            SELECT * FROM newuser where id = ?;
            <sql:param value="${164}"></sql:param>
        </sql:query>
    <center>
        <form>
            <table border="1" width="40%">
                <caption>Userlist List</caption>
                <tr>
                    <th>User ID</th>
                    <th>User Name</th>
                    <th>email</th>
                    <th colspan="2">Action</th>
                </tr>
                <c:forEach var="row" items="${result.rows}">
                    <tr>
                        <td><c:out value="${row.id}"/></td>
                        <td><c:out value="${row.firstname}"/></td>
                        <td><c:out value="${row.email}"/></td>
                        <td><a href="update.jsp?id=<c:out value="${row.id}"/>">Update</a></td>
                        <td><a href="javascript:confirmGo('Sure to delete this record?','deletedb.jsp?id=<c:out value="${row.id}"/>')">Delete</a></td>
                         
                    </tr>
                </c:forEach>
            </table>
        </form>
        <a href="index.jsp">Go Home</a>
    </center>
</body>
</html>