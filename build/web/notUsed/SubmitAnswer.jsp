<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String URL = request.getParameter("URL");

%>
<%!            public boolean validateUser(String username, String password, int userId) {
        boolean found = false;
        try {
            String cookiesMail = username;
            String cookiesPassword = password;

            Connection connection = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
            try {

                String v = "SELECT ID,email,password FROM newuser WHERE email = ?";

                preparedStatement = connection.prepareStatement(v);
                preparedStatement.setString(1, cookiesMail);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String password1 = resultSet.getString("password");
                    int id_of_user = resultSet.getInt("ID");
                    if (cookiesPassword.equals(password1) && userId == id_of_user) {
                        found = true;
                    }
                }
            } catch (Exception e) {
                //  out.println("Error in main try block:-" + e);
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (Exception msg) {

        }
        return found;
    }
%>
<%!    public void submitAnswer(int Q_id, String answer, int id_of_user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    //out.println("Exception in loading the class forname Driver" + ex);
                }
                connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
            }
            int user_id_of_who_asked_the_question = 0;
            try {
                String sql = "insert into answer(q_id,answer,Answer_by_id,vote) values(?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Q_id);
                preparedStatement.setString(2, answer);
                preparedStatement.setInt(3, id_of_user);
                preparedStatement.setInt(4, 0);
                preparedStatement.executeUpdate();
                try {
                    String sql_user_id = "SELECT id FROM question WHERE q_id = ?";
                    preparedStatement = connection.prepareStatement(sql_user_id);
                    preparedStatement.setInt(1, Q_id);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        user_id_of_who_asked_the_question = resultSet.getInt("id");
                    }
                } catch (Exception msg) {
                    // out.println("Error in getting the user of of the question id" + msg);
                }

                //Save some information for the notification to user
                //For, send the notification to user to you got an answer
                try {
                    String sql_notification = "INSERT INTO notification (user_id,notification_type,followers_id,question_id)VALUES(?,?,?,?)";
                    preparedStatement = connection.prepareStatement(sql_notification);
                    
                    preparedStatement.execute();
                } catch (Exception msg) {
                    //out.println("Error in Saving the notifiation" + msg);
                }
                //End of the script to saving the notification to display of got the answer of a question
            } catch (Exception e1) {
                //out.print("Error:-" + e1);
            }
        } catch (Exception e) {
            //out.println("Error in main try block:-" + e);
        } finally {

            if (connection != null || !connection.isClosed()) {
                try {
                    connection.close();
                } catch (Exception e) {
                    //out.println("Exception in closing connection " + e);
                }
            }
            if (preparedStatement != null || !preparedStatement.isClosed()) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    //out.println("Exception in closing preparedStatement " + e);
                }
            }
        }
    }
%>
<%    try {
        if (request.getParameter("answer") != null
                && request.getParameter("answer").trim().length() > 0
                && request.getParameter("q_id") != null
                && request.getParameter("_id_of_user") != null
                && request.getParameter("question") != null) {

            String answer = request.getParameter("answer");
            String v = request.getParameter("q_id");
            String v2 = request.getParameter("_id_of_user");
            String question = request.getParameter("question");

            int Q_id = Integer.valueOf(v);
            int id_of_user = Integer.valueOf(v2);
            submitAnswer(Q_id, answer, id_of_user);
            response.sendRedirect("Answer.jsp?q=" + question.replaceAll(" ", "-") + "&Id=" + Q_id);
//            if (session.getAttribute("Session_id_of_user") != null) {
//                submitAnswer(Q_id, answer, id_of_user);
//                response.sendRedirect("Answer.jsp?q=" + question.replaceAll(" ", "-") + "&Id=" + Q_id);
//            }
//            if (session.getAttribute("Session_id_of_user") == null) {
//                Cookie[] cookies = request.getCookies();
//                String username = "";
//                String password = "";
//                if (cookies != null) {
//                    for (int i = 0; i < cookies.length; i++) {
//                        Cookie cookie = cookies[i];
//                        if (cookie.getName().equals("username-cookie")) {
//                            username = cookie.getValue();
//                        } else if (cookie.getName().equals("password-cookie")) {
//                            password = cookie.getValue();
//                        }
//                    }
//                } else {
//                    response.sendRedirect("Login.jsp?msg=submitAns&URL=" + URL + "&ans=" + answer);
//                }
//                if (username != "" && password != "") {
//                    boolean found = validateUser(username, password, id_of_user);
//                    if (found) {
//                        submitAnswer(Q_id, answer, id_of_user);
//                        response.sendRedirect("Answer.jsp?q=" + question.replaceAll(" ", "-") + "&Id=" + Q_id);
//                    } else {
//                        response.sendRedirect("Login.jsp?ref=s_ans");
//                    }
//                } else {
//                    response.sendRedirect("Login.jsp?msg=submitAns&URL=" + URL + "&ans=" + answer);
//                }
//            }

        } else {
%>

<c:if test="${insert_question>=1}">
    <font size="5" color='green'> Something went wrong!!!!&nbsp;Or may be bad argument</font><br>
    <font size="5" color='green'> Please report us on <a href="ContactUs.jsp">Contact Us</a> form</font><br>
</c:if> 
<%
        }
    } catch (Exception msg) {
        out.println(msg);
    }

%>
