<%-- 
    Document   : dit_question_and_tag
    Created on : 6 Mar, 2019, 6:09:43 PM
    Author     : AngryLion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String question = request.getParameter("question");
        int question_id = Integer.parseInt(request.getParameter("question_id"));
        %>
        <h1>Edit the shaprated question and tag here!!!!</h1>
        <div>
            <div>
                Question : <%=question%>
                <br><br><br>Tag : <textbox name="tag" >tag,and athe ,tag</textbox>
            </div>
                <div>
                    
                </div>
        </div>
    </body>
</html>
