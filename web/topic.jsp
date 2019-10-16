<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="site.jsp" %>
<jsp:useBean class="com.topic.topicFollow" id="topic" scope="page"/>
<jsp:useBean class="com.topic.topicDetail" id="tDetail" scope="page"/>
<jsp:useBean class="com.topic.getTopic" id="get_Topic" scope="page"/>
<jsp:useBean class="com.string.name" id="word" scope="page"/>
<jsp:useBean class="com.question.getQuestion" id="question" scope="page"/>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <script>
            function openCity(cityName) {
                var i;
                var x = document.getElementsByClassName("city");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                document.getElementById(cityName).style.display = "block";
            }
        </script>

        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%--
        Getting the data from database
        --%>

        <c:catch var="ex">
            <c:if test="${param.id ne null and not empty param.id}">                                                                   
                <c:forEach items="${tDetail.topic(param.id)}" var="td">
                    <c:set value="${td.topicId}" var="topicId"/>
                    <c:set value="${td.topicName}" var="topicName"/>
                    <c:set value="${td.descHindi}" var="topicDescHindi"/>
                    <c:set value="${td.descEng}" var="topicDescEng"/>
                    <c:set value="${td.imageUrl}" var="topicImgUrl"/>
                    <c:set value="${td.crawl}" var="crawl"/>
                    <c:set value="${td.relatedQuestion}" var="relatedQuestion"/>
                    <c:set value="${td.totalFollowers}" var="totalFollowers"/>
                </c:forEach>
            </c:if>
        </c:catch>
        <c:if test="${ex ne null}">
            ${ex}
        </c:if>

        <%--
       Meta tag For the SEO
        --%>
        <title>${word.convertStringUpperToLower(topicName)} Topic | inquiryhere.com</title>
        <c:catch var="exe">
            <c:choose>
                <c:when test="${topicDescEng ne null and not empty topicDescEng}">
                    <meta property="og:description" content="${topicDescEng}" />
                </c:when>
                <c:when test="${topicDescHindi ne null and not empty topicDescHindi}">
                    <meta property="og:description" content="${topicDescHindi}" />
                </c:when>
            </c:choose>
            <c:if test="${not crawl}">
                <meta name="robots" content="noindex, nofollow" />
            </c:if>
            <c:choose>
                <c:when test="${topicImgUrl ne null and not empty topicImgUrl}">
                    <meta property="og:image" content="${topicImgUrl}" />  
                    <link rel="icon" href="${topicImgUrl}" type="image/png">
                </c:when>
                <c:otherwise>
                    <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
                    <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
                </c:otherwise>
            </c:choose>
        </c:catch>
        <c:if test="${exe ne null}">
            ${exe}
        </c:if>

        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:url" content="https://www.inquiryhere.com/topic.jsp">
        <meta property="og:site_name" content="www.inquiryhere.com" />

        <script type="text/javascript">
            function take_value(el, _topic_id, id_of_user) {
            <% if (session.getAttribute("Session_id_of_user") == null) { %>
                alert("Please login first");<%
                } else {%>
                if (el.value === "follow") {
                    el.value = "followed";
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_follow_topic.jsp?val_topic=" + _topic_id + "&val2_topic=" + id_of_user + "&action=follow", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                } else {
                    el.value = "follow";
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_follow_topic.jsp?val_topic=" + _topic_id + "&val2_topic=" + id_of_user + "&action=delete", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }

            <% }%>
            }
        </script>        
    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <c:if test="${param.id eq null or empty param.id}">
                <c:redirect url="FollowMoreTopic.jsp"/>
            </c:if>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="themeBox" style="height:auto;">
                                    <center> 
                                        <div class="boxHeading">
                                            <div class="row">
                                                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                                    <div class="row">
                                                        <div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">
                                                            <span title="Total followers of ${topicName} is ${totalFollowers} and question is ${relatedQuestion}">
                                                                <c:choose>
                                                                    <c:when test="${topicImgUrl ne null or not empty topicImgUrl}">
                                                                        <img src="${topicImgUrl}" alt="inquiryhere.com" height="100" width="100">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" alt="inquiryhere.com" height="100" width="100">
                                                                    </c:otherwise>
                                                                </c:choose>   
                                                                <h4>${word.convertStringUpperToLower(topicName)}&nbsp;(${totalFollowers})</h4>
                                                            </span>

                                                        </div>
                                                        <div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">

                                                            <c:catch var="exc">
                                                                <c:if test="${sessionScope.Session_id_of_user ne null and param.id ne null and not empty param.id }">
                                                                    <c:choose>
                                                                        <c:when test="${topic.topicFollw(param.id, sessionScope.Session_id_of_user)}">
                                                                            <input type="button" value="Unfollow" id="myButton1" onclick="return take_value(this, '${param.id}', '${sessionScope.Session_id_of_user}');" />
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <input type="button" value="follow" id="myButton1" onclick="return take_value(this, '${param.id}', '${sessionScope.Session_id_of_user}');" />
                                                                        </c:otherwise>
                                                                    </c:choose>                                                       
                                                                </c:if>
                                                            </c:catch>
                                                            <c:if test="${exp ne null}">
                                                                ${exp}
                                                            </c:if> 
                                                        </div>
                                                    </div>

                                                </div>
                                                <c:if test="${topicDescEng ne null and not empty topicDescEng or topicDescHindi ne null and not empty topicDescHindi}">
                                                    <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12" align="left" style="border-style: groove; font-family: serif; font-size: 15px;">
                                                        <div class="w3-bar w3-black">
                                                            <c:if test="${topicDescEng ne null and not empty topicDescEng}">
                                                                <button class="w3-bar-item w3-button" onclick="openCity('English')">English</button>
                                                            </c:if>
                                                            <c:if test="${topicDescHindi ne null and not empty topicDescHindi}">
                                                                <button class="w3-bar-item w3-button" onclick="openCity('Hindi')">Hindi</button>
                                                            </c:if>
                                                        </div> 
                                                        <c:if test="${topicDescEng ne null and not empty topicDescEng}">
                                                            <div id="English" class="w3-container city">
                                                                <p>${topicDescEng}</p>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${topicDescHindi ne null and not empty topicDescHindi}">
                                                            <div id="Hindi" class="w3-container city" style="display: none;">
                                                                <p>${topicDescHindi}</p> 
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${topicDescEng eq null or empty topicDescEng and topicDescHindi ne null and not empty topicDescHindi}">
                                                            <div id="Hindi" class="w3-container city">
                                                                <p>${topicDescHindi}</p> 
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </c:if>
                                            </div>                             
                                        </div>
                                    </center>
                                    <div class="clear-fix"></div>
                                </div>
                                <div class="themeBox" style="height:auto;">
                                    <div>
                                        <h4>Related Question <span title="Totoal ${relatedQuestion} question asked related to this topic">(${relatedQuestion})</span></h4><br>
                                        <c:set scope="page" value="0" var="count"/>
                                        <c:if test="${param.id ne null and not empty param.id}">
                                            <c:set var="rowsPerPage" value="30" />
                                            <c:set var="pageNumber" value="1" />
                                            <c:if test="${param.p ne null}">
                                                <c:set var="pageNumber" value="${param.p}" />
                                            </c:if>

                                            <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
                                            <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
                                            <sql:query dataSource="jdbc/mydatabase" var="relatedQuestion">
                                                select q.id,q.question,q.q_id from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id=?;
                                                <sql:param value="${param.id}"/>
                                            </sql:query>
                                            <c:set var="a">
                                                <fmt:formatNumber value="${relatedQuestion.rowCount/rowsPerPage}" maxFractionDigits="0"/>
                                            </c:set>
                                            <c:set var="b" value="${relatedQuestion.rowCount/rowsPerPage}" />
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
                                            <c:forEach items="${relatedQuestion.rows}" var="rq" begin="${start}" end="${stop}" varStatus="loop">
                                                <c:set scope="page" value="${loop.count}" var="count"/>
                                                <a href="Answer.jsp?q=${fn:replace(fn:replace(rq.question, "|", ""), " ", "-")}&Id=${rq.q_id}" >&nbsp;${rq.question}?</a><br><br>
                                            </c:forEach>
                                            <c:if test="${count eq 0}">
                                                Opps !!!!! No question found related to this topic...
                                            </c:if>
                                            <%--For displaying Previous link --%>
                                            <c:if test="${pageNumber gt 1}">
                                                <a href="topic.jsp?p=${pageNumber - 1}&t=${param.t}&id=${param.id}">Previous</a>
                                            </c:if>
                                            <c:if test="${numberOfPages gt 1}">
                                                <c:forEach begin="1" end="${numberOfPages}" var="i">
                                                    <c:choose>
                                                        <c:when test="${i!=pageNumber}">
                                                            <a href="topic.jsp?p=<c:out value="${i}&t=${param.t}&id=${param.id}"/>"><c:out value="${i}"/></a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <b style="color: red;"><c:out value="${i}"/></b>
                                                        </c:otherwise>        
                                                    </c:choose>       
                                                </c:forEach>  
                                            </c:if>                                            
                                            <%--For displaying Next link --%>
                                            <c:if test="${pageNumber lt numberOfPages}">
                                                <a href="topic.jsp?p=${pageNumber + 1}&t=${param.t}&id=${param.id}">Next</a>
                                            </c:if>
                                        </c:if>
                                    </div>

                                    <div class="clear-fix"></div>

                                </div>

                                <c:if test="${count lt 30}">

                                    <div class="themeBox" style="height:auto;">
                                        <h4 style="background-color: yellow;">Also Read..</h4>
                                        <c:forEach items="${question.getRandomQuestionByLimit(30 - count)}" var="rq">
                                            <a href="Answer.jsp?q=${fn:replace(fn:replace(rq.value, "|", ""), " ", "-")}&Id=${rq.key}" >&nbsp;${rq.value}?</a><br><br>                                           
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Related Topic
                                </div>
                                <div>
                                    <c:catch var="exp">
                                        <c:if test="${param.id ne null and not empty param.id}">

                                            <c:forEach items="${get_Topic.getTopicDetailByRefId(param.id)}" var="rt" varStatus="loop">  
                                                <c:if test="${rt.topicId ne param.id}">
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="border: #000000; border-style: solid;">
                                                        <span title="Total followers of ${rt.topicName} is ${rt.totalFollowers} and total question is ${rt.relatedQuestion}"><a href="topic.jsp?t=${fn:replace(rt.topicName, ' ', '-')}&id=${rt.topicId}">
                                                                <c:choose>
                                                                    <c:when test="${rt.imageUrl ne null or not empty rt.imageUrl}">
                                                                        <img src="${rt.imageUrl}" alt="inquiryhere.com" height="100" width="100">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" alt="inquiryhere.com" height="100" width="100">
                                                                    </c:otherwise>
                                                                </c:choose>       
                                                                ${word.convertStringUpperToLower(rt.topicName)}</a>
                                                        </span>
                                                    </div>      
                                                </c:if> 
                                            </c:forEach> 
                                            <br>  <a href='FollowMoreTopic.jsp' style='color:red;'>Follow More Topic</a>
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${exp ne null}">
                                        ${exp}
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
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->
    </body></html>