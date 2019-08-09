<%-- 
    Document   : editTopic
    Created on : Aug 8, 2019, 2:47:03 PM
    Author     : inquiryhere.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Topic : ${param.tName}</h1>
        <h3>Topic Id :- <b style="color: green;">${param.id}</b></h3>
        <form action="saveTopicDetail.jsp" name="saveTopicDetail">            
            <input name="p" value="${param.p}" type="hidden"/>
            <input name="topicId" value="${param.id}" type="hidden"/>
            <h3>Topic Name :- <input required="" size="60" style="color: green;" name="topic_name" type="text" value="${param.tName}"/></h3>
            <h3>Image Url:- <input size="60" style="color: green;" name="topic_url" type="text" value="${param.url}"/></h3>
            <h3>Desc Hindi :- <textarea rows="10" cols="60" name="topic_desc_hindi" placeholder="This is description in hindi">${param.d_hindi}</textarea></h3>
            <h3>Desc English :- <textarea rows="10" cols="60" name="topic_desc_eng" placeholder="This is description in englsih">${param.d_eng}</textarea></h3>
            <input type="submit" value="Update">
        </form>     
    </body>
</html>
