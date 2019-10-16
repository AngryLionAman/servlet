<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="site.jsp" %>
<%@ include file="validator.jsp" %>
<%@ page isErrorPage="true" errorPage="error.jsp" %>        
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.index.indexPage" id="Question" scope="page"/>
<jsp:useBean class="com.index.comments" id="comment" scope="page"/>
<jsp:useBean class="com.index.topicDetals" id="topic" scope="page"/>
<jsp:useBean class="com.string.name" id="function" scope="page"/>
<jsp:useBean class="com.advertise.displayAds" id="ads" scope="page"/>
<jsp:useBean class="com.fun.helpingFunction" id="fun" scope="page"/>
<html lang="en">
    <head>  
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html" charset="utf-8">
        <title>INQUIRYHERE.COM | HOME PAGE</title>
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png" />
        <link rel="canonical" href="https://www.inquiryhere.com" />

        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">

        <meta property="og:description" content="india's first question answer based social media where experts give 
              you advise and suggestion related to your query .you can ask and share the 
              information which you want to explore.our motive is to be specific according to your demand" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="india first knowledge based social media platform where experts give
              you advise and suggestion related to your query" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="inquiryhere.com" />

        <script type="text/javascript">
            function take_value(el, question_id, sUserid, action) {
                if (question_id.length > 0 && question_id !== null && sUserid !== null && sUserid.length > 0 && action !== null) {
                    el.onclick = function (event) {
                        event.preventDefault();
                    };
                    if (action === "upvote") {
                        var http = new XMLHttpRequest();
                        http.open("POST", "<%=DB_AJAX_PATH%>/submit_question_vote.jsp?question_id=" + question_id + "&action=upvote", true);
                        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        http.send();
                    }
                    if (action === "downvote") {
                        var http = new XMLHttpRequest();
                        http.open("POST", "<%=DB_AJAX_PATH%>/submit_question_vote.jsp?question_id=" + question_id + "&action=downvote", true);
                        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        http.send();
                    }
                } else {
                    alert("please login first!!!");
                }

            }
        </script>
        <style>
            a { color: black; } /* CSS link color */
            /* Style the tab */
            .tab {
                overflow: hidden;
                border: 1px solid #ccc;
                background-color: white;
            }

            /* Style the buttons inside the tab */
            .tab button {
                background-color: inherit;
                color: black;
                font-family: serif;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
                font-size: 17px;
            }

            /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #ddd;
            }

            /* Create an active/current tablink class */
            .tab button.active {
                background-color: #ccc;
            }

        </style>
        <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <script>
            (adsbygoogle = window.adsbygoogle || []).push({
                google_ad_client: "ca-pub-8778688755733551",
                enable_page_level_ads: true
            });
        </script>
    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="clear-fix" align="center" style="font-size: 20px;color: green;background-color: yellow;">
                <c:if test="${param.msg ne null and not empty param.msg}">
                    ${param.msg}
                </c:if>
            </div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="themeBox">
                                <div class="row">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        Also Read
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">
                                        <ul>
                                            <li><a href="search.jsp?q=Class 12">Class 12</a></li>
                                            <li><a href="search.jsp?q=Class 11">Class 11</a></li>
                                            <li><a href="search.jsp?q=Class 10">Class 10</a></li>
                                            <li><a href="search.jsp?q=Class 9">Class 9</a></li>
                                            <li><a href="search.jsp?q=Hindi">Hindi</a></li>
                                            <li><a href="search.jsp?q=English">English</a></li>                                            
                                            <li><a href="search.jsp?q=Political science">Political science</a></li>
                                            <li><a href="search.jsp?q=Biology">Biology</a></li>
                                            <li><a href="search.jsp?q=Social science">Social science</a></li>
                                        </ul> 
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">
                                        <ul>
                                            <li><a href="search.jsp?q=Math">Math</a></li>
                                            <li><a href="search.jsp?q=Physics">Physics</a></li>
                                            <li><a href="search.jsp?q=Chemistry">Chemistry</a></li>
                                            <li><a href="search.jsp?q=Civics">Civics</a></li>
                                            <li><a href="search.jsp?q=History">History</a></li>
                                            <li><a href="search.jsp?q=gk">GK</a></li>
                                        </ul> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="themeBox" style="height:200px;">
                                    <div class="boxHeading">
                                        Post something
                                    </div>
                                    <div><textarea type="text" class="anstext" placeholder="Post you question here" data-toggle="modal" data-target="#myModal2" readonly=""></textarea></div>

                                    <div class="float-right margintop20" style="vertical-align:bottom">
                                        <button type="button" class="btn" data-toggle="modal" data-target="#myModal2">POST</button>
                                        <!-- btn-info btn-lg -->
                                    </div>
                                    <div class="clear-fix"></div>
                                </div>
                            </div>
                            <script async custom-element="amp-auto-ads" src="https://cdn.ampproject.org/v0/amp-auto-ads-0.1.js"></script>
                            <amp-auto-ads type="adsense" data-ad-client="ca-pub-8778688755733551"></amp-auto-ads>
                            <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                            <!-- just down the question and just up the answer on the answer page -->
                            <ins class="adsbygoogle"
                                 style="display:inline-block;width:728px;height:50px"
                                 data-ad-client="ca-pub-8778688755733551"
                                 data-ad-slot="2387926821"></ins>
                            <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
                            </script>
                            <div class="row">
                                <div class="tab">
                                    <a href="<%=request.getContextPath()%>?tab=recent"> <button class="tablinks">Recent</button></a>
                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                        <a href="<%=request.getContextPath()%>?tab=related"><button class="tablinks">Related</button></a>   
                                    </c:if>                                         
                                    <a href="getAllQuestion"><button class="tablinks">All</button></a>   
                                    <a href="unanswered"><button class="tablinks">Unanswered</button></a>   
                                </div>
                                <c:if test="${param.tab eq null or empty param.tab}">
                                    <c:set var="tab" value="recent" scope="page"/>
                                </c:if>
                                <c:if test="${requestScope.tab ne null}">
                                    <c:set var="tab" value="${requestScope.tab}" scope="page"/>
                                </c:if>
                                <c:if test="${param.tab ne null and not empty param.tab}">
                                    <c:choose>
                                        <c:when test="${param.tab eq 'recent'}">
                                            <c:set var="tab" value="recent" scope="page"/>
                                        </c:when>
                                        <c:when test="${param.tab eq 'related'}">
                                            <c:set var="tab" value="related" scope="page"/>
                                        </c:when>
                                        <c:when test="${param.tab eq 'all'}">
                                            <c:set var="tab" value="all" scope="page"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="tab" value="all" scope="page"/>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <!---------------------------------------------------------------------->
                                <c:if test="${tab eq 'recent'}">
                                    <c:if test="${empty param.iPageNo and empty param.cPageNo and param.p eq null and param.page lt 2 or empty param.page}">
                                        <h4>Recent posted question</h4>
                                        <c:catch var="rcExp">
                                            <c:forEach var="q" items="${Question.recentPostQuestion()}">
                                                <div class="themeBox" style="height:auto;">
                                                    <div align="left" style="font-size: 15px;font-family: serif;">
                                                        <c:if test="${q.userType eq 'guest'}">
                                                            Posted by  <i style="color: red;">${function.convertStringUpperToLower(q.userName)}</i>
                                                        </c:if>
                                                        <c:if test="${q.userType ne 'guest'}">
                                                            Posted by <a href="profile.jsp?user=${q.userName}&ID=${q.userId}"> ${function.convertStringUpperToLower(q.fullName)}</a>
                                                            <c:if test="${not empty q.higherEdu}">
                                                                (${q.higherEdu})
                                                            </c:if> 
                                                        </c:if>
                                                        ,
                                                        <c:choose>
                                                            <c:when test="${q.days eq 0}">
                                                                Posted Today
                                                            </c:when>
                                                            <c:otherwise>
                                                                Posted ${q.days} days ago
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                    <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #ffffcc; " >
                                                        <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >${function.convertStringUpperToLower(q.question)} ?</a>
                                                        <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                            <c:if test="${q.userId eq sessionScope.Session_id_of_user}">
                                                                <a href="edit_q.jsp?Id=${q.questionId}&q=${q.question}"/>edit</a>
                                                            </c:if>
                                                        </c:if>                                                   
                                                    </div>
                                                    <div class="questionArea">
                                                        <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="upvote"%>');" >Upvote(${q.vote})</a>&nbsp;&nbsp; 
                                                        <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="downvote"%>');" >Downvote</a>&nbsp;&nbsp; 
                                                        <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >Ans(${q.totalAnswer})</a>&nbsp;&nbsp;
                                                        <a href="javascript:void(0)">View(${q.totalView})</a>
                                                        <!-- Comment on question -->
                                                        <c:forEach items="${comment.commentsOnQuestion(q.questionId)}" var="c">
                                                            <div align="right" style="border-style: groove;">
                                                                ${c.comment}:-
                                                                <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${function.convertStringUpperToLower(c.userFullName)}</a>,${c.time}
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:catch>

                                        <c:if test="${rcExp ne null}">
                                            ${rcExp}
                                        </c:if>                                        
                                    </c:if>
                                </c:if>
                                <!--------------------------------------------------->
                                <c:if test="${sessionScope.Session_id_of_user ne null and tab eq 'related'}">
                                    <h4>Related question</h4>  
                                    <c:catch var="REexp">
                                        <c:set scope="page" value="0" var="count"/>
                                        <c:forEach var="q" items="${Question.relatedQuestion(sessionScope.Session_id_of_user)}" varStatus="loop">
                                            <c:set scope="page" value="${loop.count}" var="count"/>
                                            <div class="themeBox" style="height:auto;">
                                                <div align="left" style="font-size: 15px;font-family: serif;">
                                                    <c:if test="${q.userType eq 'guest'}">
                                                        Posted by  <i style="color: red;">${function.convertStringUpperToLower(q.userName)}</i>
                                                    </c:if>
                                                    <c:if test="${q.userType ne 'guest'}">
                                                        Posted by <a href="profile.jsp?user=${q.userName}&ID=${q.userId}"> ${function.convertStringUpperToLower(q.fullName)}</a>
                                                        <c:if test="${not empty q.higherEdu}">
                                                            (${q.higherEdu})
                                                        </c:if> 
                                                    </c:if>
                                                    ,
                                                    <c:choose>
                                                        <c:when test="${q.days eq 0}">
                                                            Posted Today
                                                        </c:when>
                                                        <c:otherwise>
                                                            Posted ${q.days} days ago
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #ffffcc; " >
                                                    <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >${function.convertStringUpperToLower(q.question)} ?</a>
                                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                        <c:if test="${q.userId eq sessionScope.Session_id_of_user}">
                                                            <a href="edit_q.jsp?Id=${q.questionId}&q=${q.question}"/>edit</a>
                                                        </c:if>
                                                    </c:if>                                                   
                                                </div>
                                                <div class="questionArea">
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="upvote"%>');" >Upvote(${q.vote})</a>&nbsp;&nbsp; 
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="downvote"%>');" >Downvote</a>&nbsp;&nbsp; 
                                                    <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >Ans(${q.totalAnswer})</a>&nbsp;&nbsp;
                                                    <a href="javascript:void(0)">View(${q.totalView})</a>
                                                    <!-- Comment on question -->
                                                    <c:forEach items="${comment.commentsOnQuestion(q.questionId)}" var="c">
                                                        <div align="right" style="border-style: groove;">
                                                            ${c.comment}:-
                                                            <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${function.convertStringUpperToLower(c.userFullName)}</a>,${c.time}
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>    
                                        <c:if test="${count eq 0}">
                                            <font style="color:red; font-size: 20px;"> You may not following any topic, please follow at least five topic.<br><a href="FollowMoreTopic.jsp" style="color: green;">Click here to follow</a></font>
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${REexp ne null}">
                                        ${REexp}
                                    </c:if>
                                </c:if>
                                <!--*******************************************-->
                                <c:if test="${tab eq 'all'}"> 
                                    <h4>Question you may like</h4>                                                                                          
                                    <c:catch var="exp">
                                        <c:forEach var="q" items="${list}">
                                            <div class="themeBox" style="height:auto;">
                                                <div align="left" style="font-size: 15px;font-family: serif;">
                                                    <c:if test="${q.userType eq 'guest'}">
                                                        Posted by  <i style="color: red;">${function.convertStringUpperToLower(q.userName)}</i>
                                                    </c:if>
                                                    <c:if test="${q.userType ne 'guest'}">
                                                        Posted by <a href="profile.jsp?user=${q.userName}&ID=${q.userId}"> ${function.convertStringUpperToLower(q.fullName)}</a>
                                                        <c:if test="${not empty q.higherEdu}">
                                                            (${q.higherEdu})
                                                        </c:if> 
                                                    </c:if>   ,
                                                    <c:choose>
                                                        <c:when test="${q.days eq 0}">
                                                            Posted Today
                                                        </c:when>
                                                        <c:otherwise>
                                                            Posted ${q.days} days ago
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #ffffcc; " >
                                                    <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >${q.question} ?</a>
                                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                        <c:if test="${q.userId eq sessionScope.Session_id_of_user}">
                                                            <a href="edit_q.jsp?Id=${q.questionId}&q=${q.question}"/>edit</a>
                                                        </c:if>
                                                    </c:if>                                                   
                                                </div>
                                                <div class="questionArea">
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="upvote"%>');" >Upvote(${q.vote})</a>&nbsp;&nbsp; 
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="downvote"%>');" >Downvote</a>&nbsp;&nbsp; 
                                                    <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >Ans(${q.totalAnswer})</a>&nbsp;&nbsp;
                                                    <a href="javascript:void(0)">View(${q.totalView})</a>
                                                    <!-- Comment on question -->
                                                    <c:forEach items="${comment.commentsOnQuestion(q.questionId)}" var="c">
                                                        <div align="right" style="border-style: groove;">
                                                            ${c.comment}:-
                                                            <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${function.convertStringUpperToLower(c.userFullName)}</a>,${c.time}
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <c:catch var="msg">
                                            <c:set value="1" var="pageNo"/>
                                            <c:if test="${param.p ne null}">
                                                <c:set value="${param.p}" var="pageNo"/>
                                            </c:if>
                                            <c:if test="${pageNo gt 1}">
                                                <a href="getAllQuestion?p=${pageNo - 1}">Pre</a>&nbsp;
                                            </c:if>
                                            <c:if test="${totalNumberOfpage <= 15}">
                                                <c:forEach begin="1" end="${totalNumberOfpage}" step="1" varStatus="loop">
                                                    <a href="getAllQuestion?p=${loop.count}">${loop.count}</a>&nbsp;
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${totalNumberOfpage > 15}">
                                                <c:forEach begin="1" end="8" step="1" varStatus="loop">
                                                    <a href="getAllQuestion?p=${loop.count}">${loop.count}</a>&nbsp;
                                                </c:forEach>
                                                ......
                                                <c:set scope="page" value="${totalNumberOfpage - 8}" var="startFrom"/>
                                                <c:forEach begin="${startFrom}" end="${totalNumberOfpage}" step="1">
                                                    <a href="getAllQuestion?p=${startFrom}">${startFrom}</a>&nbsp;
                                                    <c:set scope="page" value="${startFrom + 1}" var="startFrom"/>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${pageNo lt totalNumberOfpage}">
                                                <a href="getAllQuestion?p=${pageNo + 1}">Next</a>&nbsp;
                                            </c:if>
                                        </c:catch>
                                        <c:if test="${msg ne null}">
                                            ${msg}
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${exp ne null}">
                                        ${exp}
                                    </c:if>   
                                </c:if>
                                <!---------------------------->
                                <!--*******************Unanswered question************************-->
                                <c:if test="${tab eq 'unanswered'}"> 
                                    <h4>Please feel free to answer this question</h4>                                                                                          
                                    <c:catch var="exp">
                                        <c:set scope="page" value="0" var="count"/>
                                        <c:forEach var="q" items="${list}" varStatus="loop">
                                            <c:set scope="page" value="${loop.count}" var="count"/>
                                            <div class="themeBox" style="height:auto;">
                                                <div align="left" style="font-size: 15px;font-family: serif;">
                                                    <c:if test="${q.userType eq 'guest'}">
                                                        Posted by  <i style="color: red;">${function.convertStringUpperToLower(q.userName)}</i>
                                                    </c:if>
                                                    <c:if test="${q.userType ne 'guest'}">
                                                        Posted by <a href="profile.jsp?user=${q.userName}&ID=${q.userId}"> ${function.convertStringUpperToLower(q.fullName)}</a>
                                                        <c:if test="${not empty q.higherEdu}">
                                                            (${q.higherEdu})
                                                        </c:if> 
                                                    </c:if>   ,
                                                    <c:choose>
                                                        <c:when test="${q.days eq 0}">
                                                            Posted Today
                                                        </c:when>
                                                        <c:otherwise>
                                                            Posted ${q.days} days ago
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #ffffcc; " >
                                                    <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >${q.question} ?</a>
                                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                        <c:if test="${q.userId eq sessionScope.Session_id_of_user}">
                                                            <a href="edit_q.jsp?Id=${q.questionId}&q=${q.question}"/>edit</a>
                                                        </c:if>
                                                    </c:if>                                                   
                                                </div>
                                                <div class="questionArea">
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="upvote"%>');" >Upvote(${q.vote})</a>&nbsp;&nbsp; 
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="downvote"%>');" >Downvote</a>&nbsp;&nbsp; 
                                                    <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >Ans(${q.totalAnswer})</a>&nbsp;&nbsp;
                                                    <a href="javascript:void(0)">View(${q.totalView})</a>
                                                    <!-- Comment on question -->
                                                    <c:forEach items="${comment.commentsOnQuestion(q.questionId)}" var="c">
                                                        <div align="right" style="border-style: groove;">
                                                            ${c.comment}:-
                                                            <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${function.convertStringUpperToLower(c.userFullName)}</a>,${c.time}
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <c:if test="${count eq 0}">
                                            <br><br><font style="color: green; font-size: 20px;align-content: center;">Hurry !!! We don't have any unanswered question</font>
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${exp ne null}">
                                        ${exp}
                                    </c:if>   
                                </c:if>
                                <!---------------------------->
                                <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                                <!-- just down the recent question post -->
                                <ins class="adsbygoogle"
                                     style="display:block"
                                     data-ad-format="fluid"
                                     data-ad-layout-key="-6t+ed+2i-1n-4w"
                                     data-ad-client="ca-pub-8778688755733551"
                                     data-ad-slot="9252283301"></ins>
                                <script>
                                                        (adsbygoogle = window.adsbygoogle || []).push({});
                                </script>
                                <div class="clear-fix"></div>
                            </div>

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" >
                            <div class="boxHeading" style="text-align: center; background-color: gold;">
                                Advertise&nbsp;<a href="ads.jsp" style="size: 10px; font-size: 10px;">(Click here to Advertise with us)</a>
                            </div>
                            <div>
                                <c:catch var="ex">
                                    <c:forEach items="${ads.displayRandomAds()}" var="a">
                                        <a href="${a.forwardUrl}" target="_blank"> 
                                            <img src="images/ads/${a.imageName}" height="${a.imageHeight}" width="${a.imageWidth}" alt="${a.imageAlt}">
                                        </a>
                                    </c:forEach>
                                </c:catch>
                                <c:if test="${ex ne null}">
                                    ${ex}
                                </c:if>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" >
                            <div class="row">
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        <c:if test="${sessionScope.Session_id_of_user ne null}">
                                            Followed Topic
                                        </c:if>
                                        <c:if test="${sessionScope.Session_id_of_user eq null}">
                                            Topic you may like
                                        </c:if>                                  
                                    </div>
                                    <div>
                                        <ul>
                                            <c:choose>
                                                <c:when test="${sessionScope.Session_id_of_user ne null}">
                                                    <c:set scope="page" value="0" var="count"/>
                                                    <c:forEach var="t" items="${topic.userFollowedTopic(sessionScope.Session_id_of_user)}" varStatus="loop">
                                                        <c:set scope="page" value="${loop.count}" var="count"/>
                                                        <li>
                                                            <span title="Total followers of <c:out value="${function.convertStringUpperToLower(t.topicName)}"/> is <c:out value="${t.totalFollowers}"/> and related question is ${t.relatedQuestion}">
                                                                <a href="topic.jsp?t=<c:out value="${fn:replace(t.topicName,' ','+')}"/>&id=<c:out value="${t.topicId}"/>">
                                                                    <c:out value="${function.convertStringUpperToLower(t.topicName)}"/>
                                                                </a> (<c:out value="${t.totalFollowers}"/>) 
                                                            </span>
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${count eq 0}">
                                                        You are not following any topic or may something went wrong.Please follow at least five topic.
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="t" items="${topic.randomTopic(15)}">
                                                        <li>
                                                            <span title="Total followers of <c:out value="${function.convertStringUpperToLower(t.topicName)}"/> is <c:out value="${t.totalFollowers}"/> and related question is ${t.relatedQuestion}">
                                                                <a href="topic.jsp?t=<c:out value="${fn:replace(t.topicName,' ','+')}"/>&id=<c:out value="${t.topicId}"/>">
                                                                    <c:out value="${function.convertStringUpperToLower(t.topicName)}"/>
                                                                </a> (<c:out value="${t.totalFollowers}"/>) 
                                                            </span>
                                                        </li>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                            <a href="FollowMoreTopic.jsp">Click here to more topic</a>
                                        </ul>
                                    </div>
                                </div> 
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        Fun Zone
                                    </div>
                                    <div>
                                        <ul>
                                            <c:catch var="msg">
                                                <c:forEach items="${fun.CategoryDetail()}" var="m">
                                                    <li><a href="fun?category=${m}">${function.convertStringUpperToLower(m)}</a></li>
                                                    </c:forEach>
                                                </c:catch>
                                                <c:if test="${msg ne null}">
                                                    ${msg}
                                                </c:if>
                                        </ul>
                                    </div>
                                </div>
                                <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                                <ins class="adsbygoogle"
                                     style="display:block"
                                     data-ad-format="fluid"
                                     data-ad-layout-key="-6t+ed+2i-1n-4w"
                                     data-ad-client="ca-pub-8778688755733551"
                                     data-ad-slot="9252283301"></ins>
                                <script>
                                                        (adsbygoogle = window.adsbygoogle || []).push({});
                                </script>
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        Shortcut Key
                                    </div>
                                    <div>
                                        <ul>
                                            <li><a href="UserProfile.jsp">Complete User List</a></li>
                                            <li><a href="FollowMoreTopic.jsp">Complete Topic List</a></li>
                                            <li><a href="WriteBlogHere.jsp">Write a Blog</a></li>
                                            <li><a href="optionalquestion">Read Objective Question</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <div class="clear-fix"></div>
                        </div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>        </div> <!-- /.main-page-wrapper -->
    </body>
</html>