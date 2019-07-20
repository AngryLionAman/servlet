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
        <title>Uploading poem in Hindi</title>
    </head>
    <body><center>
        <h1>Upload a poem here,Make people happy!!!!</h1>
        <form name="save_story" method="post" action="savePoem.jsp">
          Poem Title :  <input type="text" name="poem_title"><br><br>
          Category :  <select id="s5"  name="poem_category" >
                <option>fun</option>
                <option>educational</option>
                <option selected="">inspirational</option>
                <option>desh bhakt</option>
                <option>holi</option>
                <option>ramnavi</option>
                <option>Sad</option>
                <option>Love</option>
            </select><br>
            <br><textarea class="ckeditor" name="poem" required=""></textarea><br>
            <input type="submit" name="Post" value="Save poem"> 
            
        </form>
    </center>
    <a href="adminModule.jsp">Admin Page</a>

</body>
</html>
