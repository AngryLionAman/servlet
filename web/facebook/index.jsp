<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tech.facebook.CreateFBConnection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test Facebook OAuth</title>
</head>
<body>
    <%
        CreateFBConnection fbConnection = new CreateFBConnection();
    %>
    <h3>Test Facebook Authentication</h3>
    <a href="<%=fbConnection.getFBAuthUrl()%>">Facebook Login</a>
</body>
</html>