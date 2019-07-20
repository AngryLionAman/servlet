<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Privacy | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%!
            String PRIVACY_NOTES = "";
            String PRIVACY_DESCRIPTION = "";
            String COOKIES = "";
            String COOKIES_DESCRIPTION = "";
            String QUESTION = "";
            String QUESTION_DESCRIPTION = "";


        %>
        <%
            //String page_name = request.getParameter("page");
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                PRIVACY_NOTES = "सुरक्षा और नीति";
                PRIVACY_DESCRIPTION = "inquiryhere.com आपकी गोपनीयता को गंभीरता से लेता है। हम किसी भी व्यक्तिगत जानकारी को बेचते या पुनर्वितरित नहीं करते हैं। inquiryhere.com वेबसाइट अलग-अलग उपयोगकर्ताओं के बारे में जानकारी एकत्र या संग्रहित नहीं करती है।";
                COOKIES = "कुकीज़";
                COOKIES_DESCRIPTION = "inquiryhere.com आपके computer,Mobile के किसी भी प्रकार के data को कभी नहीं पढ़ता है या संग्रहीत नहीं करता है। यदि उपयोगकर्ता कभी भी बेहतर प्रदर्शन के लिए कुकीज़ का उपयोग नहीं करता है, तो यह Google गोपनीयता नीति के तहत आएगा";
                QUESTION = "सवाल";
                QUESTION_DESCRIPTION = "यदि आपके पास इस सुरक्षा नीति के बारे में कोई प्रश्न हैं, तो आप inquiryhere.com से <a href=ContactUs.jsp?sl="+sl+">संपर्क</a> कर सकते हैं";

            } else {
                PRIVACY_NOTES = "Privacy And Policy";
                PRIVACY_DESCRIPTION = "inquiryhere.com takes your privacy seriously. We do not sell or redistribute any personal information. The inquiryhre.com website do not collect, store information or activiry about individual users.";
                COOKIES = "Cookies";
                COOKIES_DESCRIPTION = "inquiryhere.com never read or store any type of cookies of your local machine.if inquiryhere.com ever use cookies for the user better performance, it will come under the Google Privacy Policy";
                QUESTION = "Question";
                QUESTION_DESCRIPTION = "If you have questions regarding this privacy policy, you may <a href=ContactUs.jsp?sl="+sl+">contact</a> inquiryhere.com.";
            }
        %>

    </head>

    <body>
        <div class="main-page-wrapper">
            <!-- Header _________________________________ -->
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"></jsp:param>
            </jsp:include>
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
                                        <div class="boxHeading">
                                            <%=PRIVACY_NOTES%>
                                        </div><br>
                                        <div class="boxHeading">
                                            <%=PRIVACY_DESCRIPTION%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading">
                                            <%=COOKIES%>
                                        </div><br>
                                        <div class="boxHeading">
                                            <%=COOKIES_DESCRIPTION%>
                                        </div>
                                    </div>
                                </div>
                            </div>                          
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading">
                                            <%=QUESTION%>
                                        </div><br>
                                        <div class="boxHeading">
                                            <%=QUESTION_DESCRIPTION%>
                                        </div>
                                    </div>
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
            <%@include file="notificationhtml.jsp" %>
             <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> 
    </body>
</html>