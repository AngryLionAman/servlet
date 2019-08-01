<%-- 
    Document   : getComment
    Created on : Jul 31, 2019, 3:49:40 PM
    Author     : inquiryhere.com
--%>

<%@page import="com.index.commentPojo"%>
<%@page import="com.index.comments"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            comments cm = new comments();
            int questionId = (Integer) pageContext.getAttribute("tq.q_id");
            for (commentPojo obj : cm.commentsOnQuestion(questionId)) {

        %>
        <p>
            <%=obj.getComment()%>:-
            <a href="profile.jsp?user=<%=obj.getUserUserName()%>&ID=<%=obj.getUserId()%>"><%=obj.getUserFullName()%></a>
        </p>
        <%
            }
        %>     
    </body>
</html>
