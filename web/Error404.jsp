<%-- 
    Document   : Error404
    Created on : Aug 13, 2019, 9:43:05 AM
    Author     : inquiryhere.com
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error404</title>
        <meta property="og:url" content="https://www.inquiryhere.com/Error404.jsp">
        <meta property="og:site_name" content="www.inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="Sorry for the inconvenience" />
        <meta property="og:description" content="Sorry for the inconvenience, Inquiryhere always for you."/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
    </head>
    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12" >
                            <div class="row" style="padding-left: 10px;">
                                <img src="https://webhostingmedia.net/wp-content/uploads/2018/01/http-error-404-not-found.png"  alt="Error"/>
                                <p style="font-size: 25px;"><font style="color: red; font-size: 30px;">404 :</font> This is an Error.</p>        
                                <br> <p style="font-size: 25px;"><font style="color: red; font-size: 30px;">Mean :</font> The page you are looking for may has been removed or location has been changed</p>
                                    <c:if test="${message ne null}">
                                    <br> <p style="font-size: 25px;"><font style="color: red; font-size: 30px;">Specific Reason :</font> ${message}</p>
                                    </c:if>
                                <br> <p style="font-size: 25px;"><font style="color: red; font-size: 30px;">What you can do :</font> 
                                    <br><br> <font style="color: #00ff00">Process 1 : </font> You can report us with the proper link you are looking for by clicking on <a href="help.jsp">HELP PAGE</a></p>

                                <br><br><p style="font-size: 25px;"><font style="color: #00ff00">Process 2 : </font> You can search the same content in the above mention <a href="search">SEARCH BAR</a></p>

                                <br><br><p style="font-size: 25px;"><font style="color: #00ff00">Process 3 : </font> You can directly visit to below listed link</p>

                                <ul style="font-size: 25px; padding-left: 20px; padding-top: 10px;">
                                    <li style="padding-top: 5px;padding-bottom: 5px;">You can ask question By clicking <a href="#" data-toggle="modal" data-target="#myModal2">THIS LINK</a></li>
                                    <li style="padding-top: 5px;padding-bottom: 5px;">You can read the blog By visiting <a href="blog">BLOG PAGE</a></li>
                                    <li style="padding-top: 5px;padding-bottom: 5px;">You can read the  <a href="optionalquestion">OBJECTIVE QUESTION</a></li>
                                    <li style="padding-top: 5px;padding-bottom: 5px;">You can read the joke, Sayari, Poem, Full Form, Quotes by Visiting <a href="fun">FUN PAGE</a></li>
                                </ul>
                                <br><br>
                                <p style="font-size: 25px;"><font style="font-size: 25px; color: #cccc00">Thank You Note :</font>
                                    <br><br>Thank You very much to taking interest in our Website,
                                    <br><br>We always work for the user satisfaction,
                                    <br><br> We always work for the better tomorrow, 
                                    <br><br>Thanks for you patience.
                                </p>

                                <br><br> <p style="font-size: 25px;">Click here to visit <a href="https://www.inquiryhere.com">inquiryhere</a> <a href="index">HomePage</a></p>
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
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div>

    </body></html>