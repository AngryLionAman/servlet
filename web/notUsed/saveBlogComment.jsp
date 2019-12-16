<%-- 
    Document   : saveBlogComment
    Created on : 3 May, 2019, 9:20:59 AM
    Author     : AngryLion
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="site.jsp" %>
<c:catch var="ex">
    <c:if test="${param.blog_id ne null and
                  not empty param.blog_id and 
                  param.bloggerUserId ne null and 
                  not empty param.bloggerUserId and 
                  param.comments ne null and 
                  not empty param.comments}">
        <c:out value="inside the if blog"/>
        <c:set var="userId" value="${param.sessionUserId}"/>
        <c:if test="${param.sessionUserId eq null or empty param.sessionUserId}">
            <c:set var="userId" value="0"/>
        </c:if>
        <sql:update var="saveComments" dataSource="jdbc/mydatabase">
            INSERT INTO comments (user_id,blog_id,comments)VALUES(?,?,?);
            <sql:param value="${userId}"/>
            <sql:param value="${param.blog_id}"/>
            <sql:param value="${param.comments}"/>
        </sql:update>
        <sql:update dataSource="jdbc/mydatabase" var="saveNotification">
            INSERT INTO notification (user_id,notification_type,followers_id,blog_id ) VALUES (?,?,?,?);
            <sql:param value="${param.bloggerUserId}"/>
            <sql:param value="comment_on_Blog"/>
            <sql:param value="${userId}"/>
            <sql:param value="${param.blog_id}"/>
        </sql:update>

    </c:if>
</c:catch>
<c:if test="${ex ne null}">
    ${ex}
</c:if>
<c:redirect url="D_Blog.jsp?sub=${param.blogSub}&Blog_Id=${param.blog_id}"/>
