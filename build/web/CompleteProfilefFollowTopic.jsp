<html lang="en">
    <head>
        <%@page language="java"%>
        <%@page import="java.sql.*"%>
        <%@include file="site.jsp" %>
        <%@include file="validator.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

        <%!            String SELECT_AT_LEAST_FIVE_TOPIC = "";
            String FOLLOW = "";
            String NOTE = "";
            String SEARCH = "";
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {

                SELECT_AT_LEAST_FIVE_TOPIC = "कम से कम पांच विषय का चयन करें";
                FOLLOW = "फॉलो करे ";
                NOTE = "नोट: यदि आप डेस्कटॉप ब्राउज़र का उपयोग कर रहे हैं तो <b> CTRL </b> दबाएं और विषय का चयन करें";
                SEARCH = "खोजे";
            } else {

                SELECT_AT_LEAST_FIVE_TOPIC = "Select at least five topic";
                FOLLOW = "Follow";
                NOTE = "Note : If you are using desktop browser then press <b>CTRL</b> and select topic";
                SEARCH = "Search";
            }
        %>
        <%
            if (session.getAttribute("email") == null) {
                response.sendRedirect("signup.jsp?sl=" + sl);
            }
        %>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>SignUp | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
    </head>
    <body>
        <div class="main-page-wrapper">
            <header class="home-page">
                <div class="container clear-fix">
                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" style="padding-left:0px;">
                        <div class="logo float-left">
                            <a href="#" style="vertical-align:middle;">
                                <h4>
                                    <div class="logotext">
                                        Inquiryhere.com
                                        <div>

                                            </h4>
                                            </a></div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 serachhere" style="display:inline-block;">

                                        <div style="overflow: hidden; padding-right: .5em;">
                                            <form action="SearchBar.jsp">
                                                <input type="hidden" name="sl" value="<%=sl%>">
                                                <input type="text" style="width: 100%;" name="search" required="" >
                                                <button type="submit" style="float: right;width: 50px;" /><%=SEARCH%></button>
                                            </form>
                                        </div>


                                    </div>

                                    </div>

                                    </header>
                                    <div class="clear-fix"></div>
                                    <div class="bodydata">
                                        <div class="container clear-fix">
                                            <div class="row">

                                                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">                                                   
                                                    <div class="row"><center>                                                           
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                <div class="themeBox" style="height:210px;">  
                                                                    <label for="fname"><%=SELECT_AT_LEAST_FIVE_TOPIC%></label>
                                                                    <form action="SubmitProfileFollowTopic.jsp" method="get" name="dropdown_selection">  
                                                                        <input type="hidden" name="sl" value="<%=sl%>">
                                                                        <select id="s5" multiple="multiple" name="MultipleSelectedTopic" >

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
                                                                            <%
                                                                                String TopicName;
                                                                                try {
                                                                                    String sql_v = "SELECT * FROM topic";
                                                                                    preparedStatement = connection.prepareStatement(sql_v);
                                                                                    resultSet = preparedStatement.executeQuery();
                                                                                    while (resultSet.next()) {
                                                                                        TopicName = resultSet.getString("topic_name").substring(0, 1).toUpperCase() + resultSet.getString("topic_name").substring(1).toLowerCase();
                                                                                        out.println("<option>" + TopicName + "</option>");
                                                                                    }

                                                                                } catch (Exception e) {
                                                                                    out.println(e.getMessage());
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
                                                                        </select>                                                                                 
                                                                        <div class="float-right margintop20" style="vertical-align:bottom">
                                                                            <button type="submit" class="btn" data-toggle="modal" ><%=FOLLOW%></button>
                                                                        </div>
                                                                    </form>                                                                        
                                                                </div>
                                                            </div>
                                                            <div style="color:red;"><%=NOTE%></div>

                                                        </center> </div>


                                                </div>

                                                <div class="clear-fix"></div>
                                            </div>
                                            <div class="clear-fix"></div>
                                        </div>
                                        <div class="clear-fix"></div>
                                    </div>
                                    <div class="clear-fix"></div>


                                    <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
                                    <!-- Bootstrap JS -->
                                    <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
                                    <!-- Bootstrap Select JS -->
                                    <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>

                                    </div> 
                                    </body>
                                    </html>