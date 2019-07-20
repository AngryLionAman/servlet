<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
<c:if test="${param.userid ne null and param.question ne null and param.tag_of_question ne null}">
    <c:catch var="exception">
        <sql:update dataSource="${dbsource}" var="insert_question">
            insert into question(id,question,vote) values(?,?,?);
            <sql:param value="${param.userid}"/>
            <sql:param value="${fn:trim(param.question)}"/>
            <sql:param value="0"/>
        </sql:update>
        <sql:update dataSource="${dbsource}" var="updated_notification_list">
            INSERT INTO notification(notification_type,followers_id)VALUES(?,?);
            <sql:param value="submit_question"/>
            <sql:param value="${param.userid}"/>
        </sql:update>
        <sql:query dataSource="${dbsource}" var="get_question_id">
            select * from question where question= ?;
            <sql:param value="${fn:trim(param.question)}" />
        </sql:query>
        <c:forEach items="${get_question_id.rows}" var="q_id">
            <c:set var="question_id" value="${q_id.q_id}" scope="page" />
        </c:forEach>
        <c:set var="tag"><c:out value="${fn:trim(param.tag_of_question)}"/></c:set>
        <c:forTokens items="${tag}" delims="," var="s_tag">
            <sql:query dataSource="${dbsource}" var="tag_found">
                select count(*) as cnt from topic where lower(topic_name) = ? limit 1;
                <sql:param value="${fn:trim(s_tag)}"/>
            </sql:query>
            <c:forEach items="${tag_found.rows}" var="t">
                <c:set scope="page" var="tac" value="${t.cnt}"/>
            </c:forEach>
            <c:if test="${tac eq 0}">
                <sql:update dataSource="${dbsource}" var="insert_tag">
                    insert into topic(topic_name) values(?);
                    <sql:param value="${fn:trim(s_tag)}"/>
                </sql:update>
            </c:if>
            <sql:query dataSource="${dbsource}" var="get_id_t">                
                select unique_id from topic where topic_name = ? limit 1;
                <sql:param value="${fn:trim(s_tag)}"/>
            </sql:query>    
            <c:forEach items="${get_id_t.rows}" var="upload_t_id_with_q_id">
                <sql:update dataSource="${dbsource}" var="save_t_Tag_with_q_id">
                    insert into question_topic_tag(question_id,tag_id) values(?,?);
                    <sql:param value="${question_id}" />
                    <sql:param value="${upload_t_id_with_q_id.unique_id}" />
                </sql:update>
            </c:forEach>
        </c:forTokens>
    </c:catch>
</c:if>
<c:if test = "${exception != null}">
    <p>The exception is : ${exception} <br />
        There is an exception: ${exception.message}</p>
</c:if>
<c:redirect url = "/"/>