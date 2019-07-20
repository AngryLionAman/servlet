<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    if (request.getParameter("toptendescription") != null) {
        String toptendescription = request.getParameter("toptendescription");
        String toptentitle = request.getParameter("toptentitle");
        out.println("Top 10 Title:-> ");
        out.println(toptentitle);
%>
<br><br><a href="topten.jsp">Back to upload poem page</a><br><br>
<%
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    out.println("Exception in loading the class forname Driver" + ex);
                }
                connection = DriverManager.getConnection("jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8", "root", null);
            }
            //***Actual coad//
            try {
                //Here, Using the REPLACE sql command instand of insert.
                //Both work same but , in replace case if value is not exist the it will store the value
                //but in insert command always be create a new row
                String sql = "INSERT INTO `topten` (`tt_title`,`tt_description`) VALUES (?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, toptentitle);
                preparedStatement.setString(2, toptendescription);
                boolean value = preparedStatement.execute();
                if (!value) {
                    out.println("<br> Upload successfully<br>");
                }
            } catch (Exception msg) {
                out.println("Exception error in actual coad:->" + msg);
            }

            //Actual coad eanding
            //Display the Story Title
            try {
                String sql1 = "select * from topten order by tt_id desc limit 10";
                preparedStatement = connection.prepareStatement(sql1);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    String tt_title = resultSet.getString("tt_title");
                    int tt_id = resultSet.getInt("tt_id");
                    out.println("<br>");
                    out.println((tt_id - 2) + ". " + tt_title);

                }
            } catch (Exception msg) {
                out.println("displaying poem title:" + msg);
            }
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

            if (preparedStatement != null || !preparedStatement.isClosed()) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    out.println("Exception in closing preparedStatement " + e);
                }
            }
        }
    }
%>