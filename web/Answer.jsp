<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean class="com.string.name" id="word" scope="page" />
<jsp:useBean class="com.answer.time" id="dateTIme" scope="page" />
<jsp:useBean class="com.answer.getAnswer" id="ans" scope="page" />
<jsp:useBean class="com.answer.comments" id="commentOnAnswer" scope="page" />
<jsp:useBean class="com.index.indexPage" id="Question" scope="page" />
<jsp:useBean class="com.index.comments" id="comment" scope="page"/>
<jsp:useBean class="com.answer.SEO" id="SEO" scope="page" />
<jsp:useBean class="com.question.getQuestion" id="related" scope="page" />
<jsp:useBean class="com.advertise.displayAds" id="ads" scope="page"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="site.jsp" %>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
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
        <script src="ckeditor/ckeditor.js"></script>        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
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
            <c:catch var="ex">
                <c:forEach var="h_seo" items="${SEO.getTitleAndDescripiton(param.Id)}">
                    <title><c:out value="${h_seo.questionTitle}"/>- inquiryhere.com</title>    
                    <c:set scope="page" value="${h_seo.questionTitle}" var="quesTion"/>
                    <meta property="og:title" content="<c:out value="${h_seo.questionTitle}"/>" />
                    <c:if test="${not empty h_seo.questionDescription and h_seo.questionDescription ne null}">
                        <meta property="og:description" content="<c:out value="${h_seo.questionDescription}"/>"/>
                        <meta property="description" content="<c:out value="${h_seo.questionDescription}"/>"/>
                    </c:if>
                    <c:if test="${empty h_seo.questionDescription or h_seo.questionDescription eq null}">
                        <meta property="og:description" content="<c:out value="${h_seo.questionTitle}"/>"/>
                        <meta property="description" content="<c:out value="${h_seo.questionTitle}"/>"/>
                    </c:if>
                </c:forEach>
                <c:set var="myVar" value="inquiryhere.com"/>
                <c:forEach items="${SEO.getQuestionTag(param.Id)}" var="currentItem" varStatus="stat">
                    <meta property="article:tag" content="${currentItem}"/>
                    <c:set var="myVar" value="${myVar},${currentItem}"/>
                </c:forEach>
                <meta name="keywords" content="${myVar}"/>
            </c:catch>
            <c:if test="${ex ne null}">
                ${ex}
            </c:if>           
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
            <jsp:include page="header.jsp"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                            <div>
                                <c:if test="${not empty param.Id}">                                    
                                    <c:set scope="page" var="count_tag" value="0"/>
                                    <c:forEach items="${SEO.getQuestionTagWithId(param.Id)}" var="tag" varStatus="loop">
                                        <c:set scope="page" var="count_tag" value="${loop.count}"/>
                                        <a style="font-size: 15pt;color: #000210;background: #f0f0f0; border-radius: 5px;padding-top: 0px;padding-bottom: 0px;padding-left: 1px;padding-right: 2px;" href="topic.jsp?t=${fn:replace(tag.value,' ','-')}&id=${tag.key}">${word.convertStringUpperToLower(tag.value)}</a>
                                    </c:forEach>
                                    <c:if test="${count_tag eq 0}">
                                        <c:out value=""/><!--old code: if question has no tag...-->
                                    </c:if>
                                </c:if>
                            </div>

                            <div class="row">
                                <!-- Displaying question and detail section-->
                                <c:if test="${not empty param.Id}">
                                    <c:catch var="exp">
                                        <c:forEach var="q" items="${Question.getQuestion(param.Id)}">
                                            <c:set scope="page" value="${q.question}" var="current_q_string"/>
                                            <div class="themeBox" style="height:auto;">
                                                <div align="left" style="font-size: 15px;font-family: serif;">
                                                    <c:if test="${q.userType eq 'guest'}">
                                                        Posted by  <i style="color: red;">${q.userName}</i>
                                                    </c:if>
                                                    <c:if test="${q.userType ne 'guest'}">
                                                        <c:set scope="page" value="${q.userId}" var="user_id_who_asked_question"/>                                                        
                                                        Posted by <a href="profile.jsp?user=${q.userName}&ID=${q.userId}"> ${word.convertStringUpperToLower(q.fullName)}</a>
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
                                                    <h1 style="font-size: 20px;">${q.question}</h1>
                                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                        <c:if test="${q.userId eq sessionScope.Session_id_of_user}">
                                                            <a href="edit_q.jsp?Id=${q.questionId}&q=${q.question}"/>edit</a>
                                                        </c:if>
                                                    </c:if>                                                   
                                                </div>
                                                <div class="questionArea">
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="upvote"%>');" >Upvote(${q.vote})</a>&nbsp;&nbsp; 
                                                    <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '${q.questionId}', '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="downvote"%>');" >Downvote</a>&nbsp;&nbsp; 
                                                    <a href="javascript:void(0)" value="Comment" onclick="showCommentBox()">Comment</a>&nbsp;&nbsp;
                                                    <a href="javascript:void(0)">View(${q.totalView})</a>
                                                    <form action="SubmitQuestionComment.jsp" method="get" name="q_com">
                                                        <div class="hidden" id="comment">
                                                            <input type="hidden" name="session_active_user_id" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                                            <input type="hidden" name="id_of_user_who_posted_question" value="<c:out value="${q.userId}"/>">
                                                            <input type="hidden" name="question_id" value="<c:out value="${q.questionId}"/>">
                                                            <input type="hidden" name="question" value="<c:out value="${q.question}"/>">
                                                            <textarea name="comments" rows="3" cols="30" required=""></textarea>
                                                            <input type="submit" name="sub" value="Send Comment">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:catch>
                                    <c:if test="${exp ne null}">
                                        ${exp}
                                    </c:if>   
                                </c:if>

                                <!-- Comment on question -->
                                <c:if test="${not empty param.Id}">                                    
                                    <c:forEach items="${comment.commentsOnQuestion(param.Id)}" var="c">
                                        <div align="right" style="border-style: groove;">
                                            ${c.comment} :-
                                            <a href="profile.jsp?user=${c.userUserName}&ID=${c.userId}">${word.convertStringUpperToLower(c.userFullName)}</a> ${c.time} 
                                        </div>
                                    </c:forEach>                                    
                                </c:if>
                                <div class="boxHeading marginbot10">Answer:</div>
                                <!-- Answer of a question -->
                                <c:if test="${not empty param.Id}">
                                    <c:set scope="page" var="ans_count" value="0"/>
                                    <c:forEach items="${ans.getAnswerById(param.Id)}" var="a" varStatus="loop">
                                        <div class="themeBox" style="height:auto;">
                                            <div style="padding: 5px; background-color: #BCC6CC; display: inline-block;">
                                                Answer By : <a href="profile.jsp?user=<c:out value="${a.userName}"/>&ID=${a.userId}"><c:out value="${word.convertStringUpperToLower(a.fullName)}"/></a> 
                                            </div>
                                            <c:if test="${sessionScope.Session_id_of_user ne null}">                                                
                                                <c:if test="${a.userId eq sessionScope.Session_id_of_user}">
                                                    <div style="padding: 5px; background-color: #BCC6CC; display: inline-block;" align="right">
                                                        <a href="edit_a.jsp?q=${quesTion}&q_id=<c:out value="${param.Id}"/>&ans_id=<c:out value="${a.answerId}"/>&ans_by_id=<c:out value="${a.userId}"/>">Edit</a>
                                                    </div>
                                                </c:if>
                                            </c:if>
                                            <br><br>
                                            <div class="boxHeading marginbot10" style="font-size: 15px;font-family: Arial, Helvetica, sans-serif;">
                                                <c:out value="${a.answer}" escapeXml="false"/>
                                                <c:set scope="page" var="ans_count" value="${loop.count}"/>
                                            </div>
                                            <a href="javascript:void(0)" onclick="this.style.color = 'red'; return take_value(this, '<c:out value="${a.answerId}"/>', 'upvote', 'answer');" >Upvote(<c:out value="${a.vote}"/>)</a>&nbsp;&nbsp; 
                                            <a href="javascript:void(0)" onclick="this.style.color = 'red';return take_value(this, '<c:out value="${a.answerId}"/>', 'downvote', 'answer');" >Downvote</a>&nbsp;&nbsp;
                                            <a href="javascript:void(0)" value="Comment" onclick="showAns<c:out value="${a.answerId}"/>CommentBox()">Comment</a>&nbsp;&nbsp;
                                            <a href="javascript:void(0)">View(<c:out value="${a.totalView}"/>)</a>

                                            <div class="hidden" id="Anscomment<c:out value="${a.answerId}"/>">
                                                <form action="SubmitAnswerComment.jsp" method="get">
                                                    <input type="hidden" name="session_active_user_id" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                                    <input type="hidden" name="id_of_user_who_posted_question" value="<c:out value="${user_id_who_asked_question}"/>">
                                                    <input type="hidden" name="answer_id" value="<c:out value="${a.answerId}"/>">
                                                    <input type="hidden" name="question_id" value="<c:out value="${param.Id}"/>">
                                                    <input type="hidden" name="question" value="<c:out value="${current_q_string}"/>">
                                                    <textarea name="comments" rows="3" cols="30" required=""></textarea>
                                                    <input type="submit" name="sub" value="Send Comment">
                                                </form>
                                            </div>

                                            <script type="text/javascript">
                                                function showAns<c:out value="${a.answerId}"/>CommentBox() {
                                                <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                    var div = document.getElementById('Anscomment<c:out value="${a.answerId}"/>');
                                                    div.className = 'visible';
                                                </c:if>
                                                <c:if test="${sessionScope.Session_id_of_user eq null}">
                                                    alert("Please Login First to comment!!!");
                                                </c:if>
                                                }
                                            </script>
                                        </div>
                                        <!-- Comment on Answer -->

                                        <c:catch var="exp">
                                            <c:forEach var="cmt" items="${commentOnAnswer.getAnswerCommentByAnswerid(a.answerId)}">
                                                <div align="right" style="border-style: groove;">
                                                    ${cmt.comments} : <a href="profile.jsp?user=${cmt.userName}&ID=${cmt.commentPostedById}"><c:out value="${word.convertStringUpperToLower(cmt.fullName)}"/></a> ${cmt.date}
                                                </div>
                                            </c:forEach>   
                                        </c:catch>
                                        <c:if test="${exp ne null}">
                                            ${exp}
                                        </c:if>
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
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
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


                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading" style="text-align: center; background-color: gold;">
                                    Related Question
                                </div>
                                <div>
                                    <c:if test="${not empty param.Id}">
                                        <c:set scope="page" value="0" var="count"/>
                                        <c:forEach items="${related.getRelatedQuestionById(param.Id)}" var="rq" varStatus="loop">

                                            <c:set scope="page" value="${loop.count}" var="count"/>
                                            <a href="Answer.jsp?q=<c:out value="${fn:replace(fn:replace(rq.value,'|',''),' ','-')}"/>&Id=${rq.key}" >${rq.value}</a><br><br>

                                        </c:forEach>
                                        <c:if test="${count eq 0}">
                                            <c:out value="No related question found"/>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="clear-fix"></div>
                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading" style="text-align: center; background-color: gold;">
                                    Question you may like
                                </div>
                                <div>
                                    <c:forEach items="${related.getRandomQuestion()}" var="r_q">
                                        <a href="Answer.jsp?q=<c:out value="${fn:replace(fn:replace(r_q.value,'|',''),' ','-')}"/>&Id=<c:out value="${r_q.key}"/>" ><c:out value="${r_q.value}"/></a><br><br>
                                    </c:forEach>                             
                                </div>

                            </div>
                        </div>
                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
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
        </div> <!-- /.main-page-wrapper -->
    </body>
</html>