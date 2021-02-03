<%@page language="java"%>
<%@page import="java.sql.*"%>
<%
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bharat", "root", null);
%>
<%
    String[] NotSearchableString = {"who", "what", "when", "where", "why", "whome", "whose", "which", "how", "is",
        "my", "you", "your", "mine"};
    int SizeOfNotSearchableString = NotSearchableString.length;
    boolean found = false;
%>
<%
    String question_tag = (String) request.getParameter("question");
    String[] QuestionArraySplit = question_tag.split(" ");
    for (int i = 0; i < QuestionArraySplit.length; i++) {
        for (int j = 0; j < SizeOfNotSearchableString; j++) {
            if (NotSearchableString[j].equalsIgnoreCase(QuestionArraySplit[i])) {
                found = true;
                break;
            }
        }
        if (!found) {
            try {
                //out.println("<br>For testing");
                String sql = "SELECT * FROM topic WHERE topic_name LIKE '" + QuestionArraySplit[i] + "%'";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    out.println("<br>--------------->" + resultSet.getInt("unique_id") + " " + resultSet.getString("topic_name"));
                }
            } catch (Exception e1) {
                out.print("Error:-" + e1);
            }
        } else {
            out.println("<br>The Not searchable array contains the string: " + QuestionArraySplit[i]);
        }
        found = false;
    }
%>