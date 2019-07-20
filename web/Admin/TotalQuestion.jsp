<%-- 
    Document   : TotalQuestion
    Created on : 28 Feb, 2019, 11:30:21 PM
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
        <title>Question</title>
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
    </head>
    <body>
        <h1>Update question and it's tag  !!!!</h1>

        <table>
            <tr>
                <th>ID</th>
                <th>Question</th>
                <th>Tag</th>
                <th>Action</th>
            </tr>
            <%
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                    String sql = "SELECT q_id,question from question";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        String question = resultSet.getString("question");
                        int q_id = resultSet.getInt("q_id");
                        out.println("<tr>");
                        out.println("<td>" + q_id + "</td>");
                        out.println("<td>" + question + "</td>");
                        out.println("<td>");
                        String sql_tag = "select topic_name from topic right join question_topic_tag "
                                + "on topic.unique_id = question_topic_tag.tag_id where question_id =?";

                        PreparedStatement preparedStatement1 = connection.prepareStatement(sql_tag);
                        preparedStatement1.setInt(1, q_id);
                        ResultSet resultSet1 = preparedStatement1.executeQuery();
                        while (resultSet1.next()) {
                            String tag_name = resultSet1.getString("topic_name");
                            out.println(tag_name+",");
                        }
                        preparedStatement1.close();
                        resultSet1.close();
                        out.println("</td>");
                        out.println("<td><a href='edit_question_and_tag.jsp'>Edit</a></td>");
                        out.println("</tr>");
                    }
                } catch (Exception msg) {
                    out.println(msg);
                }
            %>
        </table>
    </body>
</html>
