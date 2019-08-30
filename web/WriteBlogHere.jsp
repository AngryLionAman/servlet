<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" %>
<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <script src="ckeditor/ckeditor.js"></script>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Blog | inquiryhere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">

    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <c:if test="${sessionScope.Session_id_of_user eq null}">
                <c:redirect url="login.jsp?ref=Please Login first!!"/>
            </c:if>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <form name="submitquestion" method="post" action="Submitblog.jsp">
                                        <div class="boxHeading marginbot10">
                                            <div class="themeBox" style="height:auto;">
                                                Write Blog Subject :
                                                <div class="boxHeading">
                                                    <textarea type="text" class="anstext" name="blog_subject" required="" ></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <b>Write Blog Description :</b>
                                        <textarea class="ckeditor" name="blog_description" required="" ></textarea>
                                        <input type="submit" name="Post" value="Submit"> 
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->

    </body></html>