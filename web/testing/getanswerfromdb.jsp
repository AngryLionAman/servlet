<%-- 
    Document   : getanswerfromdb
    Created on : Jul 18, 2019, 10:33:26 AM
    Author     : inquiryhere.com
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="../site.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <%! String str = null; %>
<c:out value="${str}" default="default value of c:out"/>
        <sql:query var="ans" dataSource="${dbsource}" >
            select answer from answer where q_id = 538;
        </sql:query>
        <c:forEach items="${ans.rows}" var="a">
            <c:out value="${a.answer}" escapeXml="false" default="there are no anser" />
        </c:forEach>
    </body>
</html>
