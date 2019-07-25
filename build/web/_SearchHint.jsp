<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%
    String query = request.getParameter("str");
    if (query != null && query.length() != 0) {
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
            String StoredQuestin = "";
            try {

                String sql = "SELECT * FROM question WHERE question LIKE '%" + query + "%' limit 10";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                int i = 0;
                while (resultSet.next()) {
                    StoredQuestin = resultSet.getString("question");
%>
<a href="Answer.jsp?Id=<%=StoredQuestin%>"><%=StoredQuestin%></a>
<%
                }
            } catch (Exception e) {
                out.println("Exception in SearchHind ->" + e);
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
    } else {
        out.println("Trying to access page directly");
    }
%>