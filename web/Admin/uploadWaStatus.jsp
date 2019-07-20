
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page language="java"%>
        <script src="../ckeditor/ckeditor.js"></script>
        <title>Upload sayari and WhatsApp Status</title>
    </head>
    <body>
        <h1>Upload sayari and WhatsApp status on every mood !</h1>
        <a href="adminModule.jsp">Home</a>
    <center>
         <form name="SaveWaStatus" method="post" action="saveWaStatus.jsp">
             WhatsApp Status: 
             <textarea class="ckeditor" name="wa_status" required=""></textarea><br><br>
           Category :  <select name="category">
                <option>null</option>
                <option selected="">attitude</option>
                <option>love</option>
                <option>desk bhakti</option>
                <option>motivational</option>
                <option>romantic</option>
                <option>sad</option>
                <option>boy</option>
                <option>girl</option>
                <option>fun</option>
                <option>mixed</option>     
            </select><br><br>
            <input type="submit" value="Save status"> 
         </form> 
    </center>
       
    </body>
</html>
