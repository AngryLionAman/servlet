<%-- 
    Document   : question_lenght
    Created on : Jul 19, 2019, 2:43:08 PM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../site.jsp" %>
<!DOCTYPE html>
<html>
    <head>
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
    </head>
    <body>

        <h2>HTML Table</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Question</th>
                <th>Question length</th>
                <th>Question char length</th>
            </tr>
            <c:catch var="ex">
                <sql:query dataSource="${dbsource}" var="length">
                    select q_id,question,length(question)as na,char_length(question)as cl from question;
                </sql:query>
                <c:forEach items="${length.rows}" var="q">
                    <tr>
                        <td> <c:out value="${q.q_id}"/></td>
                        <td><c:out value="${fn:replace(q.question,' ','+')}"/></td>
                        <td><c:out value="${q.na}"/></td>
                        <td> <c:out value="${q.cl}"/><br><br></td>
                    </tr>
                </c:forEach>
            </c:catch>
            <c:if test="${ex ne null}" >
                <c:out value="${ex}"/>
            </c:if>


        </table>

    </body>
</html>

