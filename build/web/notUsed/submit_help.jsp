<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%
    String Name_h, Email_h, query;
    Name_h = request.getParameter("Name_h");
    Email_h = request.getParameter("Email_h");
    query = request.getParameter("Q_h");

    String Name_s, Email_s, S_s;
    Name_s = request.getParameter("Name_s");
    Email_s = request.getParameter("Email_s");
    S_s = request.getParameter("S_s");
    if (Name_h != null && Email_h != null && query != null) {
        Statement stmt;
        Connection cn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
            stmt = cn.createStatement();
            String p = "insert into asked_help_query(name,Email,Query) values('" + Name_h + "','" + Email_h + "','" + query + "') ";
            stmt.execute(p);
            stmt.close();
            cn.close();
        } catch (Exception e1) {
            out.print("Error:-" + e1);
        }
    } else if (Name_s != null && Email_s != null && S_s != null) {
        Statement stmt;
        Connection cn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
            stmt = cn.createStatement();
            String p = "insert into suggested_query(name,Email,suggestion) values('" + Name_s + "','" + Email_s + "','" + S_s + "') ";
            stmt.execute(p);
            stmt.close();
            cn.close();
        } catch (Exception e1) {
            out.print("Error:-" + e1);
        }
    } else {
%>
<script>window.alert("you can't access this page directly!!!");
    window.location = "<%=DB_AJAX_PATH%>/ContactUs.jsp";</script><%
        }
    %>
<script>window.alert("Thanks for your precious time you always be important for us.\nWe have started working on it ");
    window.location = "<%=DB_AJAX_PATH%>/index.jsp";</script>