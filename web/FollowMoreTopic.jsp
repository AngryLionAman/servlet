<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
<jsp:useBean class="com.topic.topicFollow" id="topic" scope="page"/>
<c:if test="${list eq null}">
    <c:redirect url="moretopic"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Follow More Topic | inquiryere.com </title>        <!-- Main style sheet -->
        <meta property="og:url" content="https://www.inquiryhere.com/FollowMoreTopic.jsp">
        <meta property="og:site_name" content="inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="Follow some topic and get the best result" />
        <meta property="og:description" content="Follow some topic and get the notification if someone posted question realted to your intrest"/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
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
        <jsp:include page="header.jsp"/>
        <div class="main-page-wrapper">
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="themeBox" style="height:auto;margin-bottom:0px;">
                            <div class="boxHeading">
                                Topic, may you like
                            </div> 
                            <c:catch var="ex">                       
                                <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
                                    
                                    <c:forEach var="t" items="${list}">
                                        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4" style="border-style: solid;">
                                            <c:set var="totalQuestion" value="${t.totalQuestion}"/>
                                            <span title="Totoal followers of ${t.topicName} is ${t.totalFollowers} and total question asked related this topic is ${totalQuestion} ">
                                                <a href=topic?t=${fn:replace(t.topicName, ' ', '-')}&id=${t.topicId}>
                                                    <c:choose>
                                                        <c:when test="${t.imageUrl ne null or not empty t.imageUrl}">
                                                            <img src="${t.imageUrl}" alt="inquiryhere.com" height="100" width="100">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" alt="inquiryhere.com" height="100" width="100">
                                                        </c:otherwise>
                                                    </c:choose> 
                                                    ${word.convertStringUpperToLower(t.topicName)}(${t.totalFollowers})(${t.totalQuestion})
                                                </a> 
                                            </span>  
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
                                    <c:catch var="msg">
                                        <c:set value="1" var="pageNo"/>
                                        <c:if test="${param.p ne null}">
                                            <c:set value="${param.p}" var="pageNo"/>
                                        </c:if>
                                        <c:if test="${pageNo gt 1}">
                                            <a href="moretopic?p=${pageNo - 1}">Pre</a>&nbsp;
                                        </c:if>
                                        <c:if test="${totalNumberOfpage <= 15}">
                                            <c:forEach begin="1" end="${totalNumberOfpage}" step="1" varStatus="loop">
                                                <a href="moretopic?p=${loop.count}">${loop.count}</a>&nbsp;
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${totalNumberOfpage > 15}">
                                            <c:forEach begin="1" end="8" step="1" varStatus="loop">
                                                <a href="moretopic?p=${loop.count}">${loop.count}</a>&nbsp;
                                            </c:forEach>
                                            ......
                                            <c:set scope="page" value="${totalNumberOfpage - 8}" var="startFrom"/>
                                            <c:forEach begin="${startFrom}" end="${totalNumberOfpage}" step="1">
                                                <a href="moretopic?p=${startFrom}">${startFrom}</a>&nbsp;
                                                <c:set scope="page" value="${startFrom + 1}" var="startFrom"/>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${pageNo lt totalNumberOfpage}">
                                            <a href="moretopic?p=${pageNo + 1}">Next</a>&nbsp;
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${msg ne null}">
                                        ${msg}
                                    </c:if>
                                    <c:if test="${list eq null or empty list}">
                                        <c:out value="Click here if you not get the all topic"/>
                                        <a href="<%=request.getContextPath()%>/moretopic">Get the all topic detail</a>
                                    </c:if>
                                </div>  
                            </c:catch>
                            <c:if test="${ex ne null}">
                                ${ex}
                            </c:if>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>