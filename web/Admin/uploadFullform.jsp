
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page language="java"%>
        <script src="../ckeditor/ckeditor.js"></script>
        <title>Upload the Full Form</title>
    </head>
    <body>
        <h1>Upload Short word and its full form. technical and non technical !</h1>
    <center>
         <form name="save_full_form" method="post" action="saveFullForm.jsp">
             Short Word: <input type="text" name="shord_word" required="" /><br><br>
             Full form: <input type="text" name="full_form" required="" /><br><br>
            <select name="category">
                <option>null</option>
                <option>education</option>
                <option>exam</option>
                <option>finance</option>
                <option>defence</option>
                <option>banking</option>
                <option>automobile</option>
                <option>general</option>
                <option>internet slang</option>
                <option>political</option>
                <option>organizational</option>
                <option>medical</option>
                <option>information technology</option>
                <option>railway</option>
                <option selected="">mixed</option>
                <option>telecom</option>
                <option>technical</option>
                <option>fun</option>
            </select>
            <input type="submit" value="Save full form"> 
         </form> 
    </center>
       
    </body>
</html>
