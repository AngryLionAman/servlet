
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page language="java"%>
        <script src="../ckeditor/ckeditor.js"></script>
        <title>Did You Know</title>
    </head>
    <body>
        <h1>Did You really know what you are uploading !</h1>
    <center>
         <form name="didyouknow" method="post" action="savedidyouknow.jsp">
            <textarea class="ckeditor" name="didyouknow" required=""></textarea><br><br>
            <input type="submit" name="Post" value="Save this"> 
         </form> 
    </center>
       
    </body>
</html>
