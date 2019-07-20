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
        <title>Uploading joke panel</title>
    </head>
    <body><center>
        <h1>Upload a joke here,Make people laugh!</h1>
        <form name="save_joke" method="post" action="saveJoke.jsp">
            <select id="s5"  name="joke_on" >
                <option>santa banta</option>
                <option selected="">husband wife</option>
                <option>girl</option>
                <option>not categorized</option>
            </select>
            <textarea class="ckeditor" name="joke" required=""></textarea>
            <input type="submit" name="Post" value="Save joke"> 
            
        </form>
    </center>

</body>
</html>
