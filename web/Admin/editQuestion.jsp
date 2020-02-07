<%-- 
    Document   : editQuestion
    Created on : 23 Aug, 2019, 2:37:38 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.adminUserId eq null}">
    <c:redirect url="visit.jsp?msg=Session is not valid"/>
</c:if>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Edit question and tag</title>
    </head>
    <body>
        <h1>Hello,   ${sessionScope.userName} <br>Edit question and tag !!!
            <a href="<%=request.getContextPath()%>/Admin/adminModule.jsp">HOME</a>
        </h1>
        <h1>Question Details.. <a href="modifyQuestion.jsp?p=${param.p}"> Back </a></h1>

         <sql:query dataSource="jdbc/mydatabase" var="e_q" >
           select q.q_id as q_id,q.question as question,q.vote,q.total_view,q.posted_time,q.updated_time as date,user.id as userId,user.firstname as fullname,
           user.username,user.user_type,user.higher_edu from question q inner join newuser user on user.id = q.id WHERE q.q_id = ?;
            <sql:param value="${param.qId}"/>
        </sql:query>
        <c:catch var="exp">
            <c:forEach var="q" items="${e_q.rows}">

                <c:if test="${q.user_type eq 'guest'}">
                    Posted by : <i style="color: red;">${q.username}</i>
                </c:if>
                <c:if test="${q.user_type ne 'guest'}">                                                      
                    Posted by : ${q.firstname}
                    <c:if test="${not empty q.higher_edu}">
                        (${q.higher_edu})
                    </c:if> 
                </c:if>  <br><br>
                <c:choose>
                    <c:when test="${q.date eq 0}">
                        Posted : Today
                    </c:when>
                    <c:otherwise>
                        Posted : ${q.updated_time} days ago
                    </c:otherwise>
                </c:choose><br><br>
                Who Posted the question : ${q.id} <br><br>
                <form name="updateQuestionTag" action="<%=request.getContextPath()%>/updateQuestion" method="post">
                    <input type="hidden" name="pageNumber" value="${param.p}">
                     <sql:query dataSource="jdbc/mydatabase" var="tag_q">
                        SELECT topic.unique_id AS tag_id, topic.topic_name AS topic_name FROM topic INNER JOIN question_topic_tag ON topic.unique_id = question_topic_tag.tag_id WHERE question_id = ? AND tag_id IS NOT NULL ORDER BY tag_id;
                        <sql:param value="${q.q_id}"/>
                    </sql:query>
                    <c:forEach items="${tag_q.rows}" var="t">
                        <c:set var="myVar" value="${t.topic_name},${myVar}"/>
                    </c:forEach>
                    Question ID : <input type="text" name="questionId" value="${q.q_id}">
                    <br><br>
                    Question :  <input type="text" name="question" value="${q.question}" style="height: 30px; width: 1000px;">
                    <br><br>
                    Question Tag : 
                    <textarea type="text" name="tag_of_question" rows="10" cols="100">${myVar}</textarea>   
                    <input type="submit" name="Update" value="Update">
                </form>
                <br><br> <a href="<%=request.getContextPath()%>/deleteQuestionById?qId=${q.q_id}&p=${param.p}" style="color: red;">Delete This question</a>
            </c:forEach>

        </c:catch>
        <c:if test="${exp ne null}">
            ${exp}
        </c:if>   
    </body>
</html>
