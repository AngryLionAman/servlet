<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${notification eq null}">
    <c:redirect url="login.jsp?ref=Please login"/>
</c:if>
<jsp:useBean scope="page" id="nt" class="com.notifications.NotificationExtraSupportingClass"/>
<html lang="en"><head>
        <meta charset="UTF-8">
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>inbox</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" >
                            <div class="row">                                 
                                <div class="themeBox" style="height:auto;">
                                    <c:catch var="msg">
                                        <c:if test="${notification eq null or empty notification}">
                                            You don't have any notification yet
                                        </c:if>
                                        <c:if test="${notification ne null and not empty notification}">
                                            <c:forEach var="n" items="${notification}">
                                                <c:choose>

                                                    <c:when test="${n.notification_type eq 'followed_by'}">
                                                        <div class="boxHeading">
                                                            <a href="profile?user=${nt.getUserNameById(n.notificationCreatedBy)}&id=${n.notificationCreatedBy}&c_id=${n.comment_id}"> <b>${nt.getFullNameById(n.notificationCreatedBy)}</b> started following you</a>  
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'got_answer_of_a_question'}">
                                                        <div class="boxHeading">
                                                            <a href="questions?id=${n.content_id}&ans_by=${nt.getFullNameById(n.notificationCreatedBy)}&c_id=${n.comment_id}"><b>${nt.getFullNameById(n.notificationCreatedBy)}</b> give you an answer of <b>${nt.getQuestionById(n.content_id)}</b></a>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'comment_on_question'}">
                                                        <div class="boxHeading">
                                                            <a href="questions?id=${n.content_id}&comment_by=${nt.getFullNameById(n.notificationCreatedBy)}&c_id=${n.comment_id}"><b>${nt.getFullNameById(n.notificationCreatedBy)}</b> commented on : <b>${nt.getQuestionById(n.content_id)}</b>, Which belongs to you</a>  
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'comment_on_Answer'}">
                                                        <div class="boxHeading">
                                                            <a href="questions?id=${nt.getQuestionIdByAnswerId(n.content_id)}&comment_by_id=${n.notificationCreatedBy}&c_id=${n.comment_id}"><b>${nt.getFullNameById(n.notificationCreatedBy)}</b> commented on a answer which something belongs to you and question is :- <b>${nt.getQuestionByAnswerId(n.content_id)}</b></a>    
                                                        </div>    
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'comment_on_Blog'}">
                                                        <div class="boxHeading"> 
                                                            <a href="blog?id=${n.content_id}&comment_by_id=${n.notificationCreatedBy}&c_id=${n.comment_id}"><b>${nt.getFullNameById(n.notificationCreatedBy)}</b> Commented on your Blog </a>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'comment_on_Profile'}">
                                                        <div class="boxHeading">  
                                                            <a href="profile?id=${n.content_id}&c_id=${n.comment_id}"> <b>${nt.getFullNameById(n.notificationCreatedBy)}</b> Commented on You profile</a>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'modified_question_approved'}">
                                                        <div class="boxHeading">  
                                                            <a href="questions?id=${n.content_id}&c_id=${n.comment_id}">Your modified question has been approved not</a>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'question_approved_by_user'}">
                                                        <div class="boxHeading">  
                                                            <a href="questions?id=${n.content_id}&c_id=${n.comment_id}">Your question has been approved by user, Approval is pending by the admin</a>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'question_approvel_rejected_by_user'}">
                                                        <div class="boxHeading">  
                                                            <a href="questions?id=${n.content_id}&c_id=${n.comment_id}">Your modification request for the question has been rejected by user, Wait for administrator action</a>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'approvel_for_question'}">
                                                        <div class="boxHeading">  
                                                            <a href="approval_for_question?q_id=${n.content_id}&c_id=${n.comment_id}"> <b>${nt.getFullNameById(n.notificationCreatedBy)}</b> modified your question</a>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'question_approved_by_admin'}">
                                                        <div class="boxHeading">  
                                                            <a href="questions?id=${n.content_id}&c_id=${n.comment_id}">Your question has been approved by Admin, Approval is pending by the User</a>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${n.notification_type eq 'question_approvel_rejected_by_admin'}">
                                                        <div class="boxHeading">  
                                                            <a href="questions?id=${n.content_id}&c_id=${n.comment_id}">Your modification request for the question has been rejected by admin, Reason not specified. Just wait for some time , we will let you know the reason of rejection</a>
                                                        </div>
                                                    </c:when>

                                                </c:choose><br><br>
                                            </c:forEach>
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${msg ne null}">
                                        ${msg}
                                    </c:if>
                                </div>
                            </div>
                        </div>

                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div>

    </body></html>