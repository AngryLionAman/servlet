<%-- 
    Document   : saveTopicDetail
    Created on : Aug 8, 2019, 3:22:05 PM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Saving Topic Detail</title>
    </head>
    <body>
        <h1>Updating the tag :  ${param.topic_name}!</h1>
        <c:catch var="ex">
            ${param.topicId}<br>
            ${param.topic_name}<br>
            ${param.topic_desc_hindi}<br>
            ${param.topic_desc_eng}<br>
            ${param.topic_url}<br>
            <c:if test="${param.topic_name ne null}">
                <sql:update var="name" dataSource="jdbc/mydatabase">
                    update topic set topic_name = ? ,desc_hindi=? ,desc_english=? ,image_url=? where 
                    unique_id = ?;

                    <sql:param value="${param.topic_name}"/>
                    <sql:param value="${param.topic_desc_hindi}"/>
                    <sql:param value="${param.topic_desc_eng}"/>
                    <sql:param value="${param.topic_url}"/>
                    <sql:param value="${param.topicId}"/>
                </sql:update>
                <c:if test="${name>=1}">
                    Data has been saved..
                </c:if>                
            </c:if>
        </c:catch>
        <c:if test="${ex ne null}">
            ${ex}
        </c:if>
        <c:redirect url="topic.jsp?p=${param.p}"/>
    </body>
</html>
