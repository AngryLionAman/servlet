<%-- 
    Document   : advertise
    Created on : 5 Sep, 2019, 2:12:39 AM
    Author     : AngryLion
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.adminUserId eq null}">
    <c:redirect url="visit.jsp?msg=Session is not valid"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robots" content="noindex, nofollow" />
        <title>Add a product</title>
    </head>
    <body>
        <c:if test="${message ne null}">
            <h1 align="center" style="color: green;">${message}</h1>
        </c:if>

        <h1>Hello,   ${sessionScope.userName},  Upload image name and details.. <a href="<%=request.getContextPath()%>/Admin/adminModule.jsp">HOME</a></h1>
        <form action="<%=request.getContextPath()%>/saveAdvertise" name="advertise" method="post">
            Upload Image:- <input id="upload" type="file" name="filename" required=""/> (To get the specific name)<br><br>
            Image alt :- <input type="text" name="imageAlt" required=""/><br><br>
            Height :- <input type="number" name="height" value="200" required=""/><br><br>
            Width :- <input type="number" name="width" value="450" required=""/><br><br>
            Promoted By :- <input type="text" name="promoted_by" required=""/><br><br>
            View :- 
            <select name="view_status">
                <option value="1">Display</option>
                <option value="0" selected="">Not display</option>
            </select><br><br>
            Days display :- <input type="number" name="days" value="7" required=""/><br><br>
            Forward link :- <input type="text" name="forward_link" size="100" required=""/><br><br>
            Ads Type :- <select name="ads_type" required="">
                <option value="facebook_profile">Facebook Profile</option>
                <option value="facebook_page">Facebook Page</option>
                <option value="twitter_profile">Twitter Profile</option>
                <option value="instagram_profile">Instagram Profile</option>
                <option value="youtube_channe" >YouTube Channel</option>
                <option value="website_url">Website</option>
                <option value="tiktok_profile" >TikTok Profile</option>
                <option >Other</option>
            </select><br><br>
            Description :- <textarea name="description" rows="10" cols="100" required=""></textarea><br><br>
            UserName :- <input type="text" name="username" required=""/><br><br>
            Password :- <input type="password" name="password" required=""/><br><br>
            <input type="submit" name="save" value="Save to database">

        </form>
    </body>
</html>
