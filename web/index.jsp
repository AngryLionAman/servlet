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
<html lang="en">
    <style>
        body {font-family: Arial;}

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
    <head>  
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html" charset="utf-8">
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-128307055-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-128307055-1');
        </script> 
        <title>INQUIRYHERE.COM | HOME PAGE</title>
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <meta property="og:description" content="india's first question answer based social media where experts give 
              you advise and suggestion related to your query .you can ask and share the 
              information which you want to explore.our motive is to be specific according to your demand" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="india first knowledge based social media platform where experts give
              you advise and suggestion related to your query" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="inquiryhere.com" />

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>

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
        </style>

    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="themeBox" style="height:200px;">
                                    <div class="boxHeading">
                                        Post something
                                    </div>
                                    <div><textarea type="text" class="anstext" placeholder="Post you question here" data-toggle="modal" data-target="#myModal" readonly=""></textarea></div>

                                    <div class="float-right margintop20" style="vertical-align:bottom">
                                        <button type="button" class="btn" data-toggle="modal" data-target="#myModal">POST</button>
                                        <!-- btn-info btn-lg -->
                                    </div>
                                    <div class="clear-fix"></div>
                                </div>
                            </div>

                            <div class="row">
                                    <div class="tab">
                                        <a href="?tab=recent"> <button class="tablinks">Recent</button></a>
                                        <c:if test="${sessionScope.Session_id_of_user ne null}">
                                            <a href="?tab=related"><button class="tablinks">Related</button></a>   
                                        </c:if>                                         
                                        <a href="?tab=all"><button class="tablinks">All</button></a>   
                                    </div>
                                    <c:if test="${param.tab eq null or empty param.tab}">
                                        <c:set var="tab" value="recent" scope="page"/>
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
                                                        <div align="left" style="font-size: 20px;font-family: serif;">
                                                            Posted by <a href="profile.jsp?user=${q.userName}&ID=${q.userId}"> ${q.fullName}</a>
                                                            <c:if test="${not empty q.higherEdu}">
                                                                (${q.higherEdu})
                                                            </c:if>    ,
                                                            <c:out value="${q.date}" />
                                                        </div>
                                                        <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #7aab87; " >
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
                                                            <div align="right">
                                                                <c:forEach items="${comment.commentsOnQuestion(q.questionId)}" var="c">
                                                                    <p>
                                                                        ${c.comment}:-
                                                                        <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${c.userFullName}</a>
                                                                    </p>
                                                                </c:forEach>
                                                            </div>
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
                                            <c:forEach var="rq" items="${Question.relatedQuestion(sessionScope.Session_id_of_user)}">
                                                <div class="themeBox" style="height:auto;">

                                                    <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #7aab87;">
                                                        <a href="Answer.jsp?q=${fn:replace(fn:replace(rq.question, '|',''),' ','-')}&Id=${rq.questionId}" ><c:out value="${rq.question}"/> ?</a>
                                                        <!--update the question count-->
                                                    </div>
                                                    <div class="questionArea">
                                                        <div class="postedBy">Posted By : <a href="profile.jsp?user=${rq.userName}&ID=${rq.userId}"><c:out value="${function.convertStringUpperToLower(rq.fullName)}"/></a></div>
                                                    </div>
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${rq.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="upvote"%>');" >Upvote(${rq.vote})</a>&nbsp;&nbsp; 
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${rq.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="downvote"%>');" >Downvote</a>&nbsp;&nbsp; 
                                                    <a href="Answer.jsp?q=${fn:replace(fn:replace(rq.question, '|',''),' ','-')}&Id=${rq.questionId}" >Ans(${rq.totalAnswer})</a>&nbsp;&nbsp;                                         
                                                    <a href="javascript:void(0)">View(${rq.totalView}) </a>
                                                    <!-- Comment on question -->
                                                    <div align="right">
                                                        <c:forEach items="${comment.commentsOnQuestion(rq.questionId)}" var="c">
                                                            <p>
                                                                ${c.comment}:-
                                                                <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${c.userFullName}</a>
                                                            </p>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </c:forEach>                                           
                                        </c:catch>
                                        <c:if test="${REexp ne null}">
                                            ${REexp}
                                        </c:if>
                                    </c:if>
                                    <!--*******************************************-->
                                    <c:if test="${tab eq 'all'}"> 
                                        <h4>Question you may like</h4>                                                                                          
                                        <c:catch var="exp">
                                            <c:forEach var="q" items="${Question.questionYouMayLike()}">
                                                <div class="themeBox" style="height:auto;">
                                                    <div align="left" style="font-size: 20px;font-family: serif;">
                                                        Posted by <a href="profile.jsp?user=${q.userName}&ID=${q.userId}"> ${q.fullName}</a>
                                                        <c:if test="${not empty q.higherEdu}">
                                                            (${q.higherEdu})
                                                        </c:if>    ,
                                                        <c:out value="${q.date}" />
                                                    </div>
                                                    <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #7aab87; " >
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
                                                        <div align="right">
                                                            <c:forEach items="${comment.commentsOnQuestion(q.questionId)}" var="c">
                                                                <p>
                                                                    ${c.comment}:-
                                                                    <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${c.userFullName}</a>
                                                                </p>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:catch>
                                        <c:if test="${exp ne null}">
                                            ${exp}
                                        </c:if>   
                                    </c:if>
                                    <!---------------------------->
                                    <div class="clear-fix"></div>
                            </div>

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" >
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
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
                                                <c:forEach var="t" items="${topic.userFollowedTopic(sessionScope.Session_id_of_user)}">
                                                    <li>
                                                        <span title="Total followers of <c:out value="${function.convertStringUpperToLower(t.topicName)}"/> is <c:out value="${t.totalFollowers}"/> and related question is ${t.relatedQuestion}"><a href="topic.jsp?t=<c:out value="${fn:replace(t.topicName,' ','+')}"/>&id=<c:out value="${t.topicId}"/>"><c:out value="${function.convertStringUpperToLower(t.topicName)}"/></a> (<c:out value="${t.totalFollowers}"/>) </span>
                                                    </li>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="t" items="${topic.randomTopic()}">
                                                    <li>
                                                        <span title="Total followers of <c:out value="${function.convertStringUpperToLower(t.topicName)}"/> is <c:out value="${t.totalFollowers}"/> and related question is ${t.relatedQuestion}"><a href="topic.jsp?t=<c:out value="${fn:replace(t.topicName,' ','+')}"/>&id=<c:out value="${t.topicId}"/>"><c:out value="${function.convertStringUpperToLower(t.topicName)}"/></a> (<c:out value="${t.totalFollowers}"/>) </span>
                                                    </li>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                        <a href="FollowMoreTopic.jsp">Click here to more topic</a>
                                    </ul>
                                </div>
                            </div> 
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Fun Zone
                                </div>
                                <div>
                                    <jsp:include page="funZoneList.jsp"></jsp:include>
                                    </div>
                                </div>
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading">
                                        Education Zone
                                    </div>
                                    <div>
                                    <jsp:include page="eduZoneList.jsp"></jsp:include>
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


                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Post your question here</h4>
                            </div>
                        <%if (session.getAttribute("Session_id_of_user") != null) {%>
                        <form name="submitquestion" method="post" action="SubmitQuestion.jsp">
                            <input type="hidden" name="userid" value="<%=session.getAttribute("Session_id_of_user")%>">
                            <div class="modal-body">
                                <div>
                                    <div>Put your question here</div>
                                    <textarea type="text" class="anstext" name="question" placeholder="Ex: What is,How to.." required=""></textarea>
                                </div>
                                <div class="margintop20">
                                    <div>Tag suggestion description </div>
                                    <textarea type="text" class="anstext" name="tag_of_question" placeholder="Ex:Java,Database,c language" required=""></textarea></div>
                                <!-- <p>Some text in the modal.</p> -->
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn">POST</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
                            </div>
                        </form>
                        <%} else {%>
                        <div class="modal-body">
                            <div>
                                <div><h4 style="color: red;">Please login first!!!</h4></div>
                                <div><a href="login.jsp">Click here to login</a></div>
                            </div>
                        </div>
                        <div class="modal-footer">                                                    
                            <button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
                        </div>

                        <% }%>
                    </div>

                </div>
            </div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->
    </body>
</html>