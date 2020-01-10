<%-- 
    Document   : approval
    Created on : 26 Dec, 2019, 2:19:29 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en"><head>
        <meta charset="UTF-8">
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="robots" content="noindex, nofollow" />
        <title>Modify question</title>     
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
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

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" >
                            <div class="row">
                                <c:if test="${message ne null}">
                                    <div class="clear-fix" align="center" style="font-size: 20px;color: green;background-color: yellow;">
                                        ${message}
                                    </div>                                   
                                </c:if>
                                <center align="left">   
                                    <div class="themeBox" style="height:auto;" id="f-pass-email">
                                        <div class="boxHeading">
                                            Terms and condition
                                        </div><BR>
                                        <div>
                                            1.User can reject the modification request with reason or without reason.<br><br>
                                            1.Question modification work according the Google privacy and policy.<br>
                                        </div>
                                    </div>
                                </center> 
                            </div>
                            <div class="clear-fix"></div>
                            <c:if test="${questionForApprobval eq null or empty questionForApprobval}">
                                <font style="color: red; font-size: 20px;" >This is dependent page, You can't access this page directly. Please follow the procedure. </font>
                            </c:if>
                            <c:if test="${questionForApprobval ne null and not empty questionForApprobval}">
                                <c:forEach items="${questionForApprobval}" var="t">

                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading">
                                            Question
                                        </div>
                                        <div style="background-color: yellow;">
                                            ${t.oldQuestionId} ${t.oldQuestion}
                                        </div>
                                    </div>
                                    <div class="clear-fix"></div>
                                    <div class="themeBox" style="height:auto;">
                                        <div class="boxHeading">
                                            Updated Question
                                        </div>
                                        <div style="background-color: yellow;">
                                            ${t.newQuestionId} ${t.newQuestion}
                                        </div>
                                    </div>
                                    <form name="apr" action="action_approval_by_user" method="">
                                        <input type="hidden" name="notifiaction_id" value="${notifiaction_id}"/>
                                        <input type="hidden" name="old_question_id" value="${t.oldQuestionId}"/>
                                        <input type="hidden" name="old_question" value="${t.oldQuestion}"/>
                                        <input type="hidden" name="new_question_id" value="${t.newQuestionId}"/>
                                        <input type="hidden" name="old_question_id" value="${t.newQuestion}"/>
                                        <textarea name="reason_message" rows="4" placeholder="If you can describe the reason (Optional)"></textarea>
                                        <input type="submit" name="action" value="Accept"/>
                                        <input type="submit" name="action" value="Delete"/>
                                    </form>
                                </c:forEach>
                            </c:if>
                        </div>
                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>
            <jsp:include page="footer.jsp"/>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div>

    </body></html>