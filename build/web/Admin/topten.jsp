<%-- 
    Document   : uploadJoke
    Created on : 14 Mar, 2019, 10:30:43 PM
    Author     : AngryLion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page language="java"%>
        <script src="../ckeditor/ckeditor.js"></script>
        <title>Uploading Top 10 in the world</title>
    </head>
    <body><center>
        <h1>Upload Top 10 in the world,Make people amaze!!!!</h1>
        <form name="topten" method="post" action="savetopten.jsp">
            Title of top 10 :  <input type="text" name="toptentitle" size="70"><br><br>
            <br><textarea class="ckeditor" name="toptendescription" required=""></textarea><br>
            <input type="submit" name="Post" value="Save Top 10"> 
            
        </form>
    </center>
    <a href="adminModule.jsp">Admin Page</a>

</body>
</html>
