<%-- 
    Document   : UploadNotes
    Created on : 24 Jun, 2019, 11:33:43 AM
    Author     : AngryLion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                text-align: center;
                border-style: double;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Notes for Student</title>
    </head>
    <body>
        <h1>WelCome to admin Panel</h1>
        <form action="save_notes.jsp" method="get" name="form">
        Upload File :    <input type="file" name="file_location" required="" /><br><br>
        Notes Name  :    <input type="text" name="notes_name" required=""><br><br>
        Notes By    :    <input type="text" name="notes_by" required=""><br><br>
        Notes subject:    <input type="text" name="notes_subject" required=""><br><br>
        Notes for    :    <input type="text" name="notes_for" required=""><br><br>
        Notes Prise  :  <input type="number" name="notes_price" required=""><br><br>
        Notes discount:    <input type="number" name="notes_discount" required=""><br><br>
        Notes current prise:    <input type="number" name="notes_current_prise" required=""><br><br>
            <input type="submit" name="Upload" value="save"><br><br>
             
        </form>
    </body>
</html>
