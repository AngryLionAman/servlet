<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="site.jsp" %>
<c:if test="${param.session_userid ne null and 
              not empty param.session_userid and 
              param.OnCommentUserId ne null and 
              not empty param.OnCommentUserId and 
              param.comments ne null and 
              not empty param.comments and 
              param.sub ne null and 
              not empty param.sub}">
      
    <sql:update dataSource="jdbc/mydatabase" var="commment">
        INSERT INTO comments (user_id,userprofileid,comments)VALUES(?,?,?);
        <sql:param value="${param.session_userid}"/>
        <sql:param value="${param.OnCommentUserId}"/>
        <sql:param value="${param.comments}"/>
    </sql:update>
        
    <sql:update dataSource="jdbc/mydatabase" var="noti">
        INSERT INTO notification (user_id,notification_type,followers_id) VALUES (?,?,?);
        <sql:param value="${param.OnCommentUserId}"/>
        <sql:param value="comment_on_Profile"/>
        <sql:param value="${param.session_userid}"/>
    </sql:update>
</c:if>
<c:redirect url = "profile.jsp?user=${param.firstname}&ID=${param.OnCommentUserId}"/>