<%-- 
    Document   : validateUserName
    Created on : 1 May, 2019, 8:07:48 PM
    Author     : AngryLion
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@include file="site.jsp" %>
<%

    if (request.getParameter("userName") != null) {
        String userName = request.getParameter("userName");
        int userNameLength = userName.length();
        if (userNameLength < 5) {
            out.println(userName+ " Username length should be at least 5 charactor");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                String sql = "select username from newuser where username = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userName);
                ResultSet resultSet = preparedStatement.executeQuery();
                boolean status = false;
                while (resultSet.next()) {
                    status = true;
                }
                if (status) {
                    out.println("<b>" + userName + "</b> is taken by someone");
                } else {
                    out.println("<b>" + userName + " </b>is Available");
                }
                connection.close();
                resultSet.close();
                preparedStatement.close();
            } catch (Exception e) {
                out.println(e);
            }
        }

    }

%>
