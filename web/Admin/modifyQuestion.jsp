<%-- 
    Document   : modifyQuestion
    Created on : 23 Aug, 2019, 2:18:01 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="com.answer.SEO" id="tag" scope="page" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Question list</title>
    </head>
    <body>
        <c:if test="${param.msg ne null}">
        <center><h1 style="color: green; font-size: 35px;">${param.msg}</h1></center> 
        </c:if>
        <h1>Hello,   ${sessionScope.userName} <br> Select question to modify
            <a href="adminModule.jsp">Home</a>, <a href="<%=request.getContextPath()%>/Logout">Logout</a>
            , <a href="<%=request.getContextPath()%>/Admin/modifyQuestion.jsp">Refresh</a>
        </h1>
        <c:set var="rowsPerPage" value="20" />
        <c:set var="pageNumber" value="1" />
        <c:if test="${param.p ne null}">
            <c:set var="pageNumber" value="${param.p}" />
        </c:if>

        <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
        <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
        <sql:query dataSource="jdbc/mydatabase" var="question">
            select id,q_id,question from question order by q_id desc;
        </sql:query>
        <c:set var="a">
        <fmt:formatNumber value="${question.rowCount/rowsPerPage}" maxFractionDigits="0"/>
    </c:set>

    <c:set var="b" value="${question.rowCount/rowsPerPage}" />

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
    <table border="1">
        <tr>
            <th>U_id</th>
            <th>Q_id</th>
            <th>Question</th>
            <th>related tag</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${question.rows}" var="q" begin="${start}" end="${stop}">
            <tr>                
                <td>${q.id}</td>
                <td>${q.q_id}</td>
                <td>${q.question}</td>
                <td> <c:forEach items="${tag.getQuestionTag(q.q_id)}" var="t">
                        ${t},&nbsp;
                    </c:forEach> </td>
                <td><a href="editQuestion.jsp?qId=${q.q_id}&p=${pageNumber}">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${pageNumber gt 1}">
        <a href="modifyQuestion.jsp?p=${pageNumber - 1}">Previous</a>
    </c:if>
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${i!=pageNumber}">
                <a href="modifyQuestion.jsp?p=<c:out value="${i}"/>"><c:out value="${i}"/></a>
            </c:when>
            <c:otherwise>
                <b style="color: red;"><c:out value="${i}"/></b>
            </c:otherwise>        
        </c:choose>       
    </c:forEach>  
    <%--For displaying Next link --%>
    <c:if test="${pageNumber lt numberOfPages}">
        <a href="modifyQuestion.jsp?p=${pageNumber + 1}">Next</a>
    </c:if>

</body>
</html>
