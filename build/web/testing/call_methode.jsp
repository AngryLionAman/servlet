<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>fn:split Demo</title>
    </head>
    <body>
        <c:set var="alphabet">    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O, P,Q,R,S,T,U,V ,W,X,Y,Z   </c:set>
       
        <c:forTokens items="${fn:trim(alphabet)}" delims="," var="letter">
            ${letter}
        </c:forTokens>
    </body>
</html>