<%-- 
    Document   : approva_by_admin_answer
    Created on : 3 Jan, 2020, 3:17:04 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Answer for approval</title>
    </head>
    <body>
        <h1>Hello,   ${sessionScope.userName}. Answer for approval !  
            <a href="<%=request.getContextPath()%>/Admin/approva_by_admin_answer.jsp">Refresh</a>
            <a href="<%=request.getContextPath()%>/Admin/adminModule.jsp">HOME</a>
        </h1>

        <c:if test="${message ne null}">
            <font style="color: green;font-size: 30px;text-align: center;">${message}</font><br><br>
        </c:if>

        <sql:query dataSource="jdbc/mydatabase" var="c" >
            select * from answer where approved_by_admin = 0;
        </sql:query>

        <table> 
            <tr>
                <th>question_id</th>
                <th>answer_id</th>
                <th>answer</th>
                <th>answer_by</th>                    
                <th>approved_by_user</th>
                <th>approved_by_admin</th>
                <th>Time</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${c.rows}" var="t">
                <tr>
                    <td>${t.q_id}</td>
                    <td>${t.a_id}</td>
                    <td>${t.answer}</td>
                    <td>${t.answer_by_id}</td>
                    <td>${t.approved_by_user}</td>
                    <td>${t.approved_by_admin}</td>
                    <td>${t.postedtime}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/admin_answer_approval?answer_id=${t.a_id}&action=accept">Accept</a>&nbsp;
                        <a href="<%=request.getContextPath()%>/admin_answer_approval?answer_id=${t.a_id}&action=delete">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
