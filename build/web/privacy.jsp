<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Privacy -  inquiryhere.com</title>
        <meta property="og:url" content="https://www.inquiryhere.com/privacy.jsp">
        <meta property="og:site_name" content="inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="privacy and policy" />
        <meta property="og:description" content="inquiryhere always care about the user privacy and we naver go against the user privacy"/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    </head>
    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="themeBox" style="height:auto;">
                    <div class="boxHeading" style="text-align: center;">
                        Privacy And Policy
                    </div>
                    <div >
                        inquiryhere.com takes your privacy seriously. We do not sell or redistribute any personal information. The inquiryhre.com website do not collect, store information or activiry about individual users
                    </div>
                </div>
                <div class="themeBox" style="height:auto;">
                    <div class="boxHeading" style="text-align: center;">
                        Cookies
                    </div>
                    <div >
                        inquiryhere.com never read or store any type of cookies of your local machine.if inquiryhere.com ever use cookies for the user better performance, it will come under the Google Privacy Policy
                    </div>
                </div>



                <div class="clear-fix"></div>

                <div class="clear-fix"></div>
                <jsp:include page="footer.jsp"/>
                <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>            <!-- Bootstrap JS -->
                <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>            <!-- Bootstrap Select JS -->
                <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
            </div> 
    </body>
</html>