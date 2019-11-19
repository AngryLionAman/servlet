<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <c:if test="${param.p_id eq null or empty param.p_id}">
            <c:redirect url="poem.jsp" />
        </c:if>

        <script src="ckeditor/ckeditor.js"></script>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <c:catch var="ex">
            <c:if test="${param.p_id ne null and not empty param.p_id}">
                <sql:query  var="poem" dataSource="jdbc/mydatabase">
                    select poem_title,poem from poem where poem_id = ?;
                    <sql:param value="${param.p_id}"/>
                </sql:query>
                <c:forEach var="p" items="${poem.rows}">
                    <c:set value="${p.poem_title}" var="poemTitle"/>
                    <c:set value="${p.poem}" var="poemDes"/>
                </c:forEach> 
            </c:if>

        </c:catch>
        <c:if test="${ex ne null}">
            ${ex}
        </c:if>
        <title>${poemTitle}</title>
        <meta property="og:title" content="${poemTitle}" />
        <meta property="og:description" content="${poemDes}"/>
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
                                            ${poemTitle}
                                        </div>
                                    </div>
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading marginbot10" style="font-size: 15px;font-family: Arial, Helvetica, sans-serif;">
                                            ${poemDes}
                                        </div>
                                    </div>
                                    <div class="clear-fix"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Also Read
                                </div>
                                <div>
                                    <ul>
                                        <sql:query dataSource="jdbc/mydatabase" var="more">
                                            SELECT poem_id,poem_title FROM poem ORDER BY RAND() LIMIT 5;
                                        </sql:query>
                                        <c:forEach var="m" items="${more.rows}">
                                            <li> <a href="read_poem.jsp?story=${fn:replace(m.poem_title,'|','')}&p_id=${m.poem_id}">${m.poem_title}</a></li>       
                                            </c:forEach>
                                    </ul>
                                </div>
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