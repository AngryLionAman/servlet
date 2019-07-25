<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Query String Example</title>
    </head>

    <body>Your favorite color is: 
        <b>
            <c:out value="${param.color}" />
        </b>

        <br />

        <br />

        Choose your favorite color:
        <br />

        <br />

        <a href="<c:out value="${pageContext.request.requestURI}"/>?color=red">
            red</a>

        <br />

        <a    href="<c:out value="${pageContext.request.requestURI}"/>?color=blue">
            blue</a>

        <br />

        <a            href="<c:out value="${pageContext.request.requestURI}"/>?color=green">
            green</a>

        <br />

        <a            href="<c:out value="${pageContext.request.requestURI}"/>?color=yellow">
            yellow</a>

        <br />

        <a            href="<c:out value="${pageContext.request.requestURI}"/>?color=other">
            other</a>

        <br/>
    </body>
</html>