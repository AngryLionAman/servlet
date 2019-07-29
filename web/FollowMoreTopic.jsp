<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Follow More Topic | inquiryere.com </title>
        <!-- Main style sheet -->
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script type="text/javascript">
            function take_value(el, _topic_id, id_of_user) {
            <% if (session.getAttribute("Session_id_of_user") == null) { %>
                alert("Please login first or refresh the page!!");<%
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

        <jsp:include page="header.jsp"/>
        <div class="main-page-wrapper">
            <div class="bodydata">
                <div class="themeBox" style="height:auto;margin-bottom:0px;">
                    <div class="boxHeading">
                        Topic, may you like
                    </div>
                    <c:catch var="ex">
                        <c:set var="rowsPerPage" value="100" />
                        <c:set var="pageNumber" value="1" />
                        <c:if test="${param.p ne null}">
                            <c:set var="pageNumber" value="${param.p}" />
                        </c:if>

                        <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
                        <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>

                        <sql:query var="topic" dataSource="jdbc/mydatabase" >
                            select t.unique_id,t.topic_name,count(tf.user_or_followers_id)as count from topic t left join topic_followers_detail tf on tf.topic_id = t.unique_id group by t.unique_id ;
                        </sql:query>

                        <c:set var="a">
                            <fmt:formatNumber value="${topic.rowCount/rowsPerPage}" maxFractionDigits="0"/>
                        </c:set>

                        <c:set var="b" value="${topic.rowCount/rowsPerPage}" />

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
                        <jsp:useBean class="com.followmoretopic.totalQuestion" id="fun" scope="page"/>
                        <c:forEach var="t" items="${topic.rows}" begin="${start}" end="${stop}">
                            <c:set var="totalQuestion" value="${fun.totalQestion(t.unique_id)}"/>
                            <div style="width:auto;height:52px;border:1px solid #000;float: left; margin-right: 5px; margin-bottom: 5px;" class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <span title="Totoal followers of ${t.topic_name} is ${t.count} and total question asked related this topic is ${totalQuestion} "><a href=topic.jsp?t=${fn:replace(t.topic_name, ' ', '-')}&id=${t.unique_id}>${t.topic_name}(${totalQuestion})(${t.count})</a> </span>
                                <c:if test="${sessionScope.Session_id_of_user ne null}">
                                    <sql:query dataSource="jdbc/mydatabase" var="found">
                                        SELECT count(*) as cnt FROM topic_followers_detail where topic_id = ? and user_or_followers_id = ? limit 1;
                                        <sql:param value="${t.unique_id}"/>
                                        <sql:param value="${sessionScope.Session_id_of_user}"/>
                                    </sql:query>
                                    <c:forEach items="${found.rows}" var="f" varStatus="loop">
                                        <c:set value="${f.cnt}" var="count"/>
                                    </c:forEach>
                                    <c:if test="${count eq 1}">
                                        <input type="button" value="Unfollow" id="myButton1" onclick="return take_value(this, '${t.unique_id}', '${sessionScope.Session_id_of_user}');" />
                                    </c:if>
                                    <c:if test="${count ne 1}">
                                        <input type="button" value="follow" id="myButton1" onclick="return take_value(this, '${t.unique_id}', '${sessionScope.Session_id_of_user}');" />
                                    </c:if>
                                </c:if>
                                <c:if test="${sessionScope.Session_id_of_user eq null}">
                                    <input type="button" value="follow" id="myButton1" onclick="alert('Please Login To Follow');" />
                                </c:if>
                            </div>
                        </c:forEach>

                        <div class="clear-fix" style="border-style: solid;border-width: 1px;">
                            <%--For displaying Previous link --%>
                            <c:if test="${pageNumber gt 1}">
                                <a href="FollowMoreTopic.jsp?p=${pageNumber - 1}">Previous</a>
                            </c:if>
                            <c:forEach begin="1" end="${numberOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${i!=pageNumber}">
                                        <a href="FollowMoreTopic.jsp?p=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <b style="color: red;"><c:out value="${i}"/></b>
                                    </c:otherwise>        
                                </c:choose>       
                            </c:forEach>  
                            <%--For displaying Next link --%>
                            <c:if test="${pageNumber lt numberOfPages}">
                                <a href="FollowMoreTopic.jsp?p=${pageNumber + 1}">Next</a>
                            </c:if>
                        </div>   

                    </c:catch>
                    <c:if test="${ex ne null}">
                        ${ex}
                    </c:if>
                </div>

            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>