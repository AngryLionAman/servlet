<%-- 
    Document   : link
    Created on : 15 Sep, 2019, 11:10:26 AM
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
                    String url = request.getRequestURL().toString();
                    out.println(url);
                    out.println("<br>");
                    String arg = request.getQueryString();
                    out.println(arg);
                    out.println("<br>");
                    if (arg != null) {
                        url = url + "?" + request.getQueryString();
                    }
                    out.println(url);
                    out.println("<br>");
                %> 
    </body>
</html>
