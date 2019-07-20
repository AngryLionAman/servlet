<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String url =  request.getRequestURL().toString();
String arg = request.getQueryString();
if(arg != null){
    url = url + "?" + request.getQueryString();
}
%>
<%=url%><br>
<%=arg%>