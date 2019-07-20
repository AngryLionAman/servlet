<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    if (request.getParameter("wa_status") != null) {
        String wa_status = request.getParameter("wa_status");
        String category = request.getParameter("category");
        out.println("wa_status :->"+wa_status);
        out.println("<br>");
        out.println("category :->"+category);
        %>
<br><a href="uploadWaStatus.jsp">Back to upload Sayari and WhatsApp status</a>
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
                String sql = "INSERT INTO `whatsapp_status` (`wa_status`,`wa_status_category`) VALUES (?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, wa_status);
                preparedStatement.setString(2, category);
                boolean value = preparedStatement.execute();
                if(!value){
                    out.println("<br>Value has been successfully inserted<br>");
                }else{
                    out.println("Some Error found");
                }
            } catch (Exception msg) {
                out.println("Exception error in actual coad:->" + msg);
            }
            try {
                String sql1 = "select * from whatsapp_status order by wa_status_id desc limit 10";
                preparedStatement = connection.prepareStatement(sql1);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    String sayari = resultSet.getString("wa_status");
                    String story_id = resultSet.getString("wa_status_id");
                    //out.println("<br>");
                    out.println(story_id + ". " + sayari);

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
    }else out.println("someThing wrong");
    //response.sendRedirect("uploadFullform.jsp");
%>
