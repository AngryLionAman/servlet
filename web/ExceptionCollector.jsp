<%-- This module will work when exception will occer
Get IP Address of computer
Get Parameter with argument of current page
Get User Name and ID of user if user is login other wise null value
Get exception message
--%>
<%@page import="java.net.InetAddress"%>
<%@page language="java" %>
<%@page import="java.sql.*" %> 
<meta charset="UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String userName = request.getParameter("userName");
    int userId = Integer.parseInt(request.getParameter("userID"));
    String ComputerIp = null;
    try {/*Findig the ipAddress of computer*/
        InetAddress localhost = InetAddress.getLocalHost();
        ComputerIp = (localhost.getHostAddress()).trim();

    } catch (Exception e) {
        ComputerIp = e.toString();
    }

    String URLParameter = request.getParameter("URLParameter");
    String ExceptionMessage = request.getParameter("ExceptionMessage");

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                out.println("Exception in loading the class forname Driver" + ex);
            }
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bharat", "root", null);
        }
        try {
            String sql = "insert into exception(username,userid,computer_ip,urlparameter,exception_message) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, ComputerIp);
            preparedStatement.setString(4, URLParameter);
            preparedStatement.setString(5, ExceptionMessage);
            preparedStatement.execute();
        } catch (Exception msg) {
            out.println("Exception in Exception collector module" + msg);
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
%>