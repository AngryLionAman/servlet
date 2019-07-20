<%-- 
    Document   : SubmitUserProfileComment
    Created on : 3 May, 2019, 11:49:32 AM
    Author     : AngryLion
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="site.jsp" %>
<% 
    if(session.getAttribute("Session_id_of_user") != null) {
        if (request.getParameter("OnCommentUserId") != null && request.getParameter("comments") != null) {
            int user_id = (Integer) session.getAttribute("Session_id_of_user");
            int OnCommentUserId = Integer.parseInt(request.getParameter("OnCommentUserId"));
            String CommentOnProfile = request.getParameter("comments");
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
%>

<%
    try {
        String sql = "INSERT INTO comments (user_id,userprofileid,comments)VALUES(?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);//Who commented
        preparedStatement.setInt(2, OnCommentUserId);//where user commented
        preparedStatement.setString(3, CommentOnProfile);//What Session user commented
        boolean status = preparedStatement.execute();
        if (status) {
            out.println("found some error");
        } else {
            try { //Here userId  = who will cpg the notification..
                String store_commment_notification = "INSERT INTO notification (user_id,notification_type,followers_id) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(store_commment_notification);
                preparedStatement.setInt(1, OnCommentUserId);//Who will get the notification
                preparedStatement.setString(2, "comment_on_Profile");
                preparedStatement.setInt(3, user_id); //Here user_id belong to user give the commnet meand followers id
                preparedStatement.execute();
            } catch (Exception msg) {
                out.println("Error in storing the comment notification :- " + msg);
            }
            response.sendRedirect("profile.jsp?ID=" + OnCommentUserId);
        }
    } catch (Exception msg) {
        out.println(msg);
    }

%>

<%            } catch (Exception e) {
                out.println("Error in main try block:-" + e);
            } finally {

                if (connection != null || !connection.isClosed()) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        out.println("Exception in closing connection " + e);
                    }
                }
//                if (resultSet != null || !resultSet.isClosed()) {
//                    try {
//                        resultSet.close();
//                    } catch (Exception e) {
//                        out.println("Exception in closing resulatset " + e);
//                    }
//                }
                if (preparedStatement != null || !preparedStatement.isClosed()) {
                    try {
                        preparedStatement.close();
                    } catch (Exception e) {
                        out.println("Exception in closing preparedStatement " + e);
                    }
                }
            }

        } else {
            out.println("It seem like you are logged in but trying to acces this page directly<br>Please follow the procedure");
        }
    } else {
        out.println("Please Login First to Comment....");
    }

%>