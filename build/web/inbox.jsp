<%-- 
    Document   : inbox
    Created on : 15 Oct, 2019, 4:16:33 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inbox</title>
    </head>
    <body>
        <c:catch var="msg">
            <c:if test="${notification ne null}">
                <c:forEach var="n" items="${notification}">
                    <c:choose>
                        <c:when test="${n.notification_type eq 'got_answer_of_a_question'}">
                            <a href="Answer.jsp?q=${n.question}&ans_by=${n.userFirstName}&Id=${n.question_id}&c_id=${n.comment_id}"><b>${n.userFirstName}</b> give you an answer of <b>${n.question}</b></a>
                                </c:when>
                                <c:when test="${n.notification_type eq 'followed_by'}">
                            <a href="profile.jsp?user=${n.userFirstName}&ID=${n.notificationCreatedBy}&c_id=${n.comment_id}"> <b>${n.userFirstName}</b> started following you</a>  
                        </c:when>
                        <c:when test="${n.notification_type eq 'comment_on_question'}">
                            <a href="Answer.jsp?q=${n.question}&comment_by=${n.userFirstName}&Id=${n.question_id}&c_id=${n.comment_id}"><b>${n.userFirstName}</b> commented on <b>${n.question}</b></a>  
                                </c:when>
                                <c:when test="${n.notification_type eq 'comment_on_Answer'}">
                            <a href="Answer.jsp?q=${n.question}&comment_by=${n.userFirstName}&Id=${n.question_id}&c_id=${n.comment_id}"><b>${n.userFirstName}</b> commented on a answer which something belongs to you and question is :- <b>${n.question}</b></a>    
                                </c:when>
                                <c:when test="${n.notification_type eq 'comment_on_Blog'}">
                            <a href="D_Blog.jsp?Blog_Id=${n.blog_id}&c_id=${n.comment_id}"><b>${n.userFirstName}</b> Commented on your Blog </a>
                                </c:when>
                                <c:when test="${n.notification_type eq 'comment_on_Profile'}">
                                <a href="profile.jsp?ID=${sessionScope.Session_id_of_user}&c_id=${n.comment_id}"> <b>${n.userFirstName}</b> Commented on You profile</a>
                        </c:when>
                        <c:otherwise>
                            You don't have any notification yet
                        </c:otherwise>
                    </c:choose><br><br>
                </c:forEach>
            </c:if>
        </c:catch>
        <c:if test="${msg ne null}">
            ${msg}
        </c:if>
    </body>
</html>
