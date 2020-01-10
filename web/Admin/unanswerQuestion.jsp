<%-- 
    Document   : unanswerQuestion
    Created on : Aug 12, 2019, 11:49:50 AM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.adminUserId eq null}">
    <c:redirect url="visit.jsp?msg=Session is not valid"/>
</c:if>
<jsp:useBean class="com.admin.unasweredQuestion.uQuestion" id="question" scope="page"/>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
<!DOCTYPE html>
<html>
    <head>
        <script src="../ckeditor/ckeditor.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Unanswered question</title>
    </head>
    <body>
        <h1>Hello, ${sessionScope.userName}
            <a href="adminModule.jsp">Home</a>, <a href="<%=request.getContextPath()%>/Logout">Logout</a>
            , <a href="<%=request.getContextPath()%>/Admin/unanswerQuestion.jsp">Refresh</a>
        </h1>
        <c:choose>
            <c:when test="${param.qId ne null and not empty param.qId}">
                <h1>Q : ${param.question}(${param.qId})</h1>
                <form name="" action="<%=request.getContextPath()%>/saveAnswer" method="post">
                    Question ID<input type="text" name="qId" required="" readonly="" value="${param.qId}"/>
                    <br><br>User ID<input type="text" name="userId" required="" readonly="" value="${sessionScope.adminUserId}"/>
                    <br><br>Posted By ID<input type="text" name="postedById" required="" readonly="" value="${param.postedById}"/>
                    <br><br><textarea class="ckeditor" name="answer"></textarea> 
                    <br><input type="submit" value="save answer"/>
                </form>
            </c:when>
            <c:otherwise>
                <c:if test="${param.msg ne null}">
                <center> <h1 style="color: green;">${param.msg}</h1></center>
                </c:if>
                <h1>Total answered question.....</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Question</th>
                        <th>Date</th>
                        <th>Posted by</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${question.question()}" var="q">
                        <tr>
                            <td>${q.questionId}</td>
                            <td>${q.question}</td>
                            <td>${q.date}</td>
                            <td>${q.postedByName}(${q.postedById})</td>
                            <td><a href="?qId=${q.questionId}&question=${word.UrlFormat(q.question)}&postedById=${q.postedById}">Answer This</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>

    </body>
</html>
