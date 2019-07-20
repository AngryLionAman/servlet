<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@include file="site.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
        </style>
        <title>Total user</title>
    </head>
    <body>

        <h2>Hi Aman, i hope you are doing well | List of total registred user </h2><a href="adminModule.jsp">Admin Module</a>

        <table>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Higher Edu</th>
                <th>B Ach</th>
                <th>Bio</th>
                <th>Action</th>
            </tr>

            <%
                try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                String sql = "SELECT * FROM newuser";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");
                    String email = resultSet.getString("email");
                    String higher_edu = resultSet.getString("higher_edu");
                    String best_achievement = resultSet.getString("best_achievement");
                    String bio = resultSet.getString("bio");
                    out.println("<tr>");
                    out.println("<td>" + id + "</td>");
                    out.println("<td>" + firstname + " " + lastname + "</td>");
                    out.println("<td>" + email + "</td>");
                    out.println("<td>" + higher_edu + "</td>");
                    out.println("<td>" + best_achievement + "</td>");
                    out.println("<td>" + bio + "</td>");
                    out.println("<td><a href=user_edit_update.jsp?id=" + id + ">Edit</a> Block</td>");
                    out.println("</tr>");
                }
                connection.close();
                resultSet.close();
                preparedStatement.close();
                }catch(Exception e){
                    out.println(e);
                }
            %>


        </table>

    </body>
</html>
