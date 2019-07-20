<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../ckeditor/ckeditor.js"></script>
        <title>Admin Chat Box</title>
    </head>
    <body>
        <h1>Welcome To the inquiryhere world. This is for administrator chat module..</h1>
        <a href="adminModule.jsp">Click here for main Admin page</a><br>
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
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8", "root", null);
                }
                try {
                    String sql = "SELECT * FROM chat_box";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        String chat_text = resultSet.getString("chat_text");
                        out.println(chat_text);
                        out.println("<br>");
                    }

                } catch (Exception msg) {
                    out.println("Exception error in actual coad:->" + msg);
                }
        %>
        <form>
            Enter Your Comment : <br><textarea name="chat_text" required="" cols="50" rows="5" placeholder="Type something.."></textarea>
            <input type="submit" name="chat_submit" value="Post chat">
        </form>
        <%
            if (request.getParameter("chat_text") != null && request.getParameter("chat_submit") != null) {
                String chat_text = request.getParameter("chat_text");
                try {
                    String sql = "INSERT INTO chat_box(`chat_text`) VALUES (?)";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, chat_text);
                    boolean status = preparedStatement.execute();
                    if (!status) {
                        out.println("Posted successfully.....");
                        response.sendRedirect("chat.jsp");
                    }

                } catch (Exception msg) {
                    out.println("Exception error in actual coad:->" + msg);
                }

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

                if (preparedStatement != null || !preparedStatement.isClosed()) {
                    try {
                        preparedStatement.close();
                    } catch (Exception e) {
                        out.println("Exception in closing preparedStatement " + e);
                    }
                }
            }
        %>
    </body>
</html>
