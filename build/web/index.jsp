<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.index.comments"%>
<%@page import="com.index.commentPojo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="site.jsp" %>
<%@ include file="validator.jsp" %>
<%@ page isErrorPage="true" errorPage="error.jsp" %>        
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
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
            google.load("elements", "1", {packages: "transliteration"});
        </script> 

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
            <jsp:useBean id="function" class="com.string.name"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">

                        <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
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

                            </div>


                            <div class="row">

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                                    <c:if test="${empty param.iPageNo and empty param.cPageNo and param.p eq null and param.page lt 2 or empty param.page}">
                                        <h4>Recent posted question</h4>
                                        <sql:query dataSource="jdbc/mydatabase" var="question">
                                            SELECT q.total_view,date(q.posted_time) as date,
                                            q.q_id,(select count(*) from answer where q_id = q.q_id) as tac,
                                            q.question,q.vote,user.firstname,user.higher_edu,user.ID as u_ide FROM question 
                                            q RIGHT JOIN newuser user ON user.id = q.id WHERE q.q_id IS NOT NULL 
                                            AND q.question IS NOT NULL ORDER BY q_id DESC LIMIT 5;
                                        </sql:query>
                                        <c:forEach var="question" items="${question.rows}">
                                            <div class="themeBox" style="height:auto;">
                                                <div align="left" style="font-size: 20px;font-family: serif;">
                                                    Posted by <a href="profile.jsp?user=<c:out value="${fn:replace(question.firstname,' ','+')}"/>&ID=<c:out value="${question.id}"/>"> <c:out value="${function.firstName(question.firstname)}"/></a>
                                                    <c:if test="${not empty question.higher_edu}">
                                                        (<c:out value="${question.higher_edu}" />)
                                                    </c:if>    ,
                                                    <c:out value="${question.date}" />
                                                </div>
                                                <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #7aab87; " >
                                                    <a href="Answer.jsp?q=<c:out value="${fn:replace(question.question,' ','+')}"/>&Id=<c:out value="${question.q_id}"/>" ><c:out value="${question.question}"/> ?</a>
                                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                        <c:if test="${question.ID eq sessionScope.Session_id_of_user}">
                                                            <a href="edit_q.jsp?Id=<c:out value="${question.q_id}"/>&q=<c:out value="${fn:replace(question.question,' ','+')}"/>">edit</a>
                                                        </c:if>
                                                    </c:if>
                                                    <sql:update var="inc_view" dataSource="jdbc/mydatabase">
                                                        UPDATE question
                                                        SET total_view = total_view + 1
                                                        WHERE q_id = ?;
                                                        <sql:param value="${question.q_id}"></sql:param>
                                                    </sql:update>
                                                </div>
                                                <div class="questionArea">


                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '<c:out value="${question.q_id}"/>', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="upvote"%>');" >Upvote(<c:out value="${question.vote}"/>)</a>&nbsp;&nbsp; 
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '<c:out value="${question.q_id}"/>', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="downvote"%>');" >Downvote</a>&nbsp;&nbsp; 
                                                    <a href="Answer.jsp?q=<c:out value="${fn:replace(question.question,' ','+')}"/>&Id=<c:out value="${question.q_id}"/>" >Ans(<c:out value="${question.tac}"/>)</a>&nbsp;&nbsp;
                                                    <a href="javascript:void(0)">View(<c:out value="${question.total_view}"/>)</a>
                                                    <!-- Comment on question -->
                                                    <div align="right">
                                                        <sql:query dataSource="jdbc/mydatabase" var="question_comment">
                                                            SELECT unique_id,user_id,(SELECT firstname FROM newuser WHERE id = comments.user_id )AS fullname,
                                                            q_id,comments,time FROM comments WHERE q_id = ? AND user_id IS NOT NULL AND q_id IS NOT NULL;
                                                            <sql:param value="${question.q_id}" ></sql:param>
                                                        </sql:query>
                                                        <c:forEach var="q_com" items="${question_comment.rows}">
                                                            <c:out value="${q_com.comments}"/>
                                                            <a href="profile.jsp?user=<c:out value="${q_com.fullname}"/>&ID=<c:out value="${q_com.user_id}"/>">:-<c:out value="${q_com.fullname}"/></a><br>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                        <h4>Related question</h4>  
                                        <c:catch var="exp">
                                            <c:set var="rowsPerPage" value="10" />
                                            <c:set var="pageNumber" value="1" />
                                            <c:if test="${param.p ne null}">
                                                <c:set var="pageNumber" value="${param.p}" />
                                            </c:if>

                                            <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
                                            <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
                                            <sql:query dataSource="jdbc/mydatabase" var="realted_question">
                                                select DISTINCT q.id,(SELECT firstname FROM newuser WHERE id= q.id) as firstname,
                                                q.q_id,q.total_view,q.question,q.vote,(select count(*) from answer where q_id = q.q_id) as tac from question q 
                                                inner join question_topic_tag qtt on q.q_id = qtt.question_id where tag_id IN 
                                                (select t.unique_id from topic t inner join topic_followers_detail de on t.unique_id = de.topic_id 
                                                where user_or_followers_id = ?) and q.id is not null and q.q_id is not null 
                                                and q.question is not null and (SELECT firstname FROM newuser WHERE id= q.id) is not null ORDER BY RAND();
                                                <sql:param value="${sessionScope.Session_id_of_user}"></sql:param>
                                            </sql:query>
                                            <c:set var="a">
                                                <fmt:formatNumber value="${realted_question.rowCount/rowsPerPage}" maxFractionDigits="0"/>
                                            </c:set>

                                            <c:set var="b" value="${realted_question.rowCount/rowsPerPage}" />

                                            <c:choose>
                                                <c:when test="${a==0}">
                                                    <c:set var="numberOfPages" value="1" scope="page"/>   
                                                </c:when>

                                                <c:when test="${b>a}">
                                                    <c:set var="xxx" value="${b%a}"/>
                                                    <c:if test="${xxx>0}">
                                                        <c:set var="numberOfPages" value="${b-xxx+1}" scope="page"/>   
                                                    </c:if>
                                                </c:when>

                                                <c:when test="${a>=b}">
                                                    <c:set var="numberOfPages" value="${a}" scope="page"/>    
                                                </c:when>
                                            </c:choose>

                                            <c:forEach var="r_question" items="${realted_question.rows}" begin="${start}" end="${stop}">
                                                <div class="themeBox" style="height:auto;">

                                                    <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #7aab87;">
                                                        <a href="Answer.jsp?q=<c:out value="${fn:replace(r_question.question,' ','+')}"/>&Id=<c:out value="${r_question.q_id}"/>" ><c:out value="${r_question.question}"/> ?</a>
                                                        <sql:update var="inc_view" dataSource="jdbc/mydatabase">
                                                            UPDATE question SET total_view = total_view + 1 WHERE q_id =?;
                                                            <sql:param value="${r_question.q_id}"/>
                                                        </sql:update>
                                                    </div>
                                                    <div class="questionArea">
                                                        <div class="postedBy">Posted By : <a href="profile.jsp?user=<c:out value="${r_question.firstname}"/>&ID=<c:out value="${r_question.id}"/>"><c:out value="${function.convertStringUpperToLower(r_question.firstname)}"/></a></div>
                                                    </div>
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '<c:out value="${r_question.q_id}"/>', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="upvote"%>');" >Upvote(<c:out value="${r_question.vote}"/>)</a>&nbsp;&nbsp; 
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '<c:out value="${r_question.q_id}"/>', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="downvote"%>');" >Downvote</a>&nbsp;&nbsp; 
                                                    <a href="Answer.jsp?q=<c:out value="${fn:replace(r_question.question,' ','+')}"/>&Id=<c:out value="${r_question.q_id}"/>" >Ans(<c:out value="${r_question.tac}"/>)</a>&nbsp;&nbsp;                                         
                                                    <a href="javascript:void(0)">View(<c:out value="${r_question.total_view}"/>)</a>
                                                    <!-- Comment on question -->
                                                    <div align="right">
                                                        <sql:query dataSource="jdbc/mydatabase" var="q_com">
                                                            SELECT unique_id,user_id,
                                                            (SELECT firstname FROM newuser WHERE id = comments.user_id )AS fullname,
                                                            q_id,comments,time FROM comments WHERE q_id = ? AND user_id IS NOT NULL AND q_id IS NOT NULL;
                                                            <sql:param value="${r_question.q_id}"/>
                                                        </sql:query>
                                                        <c:forEach var="q_comt" items="${q_com.rows}">
                                                            <c:out value="${q_comt.comments}"/>:-
                                                            <a href="profile.jsp?user=<c:out value="${q_comt.fullname}"/>&ID=<c:out value="${q_comt.user_id}"/>"><c:out value="${q_comt.fullname}"/></a><br>                                                        
                                                        </c:forEach>
                                                    </div>
                                                </div>

                                            </c:forEach>
                                            <div style="border-style: solid;">
                                                <%--For displaying Previous link --%>
                                                <c:if test="${pageNumber gt 1}">
                                                    <a href="index.jsp?p=${pageNumber - 1}">Previous</a>
                                                </c:if>
                                                <c:forEach begin="1" end="${numberOfPages}" var="i">
                                                    <c:choose>
                                                        <c:when test="${i!=pageNumber}">
                                                            <a href="index.jsp?p=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <b style="color: red;"><c:out value="${i}"/></b>
                                                        </c:otherwise>        
                                                    </c:choose>       
                                                </c:forEach>  
                                                <%--For displaying Next link --%>
                                                <c:if test="${pageNumber lt numberOfPages}">
                                                    <a href="index.jsp?p=${pageNumber + 1}">Next</a>
                                                </c:if> 
                                            </div>
                                        </c:catch>
                                        <c:if test="${exp ne null}">
                                            ${exp}
                                        </c:if>
                                    </c:if>
                                    <!--*******************************************-->
                                    <h4>Question you may like</h4>
                                    <c:catch var="exp">
                                        <c:set var="rowsPerPage" value="10" />
                                        <c:set var="pageNumber" value="1" />
                                        <c:if test="${param.page ne null}">
                                            <c:set var="pageNumber" value="${param.page}" />
                                        </c:if>

                                        <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
                                        <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
                                        <sql:query dataSource="jdbc/mydatabase" var="totalQuestion">
                                            select q.q_id,q.question,q.vote,q.total_view,q.posted_time,q.updated_time,
                                            user.id,user.firstname,user.username from question q inner join newuser user on user.id = q.id ORDER BY RAND();
                                        </sql:query>
                                        <c:set var="a">
                                            <fmt:formatNumber value="${totalQuestion.rowCount/rowsPerPage}" maxFractionDigits="0"/>
                                        </c:set>

                                        <c:set var="b" value="${totalQuestion.rowCount/rowsPerPage}" />

                                        <c:choose>
                                            <c:when test="${a==0}">
                                                <c:set var="numberOfPages" value="1" scope="page"/>   
                                            </c:when>

                                            <c:when test="${b>a}">
                                                <c:set var="xxx" value="${b%a}"/>
                                                <c:if test="${xxx>0}">
                                                    <c:set var="numberOfPages" value="${b-xxx+1}" scope="page"/>   
                                                </c:if>
                                            </c:when>

                                            <c:when test="${a>=b}">
                                                <c:set var="numberOfPages" value="${a}" scope="page"/>    
                                            </c:when>
                                        </c:choose>
                                        <jsp:useBean class="com.index.totalAnswer" id="call" scope="page"/>
                                        <c:forEach var="tq" items="${totalQuestion.rows}" begin="${start}" end="${stop}">
                                            <!--update question count..-->
                                            <c:set scope="page" var="questionForComment" value="${tq.q_id}"/>
                                            <div class="themeBox" style="height:auto;">
                                                <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #7aab87;">
                                                    <a href="Answer.jsp?q=${tq.question}&Id=${tq.q_id}" >${tq.question} ?</a>
                                                </div>
                                                <div class="questionArea">
                                                    <div class="postedBy">POSTED_BY :<a href="profile.jsp?user=${tq.username}&ID=${tq.id}"> ${tq.firstname}</a></div>
                                                </div>
                                                <a href="javascript:void(0)" onclick="return take_value(this, '${tq.q_id}', '<c:out value="${sessionScope.Session_id_of_user}"/>', 'upvote');">Upvote(${tq.vote})</a>&nbsp;&nbsp;
                                                <a href="javascript:void(0)" onclick="return take_value(this, '${tq.q_id}', '<c:out value="${sessionScope.Session_id_of_user}"/>', 'upvote');">Downvote</a>&nbsp;&nbsp;
                                                <a href="Answer.jsp?q=${tq.question}&Id=${tq.q_id}">Ans(${call.totalAnswer(tq.q_id)})</a>&nbsp;&nbsp;
                                                <a href="javascript:void(0)">View(${tq.total_view})</a>
                                                <!-- Fetching the Comment on question -->
                                                <div align="right">
                                                    <c:forEach items="${comm.commentsOnQuestion(tq.q_id)}" var="c">
                                                        <p>
                                                            ${c.comment}:-
                                                            <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${c.userFullName}</a>
                                                        </p>
                                                    </c:forEach>
                                                </div>
                                            </div>                                      

                                        </c:forEach>
                                        <%--For displaying Previous link --%>
                                        <c:if test="${pageNumber gt 1}">
                                            <a href="index.jsp?page=${pageNumber - 1}">Previous</a>
                                        </c:if>
                                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${i!=pageNumber}">
                                                    <a href="index.jsp?page=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <b style="color: red;"><c:out value="${i}"/></b>
                                                </c:otherwise>        
                                            </c:choose>       
                                        </c:forEach>  
                                        <%--For displaying Next link --%>
                                        <c:if test="${pageNumber lt numberOfPages}">
                                            <a href="index.jsp?page=${pageNumber + 1}">Next</a>
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${exp ne null}">
                                        ${exp}
                                    </c:if>                                    
                                    <div class="clear-fix"></div>
                                </div>
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
                                                <sql:query var="topic_by_user" dataSource="jdbc/mydatabase">
                                                    select t.unique_id,t.topic_name,
                                                    (select count(topic_id) from topic_followers_detail where topic_id = t.unique_id)as count 
                                                    from topic t right join topic_followers_detail de on t.unique_id = de.topic_id 
                                                    where user_or_followers_id =? and t.unique_id is not null and t.topic_name is not null LIMIT 10;
                                                    <sql:param value="${sessionScope.Session_id_of_user}"></sql:param>
                                                </sql:query>
                                            </c:when>
                                            <c:otherwise>
                                                <sql:query var="topic_by_user" dataSource="jdbc/mydatabase">
                                                    SELECT t.unique_id AS unique_id,t.topic_name AS topic_name,
                                                    (SELECT COUNT(user_or_followers_id) FROM topic_followers_detail WHERE topic_id = t.unique_id) AS count 
                                                    FROM topic t where t.unique_id IS NOT NULL AND t.topic_name IS NOT NULL ORDER BY RAND() LIMIT 10;
                                                </sql:query>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach var="topic" items="${topic_by_user.rows}">
                                            <li><span title="Total followers of <c:out value="${function.convertStringUpperToLower(topic.topic_name)}"/> is <c:out value="${topic.count}"/>"><a href="topic.jsp?t=<c:out value="${fn:replace(topic.topic_name,' ','+')}"/>&id=<c:out value="${topic.unique_id}"/>"><c:out value="${function.convertStringUpperToLower(topic.topic_name)}"/></a> (<c:out value="${topic.count}"/>) </span></li>
                                            </c:forEach>

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
                                <div><a href="Login.jsp">Click here to login</a></div>
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