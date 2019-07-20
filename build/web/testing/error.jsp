<%-- 
    Document   : error
    Created on : Jul 10, 2019, 12:31:44 PM
    Author     : inquiryhere.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is error page!</h1>
        <%@page isErrorPage="true" %>
<!-- print some message -->
<%= exception.toString() %>
    </body>
</html>
