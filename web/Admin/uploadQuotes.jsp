
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page language="java"%>
        <script src="../ckeditor/ckeditor.js"></script>
        <title>Upload the quotes</title>
    </head>
    <body>
        <h1>Upload quotes of famous person and make people amaze !</h1>
    <center>
         <form name="save_qotes" method="post" action="savequotes.jsp">
            Quotes By: <input type="text" name="quotes_by" /><br><br>
            <textarea class="ckeditor" name="quotes" required=""></textarea><br><br>
            <input type="submit" name="Post" value="Save quotes"> 
         </form> 
    </center>
       
    </body>
</html>
