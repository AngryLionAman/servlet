<%-- 
    Document   : submit_searched_queary
    Created on : 14 Jun, 2019, 3:30:34 PM
    Author     : AngryLion
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="site.jsp"%>
<%
if(request.getParameter("searched_queary")!= null){
    String searched_queary = request.getParameter("searched_queary");
   try {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);

        String sql = "insert into searched_queary(searched_queary) values(?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, searched_queary);
        preparedStatement.execute();

    } catch (Exception msg) {
        out.println("This is fucking exception error:" + msg);
    } 
}
    
%>