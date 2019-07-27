<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="site.jsp" %>
<html lang="en">
    <head>

        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta charset="UTF-8">

        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Topic | inquiryhere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script type="text/javascript">
            function take_value(el, _topic_id, id_of_user) {
            <% if (session.getAttribute("email") == null) { %>
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
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;">
                                        <center> 
                                            <div class="boxHeading">
                                                <c:catch var="ex">
                                                    <c:if test="${param.id ne null and not empty param.id}">
                                                        <sql:query dataSource="jdbc/mydatabase" var="topicDetail">
                                                            select topic_name,(select count(*) from question_topic_tag 
                                                            where tag_id = topic.unique_id)as askedQuestion,
                                                            (select count(*) from topic_followers_detail where topic_id = topic.unique_id)as totalFollowers 
                                                            from topic where unique_id = ?;
                                                            <sql:param value="${param.id}"/>
                                                        </sql:query>
                                                        <c:forEach items="${topicDetail.rows}" var="td">
                                                            <c:set value="${td.topic_name}" var="topicName"/>
                                                            <c:set value="${td.askedQuestion}" var="totalQuestionAsked"/>
                                                            <c:set value="${td.totalFollowers}" var="totalFollowers"/>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:catch>
                                                <c:if test="${ex ne null}">
                                                    ${ex}
                                                </c:if>

                                                <div>
                                                    <span title="Totoal followers of ${topicName} is ${totalFollowers}"><h4>${topicName}&nbsp;(${totalFollowers})</h4></span>
                                                </div>
                                                <c:catch var="exc">
                                                    <c:if test="${sessionScope.Session_id_of_user ne null and param.id ne null and not empty param.id }">
                                                        <jsp:useBean class="com.topic.topicFollow" id="topic" scope="page"/>
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
                                        </center>
                                        <div class="clear-fix"></div>
                                    </div>
                                    <div class="themeBox" style="height:auto;">
                                        <div>
                                            <h4>Related Question <span title="Totoal ${totalQuestionAsked} question asked related to this topic">(${totalQuestionAsked})</span></h4><br>
                                            <c:if test="${param.id ne null and not empty param.id}">
                                                <c:set var="rowsPerPage" value="20" />
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
                                                <c:forEach items="${relatedQuestion.rows}" var="rq" begin="${start}" end="${stop}">
                                                    <a href="Answer.jsp?q=${fn:replace(rq.question, ' ', '-')}&Id=${rq.q_id}" >&nbsp;${rq.question}?</a><br><br>
                                                </c:forEach>
                                                <%--For displaying Previous link --%>
                                                <c:if test="${pageNumber gt 1}">
                                                    <a href="topic.jsp?p=${pageNumber - 1}&t=${param.t}&id=${param.id}">Previous</a>
                                                </c:if>
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
                                                <%--For displaying Next link --%>
                                                <c:if test="${pageNumber lt numberOfPages}">
                                                    <a href="topic.jsp?p=${pageNumber + 1}&t=${param.t}&id=${param.id}">Next</a>
                                                </c:if>
                                            </c:if>
                                        </div>

                                        <div class="clear-fix"></div>
                                    </div>
                                </div>
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
                                            <sql:query dataSource="jdbc/mydatabase" var="relatedTopic">
                                                select DISTINCT t.unique_id,t.topic_name,
                                                (SELECT COUNT(user_or_followers_id) FROM topic_followers_detail WHERE topic_id=t.unique_id ) as totoal_followers 
                                                from topic t right join question_topic_tag qtt on t.unique_id=qtt.tag_id where question_id 
                                                IN (select question_id from question_topic_tag where tag_id=?)limit 30;
                                                <sql:param value="${param.id}"/>
                                            </sql:query>

                                            <ul>
                                                <c:forEach items="${relatedTopic.rows}" var="rt">
                                                    <c:if test="${rt.unique_id ne param.id}">
                                                        <li><span title="Total followers of ${rt.topic_name} is ${rt.totoal_followers}"><a href="topic.jsp?t=${fn:replace(rt.topic_name, ' ', '-')}&id=${rt.unique_id}">${rt.topic_name}</a>&nbsp;(${rt.totoal_followers})</span></li> 
                                                        </c:if>                                                   
                                                    </c:forEach> 
                                                <li><a href='FollowMoreTopic.jsp' style='color:red;'>Follow More Topic</a></li>
                                            </ul>

                                        </c:if>
                                    </c:catch>
                                    <c:if test="${exp ne null}">
                                        ${exp}
                                    </c:if>

                                </div>

                            </div>
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
    </body></html>