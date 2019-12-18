<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.Session_id_of_user ne null}">
    <c:redirect url="index?ref=signup"/>
</c:if>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Signup - inquiryhere.com</title>
        <meta property="og:url" content="https://www.inquiryhere.com/signup.jsp">
        <meta property="og:site_name" content="www.inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="Create an account" />
        <meta property="og:description" content="Creata an account and get every thing at one place"/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
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
        <script language="Javascript" type="text/javascript">

            function onlyAlphabets(e, t) {
                try {
                    if (window.event) {
                        var charCode = window.event.keyCode;
                    } else if (e) {
                        var charCode = e.which;
                    } else {
                        return true;
                    }
                    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode === 32))
                        return true;
                    else
                        return false;
                } catch (err) {
                    alert(err.Description);
                }
            }
            function userNameValidation(e, t) {
                try {
                    if (window.event) {
                        var charCode = window.event.keyCode;
                    } else if (e) {
                        var charCode = e.which;
                    } else {
                        return true;
                    }
                    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode > 47 && charCode < 58))
                        return true;
                    else
                        return false;
                } catch (err) {
                    alert(err.Description);
                }
            }

        </script>
        <script type="text/javascript">
            function take_value(email) {
                //alert(email.value);
                var http = new XMLHttpRequest();
                http.open("post", "validateUserName.jsp?userName=" + email.value, true);
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.send();
                http.onload = function () {
                    http.responseText;
                    document.getElementById("demo").innerHTML = http.responseText;
                };
            }
        </script>
    </head>
    <body>
        <div class="main-page-wrapper">
            <header class="home-page">
                <div class="container clear-fix">
                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" style="padding-left:0px;">
                        <div class="logo float-left">
                            <a href="index?ref=signup" style="vertical-align:middle;">
                                <h4>
                                    <div class="logotext">
                                        inquiryhere.com
                                    </div>

                                </h4>
                            </a>

                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 serachhere" style="display:inline-block;">
                        <div style="overflow: hidden; padding-right: .5em;">
                            <form action="search">
                                <input type="text" style="width: 100%;" name="q" required="" >
                            </form>
                        </div>
                    </div>

                    <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 float-right textalign-right">
                        <a  href="index" class="helpicon" style="color: white; width: 50px;">Home</a>
                        <a  href="login.jsp" class="helpicon" style="color: white; width: 50px;">Login</a>
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
                            <c:if test="${message ne null}">
                                <div class="themeBox" style="font-family: serif;font-variant-position: super;font-size: 25px;text-align: center;background-color: green;color: white;">
                                        ${message}
                                    </div>
                            </c:if>
                            <div class="themeBox" style="height:470px;">
                                <%
                                    String ErrorMsg = request.getParameter("Error");
                                    if (ErrorMsg != null) {
                                        out.println("<center><b style=color:red;>" + ErrorMsg + "</b></center>");
                                    }
                                %>
                                <div class="boxHeading">
                                    <form action="createNewUser" method="post" name="newUser">
                                        <label for="fname">Full Name &#8628;</label> <a href="help.jsp#fullname">&#10067;</a>                                                                  
                                        <input type="text" id="fname" name="firstname" onkeypress="return onlyAlphabets(event, this);" required="">
                                        <label for="fname">Email/Phone &#8628;</label><a href="help.jsp#emailorphone">&#10067;</a>
                                        <div class="boxHeading">
                                            <input type="text"  name="email" required="">
                                        </div>
                                        <label for="lname">Password &#8628;</label><a href="help.jsp#sign-up-password">&#10067;</a>
                                        <div class="boxHeading">
                                            <input type="password"  name="password" pattern=".{6,}" title="Six or more characters" required="">
                                        </div> 
                                        <br>
                                        <center>
                                            <button type="submit" class="button button1" data-toggle="modal"  >Create Account</button>
                                        </center>
                                    </form>

                                </div>
                                <center>
                                    <div>
                                        <form action="forgotpassword.jsp" method="post" name="forgetPassword">
                                            <button class="button button1" style="background-color: red;">Forget Password</button>
                                        </form>
                                    </div>
                                </center>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
        </div>
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <!-- Bootstrap JS -->
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <!-- Bootstrap Select JS -->
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>

    </body>
</html>