<%-- 
    Document   : postQuestion
    Created on : 14 Aug, 2019, 8:49:35 AM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.adminUserId eq null}">  
    <c:redirect url="visit.jsp?msg=Session is not valid"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Post Question Admin Module</title>
    </head>
    <body>  
         <c:if test="${param.msg ne null}">
           <center> <h1 style="color: green;">${param.msg}</h1></center>
        </c:if>
        <h1>Hello,   ${sessionScope.userName}
            <a href="adminModule.jsp">Home</a> <a href="<%=request.getContextPath()%>/Logout">Logout</a>
        </h1>
        <form action="<%=request.getContextPath()%>/saveQuestion" name="postQuestion" method="post">
            UserId  : <input type="text" name="userId" value="${sessionScope.adminUserId}" readonly="" required=""><br><br>
           Question : <textarea type="text" name="question" placeholder="Ex: What is,How to.." required="" cols="70" rows="10"></textarea><br><br>
            Tag     : <textarea type="text" class="anstext" name="tag_of_question" placeholder="Ex:Java,Database,c language" cols="70" rows="7" required=""></textarea><br><br>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="Post question">
        </form>
    </body>
</html>
