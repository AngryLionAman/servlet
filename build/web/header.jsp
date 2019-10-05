<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="site.jsp"%>
<jsp:useBean class="com.header.headerData" id="userdetails" scope="page"/>
<jsp:useBean class="com.string.name" id="fun" scope="page"/>
<jsp:useBean class="com.security.validateUser" id="function" scope="page" />
<c:catch var="ex">
    <c:if test="${sessionScope.Session_id_of_user eq null}">
        <c:if test="${pageContext.request.cookies ne null and not empty pageContext.request.cookies}">
            <c:forEach var="cookieVal" items="${pageContext.request.cookies}" > 
                <c:if test="${cookieVal.name eq 'usernamecookie'}">
                    <c:set value="${cookieVal.value}" var="username" scope="page" />
                </c:if>
                <c:if test="${cookieVal.name eq 'passwordcookie'}">
                    <c:set value="${cookieVal.value}" var="password" scope="page" />
                </c:if>
            </c:forEach>
        </c:if>

        <c:if test="${username ne null and not empty username and password ne null and not empty password}">
            <c:set scope="page" var="status" value="${function.validateUser(username, password)}"/>
        </c:if> 
        <c:if test="${status}">      
            <jsp:include page="validate.jsp">
                <jsp:param name="email" value="${username}"/>
                <jsp:param name="password" value="${password}"/>
            </jsp:include>        
        </c:if>
    </c:if>
</c:catch>
<c:if test="${ex ne null}">
    ${ex}
</c:if>
<header class="home-page">
    <div class="container clear-fix">
        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" style="padding-left:0px;">
            <div class="logo float-left">
                <a href="index.jsp" style="vertical-align:middle;">
                    <h4>
                        <div class="logotext">
                            inquiryhere.com
                        </div>
                    </h4>
                </a>
            </div>
        </div>
        <script type="text/javascript">
            function validateForm()
            {
                var a = document.forms["Form"]["q"].value;
                if (a === null || a === "", a.trim() === "")
                {
                    return false;
                } else
                {
                    var a = document.forms["Form"]["q"].value;
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_searched_queary.jsp?searched_queary=" + a, true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                    return true;
                }
            }
        </script>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 serachhere" style="display:inline-block;">
            <div style="overflow: hidden; padding-right: .5em;">
                <form action="search.jsp" name="Form" onsubmit="return validateForm()">
                    <input type="text" style="width: 100%;" name="q" required="">
                </form>
            </div>
        </div>


        <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 float-right textalign-right">          
            <a href="#" data-toggle="modal" data-target="#myModal2" class="helpicon"  style="color: white;background-color: red; padding-left: 10px;padding-right: 80px; white-space: nowrap;">Ask Question</a>           
            <c:if test="${sessionScope.Session_id_of_user eq null}">  
                <%
                    String url = request.getRequestURL().toString();
                    String arg = request.getQueryString();
                    if (arg != null) {
                        url = url + "?" + request.getQueryString();
                    }
                %>                
                <a href="signup.jsp" class="helpicon"  style="color: white;padding-left: 10px;padding-right: 30px;">SIgnUp</a>
                <a href="login.jsp?URL=<%=url%>" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">Login</a>
            </c:if>
            <a href="index.jsp" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">Home</a>
            <c:if test="${sessionScope.Session_id_of_user ne null}">
                <c:catch var="m">
                    <c:forEach var="u" items="${userdetails.headerUser(sessionScope.Session_id_of_user)}">
                        <a href="logout.jsp" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">Logout</a>
                        <a href="profile.jsp?user=${u.userName}&ID=${u.userId}" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">
                            <b><c:out value="${fun.firstName(u.fullName)}"/></b>
                        </a>             
                    </c:forEach>    
                </c:catch>
                <c:if test="${m ne null}">
                    ${m}
                </c:if>
            </c:if>
        </div>
    </div>
</header>