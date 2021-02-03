<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contect Us | inquiryhere.com</title>
        <meta property="og:url" content="https://www.inquiryhere.com/ContactUs.jsp">
        <meta property="og:site_name" content="www.inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="If you have and query or suggestion please contac us" />
        <meta property="og:description" content="if you have any query or suggestion, please let us know. Our community always feel happy to help you.we are for you and for the better world"/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="main-page-wrapper">
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row"><center>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                Thanks for your interest in Inquiryhere.com
                                            </div><br>
                                            <div >
                                                Dear user, If you have any query and suggestion about inquiryhere.com then you can drop a mail on inquiry@inquiryhere.com or you can directly post from below section.We will help you as soon as possible
                                            </div>
                                        </div>
                                    </div>

                                </center> </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="themeBox" style="height:auto;">
                                    <center><div class="boxHeading">
                                            If you need any help
                                        </div></center>
                                    <form action="help" method="post" name="contactus_form_name">
                                        <div>Name: <input type="text"  placeholder="Type your Name" name="fullName" width="auto" required=""></div>
                                        <div>Email: <input type="email"  placeholder="Type your Valid mail" name="Email" required=""></div>
                                        <div><textarea type="text" class="anstext" placeholder="write your query here" name="query" required=""></textarea></div>

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

        </div> <!-- /.main-page-wrapper -->
        <jsp:include page="footer.jsp"/>
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <!-- Bootstrap JS -->
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <!-- Bootstrap Select JS -->
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>

    </body>
</html>