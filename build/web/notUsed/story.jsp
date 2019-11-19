<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">    <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">     <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Story List | inquiryhere.com</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <meta property="og:description" content="Read intresting story in hindi and english" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="Read intresting story in hindi and english" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />

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

                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <c:catch var="ex">
                                    <c:set var="rowsPerPage" value="10" />
                                    <c:set var="pageNumber" value="1" />
                                    <c:if test="${param.p ne null}">
                                        <c:set var="pageNumber" value="${param.p}" />
                                    </c:if>

                                    <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
                                    <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>

                                    <sql:query var="story" dataSource="jdbc/mydatabase">
                                        SELECT * FROM story;
                                    </sql:query>
                                    <c:set var="a">
                                        <fmt:formatNumber value="${story.rowCount/rowsPerPage}" maxFractionDigits="0"/>
                                    </c:set>

                                    <c:set var="b" value="${story.rowCount/rowsPerPage}" />

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
                                    <c:forEach items="${story.rows}" var="s" begin="${start}" end="${stop}">
                                        <div class="themeBox" style="height:auto;">

                                            <div class="boxHeading">
                                                <a href="read_story.jsp?id=${s.story_id}">${s.story_title}</a>
                                            </div>
                                            <div class="boxHeading">
                                                ${fn:substring(s.story,0,300)}&nbsp;<a href="read_story.jsp?story=${s.story_title}&id=${s.story_id}">Read More..</a>
                                            </div>

                                        </div>
                                    </c:forEach>
                                    <%--For displaying Previous link --%>
                                    <c:if test="${pageNumber gt 1}">
                                        <a href="story.jsp?p=${pageNumber - 1}">Previous</a>
                                    </c:if>
                                    <c:forEach begin="1" end="${numberOfPages}" var="i">
                                        <c:choose>
                                            <c:when test="${i!=pageNumber}">
                                                <a href="story.jsp?p=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                                            </c:when>
                                            <c:otherwise>
                                                <b style="color: red;"><c:out value="${i}"/></b>
                                            </c:otherwise>        
                                        </c:choose>       
                                    </c:forEach>  
                                    <%--For displaying Next link --%>
                                    <c:if test="${pageNumber lt numberOfPages}">
                                        <a href="story.jsp?p=${pageNumber + 1}">Next</a>
                                    </c:if>
                                </c:catch>                                
                                <c:if test="${ex ne null}">
                                    ${ex}
                                </c:if>                        

                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
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
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> 
    </body>
</html>