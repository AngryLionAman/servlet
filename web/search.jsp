<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
<jsp:useBean class="com.topic.topicFollow" id="topic" scope="page"/>
<jsp:useBean class="com.follow.UserFollow" id="f" scope="page"/>
<c:if test="${randomTopic eq null}">
    <c:redirect url="search"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Search By inquiryhere.com</title>
        <meta property="og:url" content="https://www.inquiryhere.com/search.jsp">
        <meta property="og:site_name" content="inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="Google of Bihar" />
        <meta property="og:description" content="Search here and get the best result by inquiryhere"/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <link rel="canonical" href="https://www.inquiryhere.com/search.jsp" />
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            .a_link{
                color: black;
                background-color: #ffffcc;
                border-style: solid;
                border-spacing: 2px;
            }
        </style>
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
        <jsp:include page="header.jsp"/>
        <div class="main-page-wrapper">
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <c:if test="${message ne null}">                                
                            <div class="clear-fix" align="center" style="font-size: 20px;color: green;background-color: yellow;">
                                ${message}                                
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                <div class="row">
                                    <div class="themeBox" style="min-height:available">
                                        <div class="boxHeading">
                                            Your Activity
                                        </div>

                                        <c:catch var="exe">     
                                            <c:forEach items="${GetCountRowSearch}" var="c">
                                                <c:set scope="page" value="${c.countQuestion}" var="countQuestion"/>
                                                <c:set scope="page" value="${c.countAnswer}" var="countAnswer"/>
                                                <c:set scope="page" value="${c.countTopic}" var="countTopic"/>
                                                <c:set scope="page" value="${c.countUser}" var="countUser"/>
                                            </c:forEach>

                                            <div>
                                                <a href="?tab=question&q=<c:out value="${query}"/>">Question (${countQuestion})</a><br>
                                                <a href="?tab=answer&q=<c:out value="${query}"/>">Answer (${countAnswer})</a><br>
                                                <a href="?tab=topic&q=<c:out value="${query}"/>">Topic (${countTopic}) </a><br>
                                                <a href="?tab=profile&q=<c:out value="${query}"/>">User Profile (${countUser}) </a><br>
                                            </div>
                                        </c:catch>
                                        <c:if test="${exe ne null}}">
                                            ${exe}
                                        </c:if>                                        
                                    </div>
                                </div>
                            </div>
                            <div class="row">

                                <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                                    <div class="row">
                                        <c:catch var="exp_msg">
                                            <div class="themeBox" style="height:auto;margin-bottom:10px;">
                                                <center><div class=boxHeading>Question</div></center>
                                                    <c:if test="${questionByQueryAndLimit eq null or empty questionByQueryAndLimit}">
                                                    <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                </c:if>
                                                <c:if test="${questionByQueryAndLimit ne null and not empty questionByQueryAndLimit}">

                                                    <c:forEach items="${questionByQueryAndLimit}" var="q" varStatus="loop">
                                                        <c:set value="${loop.count}" var="result" scope="page"/>
                                                        <div class="themeBox" style="height:auto;">
                                                            <div align="left" style="font-size: 20px;font-family: serif;">
                                                                View:${q.totalView} &nbsp;  Answer:${q.totalAnswer}  
                                                            </div>
                                                            <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                                <a href="questions?id=${q.questionId}&q=${word.UrlFormat(q.question)}&ref=s" >${q.question} ?</a>
                                                            </div>
                                                        </div>
                                                    </c:forEach><br>
                                                    <c:if test="${countQuestion gt result}">
                                                        <a style="font-size: 20px;color: green;" href="?tab=question&q=${query}">Click here to display ${countQuestion - result} more question</a>
                                                    </c:if>
                                                </c:if>
                                            </div>



                                            <div class="themeBox" style="height:auto;margin-bottom:10px;">
                                                <center><div class=boxHeading>Answer</div></center>
                                                    <c:if test="${answerByQuearyAndLimit eq null or empty answerByQuearyAndLimit}">
                                                    <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                </c:if>
                                                <c:if test="${answerByQuearyAndLimit ne null and not empty answerByQuearyAndLimit}">
                                                    <c:forEach items="${answerByQuearyAndLimit}" var="a" varStatus="loop">
                                                        <c:set value="${loop.count}" var="result" scope="page"/>
                                                        <div class="themeBox" style="height:auto;">
                                                            <div align="left" style="font-size: 20px;font-family: serif;">
                                                                View:${a.totalViewOfQuestion} &nbsp;  Answer:${a.totalAnswerOfQuestion}  
                                                            </div>
                                                            <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                                <a href="questions?id=${a.questionId}&q=${word.UrlFormat(a.question)}&ref=s" >${a.question} ?</a>
                                                            </div>
                                                            <c:out value="${fn:substring(a.answer, 0, 300)}" escapeXml="false"/>&nbsp;<a href="questions?id=${a.questionId}&q=${word.UrlFormat(a.question)}&ref=s" >Continue reading</a>
                                                        </div> 
                                                    </c:forEach>
                                                    <br>
                                                    <c:if test="${countAnswer gt result}">
                                                        <a style="font-size: 20px;color: green;" href="?tab=answer&q=${query}">Click here to display ${countAnswer - result} more answer</a>
                                                    </c:if>
                                                </c:if>
                                            </div>


                                            <div class="themeBox" style="height:auto;margin-bottom:10px;">
                                                <center><div class=boxHeading>Topic</div></center>
                                                    <c:if test="${topicByQuearyAndLimit eq null or empty topicByQuearyAndLimit}">
                                                    <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                </c:if>
                                                <c:if test="${topicByQuearyAndLimit ne null and not empty topicByQuearyAndLimit}">
                                                    <c:set value="0" var="result" scope="page"/>
                                                    <c:forEach var="t" items="${topicByQuearyAndLimit}" varStatus="loop">
                                                        <c:set value="${loop.count}" var="result" scope="page"/>
                                                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-6" style="padding-top: 5px;">
                                                            <a href="topic?id=${t.topicId}&t=${word.UrlFormat(t.topicName)}&ref=s"/>
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
                                                    <br>
                                                    <c:if test="${countTopic gt result}">
                                                        <a style="font-size: 20px;color: green;" href="?tab=topic&q=${query}">Click here to display ${countTopic - result} more topic</a>
                                                    </c:if>
                                                    <c:if test="${result eq 0}">
                                                    </c:if>
                                                </c:if>
                                            </div>




                                            <div class="themeBox" style="height:auto;margin-bottom:10px;">
                                                <center><div class=boxHeading>Profile</div></center>
                                                    <c:if test="${userByQuearyAndLimit eq null or empty userByQuearyAndLimit}">
                                                    <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                </c:if>
                                                <c:if test="${userByQuearyAndLimit ne null and not empty userByQuearyAndLimit}">                                               
                                                    <c:set value="0" var="result" scope="page"/>
                                                    <c:forEach items="${userByQuearyAndLimit}" var="p" varStatus="loop">
                                                        <c:set value="${loop.count}" var="result" scope="page"/>
                                                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-6">
                                                            <img src="images/${p.imageLink}" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                            <a href="profile?user=${p.userName}&id=${p.userId}&ref=s">${p.userFullName}</a>
                                                            <c:catch var="excp">  
                                                                <c:choose>
                                                                    <c:when test="${sessionScope.Session_id_of_user ne null}">
                                                                        <c:choose>
                                                                            <c:when test="${f.IsUserFollowingByUserId(p.userId, sessionScope.Session_id_of_user)}">
                                                                                <input type="button" value="Followed" onclick="return follow_user(this, '${p.userId}', '${sessionScope.Session_id_of_user}', 'Followed');" />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <input type="button" value="Follow" onclick="return follow_user(this, '${p.userId}', '${sessionScope.Session_id_of_user}', 'Follow');" />
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
                                                    <br>
                                                    <c:if test="${countUser gt result}">
                                                        <a style="font-size: 20px;color: green;" href="?tab=profile&q=${query}">Click here to display ${countUser - result} more user</a>
                                                    </c:if>
                                                </c:if>
                                            </div>
                                        </c:catch>
                                        <c:if test="${exp_msg ne null}">
                                            ${exp_msg}
                                        </c:if>

                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="row">
                                        <div class="themeBox" style="height:auto; font-size: 20px;">
                                            <center><div class=boxHeading>Try Through these keyword...</div></center>
                                            <a href="search?q=Class 12" class="a_link">Class 12</a>
                                            <a href="search?q=Class 11" class="a_link">Class 11</a>
                                            <a href="search?q=Class 10" class="a_link">Class 10</a>
                                            <a href="search?q=Class 9" class="a_link">Class 9</a>
                                            <a href="search?q=Class 8" class="a_link">Class 8</a>
                                            <a href="search?q=science" class="a_link">Science</a>
                                            <a href="search?q=physics" class="a_link">Physics</a>
                                            <a href="search?q=chemistry" class="a_link">chemistry</a>
                                            <a href="search?q=biology" class="a_link">Biology</a>
                                            <a href="search?q=math" class="a_link">Math</a>
                                            <a href="search?q=hindi" class="a_link">Hindi</a>
                                            <a href="search?q=english" class="a_link">English</a>
                                            <a href="search?q=gk" class="a_link">GK</a>
                                            <c:forEach var="t" items="${randomTopic}">
                                                <a href="search?q=${t.topicName}" class="a_link">${word.convertStringUpperToLower(t.topicName)}</a>                                            
                                            </c:forEach>
                                        </div>
                                    </div>                                    
                                </div>
                            </div>
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