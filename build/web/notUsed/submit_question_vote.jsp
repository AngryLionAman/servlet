<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="site.jsp" %>
<c:if test="${param.question_id ne null and
              not empty param.question_id and 
              param.action ne null and 
              not empty param.action}">
    <c:if test="${param.action eq 'upvote'}">
        <sql:update dataSource="jdbc/mydatabase" var="upvote">
            UPDATE question SET vote = vote+1 WHERE q_id =?;
            <sql:param value="${param.question_id}"/>
        </sql:update>
    </c:if>
    <c:if test="${param.action eq 'downvote'}">
        <sql:update dataSource="jdbc/mydatabase" var="downvote">
            UPDATE question SET vote = vote-1 WHERE q_id =?;
            <sql:param value="${param.question_id}"/>
        </sql:update>
    </c:if>
</c:if>