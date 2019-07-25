<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean class="com.string.name" id="beanclass"  scope="page" />
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="site.jsp" %>
<html lang="en">
    <head>

        <meta charset="UTF-8">
        <meta http-equiv="content-type" content="text/html" charset="utf-8">
        <style type="text/css">
            div.hidden{
                display: none;
            }
            div.visible{
                display: block;
            }
            .comment_box{
                border-style:solid;
                border-width:1px;
                float:left;
                background-color:#d4d4cb;
                width:280px;
                padding-left:20px;
                padding-top:25px;
                padding-bottom:10px;
            }
        </style>
        <script type="text/javascript">

            function showCommentBox() {
                var s_u_id = document.q_com.session_active_user_id.value;
                var q_u_id = document.q_com.id_of_user_who_posted_question.value;
                var q_id = document.q_com.question_id.value;
                if (!(s_u_id === "") && !(q_u_id === "" || q_u_id === null) && !(q_id === "" || q_id === null)) {
                    var div = document.getElementById('comment');
                    div.className = 'visible';
                } else {
                    alert("Please Login to comment!!!");
                }

            }
        </script>
        <script src="ckeditor/ckeditor.js"></script>
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-128307055-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-128307055-1');
        </script> 


        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script type="text/javascript">
            function take_value(el, question_answer_id, action, section) {
            <% if (session.getAttribute("email") == null) { %>
                alert("Please login first");<%
                } else {%>
                el.onclick = function (event) {
                    event.preventDefault();
                };
                if (action === "upvote" && section === "answer") {
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_answer_vote.jsp?question_answer_id=" + question_answer_id + "&action=upvote&section=answer", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }
                if (action === "downvote" && section === "answer") {
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_answer_vote.jsp?question_answer_id=" + question_answer_id + "&action=downvote&section=answer", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }
                if (action === "upvote" && section === "question") {
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_answer_vote.jsp?question_answer_id=" + question_answer_id + "&action=upvote&section=question", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }
                if (action === "downvote" && section === "question") {
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_answer_vote.jsp?question_answer_id=" + question_answer_id + "&action=downvote&section=question", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }
            <% }%>
            }
        </script>        
        <c:if test="${empty param.Id or param.Id eq null}">
            <c:redirect url = "/?ref=idnotfound"/>
        </c:if>
        <c:if test="${not empty param.Id}">
            <sql:update dataSource="jdbc/mydatabase" var="update">
                UPDATE question SET total_view = total_view + 1 WHERE q_id =?;
                <sql:param value="${param.Id}"/>
            </sql:update>
            <sql:query var="seo" dataSource="jdbc/mydatabase">
                SELECT q.q_id AS q_id,q.question AS question,SUBSTRING(a.answer,1,500) AS answer 
                FROM question q LEFT JOIN answer a on q.q_id = a.q_id WHERE q.q_id = ? limit 1;
                <sql:param value="${param.Id}"/>
            </sql:query>
            <c:forEach var="h_seo" items="${seo.rows}">
                <title><c:out value="${h_seo.question}"/>- inquiryhere.com</title>    
                <meta property="og:title" content="<c:out value="${h_seo.question}"/>" />
                <c:if test="${not empty h_seo.answer}">
                    <meta property="og:description" content="<c:out value="${h_seo.answer}"/>"/>
                    <meta property="description" content="<c:out value="${h_seo.answer}"/>"/>
                </c:if>
                <c:if test="${empty h_seo.answer}">
                    <meta property="og:description" content="<c:out value="${h_seo.question}"/>"/>
                    <meta property="description" content="<c:out value="${h_seo.question}"/>"/>
                </c:if>
            </c:forEach>
            <sql:query dataSource="jdbc/mydatabase" var="tag">
                select tag_id as unique_id,
                (select topic_name from topic where unique_id = question_topic_tag.tag_id)topic_name
                from question_topic_tag where question_id =?;
                <sql:param value="${param.Id}"/>
            </sql:query>
            <c:forEach var="tagmgr" items="${tag.rows}">
                <meta property="article:tag" content="<c:out value="${tagmgr.topic_name}"/>" />    
            </c:forEach>
        </c:if>

        <meta property="og:url" content="https://www.inquiryhere.com/Answer.jsp">
        <meta property="og:site_name" content="inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">

    </head>

    <body>

        <div class="main-page-wrapper">
            <!-- Header _________________________________ -->
            <jsp:include page="header.jsp"/>

            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Topic, Related to this question                                   
                                </div>
                                <div>
                                    <ul>
                                        <c:if test="${not empty param.Id}">
                                            <sql:query dataSource="jdbc/mydatabase" var="topic">
                                                select tag_id as unique_id,
                                                (select topic_name from topic where unique_id = question_topic_tag.tag_id) topic_name 
                                                from question_topic_tag  where question_id =?;
                                                <sql:param value="${param.Id}"/>
                                            </sql:query>  
                                            <c:set scope="page" var="count_tag" value="0"/>
                                            <c:forEach items="${topic.rows}" var="tag" varStatus="loop">
                                                <c:set scope="page" var="count_tag" value="${loop.count}"/>
                                                <li><a href="topic.jsp?t=<c:out value="${fn:replace(tag.topic_name,' ','-')}"/>&id=<c:out value="${tag.tag_id}"/>"><c:out value="${beanclass.convertStringUpperToLower(tag.topic_name)}"/></a></li>
                                                </c:forEach>
                                                <c:if test="${count_tag eq 0}">
                                                    <c:out value="No Relted Tag Found"/>
                                                </c:if>
                                            </c:if>
                                        <a href="FollowMoreTopic.jsp">Click here to more topic</a>
                                    </ul>
                                </div>
                            </div> 
                            <div class="clear-fix"></div>
                            <div class="clear-fix"></div>
                        </div>
                        <%
                            //ArrayList<Integer> userId = new ArrayList<>();
                        %>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                                <div class="row">
                                    <!-- Displaying question and detail section-->
                                    <c:if test="${not empty param.Id}">
                                        <sql:query dataSource="jdbc/mydatabase" var="question">
                                            SELECT user.id,user.higher_edu,user.firstname,q.question,q.q_id,q.id,q.total_view,q.vote,date(q.posted_time) as date 
                                            FROM newuser user RIGHT JOIN question q on user.id=q.id where q_id = ?;  
                                            <sql:param value="${param.Id}"/>
                                        </sql:query>
                                        <c:forEach items="${question.rows}" var="q">
                                            <c:set scope="page" var="user_id_who_asked_question" value="${q.id}"/>
                                            <c:set scope="page" var="current_q_string" value="${q.question}"/>
                                            <div class="themeBox" style="height:auto;">
                                                <div align="left" style="font-size: 20px;font-family: serif;">
                                                    <a href="profile.jsp?user=<c:out value="${fn:replace(q.firstname,' ','-')}"/>&ID=<c:out value="${q.id}"/>"><c:out value="${beanclass.convertStringUpperToLower(q.firstname)}"/></a>
                                                    <c:if test="${not empty q.higher_edu}">
                                                        <c:out value="${q.higher_edu}"/>
                                                    </c:if>,
                                                    <c:out value="${q.date}"/>
                                                </div>
                                                <div class="boxHeading marginbot10" style="border-radius: 5px;padding-top: 10px;padding-bottom: 10px;padding-left: 10px; background: #7aab87;">

                                                    <h1 style="font-size: 20px; ">Ques :<c:out value="${q.question}"/> ?</h1> 
                                                    <c:set var="quesTion" value="${q.question}"/>

                                                </div>
                                                <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '<c:out value="${q.q_id}"/>', 'upvote', 'question');" >Upvote(<c:out value="${q.vote}"/>)</a> &nbsp;&nbsp; 
                                                <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '<c:out value="${q.q_id}"/>', 'downvote', 'question');" >Downvote</a> &nbsp;&nbsp;
                                                <a href="javascript:void(0)" value="Comment" onclick="showCommentBox()">Comment</a>&nbsp;&nbsp;

                                                View(<c:out value="${q.total_view}"/>)
                                                <form action="SubmitQuestionComment.jsp" method="get" name="q_com">
                                                    <div class="hidden" id="comment">
                                                        <input type="hidden" name="session_active_user_id" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                                        <input type="hidden" name="id_of_user_who_posted_question" value="<c:out value="${q.id}"/>">
                                                        <input type="hidden" name="question_id" value="<c:out value="${q.q_id}"/>">
                                                        <input type="hidden" name="question" value="<c:out value="${q.question}"/>">
                                                        <textarea name="comments" rows="3" cols="30" required=""></textarea>
                                                        <input type="submit" name="sub" value="Send Comment">
                                                    </div>
                                                </form>
                                            </div>
                                        </c:forEach> 
                                    </c:if>

                                    <!-- Comment on question -->
                                    <c:if test="${not empty param.Id}">
                                        <sql:query var="question_comment" dataSource="jdbc/mydatabase">
                                            SELECT unique_id,user_id,(SELECT firstname FROM newuser WHERE id = comments.user_id )AS fullname,
                                            q_id,comments,time FROM comments WHERE q_id = ? AND user_id IS NOT NULL AND q_id IS NOT NULL;
                                            <sql:param value="${param.Id}"/>
                                        </sql:query>
                                        <div align="right">
                                            <c:forEach items="${question_comment.rows}" var="comment">
                                                <c:out value="${comment.time}"/>,<c:out value="${comment.comments}"/>
                                                <a href="profile.jsp?user=<c:out value="${fn:replace(comment.fullname,' ','-')}"/>&ID=<c:out value="${comment.user_id}"/>"><c:out value="${beanclass.convertStringUpperToLower(comment.fullname)}"/></a><br>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                    <div class="boxHeading marginbot10">Answer:</div>
                                    <!-- Answer of a question -->
                                    <c:if test="${not empty param.Id}">
                                        <sql:query var="answer" dataSource="jdbc/mydatabase">
                                            SELECT user.firstname,ans.answer,ans.a_id,ans.Answer_by_id,ans.total_view,ans.vote 
                                            FROM newuser user RIGHT JOIN answer ans on user.id = ans.Answer_by_id where q_id = ? 
                                            and a_id is not null order by vote desc;
                                            <sql:param value="${param.Id}"/>
                                        </sql:query>
                                        <c:set scope="page" var="ans_count" value="0"/>
                                        <c:forEach items="${answer.rows}" var="ans" varStatus="loop">
                                            <sql:update dataSource="jdbc/mydatabase" var="store_view">
                                                UPDATE answer SET total_view = total_view + 1 WHERE a_id =?;
                                                <sql:param value="${ans.a_id}"/>
                                            </sql:update>
                                            <div class="themeBox" style="height:auto;">
                                                <div class="boxHeading marginbot10" style="font-size: 15px;font-family: Arial, Helvetica, sans-serif;">
                                                    <c:out value="${ans.answer}" escapeXml="false"/>
                                                    <c:set scope="page" var="ans_count" value="${loop.count}"/>
                                                </div>
                                                <div class="questionArea">
                                                    <div class="postedBy">Answer By :<a href="profile.jsp?user=<c:out value="${fn:replace(ans.firstname,' ','-')}"/>&ID=<c:out value="${ans.Answer_by_id}"/>"><c:out value="${beanclass.convertStringUpperToLower(ans.firstname)}"/></a> 
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                            <c:if test="${ans.Answer_by_id eq sessionScope.Session_id_of_user}">
                                                                <a href="edit_a.jsp?q=${quesTion}&q_id=<c:out value="${param.Id}"/>&ans_id=<c:out value="${ans.a_id}"/>&ans_by_id=<c:out value="${ans.Answer_by_id}"/>">Edit</a>
                                                            </c:if>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <a href="javascript:void(0)" onclick="this.style.color = 'red'; return take_value(this, '<c:out value="${ans.a_id}"/>', 'upvote', 'answer');" >Upvote(<c:out value="${ans.vote}"/>)</a>&nbsp;&nbsp; 
                                                <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '<c:out value="${ans.a_id}"/>', 'downvote', 'answer');" >Downvote</a>&nbsp;&nbsp;
                                                <a href="javascript:void(0)" value="Comment" onclick="showAns<c:out value="${ans.a_id}"/>CommentBox()">Comment</a>&nbsp;&nbsp;
                                                <a href="javascript:void(0)">View(<c:out value="${ans.total_view}"/>)</a>
                                                <form action="SubmitAnswerComment.jsp" method="get">
                                                    <div class="hidden" id="Anscomment<c:out value="${ans.a_id}"/>">
                                                        <input type="hidden" name="session_active_user_id" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                                        <input type="hidden" name="id_of_user_who_posted_question" value="<c:out value="${user_id_who_asked_question}"/>">
                                                        <input type="hidden" name="answer_id" value="<c:out value="${ans.a_id}"/>">
                                                        <input type="hidden" name="question_id" value="<c:out value="${param.Id}"/>">
                                                        <input type="hidden" name="question" value="<c:out value="${current_q_string}"/>">
                                                        <textarea name="comments" rows="3" cols="30" required=""></textarea>
                                                        <input type="submit" name="sub" value="Send Comment">
                                                    </div>
                                                </form>

                                                <script type="text/javascript">
                                                    function showAns<c:out value="${ans.a_id}"/>CommentBox() {
                                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                        var div = document.getElementById('Anscomment<c:out value="${ans.a_id}"/>');
                                                        div.className = 'visible';
                                                    </c:if>
                                                    <c:if test="${sessionScope.Session_id_of_user eq null}">
                                                        alert("Please Login First to comment!!!");
                                                    </c:if>
                                                    }
                                                </script>
                                            </div>
                                            <!-- Comment on Answer -->
                                            <div align="right">
                                                <sql:query dataSource="jdbc/mydatabase" var="ans_comment">
                                                    SELECT unique_id,user_id,(SELECT firstname FROM newuser WHERE id = comments.user_id )AS fullname,
                                                    ans_id,comments,time FROM comments WHERE ans_id = ? AND user_id IS NOT NULL AND ans_id IS NOT NULL;
                                                    <sql:param value="${ans.a_id}"/>
                                                </sql:query>
                                                <c:forEach var="cmt" items="${ans_comment.rows}">
                                                    <c:out value="${cmt.time}" />, <c:out value="${cmt.comments}" />
                                                    <a href="profile.jsp?user=<c:out value="${fn:replace(cmt.fullname,' ','-')}"/>&ID=<c:out value="${cmt.user_id}"/>"><c:out value="${beanclass.convertStringUpperToLower(cmt.fullname)}"/></a><br>
                                                </c:forEach>                                               
                                            </div>
                                        </c:forEach>
                                        <c:if test="${ans_count eq 0}" >
                                            <div class="themeBox" style="height:auto;">
                                                <div class="boxHeading marginbot10" style="font-size: 15px;font-family: Arial, Helvetica, sans-serif;">
                                                    <b> <c:out value="No answer found, Be the first person to answer"/></b>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:if>

                                    <form name="submitAnswer" method="post" action="SubmitAnswer.jsp">
                                        <%
                                            String URL = request.getRequestURL() + "?" + request.getQueryString();
                                        %> 
                                        <input type="hidden" name="question" value="<c:out value="${current_q_string}"/>">
                                        <input type="hidden" name="_id_of_user" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                        <input type="hidden" name="q_id" value="${param.Id}">                                        
                                        <input type="hidden" name="URL" value="<%=URL%>">
                                        <c:if test="${sessionScope.Session_id_of_user eq null}">
                                            <textarea class="ckeditor" name="answer" required="" disabled="" >Please Login to answer........</textarea>
                                        </c:if>
                                        <c:if test="${sessionScope.Session_id_of_user ne null}">
                                            <textarea class="ckeditor" name="answer" required="" placeholder="Describe yourself Answer..">
                                                <%
                                                    if (request.getParameter("ans") != null) {
                                                        out.println(request.getParameter("ans"));
                                                    }
                                                %>
                                            </textarea>
                                        </c:if>
                                        <c:if test="${sessionScope.Session_id_of_user eq null}">
                                            <input type="submit" name="Post" value="Please login to submit" disabled=""> 
                                        </c:if>
                                        <c:if test="${sessionScope.Session_id_of_user ne null}">
                                            <input type="submit" name="Post" value="Submit"> 
                                        </c:if>                               
                                    </form>


                                    <div class="clear-fix"></div>

                                </div>
                            </div>

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Related Question
                                </div>
                                <div>
                                    <c:if test="${not empty param.Id}">
                                        <sql:query dataSource="jdbc/mydatabase" var="related_question">
                                            SELECT DISTINCT q.id,q.question,q.q_id FROM question q 
                                            RIGHT JOIN question_topic_tag qtt ON qtt.question_id=q.q_id 
                                            WHERE tag_id IN (SELECT DISTINCT(tag_id) AS tag_id 
                                            FROM question_topic_tag WHERE question_id = ?) AND q_id IS NOT NULL LIMIT 5;
                                            <sql:param value="${param.Id}"/>
                                        </sql:query>
                                        <c:set scope="page" value="0" var="count"/>
                                        <c:forEach items="${related_question.rows}" var="rq" varStatus="loop">
                                            <c:if test="${rq.q_id ne param.Id}" >
                                                <c:set scope="page" value="${loop.count}" var="count"/>
                                                <a href="Answer.jsp?q=<c:out value="${fn:replace(rq.question,' ','-')}"/>&Id=<c:out value="${rq.q_id}"/>" ><c:out value="${rq.question}"/></a><br><br>
                                            </c:if>                                            
                                        </c:forEach>
                                        <c:if test="${count eq 0}">
                                            <c:out value="No related question found"/>
                                        </c:if>
                                    </c:if>
                                </div>

                            </div>
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Question you may like
                                </div>
                                <div>
                                    <sql:query dataSource="jdbc/mydatabase" var="random_question">
                                        select q_id,question from question order by rand() limit 5;
                                    </sql:query>
                                    <c:forEach items="${random_question.rows}" var="r_q">
                                        <a href="Answer.jsp?q=<c:out value="${fn:replace(r_q.question,' ','-')}"/>&Id=<c:out value="${r_q.q_id}"/>" ><c:out value="${r_q.question}"/></a><br><br>
                                    </c:forEach>                             
                                </div>

                            </div>
                            <c:if test="${sessionScope.Session_id_of_user ne null}">
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading">
                                        Complete your profile
                                    </div>
                                    <div>
                                        <jsp:include page="CompleteUserProfile.jsp" />
                                    </div>

                                </div>
                            </c:if>

                            <div class="clear-fix"></div>

                            <div class="clear-fix"></div>
                        </div>
                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->

    </body>
</html>