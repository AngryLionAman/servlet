<%-- 
    Document   : topic
    Created on : Aug 8, 2019, 2:25:31 PM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Topic List</title>
    </head>
    <body>
        <c:if test="${param.msg ne null and not empty param.msg}">
        <center><h1 style="color: green;">${param.msg}</h1></center>
        </c:if>
         <h1>Hello,   ${sessionScope.userName}
            <a href="adminModule.jsp">Home</a>,  <a href="<%=request.getContextPath()%>/Logout">Logout</a>,  <a href="<%=request.getContextPath()%>/Admin/topic.jsp">Refresh</a>
        </h1>
        <c:catch var="ex">
            <c:set var="rowsPerPage" value="10" />
            <c:set var="pageNumber" value="1" />
            <c:if test="${param.p ne null and not empty param.p}">
                <c:set var="pageNumber" value="${param.p}" />
            </c:if>

            <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
            <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
            <sql:query dataSource="jdbc/mydatabase" var="topic">
                select * from topic;
            </sql:query>
            <c:set var="a">
                <fmt:formatNumber value="${topic.rowCount/rowsPerPage}" maxFractionDigits="0"/>
            </c:set>

            <c:set var="b" value="${topic.rowCount/rowsPerPage}" />

            <c:choose>
                <c:when test="${a==0}">
                    <c:set var="numberOfPages" value="1" scope="page"/>   
                </c:when>

                <c:when test="${b>a}">
                    <c:set var="xxx" value="${b%a}"/>
                    <c:if test="${xxx>0}">
                        <c:set var="numberOfPages" value="${b-xxx+1}" scope="page"/>   
                    </c:if>
                </c:when>

                <c:when test="${a>=b}">
                    <c:set var="numberOfPages" value="${a}" scope="page"/>    
                </c:when>
            </c:choose>
            <table> 
                <tr>
                    <th>unique_id</th>
                    <th>topic_name</th>
                    <th>Crawl</th>
                    <th>desc_hindi</th>
                    <th>desc_english</th>
                    <th>image_url</th>
                    <th>topcEdit</th>
                </tr>
                <c:forEach items="${topic.rows}" var="t" begin="${start}" end="${stop}">
                    <tr>
                        <td>${t.unique_id}</td>
                        <td>${t.topic_name}</td>
                        <td>${t.crawl}</td>
                        <td>${t.desc_hindi}</td>
                        <td>${t.desc_english}</td>
                        <td>${t.image_url}</td>
                        <td><a href="editTopic.jsp?id=${t.unique_id}&p=${pageNumber}">Edit</a></td>
                    </tr>
                </c:forEach>

            </table>
            <%--For displaying Previous link --%>
            <c:if test="${pageNumber gt 1}">
                <a href="topic.jsp?p=${pageNumber - 1}">Previous</a>
            </c:if>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${i!=pageNumber}">
                        <a href="topic.jsp?p=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                    </c:when>
                    <c:otherwise>
                        <b style="color: red;"><c:out value="${i}"/></b>
                    </c:otherwise>        
                </c:choose>       
            </c:forEach>  
            <%--For displaying Next link --%>
            <c:if test="${pageNumber lt numberOfPages}">
                <a href="topic.jsp?p=${pageNumber + 1}">Next</a>
            </c:if>
        </c:catch>
        <c:if test="${ex ne null}">
            ${ex}
        </c:if>

    </body>
</html>
