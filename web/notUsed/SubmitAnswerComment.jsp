<%-- 
    Document   : SubmitAnswerComment
    Created on : 17 Apr, 2019, 7:43:49 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="site.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<c:if test="${not empty param.session_active_user_id and
              not empty param.answer_id and
              not empty param.question_id and
              not empty param.question and
              not empty param.comments and
              not empty param.sub }" >
    <sql:update dataSource="jdbc/mydatabase" var="save_comment" >
        INSERT INTO comments (user_id,ans_id,comments)VALUES(?,?,?);
        <sql:param value="${param.session_active_user_id}"/>
        <sql:param value="${param.answer_id}"/>
        <sql:param value="${param.comments}"/>
    </sql:update>
    <c:if test="${not empty param.id_of_user_who_posted_question and param.id_of_user_who_posted_question ne null}">
        <sql:update dataSource="jdbc/mydatabase" var="save_notification">
            INSERT INTO notification (user_id,notification_type,followers_id,question_id,ans_id ) VALUES (?,?,?,?,?);
            <sql:param value="${param.id_of_user_who_posted_question}"/>
            <sql:param value="comment_on_Answer"/>
            <sql:param value="${param.session_active_user_id}"/>
            <sql:param value="${param.answer_id}"/>
            <sql:param value="${param.answer_id}"/>
        </sql:update>
    </c:if>   
    <c:redirect url = "/Answer.jsp?q=${fn:replace(param.question,' ','-')}&Id=${param.question_id}"/>
</c:if>