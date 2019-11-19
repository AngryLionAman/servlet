<%-- 
    Document   : copyStoryToBlog
    Created on : 25 Oct, 2019, 10:13:59 AM
    Author     : AngryLion
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.connect.DatabaseConnection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try {
                DatabaseConnection dc = DatabaseConnection.getInstance();
                Connection con = dc.getConnection();
                String sql = "select unique_id from blog";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                int max = 500;
                int min = 1;
                int range = max - min + 1;
                while (rs.next()) {
                    int unique_id = rs.getInt("unique_id");
                    try {
                        int view = (int) (Math.random() * range) + min;
                        String sql1 = "update blog set view = ? where unique_id = ?";
                        PreparedStatement ps1 = con.prepareStatement(sql1);
                        ps1.setInt(1, view);
                        ps1.setInt(2, unique_id);
                        ps1.execute();
                    } catch (Exception msg_of) {
                        out.print(msg_of);
                    }

                }
            } catch (Exception msg) {
                out.print(msg);
            }

        %>
    </body>
</html>
