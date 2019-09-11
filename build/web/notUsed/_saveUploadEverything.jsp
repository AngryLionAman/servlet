<%-- 
    Document   : saveUploadEverything
    Created on : 28 May, 2019, 7:29:53 AM
    Author     : AngryLion
--%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@include file="site.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    int userID = 0;
    if (session.getAttribute("Session_id_of_user") != null) {
        userID = (Integer) session.getAttribute("Session_id_of_user");
    }
    String page_name = request.getParameter("page");
    String category = request.getParameter("category");
    String based_on = request.getParameter("based_on");
    String subject = request.getParameter("subject");
    String description = request.getParameter("description");
    String author = request.getParameter("author");

//    out.println(
//              "<br>" + page_name 
//            + "<br>" + category 
//            + "<br>" + based_on 
//            + "<br>" + subject 
//            + "<br>" + description 
//            + "<br>" + author);

%>
<%    Connection connection = null;
    //ResultSet resultSet = null;
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
    try {
        //to save all the data
        String sql = "insert into everything(page,category,based_on,subject,description,author,posted_by)values(?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, page_name);
        preparedStatement.setString(2, category);
        preparedStatement.setString(3, based_on);
        preparedStatement.setString(4, subject);
        preparedStatement.setString(5, description);
        preparedStatement.setString(6, author);
        preparedStatement.setInt(7, userID);
        boolean status = preparedStatement.execute();
        if (status) {
            out.println(status);
        } else {
            out.println("<br>successfully inserted");
        }
    } catch (Exception msg) {
        out.println(msg);
    }

%>
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
//        try {
//            if (resultSet != null || !resultSet.isClosed()) {
//                try {
//                    resultSet.close();
//                } catch (Exception e) {
//                    out.println("Exception in closing resulatset " + e);
//                }
//            }
//        } catch (Exception msg) {
//            out.println("Error in connection" + msg);
//        }
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
response.sendRedirect("uploadEverything.jsp");
%>
