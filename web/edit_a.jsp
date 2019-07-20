<html lang="en">
    <head>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta charset="UTF-8">
        <meta http-equiv="content-type" content="text/html" charset="utf-8">
        <%
        if(session.getAttribute("Session_id_of_user")== null){
            response.sendRedirect("Login.jsp");
        }
        %>

        <%!
            String FOLLOWED_TOPIC = "";
            String QUESTION = "";
            String POSTED_BY = "";
            String ANSWER = "";
            String BE_THE_FIRST_PERSON = "";
            String SUBMIT = "";
            String RELATED_QUESTION = "";
            String COMPLETE_YOUR_PROFILE = "";
            String TRENDING_QUESTION = "";
            String TOPIC_YOU_MAY_LIKE = "";
            String ANSWERED_BY = "";
            String NO_RELATED_QUESTION_FOUND = "";
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                TOPIC_YOU_MAY_LIKE = "विषय आपको पसंद आ सकता है";
                POSTED_BY = "द्वारा प्रकाशित";
                TRENDING_QUESTION = "सक्रिय प्रश्न";
                FOLLOWED_TOPIC = "विषय जो आपको पसंद है";
                RELATED_QUESTION = "संबंधित सवाल";
                QUESTION = "प्रशन";
                ANSWER = "उत्तर";
                BE_THE_FIRST_PERSON = "इस प्रश्न का उत्तर देने वाले पहले व्यक्ति बनें";
                SUBMIT = "जमा करें";
                COMPLETE_YOUR_PROFILE = "अपनी प्रोफाइल पूरी कीजिए";
                ANSWERED_BY = "द्वारा उत्तर दिया गया";
                NO_RELATED_QUESTION_FOUND = "कोई संबंधित प्रश्न नहीं मिला";
            } else {

                TOPIC_YOU_MAY_LIKE = "Topic You May Like";
                POSTED_BY = "Posted By";
                TRENDING_QUESTION = "Tranding Question";
                FOLLOWED_TOPIC = "Followed Topic";
                RELATED_QUESTION = "Related Question";
                QUESTION = "Ques";
                ANSWER = "Answer";
                BE_THE_FIRST_PERSON = "Be the first person to answer this question";
                SUBMIT = "Update";
                COMPLETE_YOUR_PROFILE = "Complete your profile";
                ANSWERED_BY = "Answer By";
                NO_RELATED_QUESTION_FOUND = "No Related Question Found ";
            }
        %>
        <%  //need to validate here
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

        %>
        <script src="ckeditor/ckeditor.js"></script>
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-128307055-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-128307055-1');
        </script> 
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@page language="java" %>
        <%@page import="java.sql.*" %> 
        <%@include file="site.jsp" %>

    </head>

    <body>
        <div class="main-page-wrapper">
            <!-- Header _________________________________ -->
            <%--@include file="header.jsp" --%>
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
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

                            <div class="row">
                                <%
                                    Connection connection = null;
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

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                                    <div class="themeBox" style="height:auto;">

                                        <div class="boxHeading marginbot10">
                                            <%
                                                String storedQuestion = "";
                                                int question_id = Integer.valueOf(request.getParameter("q_id"));
                                                int ans_id = Integer.valueOf(request.getParameter("ans_id"));
                                                int ans_by_id = Integer.valueOf(request.getParameter("ans_by_id"));
                                                //out.println(question_id+ " "+ans_id+" "+ans_by_id);
                                                String sql = "select question from question where q_id =?";
                                                preparedStatement = connection.prepareStatement(sql);
                                                preparedStatement.setInt(1, question_id);
                                                resultSet = preparedStatement.executeQuery();
                                                while(resultSet.next()){
                                                    storedQuestion = resultSet.getString("question");
                                                }
                                            %>

                                            <%=QUESTION%> : <%=storedQuestion%> ?
                                        </div>
                                        <%
                                            String storedAnswer = "";
                                            try {
                                                String sqlA = "select answer from answer where a_id =?";
                                               preparedStatement = connection.prepareStatement(sqlA);
                                               preparedStatement.setInt(1, ans_id);
                                               resultSet = preparedStatement.executeQuery();
                                               while(resultSet.next()){
                                                   storedAnswer = resultSet.getString("answer");
                                               }

                                            } catch (Exception e) {
                                                out.println("Unable to retrieve!!" + e);
                                            }
                                        %>


                                    </div>
                                    <div class="boxHeading marginbot10"><%=ANSWER%>:</div>
                                    <form name="submitquestion" method="post" action="update_a.jsp">
                                        <input type="hidden" name="answer_id" value="<%=ans_id%>">
                                        <input type="hidden" name="question_id" value="<%=question_id%>">
                                        <textarea class="ckeditor" name="answer" required="" ><%=storedAnswer%></textarea>
                                        <input type="submit" name="Post" value="<%=SUBMIT%>"> 
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
            <div class="clear-fix"></div>

            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">
                </div>
            </div>
            <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"></jsp:param>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->

    </body></html>