<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.string.name" id="word" scope="page"/>
<%@include file="validator.jsp" %>
<%@include file="site.jsp" %>
<%---------------Programming with jstl  ---------------------------%>
<c:catch var="ex">
    <c:if test="${not empty param.ID and param.ID ne null}">
        <c:set var="userid" scope="page" value="${param.ID}"/>
    </c:if>
    <c:if test="${not empty param.user and param.user ne null}">
        <c:set var="username" scope="page" value="${param.user}"/>
    </c:if>
    <c:if test="${empty param.ID and param.ID eq null}">
        <c:if test="${sessionScope.Session_id_of_user ne null}">
            <c:set var="userid" scope="page" value="${sessionScope.Session_id_of_user}"/>
        </c:if>  
    </c:if>
    <c:if test="${empty param.ID and not empty param.user}">
        <sql:query dataSource="jdbc/mydatabase" var="f_user_id">
            select id from newuser where username = ?;
            <sql:param value="${param.user}"/>
        </sql:query>
        <c:forEach items="${f_user_id.rows}" var="user_id">
            <c:set var="userid" value="${user_id.id}" scope="page"/>
        </c:forEach>
    </c:if>
    <c:if test="${empty pageScope.username and pageScope.username eq null and empty pageScope.userid and pageScope.userid eq null}">
        <c:redirect url="index.jsp"/>
    </c:if>
    <c:choose>
        <c:when test="${not empty pageScope.userid and pageScope.userid ne null}">
            <sql:query dataSource="jdbc/mydatabase" var="usersql" scope="page">
                SELECT * FROM newuser WHERE ID = ?;
                <sql:param value="${userid}" />
            </sql:query>
        </c:when>
        <c:otherwise >
            <sql:query dataSource="jdbc/mydatabase" var="usersql" scope="page"> 
                SELECT * FROM newuser WHERE username = ?;
                <sql:param value="${username}" />
            </sql:query>                              
        </c:otherwise>
    </c:choose>
