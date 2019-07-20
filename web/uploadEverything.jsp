<%-- 
    Document   : uploadEverything
    Created on : 26 May, 2019, 10:31:08 PM
    Author     : AngryLion
--%>

<html lang="en">
    <%@include file="site.jsp" %>
    <%@page language="java" %>
    <%@page import="java.sql.*" %> 
    <head>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta charset="UTF-8">

        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Update Profile | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            input[type=text] {
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid red;
                border-radius: 2px;
            }
            textarea[type=text]{
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid red;
                border-radius: 2px;
            }
            .button {
                background-color: #4CAF50; /* Green */
                border: 1px solid red;
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
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="eng"/>
            </jsp:include>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>
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
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">                                                   
                            <div class="row"><center>                                                           
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">

                                            <form action="saveUploadEverything.jsp" method="post" >

                                                <label for="fname">What you want to upload (*)</label>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" list="page" name="page" required="">
                                                    <datalist id="page">
                                                        <%
                                                            try {
                                                                String sql_page = "SELECT DISTINCT page FROM everything";
                                                                preparedStatement = connection.prepareStatement(sql_page);
                                                                resultSet = preparedStatement.executeQuery();
                                                                while (resultSet.next()) {
                                                                    String page_list = resultSet.getString("page").toUpperCase();
                                                        %><option value="<%=page_list%>"><%
                                                                }
                                                            } catch (Exception msg) {
                                                                out.println(msg);
                                                            }

                                                            %>

                                                    </datalist>

                                                </div>
                                                <label for="fname">what is the Category</label>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" list="category" name="category">
                                                    <datalist id="category">
                                                        <%                                                            try {
                                                                String sql_page = "SELECT DISTINCT category FROM everything WHERE category IS NOT NULL";
                                                                preparedStatement = connection.prepareStatement(sql_page);
                                                                resultSet = preparedStatement.executeQuery();
                                                                while (resultSet.next()) {
                                                                    String category = resultSet.getString("category").toUpperCase();
                                                        %><option value="<%=category%>"><%
                                                                }
                                                            } catch (Exception msg) {
                                                                out.println(msg);
                                                            }

                                                            %>

                                                    </datalist>
                                                </div>
                                                <label for="fname">This is Based on</label>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" list="based_on" name="based_on" value="" >
                                                    <datalist id="based_on">
                                                        <%                                                            try {
                                                                String sql_page = "SELECT DISTINCT based_on FROM everything WHERE based_on IS NOT NULL";
                                                                preparedStatement = connection.prepareStatement(sql_page);
                                                                resultSet = preparedStatement.executeQuery();
                                                                while (resultSet.next()) {
                                                                    String based_on = resultSet.getString("based_on").toUpperCase();
                                                        %><option value="<%=based_on%>"><%
                                                                }
                                                            } catch (Exception msg) {
                                                                out.println(msg);
                                                            }

                                                            %>

                                                    </datalist>
                                                </div>
                                                <label for="fname">subject for this input</label>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" name="subject" value="" >
                                                </div>

                                                <label for="fname">Full detail of this input (*)</label>
                                                <div class="boxHeading">
                                                    <textarea type="text" class="anstext" name="description" required="" ></textarea>
                                                </div>
                                                <label for="fname">Author</label>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" name="author" list="author" value="" >
                                                    <datalist id="author">
                                                        <%                                                            try {
                                                                String sql_page = "SSELECT DISTINCT author FROM everything WHERE author IS NOT NULL";
                                                                preparedStatement = connection.prepareStatement(sql_page);
                                                                resultSet = preparedStatement.executeQuery();
                                                                while (resultSet.next()) {
                                                                    String author = resultSet.getString("author").toUpperCase();
                                                        %><option value="<%=author%>"><%
                                                                }
                                                            } catch (Exception msg) {
                                                                out.println(msg);
                                                            }

                                                            %>

                                                    </datalist>
                                                </div>
                                                <div class="float-right margintop20" style="vertical-align:bottom">
                                                    <button type="submit" class="btn" data-toggle="modal" >Save</button>
                                                </div>

                                            </form>


                                        </div>
                                    </div>

                                </center> </div>


                        </div>
                        <%                            } catch (Exception e) {
                                out.println("Error in main try block:-" + e);
                            } finally {

                                try {
                                    if (connection != null || !connection.isClosed()) {
                                        try {
                                            connection.close();
                                        } catch (Exception e) {
                                            out.println("Exception in closing connection " + e);
                                        }
                                    }
                                } catch (Exception msg) {
                                    out.println("Error in connection" + msg);
                                }
                                try {
                                    if (resultSet != null || !resultSet.isClosed()) {
                                        try {
                                            resultSet.close();
                                        } catch (Exception e) {
                                            out.println("Exception in closing resulatset " + e);
                                        }
                                    }
                                } catch (Exception msg) {
                                    out.println("Error in connection" + msg);
                                }
                                try {
                                    if (preparedStatement != null || !preparedStatement.isClosed()) {
                                        try {
                                            preparedStatement.close();
                                        } catch (Exception e) {
                                            out.println("Exception in closing preparedStatement " + e);
                                        }
                                    }
                                } catch (Exception msg) {
                                    out.println("Error in connection" + msg);
                                }
                            }
                        %>
                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>
            <%@include file="notificationhtml.jsp" %>
            <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="eng"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>

        </div> 
    </body>

</html>