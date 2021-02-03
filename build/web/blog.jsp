<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.fun.FunHelpingFunction" id="fun" scope="page"/>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog By User | inquiryhere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <meta property="og:description" content="india first knowledge based social media where experts give you advise and suggestion related to your query .you can ask and share the information which you want to explore.our motive is to be specific according to your demand" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="india first knowledge based social media platform where experts give you advise and suggestion related to your query" />
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
                                <c:if test="${message ne null}">
                                    <div class="themeBox" align="center" style="font-size: 20px;color: green;background-color: yellow;">
                                        ${message}
                                    </div>
                                </c:if>
                                <c:if test="${gotException ne null}">                                
                                    <div class="clear-fix" align="center" style="font-size: 20px;color: red;background-color: white;">
                                        ${'Got some probelm, Please refresh this page or visit after some time'}                                
                                    </div>
                                </c:if>


                                <c:if test="${blogList ne null}">
                                    <c:catch var="ex">
                                        <c:forEach items="${blogList}" var="b" >
                                            <div class="themeBox" style="height:auto;">
                                                <div class="boxHeading">
                                                    <a href="blog?id=${b.uniqueId}&sub=${word.UrlFormat(b.subject)}">${b.subject}</a>
                                                </div>
                                                <c:if test="${b.userName ne null}">
                                                    <div class="questionArea">
                                                        <div class="postedBy">Published BY :<a href="profile?user=${b.userName}&id=${b.userId}&ref=b"> ${word.convertStringUpperToLower(b.fullName)}</a></div>
                                                    </div>
                                                </c:if>                                                
                                            </div>
                                        </c:forEach>                                                                   
                                    </c:catch>
                                    <c:if test="${ex ne null}">
                                        ${ex}
                                    </c:if>
                                </c:if>   
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" >
                            <div class="row">
                                <c:if test="${funCategory ne null and not empty funCategory}">
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading" style="text-align: center; background-color: gold;">
                                            Fun Zone
                                        </div>
                                        <div>
                                            <ul>
                                                <c:catch var="msg">
                                                    <c:forEach items="${funCategory}" var="m">
                                                        <li><a href="fun?category=${m}">${word.convertStringUpperToLower(m)}</a></li>
                                                        </c:forEach>
                                                    </c:catch>
                                                    <c:if test="${msg ne null}">
                                                        ${msg}
                                                    </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </c:if>

                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        Shortcut Key
                                    </div>
                                    <div>
                                        <ul>
                                            <li><a href="UserProfile.jsp">Complete User List</a></li>
                                            <li><a href="moretopic">Complete Topic List</a></li>
                                            <li><a href="blog">Read Blog</a></li>
                                            <li><a href="WriteBlogHere.jsp">Write a Blog</a></li>
                                            <li><a href="optionalquestion">Read Objective Question</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> 
    </body>
</html>