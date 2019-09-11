<%-- 
    Document   : editQuestion
    Created on : 23 Aug, 2019, 2:37:38 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="com.index.indexPage" id="Question" scope="page" />
<jsp:useBean class="com.answer.SEO" id="tag" scope="page" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Edit question and tag</title>
    </head>
    <body>
        <h1>Question Details.. <a href="modifyQuestion.jsp?p=${param.p}"> Back</a></h1>

        <c:catch var="exp">
            <c:forEach var="q" items="${Question.getQuestion(param.qId)}">

                <c:if test="${q.userType eq 'guest'}">
                    Posted by : <i style="color: red;">${q.userName}</i>
                </c:if>
                <c:if test="${q.userType ne 'guest'}">                                                      
                    Posted by : ${q.fullName}
                    <c:if test="${not empty q.higherEdu}">
                        (${q.higherEdu})
                    </c:if> 
                </c:if>  <br><br>
                <c:choose>
                    <c:when test="${q.days eq 0}">
                        Posted : Today
                    </c:when>
                    <c:otherwise>
                        Posted : ${q.days} days ago
                    </c:otherwise>
                </c:choose><br><br>
                Who Posted the question : ${q.userId} <br><br>
                <form name="updateQuestionTag" action="<%=request.getContextPath()%>/updateQuestion" method="post">
                    <input type="hidden" name="pageNumber" value="${param.p}">
                    <c:forEach items="${tag.getQuestionTag(q.questionId)}" var="t">
                        <c:set var="myVar" value="${t},${myVar}"/>
                    </c:forEach>
                    Question ID : <input type="text" name="questionId" value="${q.questionId}">
                    <br><br>
                    Question :  <input type="text" name="question" value="${q.question}" style="height: 30px; width: 1000px;">
                    <br><br>
                    Question Tag : 
                    <textarea type="text" name="tag_of_question" rows="10" cols="100">${myVar}</textarea>   
                    <input type="submit" name="Update" value="Update">
                </form>
                <br><br> <a href="<%=request.getContextPath()%>/deleteQuestionById?qId=${q.questionId}&p=${param.p}" style="color: red;">Delete This question</a>
            </c:forEach>

        </c:catch>
        <c:if test="${exp ne null}">
            ${exp}
        </c:if>   
    </body>
</html>
