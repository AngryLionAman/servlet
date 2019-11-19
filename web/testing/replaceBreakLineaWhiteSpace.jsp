<%-- 
    Document   : replaceBreakLineaWhiteSpace
    Created on : 17 Oct, 2019, 10:37:06 AM
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
        <form action="" name="s">
            <textarea name="data"></textarea>
            <input type="submit">
        </form>
        <%
            if(request.getParameter("data")!= null && !request.getParameter("data").isEmpty()){
                String data = request.getParameter("data");
                out.print("Totoal Length:-"+data.length());
                out.print("<br>Trimed Length:-"+data.trim().length());
                out.print("<br>Break Length:-"+data.trim().replaceAll("\n", "").length());
                out.print("<br>Break word:-"+data.trim().replaceAll("\n", ""));
                out.print("<br>String is :-"+data);
            }
        
        %>
    </body>
</html>
