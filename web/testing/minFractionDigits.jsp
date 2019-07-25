<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>JSTL fmt:formatNumber Tag</title>
    </head>

    <body>
        <h3>Number Format:</h3>
        <c:set var = "balance" value = "4.5" />



        <p>Formatted Number (3): <fmt:formatNumber  maxFractionDigits = "0" value = "${balance}" /></p>



        <p>Formatted Number (6): <fmt:formatNumber type = "percent" minFractionDigits = "5" value = "${balance}" /></p>


    <sql:query dataSource="jdbc/mydatabase" var="categories" scope="page">
        select unique_id,topic_name from topic;
    </sql:query>
    Total number of row-<fmt:formatNumber value="${categories.rowCount}" maxFractionDigits="0"/>
</body>
</html>