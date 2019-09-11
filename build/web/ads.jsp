<html lang="en"><head>
        <meta charset="UTF-8">
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Advertise | inquiryhere.com</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">       
    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                            <div class="row">
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading" align="center">
                                        Thanks for your interest in inquiryhere.com
                                    </div>
                                    <div class="boxHeading" style="background-color: yellow;">
                                        Terms and condition for advertisement
                                    </div>
                                    <div>
                                        <ol type="I">
                                            <li>your ads will display on index.jsp and answer.jsp page</li> 
                                            <li>this is example</li> 
                                            <li>this is example</li> 
                                            <li>this is example</li> 
                                            <li>this is example</li> 
                                            <li>this is example</li> 
                                        </ol>
                                    </div>
                                </div>
                            </div>
                        </div>                     
                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div>

    </body>
</html>