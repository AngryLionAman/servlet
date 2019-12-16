<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${questionId eq null}">
    <c:redirect url="login.jsp?ref=Invalid url"/>
</c:if>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit question</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
    </head>
    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <c:if test="${message ne null}">
                                ${message}
                            </c:if>
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <form name="Form_name_submitquestion" method="post" action="update_q">
                                        <input type="hidden" name="question_id" value="${questionId}">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                Edit your question here
                                            </div><br>
                                            <div style="color: green; background-color: #ccccff;">
                                                Edit question
                                            </div><br>
                                            <div>
                                                <textarea type="text" class="anstext" name="question" placeholder="Question">${question}</textarea>
                                            </div><br>
                                             <div style="color: green; background-color: #ccccff;">
                                                Edit Tag here
                                            </div><br>
                                            <div>
                                                <c:if test="${topic ne null and not empty topic}">
                                                    <input type="text" style="width: 540px; padding-top: 5px;" name="tag" value="${topic}">
                                                </c:if>
                                                <c:if test="${topic eq null or empty topic}">
                                                    <textarea type="text" class="anstext" name="tag" placeholder="Please add some tag"></textarea>
                                                </c:if>
                                            </div>
                                            <div class="float-right margintop20" style="vertical-align:bottom">
                                                <button type="submit"  class="btn" >Update</button>
                                                <!-- btn-info btn-lg -->
                                            </div>
                                        </div> 
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->
    </body>
</html>