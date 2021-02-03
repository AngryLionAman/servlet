<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <%  //need to validate here
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        %>
        <meta charset="UTF-8">
        <meta http-equiv="content-type" content="text/html" charset="utf-8">

        <script src="ckeditor/ckeditor.js"></script>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
    </head>
    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <c:if test="${sessionScope.Session_id_of_user eq null}">
                <c:redirect url="login.jsp"/>
            </c:if>   
            <c:if test="${answerid eq null}">
                <c:redirect url="index?ref=Invalid url"/>
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
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading marginbot10">
                                            Ques : ${question} ?
                                        </div>                                    
                                    </div>
                                    <div class="boxHeading marginbot10">Answer:</div>
                                    <form name="form_name_submitquestion" method="post" action="saveUpdatedAnswer">
                                        <input type="hidden" name="answer_id" value="${answerid}">
                                        <input type="hidden" name="question_id" value="${id}">
                                        <textarea class="ckeditor" name="answer" required="" >${answer}</textarea>
                                        <input type="submit" name="Post" value="Submit"> 
                                    </form>
                                    <div class="clear-fix"></div>
                                </div>
                            </div>
                        </div>
                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->

    </body></html>