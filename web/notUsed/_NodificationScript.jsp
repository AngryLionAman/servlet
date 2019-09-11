<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%@include file="validator.jsp" %>
<% //Got the id from Here only
    if (session.getAttribute("email") != null && session.getAttribute("Session_id_of_user") != null) {
        int CurrentUserId = (Integer) session.getAttribute("Session_id_of_user");
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

            /* Display the stored notification to the user*/
 /* Limited the user notification to five(5)*/
            try {

                String sql = "SELECT unique_id,user_id,user_email,notification_type,followers_id,"
                        + "(SELECT firstname FROM newuser WHERE id = notification.followers_id)AS firstname,"
                        + "question_id,"
                        + "(SELECT question FROM question WHERE q_id = notification.question_id)AS QUESTION,ans_id,"
                        + "(SELECT answer FROM answer WHERE a_id = notification.ans_id)AS answer,blog_id,time FROM notification "
                        + "WHERE user_id = ? OR user_id IS NULL ORDER BY unique_id DESC LIMIT 10";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, CurrentUserId);
                resultSet = preparedStatement.executeQuery();
%>
<% boolean status = true;
    while (resultSet.next()) {
        status = false;
        int comment_id = resultSet.getInt("unique_id");
        int question_id = resultSet.getInt("question_id");
        int blog_id = resultSet.getInt("blog_id");
        String question = resultSet.getString("question");
        //String answer = resultSet.getString("answer");
        int userId = resultSet.getInt("followers_id");//who created the notification
        String userFirstName = convertStringUpperToLower(resultSet.getString("firstname"));
        String notification_type = resultSet.getString("notification_type");
        if (notification_type.equalsIgnoreCase("got_answer_of_a_question")) {

%>
<a href="Answer.jsp?q=<%=question.replaceAll(" ", "-")%>&ans_by=<%=userFirstName.replaceAll(" ", "+")%>&Id=<%=question_id%>&c_id=<%=comment_id%>"><b><%=userFirstName%></b> give you an answer of <b><%=question%></b></a>
<br>-------------------------------------------------<br>
<%
} else if (notification_type.equalsIgnoreCase("followed_by")) {

%>
<a href="profile.jsp?user=<%=userFirstName.replaceAll(" ", "+")%>&ID=<%=userId%>&c_id=<%=comment_id%>"> <b><%=userFirstName%></b> started following you</a>
<br>-------------------------------------------------<br>
<%
} else if (notification_type.equalsIgnoreCase("submit_question")) {
    //Notification for submit a question
    try {
        String sql_for_q_notify = "SELECT * FROM ak_follower_detail WHERE followers_id = ? AND user_id = ?";
        preparedStatement = connection.prepareStatement(sql_for_q_notify);
        preparedStatement.setInt(1, CurrentUserId);// CurrentUserId -> who is about to get the notifiation 
        preparedStatement.setInt(2, userId);//userId -> who created the notification
        ResultSet rs = preparedStatement.executeQuery();
        boolean foundValue = false;
        while (rs.next()) {
            //int id = resultSet.getInt("user_id");
            foundValue = true;
        }
        rs.close();
        if (foundValue) {
%>
<a href="profile.jsp?user=<%=userFirstName.replaceAll(" ", "+")%>&value=Question&ID=<%=userId%>&c_id=<%=comment_id%>"> <b><%=userFirstName%></b> posted a new question</a>
<br>-------------------------------------------------<br>
<%
            foundValue = false;
        }

    } catch (Exception msg) {
        out.println("got some error in featchig the uploaded question notification" + msg);
    }
} else if (notification_type.equalsIgnoreCase("comment_on_question")) {
%>
<a href="Answer.jsp?q=<%=question.replaceAll(" ", "-")%>&comment_by=<%=userFirstName.replaceAll(" ", "+")%>&Id=<%=question_id%>&c_id=<%=comment_id%>"><b><%=userFirstName%></b> commented on <b><%=question%></b></a>
<br>-------------------------------------------------<br>
<%
 } else if (notification_type.equalsIgnoreCase("comment_on_Answer")) {
%>
<a href="Answer.jsp?q=<%=question.replaceAll(" ", "-")%>&comment_by=<%=userFirstName.replaceAll(" ", "+")%>&Id=<%=question_id%>&c_id=<%=comment_id%>"><b><%=userFirstName%></b> commented on a answer which something belongs to you and question is :- <b><%=question%></b></a>
<br>-------------------------------------------------<br>
<%
 }else if (notification_type.equalsIgnoreCase("comment_on_Blog")) {
%>
<a href="D_Blog.jsp?Blog_Id=<%=blog_id%>&c_id=<%=comment_id%>"><b><%=userFirstName%></b> Commented on your Blog </a>
<br>-------------------------------------------------<br>
<%
 }else if (notification_type.equalsIgnoreCase("comment_on_Profile")) {
%>
<a href="profile.jsp?ID=<%=CurrentUserId%>&c_id=<%=comment_id%>"> <b><%=userFirstName%></b> Commented on You profile</a>
<br>-------------------------------------------------<br>
<%
 }
        }
        if (status) {
            out.println("You don't have any notification");
        }
    } catch (Exception msg) {
        out.println(msg);
    }
%>
<%

            /*End of the script of displaying the notification to user*/
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
        out.println("You are not loggedin, Plese login then access this site...");
    }
%>