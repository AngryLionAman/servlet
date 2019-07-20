<%@page import="java.util.ArrayList"%>
<html lang="en">
    <head>
        <%
       if(session.getAttribute("Session_id_of_user")== null){
           response.sendRedirect("Login.jsp");
       }
        %>
        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            %>
        <meta charset="UTF-8">
        <%@page language="java"%>
        <%@page import="java.sql.*"%>
        <%@include file="site.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Edit question</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">

    </head>

    <body>
        <div class="main-page-wrapper">
            <%
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                int Question = 0;
                if (request.getParameter("Id") != null) {
                    Question = Integer.valueOf(request.getParameter("Id"));
                } else {
                    response.sendRedirect("index.jsp");
                }
            %>


            <!-- Header _________________________________ -->
            <%--@include file="header.jsp" --%>
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value=""/>
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
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12"></div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <%
                                        String selectQuestion = "";
                                        try {

                                            String sql = "select question from question where q_id=?";
                                            preparedStatement = connection.prepareStatement(sql);
                                            preparedStatement.setInt(1, Question);
                                            resultSet = preparedStatement.executeQuery();
                                            while (resultSet.next()) {
                                                selectQuestion = resultSet.getString("question");
                                            }
                                        } catch (Exception error) {
                                            out.println("Error in question retriving blok");
                                        }
                                    %>
                                    <form name="submitquestion" method="post" action="update_q.jsp">
                                        <input type="hidden" name="question_id" value="<%=Question%>">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                Edit your question here
                                            </div>

                                            <div><textarea type="text" class="anstext" name="question" placeholder="Question"><%=selectQuestion%></textarea></div>


                                            <div class="float-right margintop20" style="vertical-align:bottom">
                                                <button type="submit"  class="btn" >Update</button>
                                                <!-- btn-info btn-lg -->
                                            </div>

                                            <div class="clear-fix"></div>
                                        </div> 
                                    </form>
                                </div>

                            </div>

                        </div>


                    </div>

                </div>

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
            <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>

            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->

    </body>
</html>