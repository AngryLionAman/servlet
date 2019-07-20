<%@page language="java" %>
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%
    if (session.getAttribute("Session_id_of_user") != null) {
        if (request.getParameter("action") != null && request.getParameter("session_id_of_user") != null) {
            int session_id_of_user = Integer.parseInt(request.getParameter("session_id_of_user"));
            String action = request.getParameter("action");
            String sql = "";
            Connection connection = null;
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
                if (action.equals("hide")) {
                   sql = "UPDATE newuser SET email_s = '1' WHERE id=?";
                }
                if (action.equals("show")) {
                   sql = "UPDATE newuser SET email_s = '0' WHERE id=?";
                }
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, session_id_of_user);
                preparedStatement.executeUpdate();
                //out.println("Your mail has hidden,Another user won't be able to view this.<br>Only you can see it");
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
    }
%>