<%-- 
    Document   : modify_question
    Created on : 25 Dec, 2019, 2:35:21 PM
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
                                            Terms and condition for modification
                                        </div><BR>
                                        <div>
                                            1.you can only improve the question. Ex: - Spelling mistake, Sentence sense, or you can add some word to improve the question clear understanding<br><br>
                                            2.Your improvised question will display after user approval.<br><br>
                                            3.If user not take any action within the five day, approval will goes under the administrator.<br><br>
                                            4.User have ability to reject your question with reason or without reason.<br><br>
                                            5.Question modification work according the Google privacy and policy.<br><br>
                                        </div>
                                    </div>
                                </center> 
                            </div>
                            <div class="clear-fix"></div>
                            <div class="themeBox" style="height:auto;" id="f-pass-email">
                                <div class="boxHeading">
                                    Question
                                </div>
                                <div style="background-color: yellow;">
                                    ${question}
                                </div>
                            </div>
                            <div class="clear-fix"></div>
                            <div class="themeBox" style="height:auto;" id="f-pass-email">
                                <div class="boxHeading">
                                    Updated Question
                                </div>
                                <div>
                                    <form name="save_modified_question" method="post" action="save_modified_question">
                                        <input type="hidden" value="${question}" name="old_question"/>
                                        <input type="hidden" value="${id}" name="question_id"/>
                                        <input type="hidden" value="${currentUserId}" name="currentUserId"/>
                                        <input type="hidden" value="${userIdWhoPostedQuestion}" name="userIdWhoPostedQuestion"/>
                                        <textarea name="modified_question" rows="4" >${question}</textarea>
                                        <input type="submit" value="Save"/>
                                    </form>

                                </div>
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
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div>

    </body></html>