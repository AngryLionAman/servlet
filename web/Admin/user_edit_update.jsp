<%-- 
    Document   : user_edit_update
    Created on : 28 Feb, 2019, 7:47:43 PM
    Author     : AngryLion
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@include file="site.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update the user</title>
    </head>
    <body><center>
        <h1>Edit the selected user!!</h1>
        <%
            String s_id = request.getParameter("id");
            int data = Integer.parseInt(request.getParameter("id"));
            int id = 0;
            String firstname = null;
            String lastname = "";
            String email = "";
            String higher_edu = "";
            String best_achievement = "";
            String bio = "";
            String password = "";
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                String sql = "SELECT * FROM newuser where ID = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, s_id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    firstname = resultSet.getString("firstname");
                    lastname = resultSet.getString("lastname");
                    email = resultSet.getString("email");
                    higher_edu = resultSet.getString("higher_edu");
                    best_achievement = resultSet.getString("best_achievement");
                    bio = resultSet.getString("bio");
                    password = resultSet.getString("password");
                }

            } catch (Exception e) {
                out.println(e);
            }
        %>
        <form>
            <table>
                <tr>
                    <th>ID</th> <td><input type="text" name="id" value="<%=id%>" readonly="" /></td>
                </tr>
                <tr>
                    <th>Firstname</th> <td><input type="text" name="firstname_edit" value="<%=firstname%>" /></td>
                </tr>
                <tr>
                    <th>Lastname</th> <td><input type="text" name="lastname_edit" value="<%=lastname%>" /></td>
                </tr>
                <tr>
                    <th>Email</th> <td><input type="text" name="email_edit" value="<%=email%>"/></td>
                </tr>
                <tr>
                    <th>Password</th> <td><input type="text" name="password_edit" value="<%=password%>" /></td>
                </tr>
                <tr>
                    <th>Higher Education</th> <td><input type="text" name="h_edu_edit" value="<%=higher_edu%>"/></td>
                </tr>
                <tr>
                    <th>Best achivment</th> <td> <textarea type="text" name="b_ach_edit" ><%=best_achievement%></textarea></td>
                </tr>
                <tr>
                    <th>Bio</th> <td><input type="text" name="bio_edit" value="<%=bio%>"/></td>
                </tr>
                <tr>
                    <th></th> <td><input type="submit" value="Update" name="update"/></td>
                </tr>
            </table>
        </form>
        <%

            if (request.getParameter("update") != null) {
                try {
                    String firstname_edit = request.getParameter("firstname_edit");
                    String lastname_edit =  request.getParameter("lastname_edit");
                    String email_edit =  request.getParameter("email_edit");
                    String higher_edu_edit = request.getParameter("h_edu_edit");;
                    String best_achievement_edit =  request.getParameter("b_ach_edit");
                    String bio_edit =  request.getParameter("bio_edit");
                    String password_edit =  request.getParameter("password_edit");
                    String sql = "update newuser set "
                            + "firstname = '" + firstname_edit + "'"
                            + ",lastname = '" + lastname_edit + "'"
                            + ",email = '" + email_edit + "'"
                            + ",password = '" + password_edit + "'"
                            + ",higher_edu = '" + higher_edu_edit + "'"
                            + ",bio = '" + bio_edit + "'"
                            + ",best_achievement = '" + best_achievement_edit + "'"
                            + "where ID = '" + s_id + "'";
                    preparedStatement = connection.prepareStatement(sql);
                    /*preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, higher_edu);
                preparedStatement.setString(6, bio);
                preparedStatement.setString(7, best_achievement);
                //preparedStatement.setString(8, s_id);
                preparedStatement.setInt(8, data);*/

                    preparedStatement.executeUpdate();

                    out.println("sdone");
                    connection.close();
                    preparedStatement.close();
                    response.sendRedirect("TotalRegistredUser.jsp");
                } catch (Exception msg) {
                    out.println(msg);
                }
            } else {
                out.println("some thing happen");
            }


        %>
    </center> </body>
</html>
