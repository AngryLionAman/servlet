<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    if (request.getParameter("didyouknow") != null) {
        String didyouknow = request.getParameter("didyouknow");
        
        out.println(didyouknow);
        %>
<br><br><a href="didyouknow.jsp">Back to upload</a><br><br>
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
                String sql = "INSERT INTO `didyouknow` (`didyouknow`) VALUES (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, didyouknow);
                
                preparedStatement.execute();
            } catch (Exception msg) {
                out.println("Exception error in actual coad:->" + msg);
            }
            //Actual coad eanding
            //Display the Story Title
            try {
                String sql1 = " select * from didyouknow order by dyk_id desc limit 10";
                preparedStatement = connection.prepareStatement(sql1);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    
                    String didyouknow1 = resultSet.getString("didyouknow");
                    int dyk_id = resultSet.getInt("dyk_id");
                    out.println("<br>");
                    out.println(dyk_id+". "+didyouknow1);

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
    }else out.println("someThing wrong");
%>
