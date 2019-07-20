<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@page language="java"%>
        <%@page import="java.sql.*"%>
        <%@include file="site.jsp" %>
        <%@include file="validator.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%!            
            String TOPIC_MAY_YOU_LIKE = "";
            String FOLLOW = "";
            String FOLLOWED = "";
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                TOPIC_MAY_YOU_LIKE = "विषय, आपको पसंद आ सकता है";
                FOLLOW = "फॉलो करे";
                FOLLOWED = "फॉलो कर चुके हैं";

            } else {
                TOPIC_MAY_YOU_LIKE = "Topic, may you like";
                FOLLOW = "Follow";
                FOLLOWED = "Followed";
            }
        %>
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Follow More Topic | InquiryHere </title>
        <!-- Main style sheet -->
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
            <!-- Header _________________________________ -->
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
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;margin-bottom:0px;">
                                        <div class="boxHeading">
                                            <%=TOPIC_MAY_YOU_LIKE%>
                                        </div>
                                        <%--
                                            
                                            if (session.getAttribute("email") != null) {
                                                String email = (String) session.getAttribute("email");
                                                try {
                                                    String p = "SELECT * FROM newuser WHERE email = '" + email + "'";
                                                    preparedStatement = connection.prepareStatement(p);
                                                    resultSet = preparedStatement.executeQuery();
                                                    while (resultSet.next()) {
                                                        id_of_user = resultSet.getInt("id");
                                                    }
                                                } catch (Exception e) {
                                                    out.println("Unable to retrieve!!" + e);
                                                }
                                            }
                                        --%>

                                        <%
                                            int id_of_user = 0;
                                            try{
                                                if(session.getAttribute("Session_id_of_user") != null){
                                                   id_of_user = (Integer) session.getAttribute("Session_id_of_user"); 
                                                }                       
                                            }catch(Exception msg){
                                                out.println(msg);
                                            }
                                            try {
                                                //Connection con_topic = null;
                                                String name_topic = null;
                                                String p_topic = "select t.unique_id,t.topic_name,(select count(topic_id) from topic_followers_detail "
                                                        + "where topic_id = t.unique_id)as count from topic t";
                                                preparedStatement = connection.prepareStatement(p_topic);
                                                resultSet = preparedStatement.executeQuery();
                                                int i = 1;
                                                String Status = null;
                                                while (resultSet.next()) {
                                                    int _topic_id = resultSet.getInt("t.unique_id");
                                                    int count = resultSet.getInt("count");
                                                    name_topic = convertStringUpperToLower(resultSet.getString("t.topic_name"));
                                        %>   
                                        <div style="width:auto;height:52px;border:1px solid #000;float: left; margin-right: 5px; margin-bottom: 5px;" class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                            <span title="Totoal followers of <%=name_topic%> is <%=count%>"><a href=topic.jsp?t=<%=name_topic.replaceAll(" ", "+")%>&id=<%=_topic_id%>&sl=<%=sl%>><%=name_topic%></a> (<%=count%>)</span>
                                            <%
                                                Statement stmt_topic_followers;
                                                ResultSet rs_topic_followers;
                                                stmt_topic_followers = connection.createStatement();
                                                String topic_followers = "SELECT * FROM topic_followers_detail";
                                                rs_topic_followers = stmt_topic_followers.executeQuery(topic_followers);
                                                try {
                                                    while (rs_topic_followers.next()) {
                                                        int f_topic_id = rs_topic_followers.getInt("topic_id");
                                                        int f_followers_id = rs_topic_followers.getInt("user_or_followers_id");

                                                        if ((f_topic_id == _topic_id) && (f_followers_id == id_of_user)) {
                                                            Status = "present";
                                                        }
                                                    }
                                                } catch (Exception msg) {
                                                    out.println("Error in inner blok" + msg);
                                                }
                                                stmt_topic_followers.close();
                                                rs_topic_followers.close();
                                            %>
                                            <%
                                                if (Status == "present") {
                                            %> <input type="button" value="Unfollow" id="myButton1" onclick="return take_value(this, '<%=_topic_id%>', '<%=id_of_user%>');" /><%
                                                    } else {%>
                                            <input type="button" value="follow" id="myButton1" onclick="return take_value(this, '<%=_topic_id%>', '<%=id_of_user%>');" />
                                            <% }
                                                        Status = null;
                                                        out.println("</div>");
                                                    }

                                                } catch (Exception e) {
                                                    out.println("Unable to retrieve - >" + e);
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
                <%@include file="notificationhtml.jsp" %>
                <jsp:include page="footer.jsp">
                    <jsp:param name="sl" value="<%=sl%>"/>
                </jsp:include>
                <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
                <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
                <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
            </div>
    </body>
</html>