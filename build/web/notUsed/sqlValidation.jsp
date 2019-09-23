<%-- 
    Document   : sqlValidation
    Created on : 23 Sep, 2019, 6:52:27 PM
    Author     : AngryLion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String name = "aman";
            String val = "testing";
            String sql = "Select * from fun";
            if (!name.equalsIgnoreCase("amwan")) {
                sql += " name = '"+val+"'";
            }
            sql += " limit 10,20";

            out.println(sql);

        %>
    </body>
</html>
