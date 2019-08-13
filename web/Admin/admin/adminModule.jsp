<%-- 
    Document   : adminModule
    Created on : Aug 12, 2019, 11:19:28 AM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean class="com.admin.adminUserDetail" id="user" scope="page"/>
<c:if test="${sessionScope.adminUserId eq null}">
    <c:redirect url="visit.jsp?msg=Session is not valid"/>
</c:if>
<c:if test="${sessionScope.adminUserId ne null}">    
    <c:forEach items="${user.userDetail(sessionScope.adminUserId)}" var="u">
        <c:set value="${u.userId}" var="id" scope="page"/>
        <c:set value="${u.userName}" var="userName" scope="page"/>
        <c:set value="${u.fullName}" var="fullName" scope="page"/>
        <c:set value="${u.eMail}" var="eMail" scope="page"/>
    </c:forEach>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Module</title>
    </head>
    <body>
        <h1>Hello, ${fullName} <a href="<%=request.getContextPath()%>/Logout">Logout</a></h1>
        <a href="unanswerQuestion.jsp">Unanswered question</a>
    </body>
</html>
