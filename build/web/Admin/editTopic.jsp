<%-- 
    Document   : editTopic
    Created on : Aug 8, 2019, 2:47:03 PM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.adminUserId eq null}">
    <c:redirect url="visit.jsp?msg=Session is not valid"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Edit topic here</title>
    </head>
    <body>
        <h1>Hello, <c:if test="${sessionScope.userName ne null}">
                ${sessionScope.userName}
            </c:if>
            <a href="adminModule.jsp">Home</a>  <a href="topic.jsp?p=${param.p}">Back</a> <a href="<%=request.getContextPath()%>/Logout">Logout</a>
        </h1>
        <sql:query dataSource="jdbc/mydatabase" var="topic_d">
            select * from topic where unique_id = ?;
            <sql:param value="${param.id}"/>
        </sql:query>
        <c:forEach items="${topic_d.rows}" var="t">
            <h1>Edit Topic : ${t.topic_name}</h1>
            <h3>Topic Id :- <b style="color: green;">${t.unique_id}</b></h3>
            <form action="saveTopicDetail.jsp" name="saveTopicDetail">            
                <input name="p" value="${param.p}" type="hidden"/>
                <input name="topicId" value="${t.unique_id}" type="hidden"/>
                <h3>Topic Name :- <input required="" size="60" style="color: green;" name="topic_name" type="text" value="${t.topic_name}"/></h3>
                <h3>Image Url:- <input size="60" style="color: green;" name="topic_url" type="text" value="${t.image_url}"/>
                    <img src="<c:out value="${t.image_url}"/>" alt="Image" height="100" width="100"></h3>
                <h3>Desc Hindi :- <textarea rows="10" cols="60" name="topic_desc_hindi" placeholder="This is description in hindi">${t.desc_hindi}</textarea></h3>
                <h3>Desc English :- <textarea rows="10" cols="60" name="topic_desc_eng" placeholder="This is description in englsih">${t.desc_english}</textarea></h3>
                Crawl Status status :
                <select name="crawl">
                    <option value="1" selected="">Display</option>
                    <option value="0">Not Display</option>
                </select><br><br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Update">
            </form>    
        </c:forEach>
        <br><br>
        <sql:query dataSource="jdbc/mydatabase" var="relatedQuestion">
            select q.id,q.question,q.q_id from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id=?;
            <sql:param value="${param.id}"/>
        </sql:query>
        <table border="1">
            <tr>
                <th>Question ID</th>
                <th>Question</th>
                <th>User ID(Who posted)</th>
                <th>related tag</th>
            </tr>
            <c:forEach items="${relatedQuestion.rows}" var="q">
                <tr>
                    <td>${q.q_id}</td>
                    <td>${q.question}</td>
                    <td>${q.id}</td>
                    <td>
                        <sql:query dataSource="jdbc/mydatabase" var="t">
                            SELECT topic.unique_id AS tag_id, topic.topic_name AS topic_name FROM topic INNER JOIN question_topic_tag ON topic.unique_id = question_topic_tag.tag_id WHERE question_id = ? AND tag_id IS NOT NULL ORDER BY tag_id;
                            <sql:param value="${q.q_id}"/>
                        </sql:query> 
                        <c:forEach items="${t.rows}" var="t">
                            ${t.topic_name},&nbsp;
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br><br>
        <form name="delete" action="<%=request.getContextPath()%>/deleteTopic" method="get">
            TopicId:-  <input type="text" name="topicId" value="${param.id}">
            PageNo:-   <input type="text" name="pageNo" value="${param.p}">
            <input type="submit" value="delete">
        </form>
    </body>
</html>
