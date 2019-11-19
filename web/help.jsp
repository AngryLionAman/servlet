<html lang="en"><head>
        <meta charset="UTF-8">
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Help - inquiryhere.com</title>        
        <meta property="og:url" content="https://www.inquiryhere.com/help.jsp">
        <meta property="og:site_name" content="inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="Need help, Contact us" />
        <meta property="og:description" content="We always be ready to help our user, You are everything"/>
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
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" >
                            <div class="row">
                                <center align="left">                                    
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading">
                                            Thanks for your interest in inquiryhere.com
                                        </div><BR>
                                        <div>
                                            Dear user, If you have any query and suggestion about inquiryhere.com then you can drop a mail on inquiry@inquiryhere.com or you can directly post from below section.We will help you as soon as possible  
                                        </div>
                                    </div>
                                    <div class="themeBox" style="height:auto;" id="email">
                                        <div class="boxHeading">
                                            what is email in inquiryhere.com
                                        </div><BR>
                                        <div>
                                            As you know that,Present time email is unique identification for every user.So, We make it simple..You just need to type a random mail address and just make sure it should be in email pattern.
                                            <br>You need to provide the mail id which you have been provided at the registration time.
                                        </div>
                                    </div>
                                    <div class="themeBox" style="height:auto;" id="password">
                                        <div class="boxHeading">
                                            what is password in inquiryhere.com
                                        </div><BR>
                                        <div>
                                            You need to provide that password which you given at the registration time.if you forgot your password please visit <a href="forgotpassword.jsp">Forgot password</a> page.If you got any another problem related to login or password please visit <a href="ContactUs.jsp"> Contact us</a> form.   
                                        </div>
                                    </div>
                                    <div class="themeBox" style="height:auto;" id="fullname">
                                        <div class="boxHeading">
                                            Why Full Name ?
                                        </div><BR>
                                        <div>
                                            You don't have to fill many option we just made it simple. we give you an option to fill you name.if you want to fill you first name only that's ok also.and if you have middle name , you can also write there.   
                                        </div>
                                    </div>
                                    <div class="themeBox" style="height:auto;" id="emailorphone">
                                        <div class="boxHeading">
                                            Email or Phone Number...
                                        </div><BR>
                                        <div>
                                            We gave you two option , either you can fill you email address or Mobile number.visibility will be your choice,by default is hidden.
                                            you can create any email address as per you choice or phone number, Just make sure it should be follow the stander of email patter.
                                            <br><b>Advantage :</b>You can get the amazing email pattern   
                                            <br><b>Disadvantage :</b> You won't be able to recover your password.
                                        </div>
                                    </div>
                                    <div class="themeBox" style="height:auto;" id="sign-up-password">
                                        <div class="boxHeading">
                                            Password ...
                                        </div><BR>
                                        <div>
                                            We not make any type of restriction to create your password.Just make sure your password length should be more then 6 charector .
                                            We recommend that make the strong password use mixed char like : <b>kjzng^#@45@kgT</b>
                                        </div>
                                    </div>
                                    <div class="themeBox" style="height:auto;" id="f-pass-email">
                                        <div class="boxHeading">
                                            forget Password email...
                                        </div><BR>
                                        <div>
                                            If you registred email is not valid, please <a href="ContactUs.jsp">Contect us</a>. 
                                            Or you can create the <a href="signup.jsp">new account</a>.
                                        </div>
                                    </div>
                                </center> 
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="themeBox" style="height:auto;">
                                    <center><div class="boxHeading">
                                            If you need any help
                                        </div></center>
                                    <form action="submit_help.jsp">
                                        <div>Name: <input type="text"  placeholder="Type your Name" name="Name_h" width="auto" required=""></div>
                                        <div>Email: <input type="email"  placeholder="Type your Valid mail" name="Email_h" required=""></div>
                                        <div><textarea type="text" class="anstext" placeholder="write your question here" name="Q_h" required=""></textarea></div>

                                        <div class="float-right margintop20" style="vertical-align:bottom">
                                            <button type="submit" class="btn" data-toggle="modal" >ASK FOR HELP</button>
                                            <!-- btn-info btn-lg -->
                                        </div>
                                    </form>
                                    <div class="clear-fix"></div>
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
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div>

    </body></html>