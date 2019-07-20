<html lang="en">
    <head>
        <%@page language="java"%>
        <%@page import="java.sql.*"%>
        <%@include file="site.jsp" %>
        <%@include file="validator.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta charset="UTF-8">
        <%!            
            String RELATED_QUESTION = "";
            String RELATED_TOPIC = "";
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                RELATED_QUESTION = "संबंधित प्रश्न";
                RELATED_TOPIC = "संबंधित विषय";

            } else {
                RELATED_QUESTION = "Related Question";
                RELATED_TOPIC = "Related Topic";
            }
        %>
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Topic | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script type="text/javascript">
            function take_value(el, _topic_id, id_of_user) {
            <% if (session.getAttribute("email") == null) { %>
                alert("Please login first");<%
              } else {%>
                if (el.value === "follow") {
                    el.value = "followed";
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_follow_topic.jsp?val_topic=" + _topic_id + "&val2_topic=" + id_of_user + "&action=follow", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                } else {
                    el.value = "follow";
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_follow_topic.jsp?val_topic=" + _topic_id + "&val2_topic=" + id_of_user + "&action=delete", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }

            <% }%>
            }
        </script>

    </head>

    <body>
        <div class="main-page-wrapper">
            <%--@include file="header.jsp" --%>
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
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
                                        <center> 
                                            <div class="boxHeading">
                                                <%                                                                            // String Topic_id = request.getParameter("id");
                                                    int countQuestion = 0;
                                                    int total_followers = 0;
                                                    String str = request.getParameter("id");
                                                    String Topic_id = "";
                                                    if (str != null && !(str.isEmpty())) {
                                                        for (int i = 0; i < str.length(); i++) {
                                                            if (str.charAt(i) > 47 && str.charAt(i) < 58) {
                                                                Topic_id += str.charAt(i);
                                                            }
                                                        }
                                                        try {
                                                            
                                                            String TopicName = "";
                                                            int SelectedTopicID = 0;
                                                            int StoredtopicId = 0;
                                                            int StoredfollowersID = 0;
                                                            int Session_id_of_user = 0;
                                                            

                                                            String status = "NotLogin";
                                                            String count_sql = "select count(*) from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id=? and q_id is not null";
                                                            preparedStatement = connection.prepareStatement(count_sql);
                                                            preparedStatement.setString(1, Topic_id);
                                                            resultSet = preparedStatement.executeQuery();
                                                            while(resultSet.next()){
                                                                 countQuestion = resultSet.getInt("count(*)");
                                                            }
                                                            String Total_followers = "select count(user_or_followers_id)as totoal_followers from topic_followers_detail where topic_id =?";
                                                            preparedStatement = connection.prepareStatement(Total_followers);
                                                            preparedStatement.setString(1, Topic_id);
                                                            resultSet = preparedStatement.executeQuery();
                                                            while(resultSet.next()){
                                                                total_followers = resultSet.getInt("totoal_followers");
                                                            }
                                                            
                                                            String v = "SELECT * FROM topic WHERE unique_id = '" + Topic_id + "'";
                                                            preparedStatement = connection.prepareStatement(v);
                                                            resultSet = preparedStatement.executeQuery();
                                                            while (resultSet.next()) {
                                                                TopicName = resultSet.getString("topic_name");
                                                                SelectedTopicID = resultSet.getInt("unique_id");
                                                            }
                                                            if (session.getAttribute("Session_id_of_user") != null) {
                                                                Session_id_of_user = (Integer) session.getAttribute("Session_id_of_user");
                                                                String sql = "SELECT * FROM topic_followers_detail";
                                                                preparedStatement = connection.prepareStatement(sql);
                                                                resultSet = preparedStatement.executeQuery();
                                                                while (resultSet.next()) {
                                                                    StoredtopicId = resultSet.getInt("topic_id");
                                                                    StoredfollowersID = resultSet.getInt("user_or_followers_id");

                                                                    if (StoredtopicId == SelectedTopicID && StoredfollowersID == Session_id_of_user) {
                                                                        status = "present";
                                                                        break;
                                                                    } else {
                                                                        status = "Absent";
                                                                    }
                                                                }
                                                            }

                                                %>
                                                <div>
                                                    <span title="Totoal followers of <%=convertStringUpperToLower(TopicName)%> is <%=total_followers%>"><h4><%=convertStringUpperToLower(TopicName)%>&nbsp;(<%=total_followers%>)</h4></span>  
                                                    <%
                                                        if (status == "present") {
                                                    %> <input type="button" value="Unfollow" id="myButton1" onclick="return take_value(this, '<%=SelectedTopicID%>', '<%=Session_id_of_user%>');" /><%
                                                            } else if (status == "Absent") {
                                                    %> <input type="button" value="follow" id="myButton1" onclick="return take_value(this, '<%=SelectedTopicID%>', '<%=Session_id_of_user%>');" /><%
                                                             } else {
                                                    %> <input type="button" value="Follow" id="myButton1" onclick="return take_value(this, '', '');" /><%
                                                                     }

                                                    %>  
                                                </div>
                                                <%   } catch (Exception e) {
                                                            out.println(e.getMessage());
                                                        }
                                                    } else {
                                                        response.sendRedirect("FollowMoreTopic.jsp?sl=" + sl);
                                                    }
                                                %>
                                            </div>
                                        </center>
                                        <div class="clear-fix"></div>
                                    </div>
                                    <div class="themeBox" style="height:auto;">
                                        <div>
                                            <h4><%=RELATED_QUESTION%> <span title="Totoal <%=countQuestion%> question asked related to this topic">(<%=countQuestion%>)</span></h4>
                                            <%
                                                String question_detail;
                                                try {
                                                    String q_detail = "select q.id,q.question,q.q_id from question q right join question_topic_tag qtt on qtt.question_id=q.q_id where tag_id='" + Topic_id + "'";
                                                    preparedStatement = connection.prepareStatement(q_detail);
                                                    resultSet = preparedStatement.executeQuery();
                                                    int count = 0;
                                                    while (resultSet.next()) {
                                                        question_detail = resultSet.getString("question");
                                                        int questionID = resultSet.getInt("q.q_id");
                                                        if (question_detail != null) {
                                                            count++;
                                            %><br>Q.<a href="Answer.jsp?q=<%=question_detail.replaceAll(" ", "-")%>&Id=<%=questionID%>&sl=<%=sl%>" >&nbsp;<%=question_detail%> ?</a><%
                                                        }
                                                    }
                                                    if (count == 0) {
                                                        out.println("<center><b style=color:red;>No related question found!!</b></center>");
                                                    }
                                                } catch (Exception e) {
                                                    out.println("<br><b>Question Not Found...</b>" + e);
                                                }
                                            %>
                                        </div>

                                        <div class="clear-fix"></div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    <%=RELATED_TOPIC%>
                                </div>
                                <div>
                                    <%
                                        int countQ = 0;
                                        try {
                                            String p_related_topic = "select DISTINCT "
                                                    + "t.unique_id,t.topic_name,(SELECT COUNT(user_or_followers_id) FROM topic_followers_detail WHERE topic_id=t.unique_id ) as totoal_followers from topic t "
                                                    + "right join question_topic_tag qtt "
                                                    + "on t.unique_id=qtt.tag_id where question_id "
                                                    + "IN (select question_id from question_topic_tag where tag_id='" + Topic_id + "')";
                                            preparedStatement = connection.prepareStatement(p_related_topic);
                                            resultSet = preparedStatement.executeQuery();
                                            while (resultSet.next()) {
                                                int unique_id = resultSet.getInt("unique_id");
                                                String topic_nameA = convertStringUpperToLower(resultSet.getString("topic_name"));
                                                int total_followers_sider_bar = resultSet.getInt("totoal_followers");
                                                if (Integer.valueOf(Topic_id) != unique_id) {
                                                    if (topic_nameA != null) {
                                                        countQ++;
                                                        %><li><span title="Total followers of <%=topic_nameA%> is <%=total_followers_sider_bar%>"><a href="topic.jsp?t=<%=topic_nameA.replaceAll(" ", "-")%>&id=<%=unique_id%>&sl=<%=sl%>"><%=topic_nameA%></a>&nbsp;(<%=total_followers_sider_bar%>)</span></li><%}
                                                }
                                            }
                                            if (countQ == 0) {
                                                out.println("<br>No Related Tag Found !!");
                                            }
out.println("<li style='color:red;'><a href='FollowMoreTopic.jsp' style='color:red;'>Follow More Topic</a></li>");
                                        } catch (Exception e) {
                                            out.println("Unable to retrieve!!" + e);
                                        }
                                        %>    
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
                            </div>
                            <div class="clear-fix"></div>

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
        </div> <!-- /.main-page-wrapper -->
    </body></html>