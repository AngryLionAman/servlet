<html lang="en">
    <head>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%!
            String SUBJECT = "";
            String POSTED_BY = "";
            String DESCRIPTION = "";
            String COMPLETE_YOUR_PROFILE = "";
            String TRENDING_QUUESTION = "";
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                SUBJECT = "विषय";
                POSTED_BY = "द्वारा प्रकाशित";
                DESCRIPTION = "विवरण";
                COMPLETE_YOUR_PROFILE = "अपनी प्रोफाइल पूरी कीजिए";
                TRENDING_QUUESTION = "ट्रेंडिंग सवाल";

            } else {
                SUBJECT = "Subject";
                POSTED_BY = "Posted by";
                DESCRIPTION = "Description";
                COMPLETE_YOUR_PROFILE = "Complete your profile";
                TRENDING_QUUESTION = "Trending question";
            }
        %>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String str = request.getParameter("Blog_Id");
            //String str = request.getParameter("id");
            if (str == null || str.length() <= 0) {
                response.sendRedirect("index.jsp?sl=" + sl);
            }
            String Question = "";
            try {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) > 47 && str.charAt(i) < 58) {
                        Question += str.charAt(i);
                    }
                }
            } catch (Exception msg) {
                out.println(msg);
            }

        %>

        <script src="ckeditor/ckeditor.js"></script>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-128307055-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-128307055-1');
        </script> 
        <%@page language="java" %>
        <%@page import="java.sql.*" %> 
        <%@include file="site.jsp" %>
        <%@include file="validator.jsp" %>
        <%            Connection connection = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            try {
                if (connection == null || connection.isClosed()) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        out.println("Exception in loading the class forname Driver" + ex);
                    }
                    connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                }
        %>
        <%
            String StoredQuestion = "";
            String StoredAnswer = "";
            String FirstName = "";
            int UserID = 0;
            int TotalView = 0;
            if (Question != null) {
                try {
                    String p = "SELECT b.blog_subject,b.total_view,b.blog_id,substring(b.blog,1,500),user.firstname,user.id FROM blog b right join newuser user on b.blog_posted_by = user.Id  WHERE blog_id = '" + Question + "'";
                    preparedStatement = connection.prepareStatement(p);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        StoredQuestion = resultSet.getString("blog_subject");
                        StoredAnswer = resultSet.getString("substring(b.blog,1,500)");
                        FirstName = resultSet.getString("firstname");
                        UserID = resultSet.getInt("ID");
                        TotalView = resultSet.getInt("b.total_view") + 1;
                        int blog_id = resultSet.getInt("b.blog_id");
                        try {
                            PreparedStatement ps1 = null;
                            String countView = "UPDATE blog SET total_view = total_view + 1 WHERE blog_id =? ";
                            ps1 = connection.prepareStatement(countView);
                            ps1.setInt(1, blog_id);
                            ps1.executeUpdate();
                            ps1.close();

                        } catch (Exception msg) {
                            out.println("Error in cound the view" + msg);
                        }
                    }
                } catch (Exception e) {
                    out.println("Unable to retrieve!!" + e);
                }
            }
        %>
        <title><%=StoredQuestion%></title>
        <meta property="og:title" content="<%=StoredQuestion%>" />
        <meta property="og:description" content="<%=StoredAnswer%>"/>
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <script type="text/javascript">

            function showCommentBox() {
                var div = document.getElementById('comment');
                div.className = 'visible';
            }
        </script>
        <style>
            input[type=text] {
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid #b3b3b3;
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


    </head>

    <body>
        <div class="main-page-wrapper">


            <!-- Header _________________________________ -->
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

                                    <div class="themeBox" style="height:auto;">

                                        <div class="boxHeading marginbot10">

                                          [ <%=TotalView%> ]  <%=SUBJECT%> :  <%=StoredQuestion%> 
                                        </div>
                                        <div class="questionArea">

                                            <div class="postedBy"><%=POSTED_BY%> :<a href="profile.jsp?user=<%=FirstName.replaceAll(" ", "+")%>&ID=<%=UserID%>&sl=<%=sl%>"><%=FirstName%></a></div>

                                        </div>
                                    </div>
                                    <div class="boxHeading marginbot10"><%=DESCRIPTION%></div>

                                    <%

                                        try {
                                            String p = "SELECT * FROM blog WHERE  blog_id = '" + Question + "'";
                                            preparedStatement = connection.prepareStatement(p);
                                            resultSet = preparedStatement.executeQuery();
                                            while (resultSet.next()) {
                                                String answer = resultSet.getString("blog");
                                    %>
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading marginbot10" style="font-size: 15px;font-family: Arial, Helvetica, sans-serif;">
                                            <%=answer%> 
                                        </div>

                                    </div>

                                    <div class="clear-fix"></div>
                                    Comments....<br>
                                    <div align="right">

                                        <%
                                            try {
                                                String sql_question_comment = "SELECT unique_id,user_id,"
                                                        + "(SELECT firstname FROM newuser WHERE id = comments.user_id )AS fullname,"
                                                        + "q_id,comments,time FROM comments WHERE blog_id = ? ";
                                                preparedStatement = connection.prepareStatement(sql_question_comment);
                                                preparedStatement.setString(1, Question);
                                                resultSet = preparedStatement.executeQuery();
                                                while (resultSet.next()) {
                                                    String Blog_comments = resultSet.getString("comments");
                                                    int user_id = resultSet.getInt("user_id");//userId of who commented
                                                    String userName = "GuestUser";
                                                    if (user_id != 0) {
                                                        userName = resultSet.getString("fullname");//UserName who commentd   
                                                    }
                                                    String time = resultSet.getString("time");

                                                    out.println(Blog_comments + ":- ");
                                                    if (userName.equalsIgnoreCase("GuestUser")) {
                                                        out.println("<b style=color:red;>" + userName + "</b>");
                                                    } else {
                                        %>
                                        <a href="profile.jsp?user=<%=userName.replaceAll(" ", "+")%>&ID=<%=user_id%>&sl=<%=sl%>"><%=convertStringUpperToLower(userName)%></a>
                                        <%  }
                                                    out.println("(" + time + ") <br>_____________________________<br> ");
                                                }

                                            } catch (Exception msg) {
                                                out.println("Error in loading question comment: -" + msg);
                                            }
                                        %>
                                    </div>
                                    <%
                                            }

                                        } catch (Exception e) {
                                            out.println("Unable to retrieve!!" + e);
                                        }
                                    %>
                                    <%
                                        } catch (Exception e) {
                                            out.println("Error in main try block:-" + e);
                                        } finally {

                                            if (connection != null || !connection.isClosed()) {
                                                try {
                                                    connection.close();
                                                } catch (Exception e) {
                                                    out.println("Exception in closing connection " + e);
                                                }
                                            }
                                            if (resultSet != null || !resultSet.isClosed()) {
                                                try {
                                                    resultSet.close();
                                                } catch (Exception e) {
                                                    out.println("Exception in closing resulatset " + e);
                                                }
                                            }
                                            if (preparedStatement != null || !preparedStatement.isClosed()) {
                                                try {
                                                    preparedStatement.close();
                                                } catch (Exception e) {
                                                    out.println("Exception in closing preparedStatement " + e);
                                                }
                                            }
                                        }
                                    %>

                                    <%
                                        if (session.getAttribute("Session_id_of_user") == null) {
                                    %>
                                    <div align="center">
                                        <button type="submit" class="button button1" onclick="showCommentBox()">Comment as Guest</button> 
                                    </div>
                                    <form action="saveBlogComment.jsp" method="get">
                                        <div class="hidden" id="comment">
                                            <input type="hidden" name="blog_id" value="<%=Question%>">
                                            <textarea name="comments" rows="3" cols="30" required="" placeholder="What you think..."></textarea>
                                            <input type="submit" name="sub" value="Send Comment">
                                        </div>
                                    </form>
                                    <div align="center">
                                        <button type="submit" class="button button1" onclick="location.href = 'Login.jsp';" >Login to comment</button> 
                                    </div>
                                    <% } else {%>
                                    <form action="saveBlogComment.jsp" method="get">
                                        <input type="hidden" name="blog_id" value="<%=Question%>">
                                        <textarea name="comments" rows="3" cols="30" required="" placeholder="What you think..."></textarea>
                                        <input type="submit" name="sub" value="Send Comment">
                                    </form>
                                    <% } %>


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
<!--                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    <%=TRENDING_QUUESTION%>
                                </div>
                                <div>
                                    <jsp:include page="TrendingQuestion.jsp" />
                                </div>
                            </div>-->
                            <% }--%>
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