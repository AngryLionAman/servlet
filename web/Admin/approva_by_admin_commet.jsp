<%-- 
    Document   : approva_by_admin_commet
    Created on : 3 Jan, 2020, 12:26:00 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<c:if test="${sessionScope.adminUserId eq null}">
    <c:redirect url="visit.jsp?msg=Session is not valid"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
        </style>
        <title>Comments for approval</title>
    </head>
    <body>
        <h1>Hello,   ${sessionScope.userName}. Comment for approval !  
            <a href="<%=request.getContextPath()%>/Admin/approva_by_admin_commet.jsp">Refresh</a> 
            <a href="<%=request.getContextPath()%>/Admin/adminModule.jsp">HOME</a>
        </h1>


        <c:if test="${message ne null}">
            <font style="color: green;font-size: 30px;text-align: center;">${message}</font><br><br>
        </c:if>


        <sql:query dataSource="jdbc/mydatabase" var="c" >
            select * from comments where approved_by_admin = 0 or approved_by_user = 0;
        </sql:query>

        <table> 
            <tr>
                <th>unique_id</th>
                <th>comment_type</th>
                <th>content_id</th>
                <th>Comment</th>                    
                <th>approved_by_user</th>
                <th>approved_by_admin</th>
                <th>Time</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${c.rows}" var="t">
                <tr>
                    <td>${t.unique_id}</td>
                    <td>${t.comment_type}</td>
                    <td>${t.content_id}</td>
                    <td>${t.comments}</td>
                    <td>${t.approved_by_user}</td>
                    <td>${t.approved_by_admin}</td>
                    <td>${t.time}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/commet_approval?commet_id=${t.unique_id}&action=accept">Accept</a>&nbsp;
                        <a href="<%=request.getContextPath()%>/commet_approval?commet_id=${t.unique_id}&action=delete">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
