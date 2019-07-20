<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    if (request.getParameter("shord_word") != null) {
        String shord_word = request.getParameter("shord_word").toUpperCase();
        String full_form = request.getParameter("full_form");
        String category = request.getParameter("category");
        out.println("shord_word :->"+shord_word);
        out.println("<br>");
        out.println("full_form :->"+full_form);
        out.println("<br>");
        out.println("category->"+category);
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
                String sql = "INSERT INTO `full_form` (`short_word`,`full_form`,`category`) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, shord_word);
                preparedStatement.setString(2, full_form);
                preparedStatement.setString(3, category);
                boolean value = preparedStatement.execute();
                if(!value){
                    out.println("<br>Value has been successfully inserted<br>");
                }
            } catch (Exception msg) {
                out.println("Exception error in actual coad:->" + msg);
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
    response.sendRedirect("uploadFullform.jsp");
%>
<br><a href="uploadFullform.jsp">Back to upload Full Form</a>