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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Admin Module</title>
    </head>
    <body>
        <h1>Hello, ${sessionScope.userName} <a href="<%=request.getContextPath()%>/Logout">Logout</a></h1>
        <ol>
            <li><a href="unanswerQuestion.jsp">Unanswered question</a></li><br><br>
            <li><a href="postQuestion.jsp">Post a question</a></li><br><br>
            <li><a href="topic.jsp">Update the Topic</a></li><br><br>
            <li><a href="modifyQuestion.jsp">Update question and related tag</a></li><br><br>
            <li><a href="advertise.jsp">Add a product</a></li><br><br>
            <li><a href="<%=request.getContextPath()%>/approval_request_for_admin_servlet">Question modification</a></li><br><br>
            <li><a href="approva_by_admin_commet.jsp">Comment approval</a></li><br><br>
            <li><a href="approva_by_admin_answer.jsp">Answer approval</a></li><br><br>
        </ol>
    </body>
</html>
