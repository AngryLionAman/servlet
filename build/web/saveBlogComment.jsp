<%-- 
    Document   : saveBlogComment
    Created on : 3 May, 2019, 9:20:59 AM
    Author     : AngryLion
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="site.jsp" %>
<%
    if (request.getParameter("blog_id") != null && request.getParameter("comments") != null) {
        int user_id = 0;
        if (session.getAttribute("Session_id_of_user") != null) {
          user_id  = (Integer) session.getAttribute("Session_id_of_user");
        }
        int Blog_id = Integer.parseInt(request.getParameter("blog_id"));
        String BlogComment = request.getParameter("comments");
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
        String sql = "INSERT INTO comments (user_id,blog_id,comments)VALUES(?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, Blog_id);
        preparedStatement.setString(3, BlogComment);
        boolean status = preparedStatement.execute();
        if (status) {
            out.println("found some error");
        } else {
            //out.println("Successfully comment has been inserted");
            //To fatch the user_id of who postd the question
            int User_id_who_posted_Blog = 0;
            String Blog_Subject = null;
            try {
                String sql_user_id = " SELECT blog_posted_by,blog_subject FROM blog WHERE blog_id = ?";
                preparedStatement = connection.prepareStatement(sql_user_id);
                preparedStatement.setInt(1, Blog_id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User_id_who_posted_Blog = resultSet.getInt("blog_posted_by");
                    Blog_Subject = resultSet.getString("blog_subject");
                }

            } catch (Exception msg) {
                out.println("Error in fetching the user id of the question :-" + msg);
            }
            try {
                String store_commment_notification = "INSERT INTO notification (user_id,notification_type,followers_id,blog_id ) VALUES (?,?,?,?)";
                preparedStatement = connection.prepareStatement(store_commment_notification);
                preparedStatement.setInt(1, User_id_who_posted_Blog);
                preparedStatement.setString(2, "comment_on_Blog");
                preparedStatement.setInt(3, user_id); //Here user_id belong to user give the commnet meand followers id
                preparedStatement.setInt(4, Blog_id);
                preparedStatement.execute();
            } catch (Exception msg) {
                out.println("Error in storing the comment notification :- " + msg);
            }
            response.sendRedirect("D_Blog.jsp?sub=" + Blog_Subject.replaceAll(" ", "-") + "&Blog_Id="+Blog_id);
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
        out.println("It seem like you are logged in but trying to acces this page directly<br>Please follow the procedure");
    }

%>