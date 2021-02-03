<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.Session_id_of_user ne null}">
    <c:redirect url="index?ref=f_pass"/>
</c:if>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">  

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Forger Password | inquiryhere.com</title>        
        <meta property="og:url" content="https://www.inquiryhere.com/forgotpassword.jsp">
        <meta property="og:site_name" content="inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="Forgot password, Don't worry" />
        <meta property="og:description" content="Enter your registered email and get your password"/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            input[type=text] {
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid red;
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
                            <a href="index.jsp?ref=forgerpassword" style="vertical-align:middle;">
                                <h4>
                                    <div class="logotext">
                                        inquiryhere.com
                                    </div>
                                </h4>
                            </a>          
                        </div> 
                    </div>

                    <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 float-right textalign-right">
                        <a href="index" class="helpicon" style="color: white;padding-left: 10px;padding-right: 50px;">Home</a>
                        <a  href="login.jsp" class="helpicon" style="color: white; width: 50px;">Login</a>
                        <a  href="signup.jsp" class="helpicon" style="color: white; width: 50px;">Sign up</a>
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
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:270px;">
                                            <form action="forgotpassword" method="post" name="" >
                                                <div align="left">
                                                    <label for="fname">Email  &#8628;</label><a href="help.jsp#f-pass-email">&#10067;</a>
                                                </div>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" name="mail" required="" placeholder="Enter valid email address....">
                                                </div>  
                                                <button class="button button1">Reset Password</button>
                                            </form>                                                                    
                                        </div>
                                    </div>
                                </center> 
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div> 
        <jsp:include page="footer.jsp"/>
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>