<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@include file="site.jsp" %>
<%
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;
    try {
        if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                out.println(ex);
            }
            con = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
        }

        try {
            String sql = "SELECT * FROM newuser";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                out.println("<br>" + firstname + " " + lastname);
            }
        } catch (Exception e) {
            out.println(e);
        }
        
         try {
            String sql = "SELECT * FROM newuser";
            ps2 = con.prepareStatement(sql);
            rs = ps2.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                out.println("<br>"+id+" " + firstname + " " + lastname);
            }
        } catch (Exception e) {
            out.println(e);
        }
        

    } catch (Exception e) {
        out.println(e);
    } finally {

        if (con != null || !con.isClosed()) {
            try {
                con.close();
            } catch (Exception e) {
                out.println(e);
            }
        }
        if (rs != null || !rs.isClosed()) {
            try {
                rs.close();
            } catch (Exception e) {
                out.println(e);
            }
        }
        if (ps != null || !ps.isClosed()) {
            try {
                ps.close();
            } catch (Exception e) {
                out.println(e);
            }
        }
    }
%>