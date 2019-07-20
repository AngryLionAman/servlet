<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%
    String sl = request.getParameter("sl");
    if (sl == null) {
        sl = "en";
    }
    String email = (String) session.getAttribute("email");
    if (email == null) {
        out.println("you can't access this page direcitly");
    } else {
        if (request.getParameterValues("MultipleSelectedTopic") != null && session.getAttribute("email") != null) {
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
                    connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                }
                try {
                    String v = "select ID from newuser where email='" + email + "'";
                    preparedStatement = connection.prepareStatement(v);
                    resultSet = preparedStatement.executeQuery();
                    int user_id = 0;
                    while (resultSet.next()) {
                        user_id = resultSet.getInt("ID");
                    }
                    String[] Topic = request.getParameterValues("MultipleSelectedTopic");
                    for (int i = 0; i < Topic.length; i++) {
                        int Topic_id3 = 0;
                        String v3 = "SELECT  unique_id FROM topic WHERE topic_name = '" + Topic[i] + "'";
                        preparedStatement = connection.prepareStatement(v3);
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            Topic_id3 = resultSet.getInt("unique_id");
                        }
                        String p = "insert into topic_followers_detail(topic_id,user_or_followers_id) values('" + Topic_id3 + "','" + user_id + "') ";
                        preparedStatement = connection.prepareStatement(p);
                        preparedStatement.executeUpdate();
                    }
                    out.println("Recored has been successfully updated");
                    response.sendRedirect("Login.jsp?sl=" + sl);
                } catch (Exception e1) {
                    out.print("Error:-" + e1);
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
                if (resultSet != null || !resultSet.isClosed()) {
                    try {
                        resultSet.close();
                    } catch (Exception e) {
                        out.println("Exception in closing resulatset " + e);
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
        } else {
            out.println("It seem like you are logedin but trying to access this site directly");
        }

    }
%>