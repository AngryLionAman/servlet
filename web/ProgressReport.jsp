<%-- 
    Document   : ProgressReport
    Created on : 8 Jun, 2019, 1:03:27 PM
    Author     : AngryLion
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@include file="site.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Progress Report</title>
        <!--meta http-equiv="refresh" content="3"-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            * {
                box-sizing: border-box;
            }

            h2{
                text-align: center;
            }
            p{
                text-align: center;
                color: #ff0099;
            }
            /* Create three equal columns that floats next to each other */
            .column {
                float: left;
                width: 24.33%;
                padding: 10px;
                height: 200px; /* Should be removed. Only for demonstration */
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }

            /* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
            @media screen and (max-width: 600px) {
                .column {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>

        <h2>Progress Report of inquiryhere.com</h2>
        <%
            Connection connection = null;
            ResultSet resultSet = null;
            ResultSet resultSet1 = null;
            ResultSet resultSet2 = null;
            ResultSet resultSet3 = null;
            ResultSet resultSet4 = null;
            ResultSet resultSet5 = null;
            ResultSet resultSet6 = null;
            ResultSet resultSet7 = null;
            ResultSet resultSet8 = null;
            PreparedStatement preparedStatement = null;
            PreparedStatement preparedStatement1 = null;
            PreparedStatement preparedStatement2 = null;
            PreparedStatement preparedStatement3 = null;
            PreparedStatement preparedStatement4 = null;
            PreparedStatement preparedStatement5 = null;
            PreparedStatement preparedStatement6 = null;
            PreparedStatement preparedStatement7 = null;
            PreparedStatement preparedStatement8 = null;
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
            int TotalQuestion = 0;
            int TotalAnswer = 0;
            int TotalUser = 0;
            String Last_posted_question = "";
            String Last_user_created = "";
            String Most_viewed_question = "";
            String Most_viewed_profile = "";
            int total_view = 0;
            int total_view_profile = 0;
            int Last_posted_question_Total_View = 0;
            String sql_quesion = "select count(*) totalquestion from question";//For the the question
            String sql_answer = "select count(*) totalanswer from answer";//For the the answer
            String sql_user = "select count(*) totaluser from newuser";//For the the user
            String sql_last_posted_question = "select * from question order by q_id desc limit 1";
            String sql_last_user_created = "select * from newuser order by id desc limit 1";
            String sql_most_viewed_question = "select q_id,question,total_view from question order by total_view desc limit 1";
            String sql_most_viewed_Profile = "select firstname,total_view from newuser order by total_view desc limit 1";
            String sql_recent_searched_queary = "select * from searched_queary order by primary_key desc limit 20";
            String unanswred_question = "select q_id,question,(select count(a_id) from answer where q_id = question.q_id)t_ans from question where (select count(a_id) from answer where q_id = question.q_id) = 0";
            try {
                preparedStatement = connection.prepareStatement(sql_quesion);
                preparedStatement1 = connection.prepareStatement(sql_answer);
                preparedStatement2 = connection.prepareStatement(sql_user);
                preparedStatement3 = connection.prepareStatement(sql_last_posted_question);
                preparedStatement4 = connection.prepareStatement(sql_last_user_created);
                preparedStatement5 = connection.prepareStatement(sql_most_viewed_question);
                preparedStatement6 = connection.prepareStatement(sql_most_viewed_Profile);
                preparedStatement7 = connection.prepareStatement(sql_recent_searched_queary);
                preparedStatement8 = connection.prepareStatement(unanswred_question);
                resultSet = preparedStatement.executeQuery();
                resultSet1 = preparedStatement1.executeQuery();
                resultSet2 = preparedStatement2.executeQuery();
                resultSet3 = preparedStatement3.executeQuery();
                resultSet4 = preparedStatement4.executeQuery();
                resultSet5 = preparedStatement5.executeQuery();
                resultSet6 = preparedStatement6.executeQuery();
                resultSet7 = preparedStatement7.executeQuery();
                resultSet8 = preparedStatement8.executeQuery();
                while (resultSet.next()) {
                    TotalQuestion = resultSet.getInt("totalquestion");
                }
                while (resultSet1.next()) {
                    TotalAnswer = resultSet1.getInt("totalanswer");
                }
                while (resultSet2.next()) {
                    TotalUser = resultSet2.getInt("totaluser");
                }
                while (resultSet3.next()) {
                    Last_posted_question = resultSet3.getString("question");
                    Last_posted_question_Total_View = resultSet3.getInt("total_view");
                }
                while (resultSet4.next()) {
                    Last_user_created = resultSet4.getString("firstname");
                }
                while (resultSet5.next()) {
                    Most_viewed_question = resultSet5.getString("question");
                    total_view = resultSet5.getInt("total_view");
                }
                while (resultSet6.next()) {
                    Most_viewed_profile = resultSet6.getString("firstname");
                    total_view_profile = resultSet6.getInt("total_view");
                }
            } catch (Exception msg) {
                out.println(msg);
            }
        %>
        <div class="row">
            <div class="column" style="background-color:#aaa;">
                <h2>Total Question</h2>
                <p style="font-size: 60px;"><%=TotalQuestion%></p>
            </div>
            <div class="column" style="background-color:#bbb;">
                <h2>Total Answer</h2>
                <p style="font-size: 60px;"><%=TotalAnswer%></p>
            </div>
            <div class="column" style="background-color:#ccc;">
                <h2>Total User</h2>
                <p style="font-size: 60px;"><%=TotalUser%></p>
            </div>
            <div class="column" style="background-color:#ddd;">
                <h2>Panding</h2>
                <p style="font-size: 30px;"><%="panding"%></p>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="column" style="background-color:#aaa;">
                <h2>Last Question posted</h2>
                <p style="font-size: 25px;"><%=Last_posted_question%> (<%=Last_posted_question_Total_View%>)</p>
            </div>
            <div class="column" style="background-color:#bbb;">
                <h2>Last user created</h2>
                <p style="font-size: 30px;"><%=Last_user_created%></p>
            </div>
            <div class="column" style="background-color:#ccc;">
                <h2>Most view question</h2>
                <p style="font-size: 25px;"><%=Most_viewed_question%> (<%=total_view%>)</p>
            </div>
            <div class="column" style="background-color:#ddd;">
                <h2>Most view Profile</h2>
                <p style="font-size: 25px;"><%=Most_viewed_profile%> (<%=total_view_profile%>)</p>
            </div>
            <div class="column" style="background-color:#ddd;">
                <h2>Searched query</h2>
                <%
                    while (resultSet7.next()) {
                        String searched_queary = resultSet7.getString("searched_queary");
                        out.println(searched_queary + ",");
                %>

                <%
                    }
                %>

            </div>
        </div>
        <div>
            <% int i = 0;
                while (resultSet8.next()) {

                    int unswred_question_id = resultSet8.getInt("q_id");
                    int total_answer = resultSet8.getInt("t_ans");
                    String unswred_question = resultSet8.getString("question");
                    out.println("(" + ++i + ") " + unswred_question_id + " ->" + unswred_question + " ->" + total_answer + "<br>");
                }
            %>
        </div>
        <%
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
                        resultSet1.close();
                        resultSet2.close();
                        resultSet3.close();
                        resultSet4.close();
                        resultSet5.close();
                        resultSet6.close();
                        resultSet7.close();
                        resultSet8.close();
                    } catch (Exception e) {
                        out.println("Exception in closing resulatset " + e);
                    }
                }
                if (preparedStatement != null || !preparedStatement.isClosed()) {
                    try {
                        preparedStatement.close();
                        preparedStatement1.close();
                        preparedStatement2.close();
                        preparedStatement3.close();
                        preparedStatement4.close();
                        preparedStatement5.close();
                        preparedStatement6.close();
                        preparedStatement7.close();
                        preparedStatement8.close();
                    } catch (Exception e) {
                        out.println("Exception in closing preparedStatement " + e);
                    }
                }
            }
        %>
    </body>
</html>

