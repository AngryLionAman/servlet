<html lang="en">
    <head>
        <script src="ckeditor/ckeditor.js"></script>

        <meta charset="UTF-8">
        <%@page language="java" %>
        <%@page import="java.sql.*" %> 
        <%@include file="site.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

        <%!            String WRITE_BLOG_SUBJECT = "";
            String WRITE_BLOG_DESCRIPTION = "";
            String SUBMIT = "";
            String COMPLETE_YOUR_PROFILE = "";
            String TRENDING_QESTION = "";
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "eng";
            }
            if (sl.equalsIgnoreCase("hi")) {
                WRITE_BLOG_SUBJECT = "ब्लॉग का विषय लिखें";
                WRITE_BLOG_DESCRIPTION = "ब्लॉग को लिखें";
                SUBMIT = "SAVE करने के लिए यहाँ CLICK करे";
                COMPLETE_YOUR_PROFILE = "अपनी प्रोफाइल पूरी कीजिए";
                TRENDING_QESTION = "ट्रेंडिंग सवाल";
            } else {

                WRITE_BLOG_SUBJECT = "Write the Blog Subject";
                WRITE_BLOG_DESCRIPTION = "Write your Blog Description";
                SUBMIT = "Submit";
                COMPLETE_YOUR_PROFILE = "Complete Your Profile";
                TRENDING_QESTION = "Trending question";
            }
        %>
        <%
            if (session.getAttribute("email") == null) {
                response.sendRedirect("Login.jsp?sl=" + sl + "&msg=submitAns");
            }
        %>

        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Blog | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">

    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                            <div class="clear-fix"></div>

                            <div class="clear-fix"></div>

                            <div class="clear-fix"></div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

                            <div class="row">

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                                    <%
                                        int id_of_user = 0;
                                        if (session.getAttribute("Session_id_of_user") != null) {
                                            id_of_user = (Integer) session.getAttribute("Session_id_of_user");
                                        } 
                                    %>
                                    <form name="submitquestion" method="post" action="Submitblog.jsp?id=<%=id_of_user%>">

                                        <div class="boxHeading marginbot10">
                                            <div class="themeBox" style="height:auto;">
                                                <%=WRITE_BLOG_SUBJECT%> : 
                                                <div class="boxHeading">
                                                    <textarea type="text" class="anstext" name="blog_subject" required="" ></textarea>
                                                </div>
                                            </div>

                                        </div>
                                        <b><%=WRITE_BLOG_DESCRIPTION%> :</b>
                                        <textarea class="ckeditor" name="blog_description" required="" ></textarea>
                                        <input type="submit" name="Post" value="<%=SUBMIT%>"> 
                                    </form>


                                    <div class="clear-fix"></div>

                                </div>
                            </div>

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <%
                                if (session.getAttribute("email") != null) {
                            %>
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    <%=COMPLETE_YOUR_PROFILE%>
                                </div>
                                <div><jsp:include page="CompleteUserProfile.jsp" /></div>

                            </div><% }%>
                            <div class="clear-fix"></div>
                            <%--
                                if (session.getAttribute("email") != null) {
                            %>
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    <%=TRENDING_QESTION%>
                                </div>
                                <div>
                                    <jsp:include page="TrendingQuestion.jsp" />
                                </div>
                            </div><% }--%>
                            <div class="clear-fix"></div>

                            <div class="clear-fix"></div>
                        </div>
                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>

            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">
                </div>
            </div>
            <%@include file="notificationhtml.jsp" %>
            <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->

    </body></html>