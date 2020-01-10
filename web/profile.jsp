<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="word" scope="page" class="com.string.WordFormating"/>
<jsp:useBean class="com.topic.topicFollow" id="topic" scope="page"/>
<jsp:useBean class="com.follow.UserFollow" id="f" scope="page"/>

<!DOCTYPE html>
<html lang="en">
    <head>     
        <%@include file="googleAnalytics.jsp" %>        
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">

        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <c:if test="${GetUserDetailByUserId eq null}">
            <c:choose>
                <c:when test="${param.ID ne null and not empty param.ID}">
                    <c:redirect url="profile?id=${param.ID}"/>
                </c:when>
            </c:choose>
        </c:if>

        <c:catch var="pr">
            <c:if test="${GetUserDetailByUserId ne null}">
                <c:forEach items="${GetUserDetailByUserId}" var="user">
                    <c:set scope="page" var="userId" value="${user.userId}"/>
                    <c:set scope="page" var="userName" value="${user.username}"/>
                    <c:set scope="page" var="fullName" value="${user.fullName}"/>
                    <c:set scope="page" var="email" value="${user.email}"/>
                    <c:set scope="page" var="email_s" value="${user.email_s}"/>
                    <c:set scope="page" var="higherEdu" value="${user.higherEdu}"/>
                    <c:set scope="page" var="bestAchivement" value="${user.bestAchivement}"/>
                    <c:set scope="page" var="bio" value="${user.bio}"/>
                    <c:set scope="page" var="imagePath" value="${user.imagePath}"/>
                    <c:set scope="page" var="totalView" value="${user.totalView}"/>
                </c:forEach>
            </c:if>
        </c:catch>        

        <title>${fullName} Profile | inquiryhere.com</title>

        <meta property="url" content="https://www.inquiryhere.com/" />
        <meta property="title" content="${fullName} Profile | inquiryhere.com" />
        <meta property="description" content="${bio}" />
        <meta property="type" content="website">
        <meta property="locale" content="en">

        <meta property="og:url" content="https://www.inquiryhere.com/" />
        <meta property="og:title" content="${fullName} Profile | inquiryhere.com" />
        <meta property="og:description" content="${bio}" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en">

        <script type="text/javascript">
            function showCommentBox() {
                var div = document.getElementById('comment');
                div.className = 'visible';
            }
        </script>
        <script type="text/javascript">
            function follow_topic(el, _topic_id, id_of_user) {
                if (_topic_id !== "" && id_of_user !== "") {
                    if (el.value === "follow") {
                        el.value = "followed";
                        var http = new XMLHttpRequest();
                        http.open("POST", "followTopicServlet?topicId=" + _topic_id + "&userId=" + id_of_user + "&action=follow", true);
                        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        http.send();
                    } else {
                        el.value = "follow";
                        var http = new XMLHttpRequest();
                        http.open("POST", "followTopicServlet?topicId=" + _topic_id + "&userId=" + id_of_user + "&action=unfollow", true);
                        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        http.send();
                    }
                }
            }
        </script>   
        <script type="text/javascript">
            function follow_user(el, user_id, id_of_user, action) {
                if (user_id !== null && id_of_user !== null) {
                    if (el.value === "Follow") {
                        el.value = "Followed";
                    } else if (el.value === "Followed") {
                        el.value = "Follow";
                    } else {
                        alert('This option will never call');
                    }
                    var http = new XMLHttpRequest();
                    http.open("POST", "SaveFollowUserServlet?userId=" + user_id + "&followersId=" + id_of_user + "&action=" + action, true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }
            }
        </script>
    </head>

    <body>
        <div class="main-page-wrapper">
            <!-- Header _________________________________ -->
            <jsp:include page="header.jsp"/>
            <div class="bodydata">
                <div class="container clear-fix">                           
                    <div class="row">
                        <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                            <c:if test="${message ne null}">                                
                                <div class="clear-fix" align="center" style="font-size: 20px;color: green;background-color: yellow;">
                                    ${message}                                
                                </div>
                            </c:if>

                            <c:if test="${gotException ne null}">                                
                                <div class="clear-fix" align="center" style="font-size: 20px;color: red;background-color: white;">
                                    ${'Got some probelm, Please refresh this page or visit after some time'}                                
                                </div>
                            </c:if>

                            <div class="themeBox" style="min-height:1px;">
                                <div class="boxHeading">
                                    Profile details[ View(${totalView}) ]
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                    <img src="images/${imagePath}" alt="inquiryhere" style="width:90%; margin:10px 0px 0px; border:1px solid #ddd;">
                                    <c:if test="${sessionScope.Session_id_of_user ne null and userId eq sessionScope.Session_id_of_user}">
                                        <a href="editP?id=${sessionScope.Session_id_of_user}" >UPDATE YOUR PROFILE IMAGE</a>
                                    </c:if>

                                    <c:catch var="excp">  
                                        <c:choose>
                                            <c:when test="${sessionScope.Session_id_of_user ne null}">
                                                <c:choose>
                                                    <c:when test="${sessionScope.Session_id_of_user ne userId}">
                                                        <c:choose>
                                                            <c:when test="${f.IsUserFollowingByUserId(userId, sessionScope.Session_id_of_user)}">
                                                                <input type="button" value="Followed" onclick="return follow_user(this, '${userId}', '${sessionScope.Session_id_of_user}', 'Followed');" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="button" value="Follow" onclick="return follow_user(this, '${userId}', '${sessionScope.Session_id_of_user}', 'Follow');" />
                                                            </c:otherwise>
                                                        </c:choose>    
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="button" value="Follow" onclick="alert('Please Login to follow')" />
                                            </c:otherwise>
                                        </c:choose>  
                                    </c:catch>
                                    <c:if test="${excp ne null}">
                                        ${excp}
                                    </c:if> 
                                </div>

                                <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                                    <table class="profiledetails">
                                        <tr>
                                            <td>Name </td>
                                            <td>: <c:out value="${fullName}"/></td>
                                        </tr>
                                        <tr>
                                            <td>Mail Id/Phone </td>
                                            <td>: 
                                                <c:choose>
                                                    <c:when test="${email_s eq 1}">
                                                        <c:choose>
                                                            <c:when test="${userId eq sessionScope.Session_id_of_user}">
                                                                <c:out value="${email}"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="Hidden by user"/>
                                                            </c:otherwise>
                                                        </c:choose>                                                               
                                                    </c:when>
                                                    <c:otherwise >
                                                        <c:out value="${email}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                &nbsp;&nbsp;
                                                <c:if test="${sessionScope.Session_id_of_user ne null and userId eq sessionScope.Session_id_of_user}" >
                                                    <c:choose>
                                                        <c:when test="${email_s eq 0}">
                                                            <div style="padding: 5px; display: inline-block;" align="right">
                                                                <form name="form_name_hide_mail" method="post" action="hideMail">
                                                                    <input type="hidden" name="sessionUserId" value="${sessionScope.Session_id_of_user}"/>
                                                                    <input type="hidden" name="action" value="hide"/>
                                                                    <input type="submit" value="Hide you mail">
                                                                </form>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${email_s eq 1}">
                                                            <div style="padding: 5px; display: inline-block;" align="right">
                                                                <form name="form_name_hide_mail" method="post" action="hideMail">
                                                                    <input type="hidden" name="sessionUserId" value="${sessionScope.Session_id_of_user}"/>
                                                                    <input type="hidden" name="action" value="show"/>
                                                                    <input type="submit" value="Display your mail">
                                                                </form>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Higher Qualification </td>
                                            <td>: 
                                                <c:choose>
                                                    <c:when test="${higherEdu eq null or empty higherEdu}">
                                                        <c:out value="Not provided yet"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${higherEdu}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Best Achievement</td>
                                            <td>:
                                                <c:choose>
                                                    <c:when test="${bestAchivement eq null or empty bestAchivement}">
                                                        <c:out value="Not provided yet"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${bestAchivement}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Bio </td>
                                            <td>: 
                                                <c:choose>
                                                    <c:when test="${bio eq null or empty bio}">
                                                        <c:out value="Not provided yet"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${bio}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                        <c:if test="${sessionScope.Session_id_of_user ne null and sessionScope.Session_id_of_user eq userId}">
                                            <tr>                                                   
                                                <td><a href="editP?id=${sessionScope.Session_id_of_user}">Complete your profile</a></td>
                                            </tr>
                                        </c:if>

                                        <tr>                                                   
                                            <td>Appreciation ..</td>  
                                        </tr>
                                    </table>

                                    <c:catch var="cmt">
                                        <c:if test="${GetCommentByProfileId ne null and not empty GetCommentByProfileId}">
                                            <div align="right">                                        
                                                <c:forEach items="${GetCommentByProfileId}" var="fc">

                                                    <c:choose>
                                                        <c:when test="${fc.userType eq 'guest'}">
                                                            <p>
                                                                <c:out value="${fc.comment}"/> :- 
                                                                <font style="color: red;">${fc.fullName}</font>, ${fc.date}
                                                            </p>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p>    
                                                                <c:out value="${fc.comment}"/> :- 
                                                                <a href="profile?user=${fc.userName}&id=${fc.userId}&ref=pc">${fc.fullName}</a>, ${fc.date}  
                                                            </p>
                                                        </c:otherwise>
                                                    </c:choose>                                           
                                                </c:forEach>
                                                <c:if test="${sessionScope.Session_id_of_user ne null and userId ne sessionScope.Session_id_of_user}">
                                                    <a href="javascript:void(0)" value="Comment" onclick="showCommentBox()">Write Good Thing About Him</a><br>
                                                </c:if>
                                            </div>
                                        </c:if>
                                    </c:catch>

                                    <form action="saveProfileComment" method="post">
                                        <div class="hidden" id="comment">
                                            <input type="hidden" name="session_userid" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                            <input type="hidden" name="OnCommentUserId" value="<c:out value="${userId}"/>">
                                            <textarea name="comments" rows="3" cols="30" required="" placeholder="Write about him and let the world know how good he is.."></textarea>
                                            <input type="submit" name="sub" value="Send Comment">
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>



                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="themeBox" style="height: auto;">
                                <div class="boxHeading">
                                    User Activity
                                </div>
                                <div>
                                    <c:catch var="cnt">
                                        <c:forEach items="${CountRowByUserIdController}" var="co">
                                            <a href="profile?user=${userName}&id=${userId}&tab=question">Question(<c:out value="${co.countQestion}"/>)</a><br>
                                            <a href="profile?user=${userName}&id=${userId}&tab=answer">Answer(<c:out value="${co.countAnswer}"/>)</a><br>
                                            <a href="profile?user=${userName}&id=${userId}&tab=topic">Topic Followed(<c:out value="${co.countTopic}"/>)</a><br>
                                            <a href="profile?user=${userName}&id=${userId}&tab=following">Following(<c:out value="${co.countFollowing}"/>)</a><br>
                                            <a href="profile?user=${userName}&id=${userId}&tab=followers">Followers(<c:out value="${co.countFollowers}"/>)</a><br>
                                            <a href="profile?user=${userName}&id=${userId}&tab=blog">Blog(<c:out value="${co.countBlog}"/>)</a><br>
                                        </c:forEach>
                                    </c:catch>
                                </div>

                            </div>

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;margin-bottom:0px;">

                                        <c:choose>

                                            <c:when test="${GetTotalQuestionPostedByUserId ne null and not empty GetTotalQuestionPostedByUserId}">

                                                <center><div class=boxHeading>QUESTION</div></center>                                                       
                                                    <c:set scope="page" value="0" var="count"/>
                                                    <c:forEach items="${GetTotalQuestionPostedByUserId}" var="q" varStatus="loop">
                                                        <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <div class="themeBox" style="height:auto;">
                                                        <div align="left" style="font-size: 20px;font-family: serif;">
                                                            View:${q.totalView} &nbsp;  Answer:${q.totalAnswer}  Updated : ${q.lastModifiedTime}
                                                            &nbsp;&nbsp;&nbsp;&nbsp; 
                                                            <c:if test="${sessionScope.Session_id_of_user ne null and q.questionPostedById eq sessionScope.Session_id_of_user}">
                                                                <a href="edit_q?id=${q.questionId}&q=${q.question}&ref=p"/>Edit</a>
                                                            </c:if> 
                                                        </div>
                                                        <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                            <a href="questions?id=${q.questionId}&q=${word.UrlFormat(q.question)}&ref=p" >${q.question} ?</a>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                                <c:if test="${count eq 0}">
                                                    <font style="color: red;">User not posted any question yet!!!</font>
                                                </c:if>

                                            </c:when>

                                            <c:when test="${GetTotalAnswerPostedByUserId ne null and not empty GetTotalAnswerPostedByUserId}">

                                                <center><div class=boxHeading> ANSWER </div></center>                                                   
                                                    <c:set scope="page" value="0" var="count"/>
                                                    <c:forEach items="${GetTotalAnswerPostedByUserId}" var="ans">
                                                        <c:set scope="page" value="${loop.count}" var="count"/>

                                                    <div class="themeBox" style="height:auto;">
                                                        <div align="left" style="font-size: 20px;font-family: serif;">
                                                            View:${ans.totalView} &nbsp; Last Edit :${ans.lastModifiedTime}
                                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                                            <c:if test="${sessionScope.Session_id_of_user ne null and ans.answerById eq sessionScope.Session_id_of_user}">
                                                                <a href="updateAnswer?q=${ans.question}&q_id=${ans.questionId}&ans_id=${ans.answerid}&ans_by_id=${ans.answerById}&ref=p">Edit</a>
                                                            </c:if>
                                                        </div>
                                                        <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                            <a href="questions?id=<c:out value="${ans.questionId}"/>&q=${word.UrlFormat(ans.question)}&ref=p" ><c:out value="${ans.question}"/> ?</a>
                                                        </div>
                                                        <c:out value="${ans.shortAnswer}" escapeXml="false"/>&nbsp;<a href="questions?id=${ans.questionId}&q=${word.UrlFormat(ans.question)}&ref=p"> Continue Reading</a>
                                                    </div> 
                                                </c:forEach>
                                                <c:if test="${count eq 0}">
                                                    <font style="color: red;">User not given any answer yet!!!</font>
                                                </c:if>

                                            </c:when>

                                            <c:when test="${GetTotalTopicFollowedByUserId ne null and not empty GetTotalTopicFollowedByUserId}">

                                                <center><div class=boxHeading>TOPIC FOLLOWED </div></center>                                                    
                                                    <c:set scope="page" value="0" var="count"/>
                                                    <c:forEach items="${GetTotalTopicFollowedByUserId}" var="t" >
                                                    <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6" style="padding-top: 5px;">
                                                        <c:set scope="page" value="${loop.count}" var="count"/>
                                                        <a href="topic?id=${t.topicId}&t=${word.UrlFormat(t.topicName)}&ref=p"/>
                                                        <c:if test="${t.imagePath ne null}">
                                                            <img src="${t.imagePath}" alt="${t.topicName}" width="100" height="100">
                                                        </c:if>
                                                        ${t.topicName}</a>  

                                                        <c:catch var="exc">
                                                            <c:choose>
                                                                <c:when test="${sessionScope.Session_id_of_user ne null}">
                                                                    <c:choose>
                                                                        <c:when test="${topic.topicFollw(t.topicId, sessionScope.Session_id_of_user)}">
                                                                            <input type="button" value="Unfollow" onclick="return follow_topic(this, '${t.topicId}', '${sessionScope.Session_id_of_user}');" />
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <input type="button" value="follow" onclick="return follow_topic(this, '${t.topicId}', '${sessionScope.Session_id_of_user}');" />
                                                                        </c:otherwise>
                                                                    </c:choose>                                                                        
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="button" value="follow" onclick="alert('Please Login to follow')" />
                                                                </c:otherwise>
                                                            </c:choose>  
                                                        </c:catch>
                                                        <c:if test="${exc ne null}">
                                                            ${exc}
                                                        </c:if> 
                                                    </div>
                                                </c:forEach>   
                                                <c:if test="${count eq 0}">
                                                    <font style="color: red;">User not followed any topic yet!!!</font>
                                                </c:if>
                                                <br><a href="moretopic"> Follow more topic</a> 

                                            </c:when>

                                            <c:when test="${GetTotalFollowingByUserId ne null and not empty GetTotalFollowingByUserId}">

                                                <center><div class=boxHeading> FOLLOWING </div></center>                                                   
                                                    <c:set scope="page" value="0" var="count"/>
                                                    <c:forEach items="${GetTotalFollowingByUserId}" var="follow" varStatus="loop">
                                                        <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                                        <img src="images/<c:out value="${follow.imagePath}"/>" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                        <a href="profile?user=<c:out value="${follow.userName}"/>&id=<c:out value="${follow.userId}"/>&ref=p"><c:out value="${follow.fullName}"/></a>
                                                        <c:catch var="excp">  
                                                            <c:choose>
                                                                <c:when test="${sessionScope.Session_id_of_user ne null}">
                                                                    <c:choose>
                                                                        <c:when test="${f.IsUserFollowingByUserId(follow.userId, sessionScope.Session_id_of_user)}">
                                                                            <input type="button" value="Followed" onclick="return follow_user(this, '${follow.userId}', '${sessionScope.Session_id_of_user}', 'Followed');" />
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <input type="button" value="Follow" onclick="return follow_user(this, '${follow.userId}', '${sessionScope.Session_id_of_user}', 'Follow');" />
                                                                        </c:otherwise>
                                                                    </c:choose>                                                                        
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="button" value="Follow" onclick="alert('Please Login to follow')" />
                                                                </c:otherwise>
                                                            </c:choose>  
                                                        </c:catch>
                                                        <c:if test="${excp ne null}">
                                                            ${excp}
                                                        </c:if> 
                                                    </div> 

                                                </c:forEach>
                                                <c:if test="${count eq 0}">
                                                    <font style="color: red;">User not following any user yet!!!</font>
                                                </c:if>
                                                <br><a href=UserProfile.jsp> FOLLOW MORE USER </a>

                                            </c:when>

                                            <c:when test="${GetTotalFollowersByUserId ne null and not empty GetTotalFollowersByUserId}">

                                                <center><div class=boxHeading> FOLLOWERS </div></center>                                                  
                                                    <c:set scope="page" value="0" var="count"/>
                                                    <c:forEach items="${GetTotalFollowersByUserId}" var="followers" varStatus="loop">
                                                        <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                                        <img src="images/<c:out value="${followers.imagePath}"/>" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                        <a href="profile?user=<c:out value="${followers.userName}"/>&id=<c:out value="${followers.userId}"/>&ref=p"><c:out value="${followers.fullName}"/></a>


                                                        <c:catch var="excp">  
                                                            <c:choose>
                                                                <c:when test="${sessionScope.Session_id_of_user ne null}">
                                                                    <c:choose>
                                                                        <c:when test="${f.IsUserFollowingByUserId(followers.userId, sessionScope.Session_id_of_user)}">
                                                                            <input type="button" value="Followed" onclick="return follow_user(this, '${followers.userId}', '${sessionScope.Session_id_of_user}', 'Followed');" />
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <input type="button" value="Follow" onclick="return follow_user(this, '${followers.userId}', '${sessionScope.Session_id_of_user}', 'Follow');" />
                                                                        </c:otherwise>
                                                                    </c:choose>                                                                        
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="button" value="Follow" onclick="alert('Please Login to follow')" />
                                                                </c:otherwise>
                                                            </c:choose>  
                                                        </c:catch>
                                                        <c:if test="${excp ne null}">
                                                            ${excp}
                                                        </c:if>

                                                    </div>
                                                </c:forEach>
                                                <c:if test="${count eq 0}">
                                                    <font style="color: red;">User don't have any followers yet yet!!!</font>
                                                </c:if>
                                                <br><a href=UserProfile.jsp> FOLLOW MORE USER </a>

                                            </c:when>

                                            <c:when test="${GetTotalBlogByUserId ne null and not empty GetTotalBlogByUserId}">

                                                <center><div class=boxHeading> BLOG </div></center>                                                  
                                                    <c:set scope="page" value="0" var="count"/>
                                                    <c:forEach items="${GetTotalBlogByUserId}" var="blog" varStatus="loop">
                                                        <c:set scope="page" value="${loop.count}" var="count"/>

                                                    <div class="themeBox" style="height:auto;">
                                                        <div align="left" style="font-size: 20px;font-family: serif;">
                                                            View:${blog.totalView} &nbsp; Last Edit:${blog.lastModifiedTime}
                                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                                            <c:if test="${sessionScope.Session_id_of_user ne null and blog.postedById eq sessionScope.Session_id_of_user}">
                                                                <a href="#">Edit</a>
                                                            </c:if>
                                                        </div>
                                                        <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                            <a href="blog?id=<c:out value="${blog.blogId}"/>&sub=${word.UrlFormat(blog.blogSub)}&ref=p"><c:out value="${blog.blogSub}"/></a>
                                                        </div>
                                                    </div>



                                                </c:forEach> 
                                                <c:if test="${count eq 0}">
                                                    <font style="color: red;">User not posted any blog yet!!!</font>
                                                </c:if>
                                                <br><a href=WriteBlogHere.jsp> BLOG ABOUT SOMETHING </a>

                                            </c:when>

                                        </c:choose>
                                        <div class="clear-fix"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row margintop10"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <!-- /.main-page-wrapper -->
        <jsp:include page="footer.jsp"/>
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>