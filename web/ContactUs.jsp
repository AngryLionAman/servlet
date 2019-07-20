<html lang="en"><head>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Contect Us | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%!
            String DESCRIPTION = "";
            String WELCOME_NOTES = "";
            String NEED_HELP = "";
            String HELP_US = "";

        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                DESCRIPTION = "प्रिय उपयोगकर्ता, अगर आपके पास inquiryhere.com के बारे में कोई प्रश्न और सुझाव है। तो आप inquiry@inquiryhere.com पर एक मेल छोड़ सकते हैं या आप सीधे अनुभाग से नीचे पोस्ट कर सकते हैं। हम जल्द से जल्द आपकी मदद करेंगे।";
                WELCOME_NOTES = "inquiryhere.com में आपकी रुचि के लिए धन्यवाद";
                NEED_HELP = "अगर आपको कोई मदद चाहिए"; 
                HELP_US = "यदि आपके पास हमारे लिए कोई सुझाव है";
            
            } else {
                DESCRIPTION = "Dear user, If you have any query and suggestion about inquiryhere.com then you can drop a mail on inquiry@inquiryhere.com or you can directly post from below section.We will help you as soon as possible.";
                WELCOME_NOTES = "Thanks for your interest in Inquiryhere.com";
                 NEED_HELP = "If you need any help"; 
                HELP_US = "If you have any suggestion for us";
            }
        %>


    </head>

    <body>
        <div class="main-page-wrapper">
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
                            <div class="row"><center>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                <%=WELCOME_NOTES%>
                                            </div>
                                            <div class="boxHeading">
                                            <%=DESCRIPTION%>
                                            </div>
                                        </div>
                                    </div>

                                </center> </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="themeBox" style="height:auto;">
                                    <center><div class="boxHeading">
                                            <%=NEED_HELP%>
                                        </div></center>
                                    <form action="submit_help.jsp">
                                        <div>Name: <input type="text"  placeholder="Type your Name" name="Name_h" width="auto" required=""></div>
                                        <div>Email: <input type="email"  placeholder="Type your Valid mail" name="Email_h" required=""></div>
                                        <div><textarea type="text" class="anstext" placeholder="write your query here" name="Q_h" required=""></textarea></div>

                                        <div class="float-right margintop20" style="vertical-align:bottom">
                                            <button type="submit" class="btn" data-toggle="modal" >ASK FOR HELP</button>
                                            <!-- btn-info btn-lg -->
                                        </div>
                                    </form>
                                    <div class="clear-fix"></div>
                                </div>
                            </div>
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="themeBox" style="height:auto;">
                                    <center><div class="boxHeading">
                                            <%=HELP_US%>
                                        </div></center>
                                    <form action="submit_help.jsp"> 
                                        <div>Name: <input type="text"  placeholder="Type your Name" name="Name_s" width="auto" required=""></div>
                                        <div>Email: <input type="email"  placeholder="Type your Valid mail" name="Email_s" width="auto" required=""></div>
                                        <div><textarea type="text" class="anstext" placeholder="write your suggestion here" name="S_s" required=""></textarea></div>

                                        <div class="float-right margintop20" style="vertical-align:bottom">
                                            <button type="submit" class="btn" data-toggle="modal" >SUGGEST US</button>
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
            <%@include file="notificationhtml.jsp" %>
             <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>


            <!-- Theme js -->
            <!-- <script type="text/javascript" src="js/theme.js"></script> -->

        </div> <!-- /.main-page-wrapper -->

    </body>
</html>