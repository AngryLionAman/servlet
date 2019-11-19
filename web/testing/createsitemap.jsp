<%-- 
    Document   : createsitemap
    Created on : 12 Nov, 2019, 11:25:19 AM
    Author     : AngryLion
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.connect.DatabaseConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sitemap Generator</title>
    </head>
    <body>
        <h1>Site map of inquiryhere.ocm</h1>
        <%
            DatabaseConnection dc = DatabaseConnection.getInstance();
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                con = dc.getConnection();
                String sql = "select q_id,question from question limit 5";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    String question = rs.getString("question").replaceAll(" ", "-");
                    int questionId = rs.getInt("q_id");                    
                    out.print(question+"&amp;ID="+questionId);
                    out.print("<br>");
                }
            } catch (Exception msg) {
                out.print(msg);
            }
            
        %>
    </body>
</html>
