<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${notification eq null}">
    <c:redirect url="login.jsp?ref=Please login"/>
</c:if>
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
                                        <c:if test="${notification ne null}">
                                            <c:forEach var="n" items="${notification}">
                                                <c:choose>
                                                    <c:when test="${n.notification_type eq 'got_answer_of_a_question'}">
                                                        <div class="boxHeading">
                                                            <a href="questions?q=${n.question}&ans_by=${n.userFirstName}&Id=${n.question_id}&c_id=${n.comment_id}"><b>${n.userFirstName}</b> give you an answer of <b>${n.question}</b></a>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${n.notification_type eq 'followed_by'}">
                                                        <div class="boxHeading">
                                                            <a href="profile.jsp?user=${n.userFirstName}&ID=${n.notificationCreatedBy}&c_id=${n.comment_id}"> <b>${n.userFirstName}</b> started following you</a>  
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${n.notification_type eq 'comment_on_question'}">
                                                        <div class="boxHeading">
                                                            <a href="questions?q=${n.question}&comment_by=${n.userFirstName}&Id=${n.question_id}&c_id=${n.comment_id}"><b>${n.userFirstName}</b> commented on : <b>${n.question}</b>, Which belongs to you</a>  
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${n.notification_type eq 'comment_on_Answer'}">
                                                        <div class="boxHeading">
                                                            <a href="questions?q=${n.question}&comment_by=${n.userFirstName}&Id=${n.question_id}&c_id=${n.comment_id}"><b>${n.userFirstName}</b> commented on a answer which something belongs to you and question is :- <b>${n.question}</b></a>    
                                                        </div>    
                                                    </c:when>
                                                    <c:when test="${n.notification_type eq 'comment_on_Blog'}">
                                                        <div class="boxHeading"> 
                                                            <a href="blog?id=${n.blog_id}&c_id=${n.comment_id}"><b>${n.userFirstName}</b> Commented on your Blog </a>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${n.notification_type eq 'comment_on_Profile'}">
                                                        <div class="boxHeading">  
                                                            <a href="profile.jsp?ID=${sessionScope.Session_id_of_user}&c_id=${n.comment_id}"> <b>${n.userFirstName}</b> Commented on You profile</a>
                                                        </div>
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