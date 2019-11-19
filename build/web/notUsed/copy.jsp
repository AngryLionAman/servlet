<%@page import="com.connect.DatabaseConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    ResultSet rs = null;
    DatabaseConnection dc = DatabaseConnection.getInstance();
    try {
        con = dc.getConnection();
        String sql = "select * from whatsapp_status";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        out.println("Work Processing starting....<br>");
        while (rs.next()) {            
            //String title = rs.getString("tt_title");
            String desc = rs.getString("wa_status");
            String cat = "sayari";
            String type = rs.getString("wa_status_category");
            try{
                String sql1 = "insert into fun(description,category,type)values(?,?,?)";
                ps1 = con.prepareStatement(sql1);
                ps1.setString(1, desc);
                ps1.setString(2, cat);
                ps1.setString(3, type);
                ps1.execute();
            }catch(Exception msg){
                out.println(msg);
            }
        }
        out.println("Work done!!!");
    } catch (Exception msg) {
        out.println(msg);
    }
    
%>