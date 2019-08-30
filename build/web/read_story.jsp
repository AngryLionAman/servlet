<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <script src="ckeditor/ckeditor.js"></script>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <c:if test="${param.id eq null or empty param.id}">
            <c:redirect url="story.jsp"/>
        </c:if>
        <c:if test="${param.id ne null and not empty param.id}">
            <sql:query dataSource="jdbc/mydatabase" var="story">
                select story_title,story from story where story_id = ?;
                <sql:param value="${param.id}"/>
            </sql:query>
            <c:forEach var="s" items="${story.rows}">
                <c:set var="storyTitle" value="${s.story_title}"/>
                <c:set var="storyDes" value="${s.story}"/>
            </c:forEach>
        </c:if>
        <title>${storyTitle}</title>
        <meta property="og:title" content="${storyTitle}" />
        <meta property="og:description" content="${fn:substring(storyDes, 0, 300)}"/>
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
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
                                        <div class="boxHeading marginbot10">
                                            ${storyTitle}
                                        </div>
                                    </div>
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading marginbot10" style="font-size: 15px;font-family: Arial, Helvetica, sans-serif;">
                                            ${storyDes} 
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Also Read
                                </div>
                                <div>

                                    <sql:query dataSource="jdbc/mydatabase" var="moreStory">
                                        SELECT story_id,story_title FROM story LIMIT ${param.id},5; 
                                    </sql:query>
                                    <ul>
                                        <c:forEach items="${moreStory.rows}" var="more">
                                            <li><a href="read_story.jsp?story=${fn:replace(fn:replace(more.story_title, '|', ''), ' ', '-')}&id=${more.story_id}">${more.story_title}</a></li>
                                        </c:forEach>
                                    </ul>
                                </div>

                            </div>
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Fun Zone
                                </div>
                                <div>
                                    <jsp:include page="funZoneList.jsp"/>
                                </div>

                            </div>
                            <div class="clear-fix"></div>

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
        </div> <!-- /.main-page-wrapper -->

    </body>
</html>