<html lang="en">
    <head>
        <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-8778688755733551",
    enable_page_level_ads: true
  });
</script>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>About Us | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%!
    String WELCOME_NOTES = "";        
    String DESCRIPTION = "";
    String HOW_IT_WORK = "";
    String HOW_IT_WORK_DESCRIPTION = "";
    String MOTO = "";
    String MOTO_DESCRIPTION = "";
    String HELP_US ="";
    String HELP_US_DESCRIPTION ="";
    String NEED_HELP = "";
    String NEED_HELP_DESCRIPTION = "";
    String AUTHOR = "";
    String AUTHOR_DESCRIPTION = "";
%>
<%
    //String page_name = request.getParameter("page");
    String sl = request.getParameter("sl");
    if (sl == null) {
        sl = "en";
    }
    if (sl.equalsIgnoreCase("hi")) {
        WELCOME_NOTES = "inquiryhere.com à¤®à¥à¤ à¤à¤ªà¤à¥ à¤°à¥à¤à¤¿ à¤à¥ à¤²à¤¿à¤ à¤§à¤¨à¥à¤¯à¤µà¤¾à¤¦";
        DESCRIPTION = "inquiryhere.com à¤à¤ free online à¤ªà¥à¤°à¤¶à¥à¤¨ à¤à¤° à¤à¤¤à¥à¤¤à¤° à¤à¤¾ website à¤¹à¥à¤ à¤à¤¿à¤¸à¤à¤¾ à¤®à¤à¤¸à¤¦ à¤¹à¥à¤ à¤¹à¤®à¤¾à¤°à¥ à¤à¤à¤¦à¤° à¤à¥ à¤à¤¨à¤à¤¤ à¤à¥à¤à¤¾à¤¨ à¤à¥ à¤¬à¥à¤¾à¤µà¤¾ à¤¦à¥à¤¨à¤¾ à¤à¤° à¤à¤à¤¦à¤° à¤à¥à¤ªà¥ à¤¹à¥à¤ à¤à¤²à¤¾ à¤à¥ à¤¦à¥à¤¨à¤¿à¤¯à¤¾ à¤à¥ à¤¦à¤¿à¤à¤¾à¤¨à¤¾ à¤¤à¤¾à¤à¤¿ à¤²à¥à¤ à¤à¤¸ à¤à¥à¤à¤¾à¤¨ à¤à¥ à¤ªà¥à¥ à¤à¤° à¤à¤ªà¤à¥ à¤à¤¦à¤° à¤à¤°à¥à¥¤ à¤à¥à¤¯à¥à¤à¥ à¤¹à¤®à¥ à¤µà¤¿à¤¶à¥à¤µà¤¾à¤¸ à¤à¤°à¤¤à¥ à¤¹à¤¿à¤¯à¤ à¤à¤¾à¤¨à¤à¤¾à¤°à¥ à¤¬à¤¾à¤à¤¨à¥ à¤®à¥à¤ à¤à¥à¤¯à¥à¤à¥ à¤à¥à¤à¤¾à¤¨ à¤¬à¤¾à¤à¤à¤° à¤¹à¥ à¤¹à¤® à¤¦à¥à¤¨à¤¿à¤¯à¥ à¤à¥ à¤¸à¥à¤à¥à¤¤à¤¿à¤ à¤à¥à¤° à¤¸à¤à¤¤à¥ à¤¹à¥à¤à¥¤à¤à¥à¤¸à¥ à¤à¥ à¤¹à¤®à¤²à¥à¤ à¤à¤¾à¤¨à¤¤à¥ à¤¹à¥à¤ ,à¤à¥à¤à¤¾à¤¨ à¤¬à¤¾à¤à¤¨à¥ à¤¸à¥ à¤à¥à¤à¤¾à¤¨ à¤¬à¥à¤¤à¤¾ à¤¹à¥ à¤¹à¥à¤à¥¤";
        HOW_IT_WORK = "à¤¯à¥ à¤à¤¾à¤® à¤à¥à¤¸à¥ à¤à¤°à¤¤à¤¾ à¤¹à¥"; 
        HOW_IT_WORK_DESCRIPTION = "à¤à¤à¤° à¤à¥à¤ à¤µà¥à¤¯à¤à¥à¤¤à¤¿ à¤¸à¤µà¤¾à¤² à¤ªà¥à¤à¤¤à¤¾ à¤¹à¥à¤ à¤¤à¥ à¤à¤¸à¤à¥ à¤¬à¤¾à¤¦ à¤¦à¥à¤¸à¤°à¤¾ à¤µà¥à¤¯à¤à¥à¤¤à¤¿ à¤à¤¸à¤à¤¾ à¤à¤µà¤¾à¤¬ à¤¦à¥à¤¤à¤¾ à¤¹à¥à¤ à¤à¥ à¤à¥ à¤à¤¸ topic à¤à¥  à¤¬à¤¾à¤°à¥ à¤®à¥à¤ à¤¬à¤à¥à¤¬à¥ à¤à¤¾à¤¨à¤¤à¥ à¤¹à¥à¤à¥¤à¤à¤° à¤¹à¤®à¤¾à¤°à¥ website community à¤à¥ à¤à¤à¤¦à¤° à¤¬à¤¹à¥à¤¤ à¤¸à¥ expert à¤¹à¥à¤ à¤à¤ªà¤¨à¥ à¤à¤ªà¤¨à¥ field à¤à¥à¥¤ à¤¹à¤®à¤°à¥ à¤à¤à¥à¤¸à¤ªà¤°à¥à¤ à¤¯à¥à¤à¤° à¤à¥ à¤¸à¤°à¤² à¤à¤° à¤à¤¸à¤¾à¤¨ à¤à¤¤à¥à¤¤à¥à¤° à¤¦à¥à¤¤à¥ à¤¹à¥à¤ à¤à¥ à¤à¥ à¤à¤¸à¤¾à¤¨à¥ à¤¸à¥ à¤¸à¤®à¤ à¤®à¥à¤ à¤ à¤à¤¾à¤¯à¥ à¤à¤° à¤µà¥ à¤à¤¸à¥ à¤­à¤¾à¤¸à¤¾ à¤®à¥à¤ à¤à¤µà¤¾à¤¬ à¤¦à¥à¤¤à¥ à¤¹à¥à¤ à¤à¤¿à¤¸ à¤­à¤¾à¤¸à¤¾ à¤®à¥à¤ à¤¯à¥à¤à¤° à¤¨à¥ à¤ªà¥à¤à¤¾ à¤¹à¥à¤à¥¤ ";
        MOTO = "à¤®à¤à¤¸à¤¦";
        MOTO_DESCRIPTION = "à¤¯à¥ à¤µà¥à¤¬à¤¸à¤¾à¤à¤ à¤¦à¥ à¤­à¤¾à¤·à¤¾ à¤®à¥à¤ à¤¬à¤¨à¤¾à¤¯à¤¾ à¤à¤¯à¤¾ à¤¹à¤¿à¤¯à¤ ,à¤à¥à¤¯à¤¾à¤¦à¤¾à¤¤à¤° à¤à¤¸à¤¾à¤¨  à¤¹à¤¿à¤à¤¦à¥ à¤ªà¥ à¤§à¥à¤¯à¤¾à¤¨ à¤¦à¤¿à¤ à¤à¤¯à¤¾ à¤¹à¥à¤ à¤¤à¤¾à¤à¤¿ à¤¯à¥à¤à¤° à¤à¥ à¤¸à¤°à¤² à¤à¤° à¤à¤¸à¤¾à¤¨ à¤à¤¤à¥à¤¤à¤° à¤®à¤¿à¤² à¤¸à¤à¥à¥¤";
        HELP_US = "à¤¹à¤®à¤¾à¤°à¥ à¤®à¤¦à¤¦ à¤à¤°à¥à¤";
        HELP_US_DESCRIPTION = "à¤¯à¤¦à¤¿ à¤à¤ªà¤à¥ à¤¸à¤¾à¤à¤ à¤à¥ à¤¸à¤¾à¤¥ à¤à¥à¤ à¤¸à¤®à¤¸à¥à¤¯à¤¾ à¤à¤° à¤¸à¥à¤à¤¾à¤µ à¤¹à¥, à¤¤à¥ à¤à¥à¤ªà¤¯à¤¾ à¤¹à¤®à¤¸à¥ à¤¸à¤à¤ªà¤°à¥à¤ à¤à¤°à¥à¤ à¤«à¤¼à¥à¤°à¥à¤® à¤ªà¤° à¤¹à¤®à¥à¤ à¤¬à¤¤à¤¾à¤à¤";
        NEED_HELP = "à¤®à¤¦à¤¦ à¤à¤¾à¤¹à¤¿à¤ ?";
        NEED_HELP_DESCRIPTION = "à¤¯à¤¦à¤¿ à¤à¤ªà¤à¥ à¤à¤¸ à¤µà¥à¤¬à¤¸à¤¾à¤à¤ à¤¸à¥ à¤¸à¤à¤¬à¤à¤§à¤¿à¤¤ à¤à¤¿à¤¸à¥ à¤­à¥ à¤ªà¥à¤°à¤à¤¾à¤° à¤à¥ à¤¸à¤¹à¤¾à¤¯à¤¤à¤¾ à¤à¥ à¤à¤µà¤¶à¥à¤¯à¤à¤¤à¤¾ à¤¹à¥, à¤¤à¥ à¤à¥à¤ªà¤¯à¤¾ à¤¹à¤®à¥à¤ à¤¸à¤¹à¤¾à¤¯à¤¤à¤¾ à¤«à¤¼à¥à¤°à¥à¤® à¤ªà¤° à¤¬à¤¤à¤¾à¤à¤à¥¤ à¤¹à¤® à¤¹à¤®à¥à¤¶à¤¾ à¤à¤ªà¤à¥ à¤²à¤¿à¤ à¤°à¤¹à¥à¤à¤à¥à¥¤ à¤¹à¤®à¥à¤ à¤à¤ªà¤à¥ à¤®à¤¦à¤¦ à¤à¤°à¤¨à¥ à¤®à¥à¤ à¤à¥à¤¶à¥ à¤¹à¥à¤à¥à¥¤";
        AUTHOR = "à¤²à¥à¤à¤";
        AUTHOR_DESCRIPTION = "à¤ªà¥à¤°à¥à¤à¥à¤°à¤¾à¤®à¤° à¤à¤° à¤²à¥à¤à¤ : à¤à¤®à¤¨ à¤à¥à¤®à¤¾à¤° <br> à¤¸à¤¹ à¤²à¥à¤à¤ : à¤ªà¤à¤à¤ à¤à¥à¤®à¤¾à¤° <br> à¤¦à¥à¤µà¤¾à¤°à¤¾ à¤µà¤¿à¤¤à¥à¤¤ : à¤²à¤²à¤¨ à¤¸à¤¿à¤à¤¹ ,à¤ªà¤à¤à¤ à¤à¥à¤®à¤¾à¤°";
    } else {
        WELCOME_NOTES = "Thanks for your interest in Inquiryhere.com";
        DESCRIPTION = "inquiryhere.com is a free online question and answer website whose purpose is to promote endless knowledge inside us and show the hidden art to the world so that people can read that knowledge and appreciate you. We believe that we can only make the world alive by knowing the knowledge of cuking knowledge.As we know, knowledge increases by enlightening knowledge.";
        HOW_IT_WORK = "How it work";
        HOW_IT_WORK_DESCRIPTION = "If a person asks questions, then the other person answers him, who knows the subject well.And there are many experts inside our website community. Our Expert gives simple and easy stimulation to the user who can be easily understood and in the same language as the user who has asked for it.";
        MOTO = "Our moto";
        MOTO_DESCRIPTION = "These websites have been created in two languages, mostly on easy Hindi, so that users can get simple and easy answers.";
        HELP_US = "Help us";
        HELP_US_DESCRIPTION = "if you have any problem and suggestion with site ,please let us know on contact us form";
        NEED_HELP = "You need help?";
        NEED_HELP_DESCRIPTION = "if you need any type of help related to this website ,please let us know on the help form.we will always there for you.we will happy to help you.";
        AUTHOR = "Author";
        AUTHOR_DESCRIPTION = "Programmer and writer: Aman Kumar <br> Co-author: Pankaj Kumar<br> Finance By: Lalan Singh, Pankaj Kumar ";
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
                                                <%=WELCOME_NOTES%>
                                            </div><br>
                                            <div class="boxHeading">
                                               <%=DESCRIPTION%>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                <%=HOW_IT_WORK%>
                                            </div><br>
                                            <div class="boxHeading">
                                               <%=HOW_IT_WORK_DESCRIPTION%>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                <%=MOTO%>
                                            </div><br>
                                            <div class="boxHeading">
                                               <%=MOTO_DESCRIPTION%>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                <%=HELP_US%>
                                            </div><br>
                                            <div class="boxHeading">
                                               <%=HELP_US_DESCRIPTION%>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                <%=NEED_HELP%>
                                            </div><br>
                                            <div class="boxHeading">
                                               <%=NEED_HELP_DESCRIPTION%>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                <%=AUTHOR%>
                                            </div><br>
                                            <div class="boxHeading">
                                               <%=AUTHOR_DESCRIPTION%>
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