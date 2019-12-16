<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="fun" class="com.string.WordFormating" scope="page"></jsp:useBean>
<c:if test="${sessionScope.Session_id_of_user eq null}">
    <c:redirect url="signup.jsp"/>
</c:if>
<html lang="en">
    <head>   
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">       
        <title>Select Topic | Sign Up</title>
        <meta property="og:url" content="https://www.inquiryhere.com/CompleteProfilefFollowTopic.jsp">
        <meta property="og:site_name" content="www.inquiryhere.com" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:title" content="Select at least five topic for the best result" />
        <meta property="og:description" content="Follow some topic and get the result as you want"/>
        <meta property="og:locale" content="en_US">
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
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
            <div class="bodydata">
                <div class="container clear-fix">                                                 
                    <div class="row">   
                        <c:if test="${topic eq null}">
                            <c:redirect url="signup.jsp"/>
                        </c:if>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <label for="fname">Select at least five topic</label>
                            <form action="saveFollowedTopic" method="post" name="dropdown_selection">  
                                <input type="hidden" name="userid" value="${sessionScope.Session_id_of_user}"/>
                                <select id="s5" multiple="multiple" name="MultipleSelectedTopic" size="10" autofocus="" required="">
                                    <c:catch var="exp">
                                        <c:forEach items="${topic}" var="t">
                                            <option value="${t.key}"><c:out value="${fun.convertStringUpperToLower(t.value)}"/></option>
                                        </c:forEach> 
                                    </c:catch>
                                    <c:if test="${exp ne null}">
                                        ${exp}
                                    </c:if>

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
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>        <!-- Bootstrap JS -->
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>        <!-- Bootstrap Select JS -->
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>