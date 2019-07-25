<%-- 
    Document   : dispresult
    Created on : Jul 23, 2019, 10:32:20 AM
    Author     : inquiryhere.com
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="rowsPerPage" value="20" />
        <c:set var="pageNumber" value="1" />
        <c:if test="${param.pageNumber ne null}">
             <c:set var="pageNumber" value="${param.pageNumber}" />
        </c:if>
        
         <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
        <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
        <sql:query dataSource="jdbc/mydatabase" var="categories" scope="page">
            select unique_id,topic_name from topic;
        </sql:query>    
       
        <c:set var="a">
            <fmt:formatNumber value="${categories.rowCount/rowsPerPage}" maxFractionDigits="0"/>
        </c:set>

        <c:set var="b" value="${categories.rowCount/rowsPerPage}" />

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
        
    <center>
        <table border="1" width="40%">   
            <td>id</td>
            <td>Topic</td>
            <c:forEach items="${categories.rows}" var="value" begin="${start}" end="${stop}">
                <tr>
                    <td>${value.unique_id}</td>
                    <td>${value.topic_name}</td>
                </tr>
            </c:forEach>           
        </table>

        <%--For displaying Previous link --%>
        <c:if test="${pageNumber gt 1}">
            <a href="dispresult.jsp?pageNumber=${pageNumber - 1}">Previous</a>
        </c:if>
        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${i!=pageNumber}">
                    <a href="dispresult.jsp?pageNumber=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                </c:when>
                <c:otherwise>
                    <c:out value="${i}"/>
                </c:otherwise>        
            </c:choose>       
        </c:forEach>  
        <%--For displaying Next link --%>
        <c:if test="${pageNumber lt numberOfPages}">
            <a href="dispresult.jsp?pageNumber=${pageNumber + 1}">Next</a>
        </c:if>
    </center>
</body>
</html>
