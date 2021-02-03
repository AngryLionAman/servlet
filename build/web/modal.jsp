<%-- 
    Document   : modal
    Created on : 19 Dec, 2019, 3:20:22 PM
    Author     : AngryLion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="com.modalset.ModalClass" id="opt" scope="page"/>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>

        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
        </style>
    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <c:if test="${message ne null}">
                        <div class="clear-fix" align="center" style="font-size: 20px;color: green;background-color: yellow;">                           
                            ${message}                               
                        </div>
                    </c:if>

                    <c:if test="${gotException ne null}">
                        <div class="clear-fix" align="center" style="font-size: 20px;color: red;background-color: white;">
                            ${'Got some probelm, Please refresh this page or visit after some time'}
                        </div>
                    </c:if>
                    <div class="row">
                        <form name="m" method="get" action="modal">
                            <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                                <div class="row">

                                    <div class="themeBox" style="height:auto; background-color: #f5f4f4;">
                                        <c:if test="${setQuestionById ne null and not empty setQuestionById}">
                                            <c:forEach items="${setQuestionById}" var="q">
                                                
                                                <div style="background-color: #fbe7cc;padding-top: 5px; padding-left: 5px;font-size: 20px;">                                                
                                                    ${q.key}, ${q.value}
                                                </div>
                                                <div class="row" style="padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">

                                                    <c:forEach items="${opt.getOptionByQuestionId(q.key)}" var="c" varStatus="loop">
                                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="font-family: sans-serif;margin-bottom: 10px; cursor: pointer;background-color: #d8c6ee; border-style: groove; padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
                                                            ${loop.count}. ${c}  <input type="radio" name="selectedAns" value="${c}" required=""/>  
                                                        </div> 
                                                    </c:forEach>

                                                </div>
                                            </c:forEach>

                                        </c:if>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                                <div class="themeBox" style="height:auto;">
                                    <table style="width:100%">
                                        <tr>
                                            <th>id</th>
                                            <th>A</th>
                                            <th>B</th>
                                            <th>C</th>
                                            <th>D</th>
                                        </tr>
                                        <c:if test="${initilizeArrayList ne null and not empty initilizeArrayList}">
                                            <c:forEach items="${initilizeArrayList}" var="a" varStatus="loop">
                                                <tr>
                                                    <td>
                                                        <a href="${a.questionId}">
                                                            ${loop.count}
                                                        </a>
                                                    </td>
                                                    <td>O</td>
                                                    <td>O</td>
                                                    <td>O</td>
                                                    <td>
                                                        <c:if test="${a.selected_ans ne null and not empty a.selected_ans}">
                                                            ${a.selected_ans}
                                                        </c:if>
                                                        <c:if test="${a.selected_ans eq null or empty a.selected_ans}">
                                                            ${'O'}
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                  
                                </div>
                            </div>   
                            <input type="submit" value="Next"/>
                        </form>
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
        </div> <!-- /.main-page-wrapper -->
    </body>
</html>