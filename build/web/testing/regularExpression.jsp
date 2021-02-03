<%-- 
    Document   : regularExpression
    Created on : 24 Oct, 2019, 7:04:31 PM
    Author     : AngryLion
--%>

<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regular expression</title>
    </head>
    <body>
        <%
        String word = "क्या कभी सोचा है की बहुत से लोग जब एक दूसरे!@^#* से मिलते है तो आपस में एक दूसरे को दो बार ही \" राम राम \" क्यों बोलते हैं";
        out.print(word);
        out.print(":"+word.length());
        out.print("<br>");
        
        String pattern = "[\"/[!@#$%^&*]/g]";
        String word1 = word.replaceAll(pattern, "");
        out.print(word1);
        out.print(":"+word1.length());
        %>
    </body>
</html>
