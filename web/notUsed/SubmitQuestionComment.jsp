<%-- 
    Document   : SubmitQuestionComment
    Created on : 16 Apr, 2019, 11:12:43 AM
    Author     : AngryLion
--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta http-equiv="content-type" content="text/html" charset="utf-8">
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<c:if test="${not empty param.session_active_user_id and not empty param.id_of_user_who_posted_question and 
              not empty param.question_id and not empty param.comments and not empty param.question}">
    <c:out value="${param.session_active_user_id}"/><br>
    <c:out value="${param.id_of_user_who_posted_question}"/><br>
    <c:out value="${param.question_id}"/><br>
    <c:out value="${param.comments}"/><br>
    <c:out value="${param.question}"/><br>
    <sql:update dataSource="jdbc/mydatabase" var="insert_comment" >
        INSERT INTO comments (user_id,q_id,comments)VALUES(?,?,?);
        <sql:param value="${param.session_active_user_id}"/>
        <sql:param value="${param.question_id}"/>
        <sql:param value="${param.comments}"/>
    </sql:update>
    <sql:update dataSource="jdbc/mydatabase" var="notificaiton">
        INSERT INTO notification (user_id,notification_type,followers_id,question_id ) VALUES (?,?,?,?);
        <sql:param value="${param.id_of_user_who_posted_question}"/>
        <sql:param value="comment_on_question"/>
        <sql:param value="${param.session_active_user_id}"/>
        <sql:param value="${param.question_id}"/>
    </sql:update>
    <c:redirect url = "/Answer.jsp?q=${fn:replace(param.question,' ','-')}&Id=${param.question_id}"/>
</c:if>