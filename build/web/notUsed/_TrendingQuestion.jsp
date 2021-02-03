<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%
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
        try {

            String v_count = "SELECT * FROM question ORDER BY TotalLiked DESC limit 10;";
            preparedStatement = connection.prepareStatement(v_count);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String TrendingQuestion = resultSet.getString("question");
                int questionID = resultSet.getInt("q_id");
%>
<a href="Answer.jsp?Id=<%=questionID%>" ><%=TrendingQuestion%></a><br><br>
<%
            }

        } catch (Exception e) {
            out.println("Error " + e);
        }
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
%>