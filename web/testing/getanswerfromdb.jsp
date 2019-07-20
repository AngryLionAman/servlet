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
        </c:forEach><br><br>
        <%
            Connection connection = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            try {
                if (connection == null || connection.isClosed()) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        out.println("Exception in loading the class forname Driver" + ex);
                    }
                    connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                }
                String sql = "select answer from answer where q_id = 538";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    out.println(resultSet.getString("answer"));
                }
            } catch (Exception msg) {
                out.println(msg);
            }
        %>
    </body>
</html>
