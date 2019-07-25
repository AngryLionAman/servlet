<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        %>
        <jsp:useBean class="com.security.validateUser" id="function" scope="page" />
        <c:if test="${sessionScope.Session_id_of_user ne null}">
            <c:redirect url="index.jsp?ref=login"/>
        </c:if>
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
                <jsp:forward page="validate.jsp">
                    <jsp:param name="email" value="${username}"/>
                    <jsp:param name="password" value="${password}"/>
                </jsp:forward>                    
            </c:if>
        </c:if>
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Login | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            input[type=text] {
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid #b3b3b3;
                border-radius: 2px;
            }
            .button {
                background-color: #4CAF50; /* Green */
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
            .button1 {width: 250px;}
        </style>
    </head>
    <body>
        <div class="main-page-wrapper">
            <header class="home-page">
                <div class="container clear-fix">
                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" style="padding-left:0px;">
                        <div class="logo float-left">
                            <a href="#" style="vertical-align:middle;"> <h4><div class="logotext">Inquiryhere.com</div></h4></a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 serachhere" style="display:inline-block;">
                        <div style="overflow: hidden; padding-right: .5em;">
                            <form action="SearchBar.jsp">
                                <div class="col-md-9">
                                    <input type="text" style="width: 100%;"  name="search" required="" >
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 float-right textalign-right">
                        <a href="index.jsp" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">Home</a>
                        <a  href="signup.jsp" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">Signup</a>                    
                    </div>
                </div>

            </header>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <center>
                                    <c:if test="${param.ref ne null or not empty param.ref}">
                                        <c:choose>
                                            <c:when test="${param.ref eq 'logout'}">
                                                <b style=color:red;>Thanks for using our service. Please visit again</b>
                                            </c:when>
                                            <c:when test="${param.ref eq 'submitAns'}">
                                                <b style=color:red;>Please Login First</b>
                                            </c:when>
                                            <c:when test="${param.ref eq 'valid'}">
                                                <b style=color:red;>Email and Password is not valid</b>
                                            </c:when>
                                            <c:otherwise>
                                                <b style=color:red;>${param.ref}</b>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>

                                    <div class="themeBox" style="height:300px;">
                                        <form action="validate.jsp" method="post" name="form_name">
                                            <% if (request.getParameter("URL") != null) {%>
                                            <input type="hidden" name="URL" value="<%=request.getParameter("URL")%>">
                                            <% }%> 

                                            <label for="fname">Email</label>
                                            <div class="boxHeading">
                                                <input type="text"  name="email" required="" value="">
                                            </div>
                                            <label for="lname">Password</label>
                                            <div class="boxHeading">
                                                <input type="password"  name="password" required="" value="">
                                            </div> 
                                            <br>
                                            <button type="submit" class="button button1" data-toggle="modal">Login</button>
                                        </form>

                                        <form action="forgotpassword.jsp">
                                            <button class="button button1" style="background-color: red;">Forget Password</button>
                                        </form>
                                    </div>
                                </center>
                            </div>
                        </div>

                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> 
    </body></html>