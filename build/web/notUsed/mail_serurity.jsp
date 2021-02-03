<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="site.jsp" %>
<c:if test="${not empty param.action and 
              param.action ne null and not 
              empty param.session_id_of_user and 
              param.session_id_of_user ne null}">
    <c:catch var="ex">
        <c:if test="${param.action eq 'hide'}">
            <sql:update dataSource="jdbc/mydatabase" var="hide_finction">
                UPDATE newuser SET email_s = '1' WHERE id=?;
                <sql:param value="${param.session_id_of_user}"/>
            </sql:update> 
        </c:if>
        <c:if test="${param.action eq 'show'}">
            <sql:update dataSource="jdbc/mydatabase" var="show_function">
                UPDATE newuser SET email_s = '0' WHERE id=?;
                <sql:param value="${param.session_id_of_user}"/>
            </sql:update>
        </c:if>
    </c:catch>
    <c:if test="${ex ne null}">
        <c:out value="${ex}"/>
    </c:if>

</c:if>