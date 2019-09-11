<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="fun" class="com.string.name" scope="page"></jsp:useBean>
<c:if test="${sessionScope.Session_id_of_user eq null}">
    <c:redirect url="signup.jsp"/>
</c:if>
<html lang="en">
    <head>   
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        <title>Select Topic | Sign Up</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script>
            history.pushState(null, document.title, location.href);
            window.addEventListener('popstate', function (event)
            {
                history.pushState(null, document.title, location.href);
            });
        </script>
    </head>
    <body>
        <div class="main-page-wrapper">
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
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 serachhere" style="display:inline-block;">
                        <div style="overflow: hidden; padding-right: .5em;">
                            <form action="search.jsp">
                                <input type="text" style="width: 100%;" name="search" required="" disabled="">
                            </form>
                        </div>
                    </div>
                </div>
            </header>
            <div class="clear-fix"></div>
            <sql:query dataSource="jdbc/mydatabase" var="topic">
                SELECT * FROM topic order by rand() limit 500;
            </sql:query>
            <div class="bodydata">
                <div class="container clear-fix">                                                 
                    <div class="row">   
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label for="fname">Select at least five topic</label>
                            <form action="SubmitProfileFollowTopic.jsp" method="get" name="dropdown_selection">  
                                <input type="hidden" name="userid" value="${sessionScope.Session_id_of_user}"/>
                                <select id="s5" multiple="multiple" name="MultipleSelectedTopic" size="10" autofocus="" required="">
                                    <c:forEach items="${topic.rows}" var="t">
                                        <option value="${t.unique_id}"><c:out value="${fun.convertStringUpperToLower(t.topic_name)}"/></option>
                                    </c:forEach> 
                                </select>
                                <br><br> <button type="submit" class="btn" class="float-left margintop20">Follow</button>
                            </form> 
                            <div style="color:red;">Note : If you are using desktop browser then press <b>CTRL</b> and select topic</div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <%@include file="footer.jsp" %>
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <!-- Bootstrap JS -->
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <!-- Bootstrap Select JS -->
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>