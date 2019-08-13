<%-- 
    Document   : uploadQuestion
    Created on : Aug 12, 2019, 3:17:08 PM
    Author     : inquiryhere.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.adminUserId eq null}">
    <c:redirect url="visit.jsp?msg=Session is not valid"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload question Admin Module</title>
    </head>
    <body>
        <h1>Hello, ${sessionScope.adminUserId}!</h1>
        
    </body>
</html>
