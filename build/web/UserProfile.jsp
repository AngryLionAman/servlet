<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%@include file="validator.jsp" %>
<jsp:useBean class="com.string.name" id="word" scope="page"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">               <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profie - inquiryhere.com</title>
        <meta property="og:description" content="india first knowledge based social media where experts give you advise and suggestion related to your query .you can ask and share the information which you want to explore.our motive is to be specific according to your demand" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="india first knowledge based social media platform where experts give you advise and suggestion related to your query" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />
        <link rel="stylesheet" type="text/css" href="css/style.css">  <!-- Main style sheet -->        
        <link rel="stylesheet" type="text/css" href="css/responsive.css"><!-- responsive style sheet -->
        <script type="text/javascript">

            function take_value(el, user_id, id_of_user) {
            <% if (session.getAttribute("Session_id_of_user") == null) { %>
                alert("Please login first or refrest the page");<%
                } else {%>
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
            <% }%>
            }
        </script>

    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;margin-bottom:0px;">
                                        <div class="boxHeading">
                                            User Profile
                                        </div>
                                        <!-- <div style="">Post something here</div> -->
                                        <div class="userProfiles">
                                            <c:catch var="ex">
                                                <c:set var="rowsPerPage" value="50" />
                                                <c:set var="pageNumber" value="1" />
                                                <c:if test="${param.p ne null}">
                                                    <c:set var="pageNumber" value="${param.p}" />
                                                </c:if>

                                                <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
                                                <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>

                                                <sql:query var="userProfile" dataSource="jdbc/mydatabase">
                                                    SELECT ID,username,firstname,imagepath,user_type FROM newuser where not user_type <=> "guest" and firstname is not null;
                                                </sql:query>
                                                <c:set var="a">
                                                    <fmt:formatNumber value="${userProfile.rowCount/rowsPerPage}" maxFractionDigits="0"/>
                                                </c:set>

                                                <c:set var="b" value="${userProfile.rowCount/rowsPerPage}" />

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
                                                <c:forEach items="${userProfile.rows}" var="user" begin="${start}" end="${stop}">
                                                    <c:if test="${sessionScope.Session_id_of_user ne user.ID}">
                                                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                                            <img src="images/${user.imagepath}" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                            <a href="profile.jsp?user=${fn:toLowerCase(user.username)}&ID=${user.ID}">${word.convertStringUpperToLower(user.firstname)}</a>
                                                            <c:if test="${sessionScope.Session_id_of_user ne null}">
                                                                <sql:query dataSource="jdbc/mydatabase" var="fDetail"> 
                                                                    select count(*) as cnt from ak_follower_detail where user_id = ? and followers_id = ? limit 1;
                                                                    <sql:param value="${user.ID}"/>
                                                                    <sql:param value="${sessionScope.Session_id_of_user}"/>
                                                                </sql:query>
                                                                <c:forEach items="${fDetail.rows}" var="value">
                                                                    <c:set value="${value.cnt}" var="found"/>
                                                                </c:forEach>
                                                                <c:if test="${found eq 1}">
                                                                    <input type="button" class="float-right" value="unfollow" id="myButton1" onclick="return take_value(this, '${user.ID}', '${sessionScope.Session_id_of_user}');" /> 
                                                                </c:if>
                                                                <c:if test="${found ne 1}">
                                                                    <input type="button" class="float-right" value="follow" id="myButton1" onclick="return take_value(this, '${user.ID}', '${sessionScope.Session_id_of_user}');" />
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${sessionScope.Session_id_of_user eq null}">
                                                                <input type="button" class="float-right" value="follow" id="myButton1" onclick="alert('Please Login To Follow');" />
                                                            </c:if>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <div class="clear-fix" style="border-style: solid;border-width: 1px;">&nbsp;&nbsp;&nbsp;
                                                    <%--For displaying Previous link --%>
                                                    <c:if test="${pageNumber gt 1}">
                                                        <a href="UserProfile.jsp?p=${pageNumber - 1}">Previous</a>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${numberOfPages}" var="i">
                                                        <c:choose>
                                                            <c:when test="${i!=pageNumber}">
                                                                <a href="UserProfile.jsp?p=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <b style="color: red;"><c:out value="${i}"/></b>
                                                            </c:otherwise>        
                                                        </c:choose>       
                                                    </c:forEach>  
                                                    <%--For displaying Next link --%>
                                                    <c:if test="${pageNumber lt numberOfPages}">
                                                        <a href="UserProfile.jsp?p=${pageNumber + 1}">Next</a>
                                                    </c:if>
                                                </div>   
                                            </c:catch>
                                            <c:if test="${ex ne null}">
                                                ${ex}
                                            </c:if>                                   
                                            <div class="clear-fix"></div>
                                        </div>
                                    </div>

                                </div>

                            </div>

                        </div>
                    </div>
                </div>
                  <div class="modal fade" id="myModal2" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <c:if test="${sessionScope.Session_id_of_user eq null}">
                                <h4 class="modal-title">Post question as Guest</h4>    
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
            </div> <!-- /.main-page-wrapper -->
    </body>
</html>