</c:catch>
<%-----------------------------------------------------------------------%>
<!DOCTYPE html>
<html lang="en">
    <head>         
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <meta charset="UTF-8">
        <script type="text/javascript">

            function showCommentBox(session_userid) {
                if (session_userid !== null) {
                    var div = document.getElementById('comment');
                    div.className = 'visible';
                }

            }
        </script>

        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:forEach var="user_name" items="${usersql.rows}">
            <title><c:out value="${word.convertStringUpperToLower(user_name.firstname)}"/> - inquiryhere.com</title>
        </c:forEach>


        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <meta property="og:url" content="https://www.inquiryhere.com/" />
        <meta property="og:title" content="Explore the world with your knowladge" />
        <meta property="og:description" content="Explore the world with your knowladge. inquiryhere.com" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-128307055-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());

            gtag('config', 'UA-128307055-1');
        </script> 
        <script type="text/javascript">

            function take_value(el, user_id, id_of_user) {
                if (user_id !== null && id_of_user !== null) {
                    if (el.value === "follow") {
                        el.value = "followed";
                        var http = new XMLHttpRequest();
                        http.open("POST", "<%=DB_AJAX_PATH%>/submit_follower_detail.jsp?val_topic=" + user_id + "&val2_topic=" + id_of_user + "&action=follow", true);
                        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        http.send();
                    } else {
                        el.value = "follow";
                        var http = new XMLHttpRequest();
                        http.open("POST", "<%=DB_AJAX_PATH%>/submit_follower_detail.jsp?val_topic=" + user_id + "&val2_topic=" + id_of_user + "&action=delete", true);
                        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        http.send();
                    }
                } else {
                    alert("Sorry!user sometihng wet wrong!!!!!!");
                }

            }
        </script>
        <script type="text/javascript">
            function hide_mail(el, session_id_of_user, action) {
                if (session_id_of_user !== null && action !== null) {
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/mail_serurity.jsp?session_id_of_user=" + session_id_of_user + "&action=" + action, true);
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

                    <c:if test="${usersql ne null and not empty usersql}">
                        <c:forEach items="${usersql.rows}" var="user" >
                            <c:set var="username" scope="page" value="${user.username}"/>
                            <sql:update dataSource="jdbc/mydatabase" var="updateuser">
                                UPDATE newuser SET total_view = total_view + 1 WHERE ID =? ;
                                <sql:param value="${user.id}" />
                            </sql:update>
                            <div class="row">

                                <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="min-height:1px;">
                                        <div class="boxHeading">
                                            Profile details[ View(<c:out value="${user.total_view}"/>) ]
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                            <img src="images/<c:out value="${user.imagepath}"/>" alt="Image" style="width:90%; margin:10px 0px 0px; border:1px solid #ddd;">
                                            <c:if test="${sessionScope.Session_id_of_user ne null and user.id eq sessionScope.Session_id_of_user}">
                                                <a href="UpdateUserProfile.jsp">UPDATE YOUR PROFILE IMAGE</a>
                                            </c:if>

                                            <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                <c:if test="${sessionScope.Session_id_of_user ne user.id}" ><!--If user watching there own profile they will not get the follow button-->
                                                    <sql:query dataSource="jdbc/mydatabase" var="followbutton">
                                                        SELECT * FROM ak_follower_detail where followers_id = ? and user_id = ? limit 1;
                                                        <sql:param value="${sessionScope.Session_id_of_user}"/>
                                                        <sql:param value="${user.id}"/>
                                                    </sql:query>
                                                    <c:set scope="page" var="found_profile" value="0"/>
                                                    <c:forEach items="${followbutton.rows}" var="fb" varStatus="loop">
                                                        <c:set scope="page" var="found_profile" value="${loop.count}"/>
                                                    </c:forEach>                                                    
                                                    <c:if test="${found_profile ne 0}">
                                                        <input type="button" class="float-right" value="UnFollow" id="myButton1" onclick="return take_value(this, '<c:out value="${user.id}"/>', '<c:out value="${sessionScope.Session_id_of_user}"/>');" />
                                                    </c:if>
                                                    <c:if test="${found_profile eq 0}">
                                                        <input type="button" class="float-right" value="follow" id="myButton1" onclick="return take_value(this, '<c:out value="${user.id}"/>', '<c:out value="${sessionScope.Session_id_of_user}"/>');" />
                                                    </c:if>
                                                </c:if>
                                            </c:if> 
                                        </div>

                                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                                            <table class="profiledetails">
                                                <tr>
                                                    <td>Name </td>
                                                    <td>: <c:out value="${word.convertStringUpperToLower(user.firstname)}"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Mail Id/Phone </td>
                                                    <td>: 
                                                        <c:choose>
                                                            <c:when test="${user.email_s eq 1}">
                                                                <c:choose>
                                                                    <c:when test="${user.id eq sessionScope.Session_id_of_user}">
                                                                        <c:out value="${user.email}"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:out value="Hidden by user"/>
                                                                    </c:otherwise>
                                                                </c:choose>                                                               
                                                            </c:when>
                                                            <c:otherwise >
                                                                <c:out value="${user.email}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        &nbsp;&nbsp;
                                                        <c:if test="${sessionScope.Session_id_of_user ne null}" >
                                                            <c:if test="${user.id eq sessionScope.Session_id_of_user}">
                                                                <c:choose>
                                                                    <c:when test="${user.email_s eq 0}">
                                                                        <a href="" onclick="return hide_mail(this, '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="hide"%>');">Hide your mail</a>
                                                                    </c:when>
                                                                    <c:when test="${user.email_s eq 1}">
                                                                        <a href="" onclick="return hide_mail(this, '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="show"%>');">Display your mail</a> 
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:if>

                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Higher Qualification </td>
                                                    <td>: 
                                                        <c:choose>
                                                            <c:when test="${user.higher_edu eq null or empty user.higher_edu}">
                                                                <c:out value="Not provided yet"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${user.higher_edu}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Best Achievement</td>
                                                    <td>:
                                                        <c:choose>
                                                            <c:when test="${user.best_achievement eq null or empty user.best_achievement}">
                                                                <c:out value="Not provided yet"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${user.best_achievement}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Bio </td>
                                                    <td>: 
                                                        <c:choose>
                                                            <c:when test="${user.bio eq null or empty user.bio}">
                                                                <c:out value="Not provided yet"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${user.bio}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <c:if test="${sessionScope.Session_id_of_user ne null and sessionScope.Session_id_of_user eq user.id}">
                                                    <tr>                                                   
                                                        <td><a href="UpdateUserProfile.jsp">Complete your profile</a></td>
                                                    </tr>
                                                </c:if>

                                                <tr>                                                   
                                                    <td>Appreciation ..</td>  
                                                </tr>
                                            </table>

                                            <div align="right">
                                                <sql:query dataSource="jdbc/mydatabase" var="profilecomment">
                                                    SELECT unique_id,user_id,(SELECT firstname FROM newuser WHERE id = comments.user_id )AS fullname,
                                                    q_id,comments,time FROM comments WHERE userprofileid = ?;
                                                    <sql:param value="${user.id}"/>
                                                </sql:query>
                                                <c:forEach items="${profilecomment.rows}" var="fc">
                                                    <c:out value="${fc.comments}"/>:-
                                                    <c:choose>
                                                        <c:when test="${username eq 'GuestUser'}">
                                                            <b style=color:red;><c:out value="${username}"/></b><br>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="profile.jsp?user=<c:out value="${fc.fullname}"/>&ID=<c:out value="${fc.user_id}"/>"><c:out value="${fc.fullname}"/></a><br>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:out value="__________________"/><br>
                                                </c:forEach>

                                                <c:if test="${sessionScope.Session_id_of_user ne null and user.id ne sessionScope.Session_id_of_user}">
                                                    <a href="javascript:void(0)" value="Comment" onclick="showCommentBox('<c:out value="${sessionScope.Session_id_of_user}"/>')">Write Good Thing About Him</a><br>
                                                </c:if>
                                                <c:if test="${sessionScope.Session_id_of_user eq null}">
                                                    <a href="javascript:void(0)" value="Comment" onclick="alert('Please Login To Comment');">Write Good Thing About Him</a><br>  
                                                </c:if>

                                            </div>
                                            <form action="SubmitUserProfileComment.jsp" method="get">
                                                <div class="hidden" id="comment">
                                                    <input type="hidden" name="username" value="<c:out value="${user.firstname}"/>">
                                                    <input type="hidden" name="session_userid" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                                    <input type="hidden" name="OnCommentUserId" value="<c:out value="${user.id}"/>">
                                                    <textarea name="comments" rows="3" cols="30" required="" placeholder="Write about him and let the world know how good he is.."></textarea>
                                                    <input type="submit" name="sub" value="Send Comment">
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>


                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <%-- TO the number of user activity --%>
                            <%-- For the question --%>
                            <sql:query dataSource="jdbc/mydatabase" var="question">
                                SELECT count(*)cnt FROM question WHERE id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${question.rows}" var="q_count" >
                                <c:set value="${q_count.cnt}" var="q_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Answer --%>
                            <sql:query dataSource="jdbc/mydatabase" var="answer">
                                select count(*) as cnt from answer where  Answer_by_id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${answer.rows}" var="a_count" >
                                <c:set value="${a_count.cnt}" var="a_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Topic --%>
                            <sql:query dataSource="jdbc/mydatabase" var="topic">
                                select count(*) as cnt from topic_followers_detail where user_or_followers_id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${topic.rows}" var="t_count" >
                                <c:set value="${t_count.cnt}" var="t_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Following --%>
                            <sql:query dataSource="jdbc/mydatabase" var="following">
                                select count(*) as cnt from ak_follower_detail where followers_id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${following.rows}" var="fl_count" >
                                <c:set value="${fl_count.cnt}" var="fl_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Followers --%>
                            <sql:query dataSource="jdbc/mydatabase" var="followers">
                                select count(*) as cnt from ak_follower_detail where user_id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${followers.rows}" var="fr_count" >
                                <c:set value="${fr_count.cnt}" var="fr_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Blog --%>
                            <sql:query dataSource="jdbc/mydatabase" var="blog">
                                select count(*)as cnt from blog where blog_posted_by = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${blog.rows}" var="blog_cnt" >
                                <c:set value="${blog_cnt.cnt}" var="b_cnt" scope="page"/>
                            </c:forEach>
                            <div class="themeBox" style="height: auto;">
                                <div class="boxHeading">
                                    User Activity
                                </div>
                                <div>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&tab=Question&ID=<c:out value="${userid}"/>">Question(<c:out value="${q_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&tab=Answer&ID=<c:out value="${userid}"/>">Answer(<c:out value="${a_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&tab=Topic&ID=<c:out value="${userid}"/>">Topic Followed(<c:out value="${t_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&tab=Following&ID=<c:out value="${userid}"/>">Following(<c:out value="${fl_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&tab=Followers&ID=<c:out value="${userid}"/>">Followers(<c:out value="${fr_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&tab=Blog&ID=<c:out value="${userid}"/>">Blog(<c:out value="${b_cnt}"/>)</a><br>
                                </div>

                            </div>

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;margin-bottom:0px;">
                                        <c:choose>
                                            <c:when test="${param.tab ne null and not empty param.tab}">
                                                <c:set value="${param.tab}" scope="page" var="ParametrVariable"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set scope="page" value="Question" var="ParametrVariable"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${ParametrVariable eq 'Question'}">
                                                <center><div class=boxHeading>QUESTION</div></center>    
                                                    <sql:query dataSource="jdbc/mydatabase" var="question">
                                                    SELECT * FROM question WHERE id = ?;
                                                    <sql:param value="${userid}" />                                                                                                  
                                                </sql:query>
                                                    <c:set scope="page" value="0" var="count"/>
                                                    <c:forEach items="${question.rows}" var="q" varStatus="loop">
                                                        <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <br>Q. <a href="Answer.jsp?q=<c:out value="${fn:replace(q.question,' ','-')}"/>&Id=<c:out value="${q.q_id}"/>" ><c:out value="${q.question}"/> ?</a>
                                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                                    <c:if test="${sessionScope.Session_id_of_user ne null and q.id eq sessionScope.Session_id_of_user}">
                                                        <a href="edit_q.jsp?Id=<c:out value="${q.q_id}"/>">edit</a>
                                                    </c:if>  
                                                </c:forEach>
                                                    <c:if test="${count eq 0}">
                                                        <font style="color: red;">User not posted any question yet!!!</font>
                                                    </c:if>
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Answer'}">
                                                <center><div class=boxHeading> ANSWER </div></center>
                                                    <sql:query dataSource="jdbc/mydatabase" var="answer">
                                                    SELECT q.question,q.q_id,SUBSTRING(ans.answer,1,200)short_ans,
                                                    ans.a_id,ans.Answer_by_id from answer ans right join question q 
                                                    on q.q_id = ans.q_id where Answer_by_id = ?;
                                                    <sql:param value="${userid}"/>
                                                </sql:query>
                                                    <c:set scope="page" value="0" var="count"/>
                                                <c:forEach items="${answer.rows}" var="ans">
                                                    <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <br> Q. <a href="Answer.jsp?q=<c:out value="${fn:replace(ans.question,' ','-')}"/>&Id=<c:out value="${ans.q_id}"/>" ><c:out value="${ans.question}"/> ?</a>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <c:if test="${sessionScope.Session_id_of_user ne null and ans.Answer_by_id eq sessionScope.Session_id_of_user}">
                                                        <a href="edit_a.jsp?q_id=<c:out value="${ans.q_id}"/>&ans_id=<c:out value="${ans.a_id}"/>&ans_by_id=<c:out value="${ans.Answer_by_id}"/>">Edit your answer</a>
                                                    </c:if>
                                                    <br>Ans.</b>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${ans.short_ans}" escapeXml="false"/>  <a href="Answer.jsp?Id=<c:out value="${ans.q_id}"/>&q=<c:out value="${fn:replace(ans.question,' ','-')}"/>"> Continue Reading</a>
                                                </c:forEach>
                                                     <c:if test="${count eq 0}">
                                                        <font style="color: red;">User not given any answer yet!!!</font>
                                                    </c:if>
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Topic'}">
                                                <center><div class=boxHeading>TOPIC FOLLOWED </div></center>
                                                    <sql:query dataSource="jdbc/mydatabase" var="topic">
                                                    select t.unique_id,t.topic_name from topic t 
                                                    right join topic_followers_detail de on t.unique_id = de.topic_id 
                                                    where user_or_followers_id= ? and t.unique_id is not null and t.topic_name is not null;
                                                    <sql:param value="${userid}"/>
                                                </sql:query>
                                                    <c:set scope="page" value="0" var="count"/>
                                                <c:forEach items="${topic.rows}" var="t" >
                                                    <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <br><a href="topic.jsp?t=<c:out value="${fn:replace(t.topic_name,' ','-')}"/>&id=<c:out value="${t.unique_id}"/>">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${t.topic_name}"/></a>
                                                </c:forEach>   
                                                     <c:if test="${count eq 0}">
                                                        <font style="color: red;">User not followed any topic yet!!!</font>
                                                    </c:if>
                                                <br><a href=FollowMoreTopic.jsp> Follow more topic</a>  
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Following'}">
                                                <center><div class=boxHeading> FOLLOWING </div></center>
                                                    <sql:query dataSource="jdbc/mydatabase" var="Following">
                                                    select user.ID,user.firstname,user.imagepath from newuser user 
                                                    right join ak_follower_detail ak on ak.user_id=user.ID where followers_id = ?;
                                                    <sql:param value="${userid}"/>
                                                </sql:query>
                                                    <c:set scope="page" value="0" var="count"/>
                                                <c:forEach items="${Following.rows}" var="follow">
                                                    <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                                        <img src="images/<c:out value="${follow.imagepath}"/>" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                        <a href="profile.jsp?user=<c:out value="${fn:replace(follow.firstname,' ','-')}"/>&ID=<c:out value="${follow.ID}"/>"><c:out value="${follow.firstname}"/></a>
                                                    </div> 
                                                </c:forEach>
                                                 <c:if test="${count eq 0}">
                                                        <font style="color: red;">User not following any user yet!!!</font>
                                                    </c:if>
                                                <br><a href=UserProfile.jsp> FOLLOW MORE USER </a>
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Followers'}">
                                                <center><div class=boxHeading> FOLLOWERS </div></center>
                                                    <sql:query dataSource="jdbc/mydatabase" var="followers">
                                                    select user.ID,user.firstname,user.imagepath from newuser user 
                                                    right join ak_follower_detail ak on ak.followers_id=user.ID where user_id = ?;
                                                    <sql:param value="${userid}" />
                                                </sql:query>
                                                    <c:set scope="page" value="0" var="count"/>
                                                <c:forEach items="${followers.rows}" var="followers" >
                                                    <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                                        <img src="images/<c:out value="${followers.imagepath}"/>" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                        <a href="profile.jsp?user=<c:out value="${fn:replace(followers.firstname,' ','-')}"/>&ID=<c:out value="${followers.ID}"/>"><c:out value="${followers.firstname}"/></a>
                                                    </div>
                                                </c:forEach>
                                                 <c:if test="${count eq 0}">
                                                        <font style="color: red;">User don't have any followers yet yet!!!</font>
                                                    </c:if>
                                                <br><a href=UserProfile.jsp> FOLLOW MORE USER </a>
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Blog'}">
                                                <center><div class=boxHeading> BLOG </div></center>
                                                    <sql:query dataSource="jdbc/mydatabase" var="blog">
                                                    SELECT * FROM blog WHERE blog_posted_by = ?;
                                                    <sql:param value="${userid}"/>
                                                </sql:query>
                                                    <c:set scope="page" value="0" var="count"/>
                                                <c:forEach items="${blog.rows}" var="blog">
                                                    <c:set scope="page" value="${loop.count}" var="count"/>
                                                    <br><a href="D_Blog.jsp?sub=<c:out value="${fn:replace(blog.blog_subject,' ','-')}"/>&Blog_Id=<c:out value="${blog.blog_id}"/>"><c:out value="${blog.blog_subject}"/></a>
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
          <div class="modal fade" id="myModal2" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <c:if test="${sessionScope.Session_id_of_user eq null}">
                                <h4 class="modal-title">Post question as guest</h4>    
                            </c:if>
                            <c:if test="${sessionScope.Session_id_of_user ne null}">
                                <h4 class="modal-title">Post your question here</h4>    
                            </c:if>

                        </div>
                        <c:if test="${sessionScope.Session_id_of_user eq null}">
                            <form name="submitquestion" method="get" action="<%=request.getContextPath()%>/guestSaveQuestion">
                            </c:if>
                            <c:if test="${sessionScope.Session_id_of_user ne null}">
                                <form name="submitquestion" method="post" action="SubmitQuestion.jsp">
                                    <input type="hidden" name="userid" value="<%=session.getAttribute("Session_id_of_user")%>">
                                </c:if>                        
                                <div class="modal-body">
                                    <div>
                                        <div>Put your question here <i style="color: red;">*</i></div>
                                        <textarea type="text" class="anstext" name="question" placeholder="Ex: What is,How to.." required=""></textarea>
                                    </div>
                                    <div class="margintop20">
                                        <div>Tag suggestion description <i style="color: red;">*</i></div>
                                        <textarea type="text" class="anstext" name="tag_of_question" placeholder="Ex:Java,Database,c language" required=""></textarea>
                                    </div>
                                    <c:if test="${sessionScope.Session_id_of_user eq null}">
                                        <div class="margintop20">
                                            <div>Guest Name </div>
                                            <textarea type="text" name="guestName" placeholder="Aman Kumar"></textarea>
                                        </div>
                                        <div class="margintop20">
                                            <div>Guest Email (Will not display publicly) </div>
                                            <textarea type="email" name="guestEmail" placeholder="email@gmail.com"></textarea>
                                        </div>
                                    </c:if>

                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn">POST</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
                                </div>
                            </form>
                    </div>
                </div>
            </div>
         <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>

            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>

            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>