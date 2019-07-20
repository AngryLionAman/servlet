<%-- 
    Document   : login
    Created on : 28 Feb, 2019, 6:48:50 PM
    Author     : AngryLion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Admin</title>
    </head>
    <body><center>
        <h1>Hello Admin</h1>
        <form action="login.jsp" method="post">
        <table>
            <tr>
                <th>UserName</th>
                <th><input type="password" name="username"></th>
            </tr>
            <tr>
                <th>Password</th>
                <th><input type="password" name="password"></th>
            </tr>
           <tr>
                <th></th>
                <th><input type="submit" value="Login"></th>
            </tr>
           
        </table>
        </form>
        <%
            if ((request.getParameter("username") != null && !request.getParameter("username").isEmpty()) && (request.getParameter("password") != null && !request.getParameter("password").isEmpty())) {
                if(request.getParameter("username").equals("idontknow") && request.getParameter("password").equals("incorrect")){
                    response.sendRedirect("adminModule.jsp");
                }else{
                    out.println("Login Failed!!!");
                }
               
            }
        %>
   </center> </body>
</html>
