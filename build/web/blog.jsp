<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="validator.jsp" %>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog By User | inquiryhere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <meta property="og:description" content="india first knowledge based social media where experts give 
              you advise and suggestion related to your query .you can ask and share the 
              information which you want to explore.our motive is to be specific according to your demand" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="india first knowledge based social media platform where experts give
              you advise and suggestion related to your query" />
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
                                    <sql:query var="blog" dataSource="jdbc/mydatabase">
                                        SELECT blog_id,blog_subject,ID,username,firstname FROM blog INNER JOIN newuser ON newuser.id = blog.blog_posted_by;
                                    </sql:query>
                                    <c:forEach items="${blog.rows}" var="b" >
                                        <div class="themeBox" style="height:auto;">

                                            <div class="boxHeading">
                                                <a href="D_Blog.jsp?sub=${b.blog_subject}&Blog_Id=${b.blog_id}">${b.blog_subject}</a>
                                            </div>
                                            <div class="questionArea">
                                                <div class="postedBy">Published BY :<a href="profile.jsp?user=${b.username}&ID=${b.ID}"> ${b.firstname}</a></div>
                                            </div>
                                        </div>
                                    </c:forEach>
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
                                    <jsp:include page="funZoneList.jsp"/>
                                </div>
                            </div>
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Education Zone
                                </div>
                                <div>
                                    <jsp:include page="eduZoneList.jsp"/>
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