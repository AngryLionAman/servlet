<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="site.jsp" %>
<c:if test="${paramValues.MultipleSelectedTopic ne null and 
              param.userid ne null and not empty param.userid}">
    <c:forEach items="${paramValues['MultipleSelectedTopic']}" var="t">
        <sql:update dataSource="jdbc/mydatabase" var="save_topic">
            insert into topic_followers_detail(topic_id,user_or_followers_id) values(?,?);
            <sql:param value="${t}"/>
            <sql:param value="${param.userid}"/>
        </sql:update>       
    </c:forEach>
</c:if>
<c:redirect url="/"/>