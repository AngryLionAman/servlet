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
        <title>Uploading Story in hindi</title>
    </head>
    <body><center>
        <h1>Upload a Story here,Make people amaze!!!!</h1>
        <form name="save_story" method="post" action="saveStory.jsp">
          Story Title :  <input type="text" name="story_title"><br>
          Category :  <select id="s5"  name="story_category" >
                <option>fun</option>
                <option>Educational</option>
                <option selected="">inspirational</option>
                <option>Sad</option>
                <option>short_story</option>
                <option>Story_for_children</option>
                <option>Loke khataye</option>
                <option>Love</option>
            </select>
            <textarea class="ckeditor" name="story" required=""></textarea>
            <input type="submit" name="Post" value="Save Story"> 
            
        </form>
    </center>

</body>
</html>
