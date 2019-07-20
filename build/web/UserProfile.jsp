<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%@include file="validator.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%!              String USER_PROFILE = "";
            String FOLLOW = "";
            String FOLLOWED = "";
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                USER_PROFILE = "प्रोफ़ाइल";
                FOLLOW = "फॉलो करे";
                FOLLOWED = "फॉलो कर चुके हैं";

            } else {
                USER_PROFILE = "User Profile";
                FOLLOW = "Follow";
                FOLLOWED = "Followed";
            }
        %>
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>User Profie</title>
        <meta property="og:description" content="india first knowledge based social media where experts give you advise and suggestion related to your query .you can ask and share the information which you want to explore.our motive is to be specific according to your demand" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="india first knowledge based social media platform where experts give you advise and suggestion related to your query" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />

        <!-- Main style sheet -->
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script type="text/javascript">

            function take_value(el,user_id, id_of_user) {
            <% if (session.getAttribute("email") == null) { %>
                alert("Please login first");<%
                } else { %>
                if (el.value === "follow") {
                    el.value = "followed";
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_follower_detail.jsp?val_topic=" + user_id + "&val2_topic=" + id_of_user + "&action=follow", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                } else {
                    el.value = "follow";
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_follower_detail.jsp?val_topic=" + user_id + "&val2_topic=" + id_of_user + "&action=delete", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }
            <% }%>
            }
        </script>

    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"></jsp:param>                   
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


                            <div class="clear-fix"></div>


                            <div class="clear-fix"></div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;margin-bottom:0px;">
                                        <div class="boxHeading">
                                            <%=USER_PROFILE%>
                                        </div>
                                        <!-- <div style="">Post something here</div> -->
                                        <div class="userProfiles">
                                            <%
                                                int id_of_user = 0;
                                                if(session.getAttribute("Session_id_of_user") != null){
                                                    id_of_user = (Integer) session.getAttribute("Session_id_of_user");
                                                }
//                                                if (session.getAttribute("email") != null) {
//                                                    String email = (String) session.getAttribute("email");
//                                                    try {
//                                                        String sql = "SELECT * FROM newuser WHERE email = '" + email + "'";
//                                                        preparedStatement = connection.prepareStatement(sql);
//                                                        resultSet = preparedStatement.executeQuery();
//                                                        while (resultSet.next()) {
//                                                            id_of_user = resultSet.getInt("id");
//                                                        }
//                                                    } catch (Exception e) {
//                                                        out.println("Unable to retrieve!!" + e);
//                                                    }
//                                                }
                                            %>

                                            <%
                                                String firstname, imagepath;
                                                int user_id = 0;
                                                String Status = null;
                                                try {
                                                    String sql = "SELECT ID,firstname,imagepath FROM newuser";
                                                    preparedStatement = connection.prepareStatement(sql);
                                                    resultSet = preparedStatement.executeQuery();
                                                    while (resultSet.next()) {
                                                        user_id = resultSet.getInt("ID");
                                                        firstname = resultSet.getString("firstname");
                                                        imagepath = resultSet.getString("imagepath");
                                                        Statement stmt_topic_followers;

                                                        ResultSet rs_topic_followers;
                                                        stmt_topic_followers = connection.createStatement();
                                                        String topic_followers = "SELECT * FROM ak_follower_detail";
                                                        rs_topic_followers = stmt_topic_followers.executeQuery(topic_followers);

                                                        while (rs_topic_followers.next()) {
                                                            int db_user_id = rs_topic_followers.getInt("user_id");
                                                            int f_followers_id = rs_topic_followers.getInt("followers_id");

                                                            if ((db_user_id == user_id) && (f_followers_id == id_of_user)) {
                                                                Status = "present";
                                                            }
                                                        }
                                                        stmt_topic_followers.close();
                                                        rs_topic_followers.close();
                                            %>
                                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">

                                                <img src="images/<%=imagepath%>" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                <a href="profile.jsp?user=<%=convertStringUpperToLower(firstname).replaceAll(" ", "+")%>&ID=<%=user_id%>&sl=<%=sl%>"><%=convertStringUpperToLower(firstname)%></a>
                                                <%
                                                    if (Status == "present") {
                                                        %><input type="button" class="float-right" value="unfollow" id="myButton1" onclick="return take_value(this,'<%=user_id%>', '<%=id_of_user%>');" /><%
                                                    } else {%>
                                                <input type="button" class="float-right" value="follow" id="myButton1" onclick="return take_value(this,'<%=user_id%>', '<%=id_of_user%>');" />
                                                <% }
                                                    Status = null;
                                                %>

                                            </div>
                                            <%
                                                    }

                                                } catch (Exception e) {
                                                    out.println(e.getMessage());
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

                                        <div class="clear-fix"></div>
                                    </div>
                                </div>

                            </div>


                            <div class="row margintop10">


                            </div>

                        </div>
<!--                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                            <div class="themeBox" style="min-height:320px;">
                                <div class="boxHeading">
                                    Module
                                </div>
                                <div>
                                    Need to write something   
                                </div>

                            </div>
                            <div class="clear-fix"></div>
                            <div class="themeBox" style="min-height:240px;">
                                <div class="boxHeading">
                                    Module
                                </div>
                                <div>
                                    Second module   
                                </div>


                            </div>
                            <div class="clear-fix"></div>


                            <div class="clear-fix"></div>
                        </div>-->
                    </div>
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
    </body>
</html>