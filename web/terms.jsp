<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Terms | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%!
            String USAGE_POLICY = "";
            String USAGE_POLICY_DESCRIPTION = "";
            String WEBSITE_CONTENT = "";
            String WEBSITE_CONTENT_DESCRIPTION = "";
            String COPYRIGHT_INFORMATION = "";
            String COPYRIGHT_INFORMATION_DESCRIPTION = "";
            String PRIVACY_AND_POLICY = "";
            String PRIVACY_AND_POLICY_DESCRIPTION = "";
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
                USAGE_POLICY = "उपयोग नीति";
                USAGE_POLICY_DESCRIPTION = "जानकारी साझा करने के उद्देश्य से आप इस वेबसाइट का उपयोग कर सकते हैं। आप किसी अन्य उद्देश्य के लिए इस वेबसाइट का उपयोग नहीं कर सकते।";
                WEBSITE_CONTENT = "वेबसाइट की सामग्री।";
                WEBSITE_CONTENT_DESCRIPTION = "inquiryhere.com पर पाया गया सभी सामग्री website समुदाय की संपत्ति है।इस वेबसाइट पर किसी भी वारंटी के बिना, सार्वजनिक संदर्भ के रूप में जानकारी दी गई है।प्रत्येक ब्लॉग, प्रश्न और उत्तर website  समुदाय द्वारा नहीं चलाया जाता है। जो भी inquiryhere.com  पर पाया जाता है, उपयोगकर्ता द्वारा प्रमाणित किया जाता है और व्यवस्थापक द्वारा प्रबंधित किया जाता है। हमारा समुदाय गलत सूचनाओं के लिए प्रतिक्रियाशील नहीं है।कृपया किसी भी संभावित त्रुटि के साथ हमसे <a href=ContactUs.jsp>संपर्क</a> करें।";
                COPYRIGHT_INFORMATION = "कॉपीराइट संबंधी जानकारी";
                COPYRIGHT_INFORMATION_DESCRIPTION = "इस वेबसाइट पर पाई जाने वाली सभी परिभाषाएं उपयोगकर्ता द्वारा प्रदान की जाती हैं, हम मूल सामग्री मानते हैं।इसके अतिरिक्त, सभी वेबपेज टेक्स्ट, HTML कोड, वेब स्क्रिप्ट, डेटाबेस की जानकारी, चित्र और पेज डिजाइन मूल सामग्री है और अंतर्राष्ट्रीय कॉपीराइट कानून द्वारा संरक्षित है। आप व्यक्तिगत उपयोग के लिए inquiryhere.com वेबसाइट से जानकारी का उपयोग कर सकते हैं।";
                PRIVACY_AND_POLICY = "गोपनीयता नीति";
                PRIVACY_AND_POLICY_DESCRIPTION = "inquiryhere.com व्यक्तिगत जानकारी एकत्र या पुनर्वितरित नहीं करता है। पूरी <a href=privacy.jsp>गोपनीयता</a> नीति देखें।";
                QUESTION = "सवाल";
                QUESTION_DESCRIPTION = "यदि आपके पास कोई प्रश्न है, तो कृपया <a href=help.jsp>सहायता केंद्र</a> पर जाएँ";

            } else {
                USAGE_POLICY = "Usage Policy";
                USAGE_POLICY_DESCRIPTION = "You may use this website for the intended purpose of learning sharing information.you may not use this website for any other purpose";
                WEBSITE_CONTENT = "Website Content.";
                WEBSITE_CONTENT_DESCRIPTION = "All content found on inquiryhere.com is property of site community.Information on this website is provided as a public reference, without any warranties.Each blog, question and answer is not varified by site community.whatever content found on inquiryherre.com is proviede by user and managed by administrator.Our community is not responcible for incorrect information.please <a href=ContactUs.jsp>contect us</a> with any possible error.";
                COPYRIGHT_INFORMATION = "Copyright Information";
                COPYRIGHT_INFORMATION_DESCRIPTION = "All definitions found on this website are provided by user, we consider as original content.Additionally, all webpage text, HTML code, web scripts, database information, images, and page design is original content and is protected by international copyright law. You may use information from the inquiryhere.com website for personal use.";
                PRIVACY_AND_POLICY = "Privacy Policy";
                PRIVACY_AND_POLICY_DESCRIPTION = "inquiryhere.com does not collect or redistribute personal information. View the full <a href=privacy.jsp>privacy policy</a>.";
                QUESTION = "Question";
                QUESTION_DESCRIPTION = "If you have any query,please visit <a href=help.jsp>help center</a>";
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
                                            <%=USAGE_POLICY%>
                                        </div><br>
                                        <div class="boxHeading">
                                            <%=USAGE_POLICY_DESCRIPTION%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading">
                                            <%=WEBSITE_CONTENT%>
                                        </div><br>
                                        <div class="boxHeading">
                                            <%=WEBSITE_CONTENT_DESCRIPTION%>
                                        </div>
                                    </div>
                                </div>
                            </div>                          
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading">
                                            <%=COPYRIGHT_INFORMATION%>
                                        </div><br>
                                        <div class="boxHeading">
                                            <%=COPYRIGHT_INFORMATION_DESCRIPTION%>
                                        </div>
                                    </div>
                                </div>
                            </div>                          
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading">
                                            <%=PRIVACY_AND_POLICY%>
                                        </div><br>
                                        <div class="boxHeading">
                                            <%=PRIVACY_AND_POLICY_DESCRIPTION%>
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