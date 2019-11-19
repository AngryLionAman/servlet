<%-- 
    Document   : textads
    Created on : 30 Oct, 2019, 2:57:13 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Text Ads</title>
    </head>
    <body>
        <h1>Create text type ads</h1>
        <c:if test="${message ne null}">
            ${message}
        </c:if>
        <form name="" action="<%=request.getContextPath()%>/textads" method="get">
            Promoter Name : <input type="text" name="promoter_name" size="40"/><br><br>
            Title : <input type="text" name="title" size="50" /><br><br>
            Link : <input type="text" name="link" size="100" /><br><br>
            Description : <textarea name="description" rows="5" cols="30"></textarea><br><br>
            Ads display days <input type="number" name="display_ads_days"/><br><br>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
