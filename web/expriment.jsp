<%-- 
    Document   : expriment
    Created on : 15 Apr, 2019, 1:38:53 PM
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
            try{
           int ID = (Integer) session.getAttribute("Session_id_of_user");
           String id_ = String.valueOf((Integer)session.getAttribute("Session_id_of_user"));
           out.println("Integer value ->"+ID);
           out.println("<br>");
           out.println("new String value - >"+id_);
            }catch(Exception msg){
                out.println(msg);
            }
        %>
    </body>
</html>
