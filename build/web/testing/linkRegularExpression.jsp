<%-- 
    Document   : linkRegularExpression
    Created on : 11 Oct, 2019, 12:54:21 PM
    Author     : AngryLion
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.connect.DatabaseConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!  Looking for the link</h1>
        <%
            try {
                Pattern pattern = Pattern.compile(
                        "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)"
                        + "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov"
                        + "|mil|biz|info|mobi|name|aero|jobs|museum"
                        + "|travel|[a-z]{2}))(:[\\d]{1,5})?"
                        + "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?"
                        + "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?"
                        + "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)"
                        + "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?"
                        + "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*"
                        + "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

                DatabaseConnection dc = new DatabaseConnection();
                Connection con = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    con = DatabaseConnection.makeConnection();
                    String sql = "select answer from answer where a_id = 199";
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    out.println("Searching.............<br>");
                    int i = 1;
                    while (rs.next()) {
                        String CompleteInputHtml = rs.getString("answer");
                        Matcher matcher = pattern.matcher(CompleteInputHtml);
                        boolean found = false;
                        if (matcher.find()) {
                            found = true;
                            String result = matcher.group();
                        }
                        out.print(found);
                    }

                } catch (Exception msg) {
                    out.println(msg);
                }
            } catch (Exception msg) {
                out.println(msg);
            }
        %>
    </body>
</html>
