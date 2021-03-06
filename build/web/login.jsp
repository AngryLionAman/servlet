<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.security.validateUser" id="function" scope="page" />
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">
        
        <c:if test="${sessionScope.Session_id_of_user ne null}">
            <c:redirect url="index?ref=login"/>
        </c:if>
      
        <meta http-equiv="X-UA-Compatible" content="IE=edge"><!-- For IE --> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- For Resposive Device -->
        <title>Login | inquiryhere.com</title>        
        <meta property="og:url" content="https://www.inquiryhere.com/login.jsp">
        <meta property="og:site_name" content="www.inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="Login with bihari Google" />
        <meta property="og:description" content="After login you get that place where you get all data at one place"/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            input[type=text] {
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid #b3b3b3;
                border-radius: 2px;
            }
            .button {
                background-color: #4CAF50; /* Green */
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
            .button1 {width: 250px;}
        </style>
    </head>
    <body>
        <div class="main-page-wrapper">
            <header class="home-page">
                <div class="container clear-fix">
                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" style="padding-left:0px;">
                        <div class="logo float-left">
                            <a href="#" style="vertical-align:middle;"> <h4><div class="logotext">Inquiryhere.com</div></h4></a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 serachhere" style="display:inline-block;">
                        <div style="overflow: hidden; padding-right: .5em;">
                            <form action="search">
                                <div class="col-md-9">
                                    <input type="text" style="width: 100%;"  name="q" required="" >
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 float-right textalign-right">
                        <a href="index" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">Home</a>
                        <a  href="forgotpassword.jsp" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">forgot password</a>                    
                    </div>
                </div>

            </header>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <center>
                                    <c:if test="${message ne null}">
                                        ${message}
                                    </c:if>
                                    <c:if test="${param.ref ne null or not empty param.ref}">
                                        <c:choose>
                                            <c:when test="${param.ref eq 'logout'}">
                                                <b style=color:red;>Thanks for using our service. Please visit again</b>
                                            </c:when>
                                            <c:when test="${param.ref eq 'submitAns'}">
                                                <b style=color:red;>Please Login First</b>
                                            </c:when>
                                            <c:when test="${param.ref eq 'valid'}">
                                                <b style=color:red;>Email and Password is not valid</b>
                                            </c:when>
                                            <c:otherwise>
                                                <b style=color:red;>${param.ref}</b>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>

                                    <div class="themeBox" style="height:300px;">
                                        <form action="validate" method="post" name="form_name">
                                            <div  align="left">
                                                <label for="fname">Email &#8628;</label><a href="help.jsp#email">&#10067;</a>
                                                <div class="boxHeading">
                                                    <input type="text"  name="email" required="" value="">
                                                </div>
                                                <label for="lname">Password &#8628;</label><a href="help.jsp#password">&#10067;</a>
                                                <div class="boxHeading">
                                                    <input type="password"  name="password" required="" value="">
                                                </div> 
                                            </div>
                                            <br>
                                            <button type="submit" class="button button1" data-toggle="modal">Login</button>
                                        </form>

                                        <form action="signup.jsp">
                                            <button class="button button1" >Create new account</button>
                                        </form>
                                    </div>
                                </center>
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
    </body></html>