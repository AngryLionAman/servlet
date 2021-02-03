<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean class="com.topic.topicFollow" id="topic" scope="page"/>
<jsp:useBean class="com.question.getQuestion" id="question" scope="page"/>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
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


        <c:if test="${topicDetailForSeo ne null}">    
            <c:catch var="ex">
                <c:forEach items="${topicDetailForSeo}" var="td">
                    <c:set value="${td.topicId}" var="topicId"/>
                    <c:set value="${td.topicName}" var="topicName"/>
                    <c:set value="${td.descHindi}" var="topicDescHindi"/>
                    <c:set value="${td.descEng}" var="topicDescEng"/>
                    <c:set value="${td.imageUrl}" var="topicImgUrl"/>
                    <c:set value="${td.crawl}" var="crawl"/>
                    <c:set value="${td.relatedQuestion}" var="relatedQuestion"/>
                    <c:set value="${td.totalFollowers}" var="totalFollowers"/>
                </c:forEach>
            </c:catch>
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
        <meta property="og:locale" content="en">
        <meta property="og:url" content="https://www.inquiryhere.com/topic.jsp">
        <meta property="og:site_name" content="www.inquiryhere.com" />

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
    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                            <div class="row">
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
                                                                <c:choose>
                                                                    <c:when test="${sessionScope.Session_id_of_user ne null}">
                                                                        <c:choose>
                                                                            <c:when test="${topic.topicFollw(topicId, sessionScope.Session_id_of_user)}">
                                                                                <input type="button" value="Unfollow" onclick="return follow_topic(this, '${topicId}', '${sessionScope.Session_id_of_user}');" />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <input type="button" value="follow" onclick="return follow_topic(this, '${topicId}', '${sessionScope.Session_id_of_user}');" />
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
                                    <div><c:set scope="page" value="0" var="count"/>
                                        <c:if test="${allQuestionByTopicId ne null and not empty allQuestionByTopicId}">
                                            <c:forEach items="${allQuestionByTopicId}" var="rq" varStatus="loop">
                                                <c:set scope="page" value="${loop.count}" var="count"/>
                                                <a href="questions?id=${rq.key}&q=${word.UrlFormat(rq.value)}&ref=topic" >&nbsp;${rq.value}?</a><br><br>
                                            </c:forEach>
                                            <c:catch var="msg">
                                                <c:if test="${totalNumberOfpage gt 1}">
                                                    <c:set value="1" var="pageNo"/>
                                                    <c:if test="${param.p ne null}">
                                                        <c:set value="${param.p}" var="pageNo"/>
                                                    </c:if>
                                                    <c:if test="${pageNo gt 1}">
                                                        <a href="topic?t=${topicName}&id=${topicId}&p=${pageNo - 1}">Pre</a>&nbsp;
                                                    </c:if>
                                                    <c:if test="${totalNumberOfpage <= 15}">
                                                        <c:forEach begin="1" end="${totalNumberOfpage}" step="1" varStatus="loop">
                                                            <a href="topic?t=${topicName}&id=${topicId}&p=${loop.count}">${loop.count}</a>&nbsp;
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${totalNumberOfpage > 15}">
                                                        <c:forEach begin="1" end="8" step="1" varStatus="loop">
                                                            <a href="topic?t=${topicName}&id=${topicId}&p=${loop.count}">${loop.count}</a>&nbsp;
                                                        </c:forEach>
                                                        ......
                                                        <c:set scope="page" value="${totalNumberOfpage - 8}" var="startFrom"/>
                                                        <c:forEach begin="${startFrom}" end="${totalNumberOfpage}" step="1">
                                                            <a href="topic?t=${topicName}&id=${topicId}&p=${startFrom}">${startFrom}</a>&nbsp;
                                                            <c:set scope="page" value="${startFrom + 1}" var="startFrom"/>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${pageNo lt totalNumberOfpage}">
                                                        <a href="topic?t=${topicName}&id=${topicId}&p=${pageNo + 1}">Next</a>&nbsp;
                                                    </c:if>
                                                </c:if>
                                            </c:catch>
                                            <c:if test="${msg ne null}">
                                                ${msg}
                                            </c:if>
                                            <c:if test="${count eq 0}">
                                                Opps !!!!! No question found related to this topic...
                                            </c:if>
                                        </c:if>

                                    </div>

                                    <div class="clear-fix"></div>

                                </div>
                                <c:catch var="cnt">
                                    <c:if test="${count lt 30}">
                                        <div class="themeBox" style="height:auto;">
                                            <h4 style="background-color: yellow;">Also Read..</h4>
                                            <c:forEach items="${randomQuestionByLimit}" var="rq">
                                                <a href="questions?id=${rq.key}&q=${word.UrlFormat(rq.value)}&ref=topic" >&nbsp;${rq.value}?</a><br><br>                                           
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </c:catch>
                            </div>
                        </div>
                        <c:if test="${topicDetailByRefId ne null and not empty topicDetailByRefId}">

                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading">
                                        Related Topic
                                    </div>
                                    <div>

                                        <c:catch var="exp">
                                            <c:forEach items="${topicDetailByRefId}" var="rt" varStatus="loop">  
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" style="border: #000000; border-style: solid;">
                                                    <a href="topic?t=${word.UrlFormat(rt.value)}&id=${rt.key}">
                                                        <img src="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" alt="inquiryhere.com" height="100" width="100">
                                                        ${word.convertStringUpperToLower(rt.value)}</a>
                                                </div>  
                                            </c:forEach> 
                                            <br>  <a href='moretopic' style='color:red;'>Follow More Topic</a>

                                        </c:catch>

                                    </div>
                                </div>
                            </div>
                        </c:if>           
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