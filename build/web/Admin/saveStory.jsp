<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    if (request.getParameter("story") != null) {
        String story = request.getParameter("story");
        String story_category = request.getParameter("story_category");
        String story_title = request.getParameter("story_title");
        out.println("Stoyr Title:-> ");
        out.println(story_title);
%>
<br><a href="uploadStory.jsp">Back to upload Story page</a>
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
                String sql = "INSERT INTO `story` (`story_title`,`story`,`story_category`) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, story_title);
                preparedStatement.setString(2, story);
                preparedStatement.setString(3, story_category);
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
                String sql1 = "select * from story";
                preparedStatement = connection.prepareStatement(sql1);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    String story_title1 = resultSet.getString("story_title");
                    String story_id = resultSet.getString("story_id");
                    out.println("<br>");
                    out.println(story_id + ". " + story_title1);

                }
            } catch (Exception msg) {
                out.println("displaying story title:" + msg);
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
