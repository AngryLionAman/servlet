<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.string.name" id="fun" scope="page"/>
<jsp:useBean class="com.search.search" id="search" scope="page"/>
<jsp:useBean class="com.index.topicDetals" id="topic" scope="page"/>
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
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="main-page-wrapper">
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="row">
                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                <div class="row">
                                    <div class="themeBox" style="min-height:available">
                                        <div class="boxHeading">
                                            Your Activity
                                        </div>
                                        <c:set scope="page" value="inquiryhere.com" var="query"/> 
                                        <c:if test="${param.q ne null and not empty param.q}">
                                            <c:set scope="page" value="${fn:trim(param.q)}" var="query"/>
                                        </c:if>
                                        <%--c:set scope="page" value="question" var="tab"/--%>
                                        <c:if test="${param.tab ne null and not empty param.tab}">
                                            <c:set scope="page" value="${param.tab}" var="tab"/> 
                                        </c:if>
                                        <c:catch var="exe">          
                                            <c:set scope="page" value="${search.countQuestion(query)}" var="countQuestion"/>
                                            <c:set scope="page" value="${search.countAnswer(query)}" var="countAnswer"/>
                                            <c:set scope="page" value="${search.countTopic(query)}" var="countTopic"/>
                                            <c:set scope="page" value="${search.countUser(query)}" var="countUser"/>
                                            <div>
                                                <a href="search.jsp?tab=question&q=<c:out value="${query}"/>">Question (${countQuestion})</a><br>
                                                <a href="search.jsp?tab=answer&q=<c:out value="${query}"/>">Answer (${countAnswer})</a><br>
                                                <a href="search.jsp?tab=topic&q=<c:out value="${query}"/>">Topic (${countTopic}) </a><br>
                                                <a href="search.jsp?tab=profile&q=<c:out value="${query}"/>">User Profile (${countUser}) </a><br>
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
                                            <c:if test="${empty tab or tab eq null and not empty query}">
                                                <div class="themeBox" style="height:auto;margin-bottom:10px;">
                                                    <center><div class=boxHeading>Top Five Question</div></center>
                                                        <c:set value="0" var="result" scope="page"/>
                                                        <c:forEach items="${search.getQuestionByQueryAndLimit(query, search.set(5))}" var="q" varStatus="loop">
                                                            <c:set value="${loop.count}" var="result" scope="page"/>
                                                        <div class="themeBox" style="height:auto;">
                                                            <div align="left" style="font-size: 20px;font-family: serif;">
                                                                View:${q.totalView} &nbsp;  Answer:${q.totalAnswer}  
                                                            </div>
                                                            <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                                <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >${q.question} ?</a>
                                                            </div>
                                                        </div>
                                                    </c:forEach><br>
                                                    <c:if test="${countQuestion gt 5}">
                                                        <a style="font-size: 20px;color: green;" href="?tab=question&q=${query}">Click here to display ${countQuestion - 5} more question</a>
                                                    </c:if>
                                                    <c:if test="${result eq 0}">
                                                        <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                    </c:if>
                                                </div>
                                                <div class="themeBox" style="height:auto;margin-bottom:10px;">
                                                    <center><div class=boxHeading>Top Five Answer</div></center>
                                                        <c:set value="0" var="result" scope="page"/>
                                                        <c:forEach items="${search.getAnswerByQuearyAndLimit(query, search.set(5))}" var="a">
                                                            <c:set value="${loop.count}" var="result" scope="page"/>
                                                        <div class="themeBox" style="height:auto;">
                                                            <div align="left" style="font-size: 20px;font-family: serif;">
                                                                View:${a.totalViewOfQuestion} &nbsp;  Answer:${a.totalAnswerOfQuestion}  
                                                            </div>
                                                            <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                                <a href="Answer.jsp?q=${fn:replace(fn:replace(a.question, "|", ""), " ", "-")}&Id=${a.questionId}" >${a.question} ?</a>
                                                            </div>
                                                            <c:out value="${fn:substring(a.answer, 0, 300)}" escapeXml="false"/>&nbsp;<a href="Answer.jsp?q=${fn:replace(fn:replace(a.question, "|", ""), " ", "-")}&Id=${a.questionId}" >Continue reading</a>
                                                        </div> 
                                                    </c:forEach>
                                                    <br>
                                                    <c:if test="${countAnswer gt 5}">
                                                        <a style="font-size: 20px;color: green;" href="?tab=answer&q=${query}">Click here to display ${countAnswer - 5} more answer</a>
                                                    </c:if>
                                                    <c:if test="${result eq 0}">
                                                        <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                    </c:if>
                                                </div>
                                                <div class="themeBox" style="height:auto;margin-bottom:10px;">
                                                    <center><div class=boxHeading>Top Five Topic</div></center>
                                                        <c:set value="0" var="result" scope="page"/>
                                                        <c:forEach var="t" items="${search.getTopicByQuearyAndLimit(query, search.set(5))}">
                                                            <c:set value="${loop.count}" var="result" scope="page"/>
                                                        <a href="topic?t=<c:out value="${fn:replace(fun.convertStringUpperToLower(fn:trim(t.topicName)),' ','-')}"/>&id=<c:out value="${t.topicId}"/>"> 
                                                            <c:if test="${t.imageLink ne null}">
                                                                <img src="${t.imageLink}" alt="${t.topicName}" width="100" height="100">
                                                            </c:if>
                                                            <c:out value="${fun.convertStringUpperToLower(fn:trim(t.topicName))}"/></a>
                                                        </c:forEach>
                                                    <br>
                                                    <c:if test="${countTopic gt 5}">
                                                        <a style="font-size: 20px;color: green;" href="?tab=topic&q=${query}">Click here to display ${countTopic - 5} more topic</a>
                                                    </c:if>
                                                    <c:if test="${result eq 0}">
                                                        <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                    </c:if>
                                                </div>
                                                <div class="themeBox" style="height:auto;margin-bottom:10px;">
                                                    <center><div class=boxHeading>Top Five User Profile</div></center>
                                                        <c:set value="0" var="result" scope="page"/>
                                                        <c:forEach items="${search.getUserByQuearyAndLimit(query, search.set(5))}" var="p">
                                                            <c:set value="${loop.count}" var="result" scope="page"/>
                                                        <a href="profile.jsp?user=<c:out value="${p.userName}"/>&ID=<c:out value="${p.userId}"/>">
                                                            <img src="images/${p.imageLink}" alt="${p.userFullName}" width="100" height="100">
                                                            <c:out value="${fun.convertStringUpperToLower(fn:trim(p.userFullName))}"/>
                                                        </a>
                                                    </c:forEach>
                                                    <br>
                                                    <c:if test="${countUser gt 5}">
                                                        <a style="font-size: 20px;color: green;" href="?tab=profile&q=${query}">Click here to display ${countUser - 5} more user</a>
                                                    </c:if>
                                                    <c:if test="${result eq 0}">
                                                        <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                    </c:if>
                                                </div>
                                            </c:if>
                                        </c:catch>
                                        <c:if test="${exp_msg ne null}">
                                            ${exp_msg}
                                        </c:if>
                                        <div class="themeBox" style="height:auto;margin-bottom:0px;">                                           
                                            <c:catch var="ex">
                                                <c:if test="${not empty tab and not empty query}">
                                                    <c:choose>
                                                        <c:when test="${tab eq 'question'}">
                                                            <center><div class=boxHeading>Question</div></center>
                                                                <c:set value="0" var="result" scope="page"/>
                                                                <c:forEach items="${search.getQuestionByQueryAndLimit(query, search.set('all'))}" var="q" varStatus="loop">
                                                                    <c:set value="${loop.count}" var="result" scope="page"/>
                                                                <div class="themeBox" style="height:auto;">
                                                                    <div align="left" style="font-size: 20px;font-family: serif;">
                                                                        View:${q.totalView} &nbsp;  Answer:${q.totalAnswer}  
                                                                    </div>
                                                                    <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                                        <a href="Answer.jsp?q=${fn:replace(fn:replace(q.question, "|", ""), " ", "-")}&Id=${q.questionId}" >${q.question} ?</a>
                                                                    </div>
                                                                </div>    
                                                            </c:forEach>
                                                            <c:if test="${result eq 0}">
                                                                <font style="color: red;"> Sorry... No result Found, Try some another keyword.. </font>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${tab eq 'answer'}">
                                                            <center><div class=boxHeading>Answer</div></center>
                                                                <c:set value="0" var="result" scope="page"/>
                                                                <c:forEach items="${search.getAnswerByQuearyAndLimit(query, search.set('all'))}" var="a">
                                                                    <c:set value="${loop.count}" var="result" scope="page"/>
                                                                <div class="themeBox" style="height:auto;">
                                                                    <div align="left" style="font-size: 20px;font-family: serif;">
                                                                        View:${a.totalViewOfQuestion} &nbsp;  Answer:${a.totalAnswerOfQuestion}  
                                                                    </div>
                                                                    <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background:#ffffcc; " >
                                                                        <a href="Answer.jsp?q=${fn:replace(fn:replace(a.question, "|", ""), " ", "-")}&Id=${a.questionId}" >${a.question} ?</a>
                                                                    </div>
                                                                    <c:out value="${fn:substring(a.answer, 0, 300)}" escapeXml="false"/>&nbsp;<a href="Answer.jsp?q=${fn:replace(fn:replace(a.question, "|", ""), " ", "-")}&Id=${a.questionId}" >Continue reading</a>
                                                                </div> 
                                                            </c:forEach>
                                                            <c:if test="${result eq 0}">
                                                                <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${tab eq 'topic'}">
                                                            <center><div class=boxHeading>Topic</div></center>
                                                                <c:set value="0" var="result" scope="page"/>
                                                                <c:forEach var="t" items="${search.getTopicByQuearyAndLimit(query, search.set('all'))}">
                                                                    <c:set value="${loop.count}" var="result" scope="page"/>
                                                                <a href="topic?t=<c:out value="${fn:replace(fun.convertStringUpperToLower(fn:trim(t.topicName)),' ','-')}"/>&id=<c:out value="${t.topicId}"/>"> 
                                                                    <c:if test="${t.imageLink ne null}">
                                                                        <img src="${t.imageLink}" alt="${t.topicName}" width="100" height="100">
                                                                    </c:if>
                                                                    <c:out value="${fun.convertStringUpperToLower(fn:trim(t.topicName))}"/></a>    
                                                                </c:forEach>
                                                                <c:if test="${result eq 0}">
                                                                <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                            </c:if>
                                                        </c:when>
                                                        <c:when test="${tab eq 'profile'}">
                                                            <center><div class=boxHeading>User Profile</div></center>
                                                                <c:set value="0" var="result" scope="page"/>
                                                                <c:forEach items="${search.getUserByQuearyAndLimit(query, search.set('All'))}" var="p">
                                                                    <c:set value="${loop.count}" var="result" scope="page"/>
                                                                <a href="profile.jsp?user=<c:out value="${p.userName}"/>&ID=<c:out value="${p.userId}"/>">
                                                                    <img src="images/${p.imageLink}" alt="${p.userFullName}" width="100" height="100">
                                                                    <c:out value="${fun.convertStringUpperToLower(fn:trim(p.userFullName))}"/>
                                                                </a>
                                                            </c:forEach>
                                                            <c:if test="${result eq 0}">
                                                                <font style="color: red;"> Sorry... No result Found, Try searching with our suggested keyword.. </font>
                                                            </c:if>
                                                        </c:when>
                                                    </c:choose>
                                                </c:if>
                                            </c:catch>
                                            <c:if test="${ex ne null}">
                                                ${ex}
                                            </c:if>
                                            <div class="clear-fix"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="row">
                                        <div class="themeBox" style="height:auto; font-size: 20px;">
                                            <center><div class=boxHeading>Try Through these keyword...</div></center>
                                            <a href="?q=Class 12" class="a_link">Class 12</a>
                                            <a href="?q=Class 11" class="a_link">Class 11</a>
                                            <a href="?q=Class 10" class="a_link">Class 10</a>
                                            <a href="?q=Class 9" class="a_link">Class 9</a>
                                            <a href="?q=Class 8" class="a_link">Class 8</a>
                                            <a href="?q=science" class="a_link">Science</a>
                                            <a href="?q=physics" class="a_link">Physics</a>
                                            <a href="?q=chemistry" class="a_link">chemistry</a>
                                            <a href="?q=biology" class="a_link">Biology</a>
                                            <a href="?q=math" class="a_link">Math</a>
                                            <a href="?q=hindi" class="a_link">Hindi</a>
                                            <a href="?q=english" class="a_link">English</a>
                                            <a href="?q=gk" class="a_link">GK</a>
                                            <c:forEach var="t" items="${topic.randomTopic(50)}">
                                                <a href="?q=${t.topicName}" class="a_link">${fun.convertStringUpperToLower(t.topicName)}</a>                                            
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