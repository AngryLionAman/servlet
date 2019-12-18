<%@page language="java" %>
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%
    if (session.getAttribute("Session_id_of_user") != null) {
        if (request.getParameter("action") != null && request.getParameter("question_answer_id") != null && request.getParameter("section") != null) {
            int question_answer_id = Integer.parseInt(request.getParameter("question_answer_id"));
            String action = request.getParameter("action");
            String section = request.getParameter("section");
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
                if (section.equals("answer")) {
                    if (action.equals("upvote")) {
                        sql = "UPDATE answer SET vote = vote+1 WHERE a_id =?";
                    }
                    if (action.equals("downvote")) {
                        sql = "UPDATE answer SET vote = vote-1 WHERE a_id =?";
                    }
                }
                if (section.equals("question")) {
                    if (action.equals("upvote")) {
                        sql = "UPDATE question SET vote = vote+1 WHERE q_id =?";
                    }
                    if (action.equals("downvote")) {
                        sql = "UPDATE question SET vote = vote-1 WHERE q_id =?";
                    }
                }
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, question_answer_id);
                preparedStatement.executeUpdate();
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