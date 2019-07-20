<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@page language="java" %>
        <%@page import="java.sql.*" %> 
        <%@include file="site.jsp" %>
        <%@include file="validator.jsp" %>
        <%@include file="wordProcessing.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%!             String YOUR_ACTIVITY = "";
            String QUESTION = "";
            String ANSWER = "";
            String TOPIC = "";
            String USER_PROFILE = "";
            String ANS = "";
        %>
        <%            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                YOUR_ACTIVITY = "आपकी गतिविधि";
                QUESTION = "प्रश्न";
                ANSWER = "उत्तर";
                TOPIC = "विषय";
                USER_PROFILE = "उपयोगकर्ता प्रोफ़ाइल";
                ANS = "उत्तर";

            } else {
                YOUR_ACTIVITY = "Your Activity";
                QUESTION = "Question";
                ANSWER = "Answer";
                TOPIC = "Topic";
                USER_PROFILE = "User Profile";
                ANS = "Ans";
            }
        %>
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Search | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">

    </head>

    <body>
        <script type="text/javascript">

            function take_value(str) {
                //document.getElementById("demo").innerHTML = "Welcome" + firstname+lastname;

                var http = new XMLHttpRequest();
                http.open("POST", "<%=DB_AJAX_PATH%>/SearchHint.jsp?str=" + str, true);
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.send();

                http.onload = function () {
                    http.responseText;
                    document.getElementById("demo").innerHTML = http.responseText;
                    //alert(http.responseText);
                };

            }


        </script>
        <div class="main-page-wrapper">


            <!-- Header _________________________________ -->
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <div class="bodydata">
                <div class="container clear-fix">
                    <%
                        String email = null;
                        int CurrentuserId = 0;
                        Connection connection = null;
                        ResultSet rs2 = null;
                        ResultSet resultSetQuestion = null;
                        ResultSet resultSetAnswer = null;
                        //ResultSet resultSet = null;
                        ResultSet resultSetTopic = null;
                        ResultSet resultSetUser = null;
                        //PreparedStatement preparedStatement = null;
                        PreparedStatement ps2 = null;
                        PreparedStatement preparedStatementQuestion = null;
                        PreparedStatement preparedStatementAnswer = null;
                        PreparedStatement preparedStatementTopic = null;
                        PreparedStatement preparedStatementUser = null;
                        try {
                            if (connection == null || connection.isClosed()) {
                                try {
                                    Class.forName("com.mysql.jdbc.Driver");
                                } catch (ClassNotFoundException ex) {
                                    out.println("Exception in loading the class forname Driver" + ex);
                                    if (session.getAttribute("email") == null) {
                                        email = "Anonuous";
                                    } else {
                                        email = (String) session.getAttribute("email");
                                    }
                                    if (session.getAttribute("Session_id_of_user") == null) {
                                        CurrentuserId = 0;
                                    } else {
                                        CurrentuserId = (Integer) session.getAttribute("Session_id_of_user");
                                    }
                                    String URL = request.getRequestURL() + "?" + request.getQueryString();
                    %><jsp:include page="ExceptionCollector.jsp">
                        <jsp:param name="userName" value="<%=email%>"></jsp:param>
                        <jsp:param name="userID" value="<%=CurrentuserId%>"></jsp:param>
                        <jsp:param name="URLParameter" value="<%=URL%>"></jsp:param>
                        <jsp:param name="ExceptionMessage" value="<%=ex%>"></jsp:param>
                    </jsp:include><%
                            }
                            connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                        }
                    %>
                    <%
                        int totalRowsquestion = 0;
                        int totalRowsAnswer = 0;
                        int totalRowsTopic = 0;
                        int totalRowsUser = 0;
                        List<String> SearchValue = searchWordProcess(convertStringUpperToLower(request.getParameter("search")).toLowerCase());

                        Iterator itr = SearchValue.iterator();
                        try { //For the question count 
                            String Q = "SELECT * FROM question WHERE lower(question) LIKE '%" + SearchValue.get(0) + "%'";
                            while (itr.hasNext()) {
                                Q += " OR lower(question) LIKE '%" + itr.next() + "%'";
                            }
                            Q += ";";
                            preparedStatementQuestion = connection.prepareStatement(Q);
                            resultSetQuestion = preparedStatementQuestion.executeQuery();
                            String query2 = "SELECT FOUND_ROWS() as cnt";
                            ps2 = connection.prepareStatement(query2);
                            rs2 = ps2.executeQuery();

                            try {
                                if (rs2.next()) {
                                    totalRowsquestion = rs2.getInt("cnt");
                                }
                            } catch (Exception msg) {
                                out.println(msg);
                            }
                        } catch (Exception msg) {
                            out.println(msg);
                        }
                        try {//For Answer count and resultset value
                            String Q_a = "Select q.question,q.q_id, substring(ans.answer,1,500) "
                                    + "from question q right join answer ans on ans.q_id = q.q_id where lower(answer) LIKE '%" + SearchValue.get(0) + "%'";
                            while (itr.hasNext()) {
                                Q_a += " or lower(answer) LIKE '%" + itr.next() + "%'";
                            }
                            // Q_a += ";";
                            preparedStatementAnswer = connection.prepareCall(Q_a);
                            resultSetAnswer = preparedStatementAnswer.executeQuery();
                            String query2 = "SELECT FOUND_ROWS() as cnt";
                            ps2 = connection.prepareStatement(query2);
                            rs2 = ps2.executeQuery();

                            try {
                                if (rs2.next()) {
                                    totalRowsAnswer = rs2.getInt("cnt");
                                }
                            } catch (Exception msg) {
                                out.println(msg);
                            }
                        } catch (Exception msg) {
                            out.println(msg);
                        }
                        try {//For Topic count and resultset value
                            String T = "SELECT * FROM topic WHERE lower(topic_name) LIKE '%" + SearchValue.get(0) + "%'";
                            while (itr.hasNext()) {
                                T += " or lower(topic_name) LIKE '%" + itr.next() + "%'";
                            }
                            preparedStatementTopic = connection.prepareStatement(T);
                            resultSetTopic = preparedStatementTopic.executeQuery();
                            String query2 = "SELECT FOUND_ROWS() as cnt";
                            ps2 = connection.prepareStatement(query2);
                            rs2 = ps2.executeQuery();

                            try {
                                if (rs2.next()) {
                                    totalRowsTopic = rs2.getInt("cnt");
                                }
                            } catch (Exception msg) {
                                out.println(msg);
                            }
                        } catch (Exception msg) {
                            out.println(msg);
                        }
                        try {//For User count and resultset value
                            String SQL_T = "SELECT * FROM newuser WHERE lower(firstname) LIKE '%" + SearchValue.get(0) + "%' ";
                            while (itr.hasNext()) {
                                SQL_T += " OR lower(firstname) LIKE '%" + itr.next() + "%'";
                            }
                            preparedStatementUser = connection.prepareStatement(SQL_T);
                            resultSetUser = preparedStatementUser.executeQuery();
                            String query2 = "SELECT FOUND_ROWS() as cnt";
                            ps2 = connection.prepareStatement(query2);
                            rs2 = ps2.executeQuery();

                            try {
                                if (rs2.next()) {
                                    totalRowsUser = rs2.getInt("cnt");
                                }
                            } catch (Exception msg) {
                                out.println(msg);
                            }
                        } catch (Exception msg) {
                            out.println(msg);
                        }


                    %>
                    <div class="row">
                        <div class="row">
                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                                <div class="themeBox" style="min-height:available">
                                    <div class="boxHeading">
                                        <%=YOUR_ACTIVITY%>
                                    </div>
                                    <div>
                                        <a href="SearchBar.jsp?value=Question&search=<%=request.getParameter("search")%>"><%=QUESTION%> (<%=totalRowsquestion%>)</a><br>
                                        <a href="SearchBar.jsp?value=Answer&search=<%=request.getParameter("search")%>"><%=ANSWER%> (<%=totalRowsAnswer%>)</a><br>
                                        <a href="SearchBar.jsp?value=Topic&search=<%=request.getParameter("search")%>"><%=TOPIC%> (<%=totalRowsTopic%>)</a><br>
                                        <a href="SearchBar.jsp?value=UserProfile&search=<%=request.getParameter("search")%>"><%=USER_PROFILE%> (<%=totalRowsUser%>)</a><br>
                                    </div>

                                </div>

                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;margin-bottom:0px;">
                                            <p id="demo"></p>
                                            <%
                                                String ParametrVariable = request.getParameter("value");
                                                if (ParametrVariable == null) {
                                                    ParametrVariable = "Question";
                                                }
                                                if (ParametrVariable != null && SearchValue != null) {
                                                    if (ParametrVariable.equals("Question")) {
                                                        out.println("<center><div class=boxHeading>" + QUESTION + "</div></center>");
                                                        try {
                                                            String Question_asked_by_user;
                                                            //String SearchValue_Case_Converted = SearchValue;

                                                            boolean count = true;
                                                            while (resultSetQuestion.next()) {
                                                                count = false;
                                                                Question_asked_by_user = resultSetQuestion.getString("question");
                                                                int question_id = resultSetQuestion.getInt("q_id");
                                            %> <br>Q. <a href="Answer.jsp?Id=<%=question_id%>" ><h6><%=Question_asked_by_user%> ?</h6></a><%
                                                }
                                                resultSetQuestion.close();;
                                                preparedStatementQuestion.close();
                                                if (count) {
                                                    out.println("No related question found");
                                                }
                                            } catch (Exception e1) {
                                                out.println("Error in Question search : " + e1);
                                                if (session.getAttribute("email") == null) {
                                                    email = "Anonuous";
                                                } else {
                                                    email = (String) session.getAttribute("email");
                                                }
                                                if (session.getAttribute("Session_id_of_user") == null) {
                                                    CurrentuserId = 0;
                                                } else {
                                                    CurrentuserId = (Integer) session.getAttribute("Session_id_of_user");
                                                }
                                                String URL = request.getRequestURL() + "?" + request.getQueryString();
                                            %><jsp:include page="ExceptionCollector.jsp">
                                                <jsp:param name="userName" value="<%=email%>"></jsp:param>
                                                <jsp:param name="userID" value="<%=CurrentuserId%>"></jsp:param>
                                                <jsp:param name="URLParameter" value="<%=URL%>"></jsp:param>
                                                <jsp:param name="ExceptionMessage" value="<%=e1%>"></jsp:param>
                                            </jsp:include><%
                                                    }
                                                }
                                                //Staring answer programming....................................................
                                                if (ParametrVariable.equals("Answer")) {
                                                    out.println("<center><div class=boxHeading>" + ANSWER + "</div></center>");
                                                    try {
                                                        String Answer_given_by_user;
                                                        //int Question_id = 0;
                                                        //String SearchValue_Case_Converted = SearchValue.toLowerCase();

                                                        boolean count = true;
                                                        while (resultSetAnswer.next()) {
                                                            count = false;
                                                            Answer_given_by_user = resultSetAnswer.getString("substring(ans.answer,1,500)");
                                                            String Question_by_user = resultSetAnswer.getString("question");
                                                            int question_id = resultSetAnswer.getInt("q.q_id");
                                            %><br> Q. <a href="Answer.jsp?q=<%=Question_by_user.replaceAll(" ", "-")%>&Id=<%=question_id%>" ><%=Question_by_user%> ?</a><%
                                                    out.println("<br>Ans.</b>&nbsp;&nbsp;&nbsp;&nbsp;" + Answer_given_by_user);

                                                }
                                                resultSetAnswer.close();
                                                preparedStatementAnswer.close();
                                                if (count) {
                                                    out.println("No related answer found");
                                                }
                                            } catch (Exception e2) {
                                                out.println("Error in Answer search : " + e2);
                                                if (session.getAttribute("email") == null) {
                                                    email = "Anonuous";
                                                } else {
                                                    email = (String) session.getAttribute("email");
                                                }
                                                if (session.getAttribute("Session_id_of_user") == null) {
                                                    CurrentuserId = 0;
                                                } else {
                                                    CurrentuserId = (Integer) session.getAttribute("Session_id_of_user");
                                                }
                                                String URL = request.getRequestURL() + "?" + request.getQueryString();
                                            %><jsp:include page="ExceptionCollector.jsp">
                                                <jsp:param name="userName" value="<%=email%>"></jsp:param>
                                                <jsp:param name="userID" value="<%=CurrentuserId%>"></jsp:param>
                                                <jsp:param name="URLParameter" value="<%=URL%>"></jsp:param>
                                                <jsp:param name="ExceptionMessage" value="<%=e2%>"></jsp:param>
                                            </jsp:include><%
                                                    }
                                                }
                                                //Starting the topic search program

                                                if (ParametrVariable.equals("Topic")) {
                                                    out.println("<center><div class=boxHeading>" + TOPIC + "</div></center>");
                                                    try {
                                                        //String SearchValue_Case_Converted = SearchValue.toLowerCase();

                                                        int count_ = 1;
                                                        boolean count = true;
                                                        while (resultSetTopic.next()) {
                                                            count = false;
                                                            String Topic_assgned_by_user = convertStringUpperToLower(resultSetTopic.getString("topic_name"));
                                                            int selected_topic_id = resultSetTopic.getInt("unique_id");
                                                            out.print("<br><br>" + count_++ + "<b>&nbsp;&nbsp;<a href=topic.jsp?t=" + Topic_assgned_by_user.replaceAll(" ", "+") + "&id=" + selected_topic_id + ">" + Topic_assgned_by_user + "</a></b>");

                                                        }
                                                        resultSetTopic.close();
                                                        preparedStatementTopic.close();
                                                        if (count) {
                                                            out.println("No related topic found");
                                                        }
                                                        out.println("<br><br><a href=FollowMoreTopic.jsp>For more topic</a>");
                                                    } catch (Exception e) {
                                                        out.println("Error in Topic Search:" + e);
                                                        if (session.getAttribute("email") == null) {
                                                            email = "Anonuous";
                                                        } else {
                                                            email = (String) session.getAttribute("email");
                                                        }
                                                        if (session.getAttribute("Session_id_of_user") == null) {
                                                            CurrentuserId = 0;
                                                        } else {
                                                            CurrentuserId = (Integer) session.getAttribute("Session_id_of_user");
                                                        }
                                                        String URL = request.getRequestURL() + "?" + request.getQueryString();
                                            %><jsp:include page="ExceptionCollector.jsp">
                                                <jsp:param name="userName" value="<%=email%>"></jsp:param>
                                                <jsp:param name="userID" value="<%=CurrentuserId%>"></jsp:param>
                                                <jsp:param name="URLParameter" value="<%=URL%>"></jsp:param>
                                                <jsp:param name="ExceptionMessage" value="<%=e%>"></jsp:param>
                                            </jsp:include><%
                                                    }
                                                }
                                                //Satring the userprofile search option

                                                if (ParametrVariable.equals("UserProfile")) {
                                                    out.println("<center><div class=boxHeading> " + USER_PROFILE + " </div></center>");
                                                    try {
                                                        String StoredUserFirstName;
                                                        int StoredUserID;
                                                        //String SearchValue_Case_Converted = SearchValue.toLowerCase();
//                                                        String SQL_T = "SELECT * FROM newuser WHERE lower(firstname) LIKE '%" + SearchValue.get(0) + "%' ";
//                                                        while (itr.hasNext()) {
//                                                            SQL_T += " OR lower(firstname) LIKE '%" + itr.next() + "%'";
//                                                        }
//                                                        preparedStatement = connection.prepareStatement(SQL_T);
//                                                        resultSet = preparedStatement.executeQuery();
                                                        int count_ = 1;
                                                        boolean count = true;
                                                        while (resultSetUser.next()) {
                                                            count = false;
                                                            StoredUserID = resultSetUser.getInt("ID");
                                                            StoredUserFirstName = convertStringUpperToLower(resultSetUser.getString("firstname"));
                                                            out.print("<br><br>" + count_++ + "<b>&nbsp;&nbsp;<a href=profile.jsp?user=" + StoredUserFirstName.replaceAll(" ", "+") + "&ID=" + StoredUserID + ">" + StoredUserFirstName + " </a></b>");

                                                        }
                                                        resultSetUser.close();
                                                        preparedStatementUser.close();
                                                        if (count) {
                                                            out.println("No related user profile found");
                                                        }
                                                        out.println("<br><br><a href=UserProfile.jsp>For more user</a>");
                                                    } catch (Exception e) {
                                                        out.println("Error in User profile search:" + e);
                                                        if (session.getAttribute("email") == null) {
                                                            email = "Anonuous";
                                                        } else {
                                                            email = (String) session.getAttribute("email");
                                                        }
                                                        if (session.getAttribute("Session_id_of_user") == null) {
                                                            CurrentuserId = 0;
                                                        } else {
                                                            CurrentuserId = (Integer) session.getAttribute("Session_id_of_user");
                                                        }
                                                        String URL = request.getRequestURL() + "?" + request.getQueryString();
                                            %><jsp:include page="ExceptionCollector.jsp">
                                                <jsp:param name="userName" value="<%=email%>"></jsp:param>
                                                <jsp:param name="userID" value="<%=CurrentuserId%>"></jsp:param>
                                                <jsp:param name="URLParameter" value="<%=URL%>"></jsp:param>
                                                <jsp:param name="ExceptionMessage" value="<%=e%>"></jsp:param>
                                            </jsp:include><%
                                                        }
                                                    }

                                                }//IF statement closed here
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
//                                                    try {
//                                                        if (resultSet != null || !resultSet.isClosed()) {
//                                                            try {
//                                                                resultSet.close();
//                                                            } catch (Exception e) {
//                                                                out.println("Exception in closing resulatset " + e);
//                                                            }
//                                                        }
//                                                    } catch (Exception error) {
//                                                        out.println(error);
//                                                    }
//                                                    try {
//                                                        if (preparedStatement != null || !preparedStatement.isClosed()) {
//                                                            try {
//                                                                preparedStatement.close();
//                                                            } catch (Exception e) {
//                                                                out.println("Exception in closing preparedStatement " + e);
//                                                            }
//                                                        }
//                                                    } catch (Exception error) {
//                                                        out.println(error);
//                                                    }
                                                }
                                            %>

                                            <div class="clear-fix"></div>
                                        </div>
                                    </div>

                                </div>


                                <div class="row margintop10">


                                </div>

                            </div>

                        </div>
                    </div>
                </div>

                <!-- j Query -->
                <%@include file="notificationhtml.jsp" %>
                <jsp:include page="footer.jsp">
                    <jsp:param name="sl" value="<%=sl%>"/>
                </jsp:include>
                <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
                <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
            </div> <!-- /.main-page-wrapper -->
    </body>
 </html>