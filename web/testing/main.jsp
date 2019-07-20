<%-- 
    Document   : main
    Created on : Jul 10, 2019, 12:30:17 PM
    Author     : inquiryhere.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is home page!</h1>
        <%
            try{
                int a = 4/0;
        out.println(a);
            }catch(Exception s){
                out.println(s);
            }
        
        %>
    </body>
</html>
