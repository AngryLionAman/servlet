<%-- 
    Document   : settings
    Created on : 14 Feb, 2020, 12:18:25 PM
    Author     : AngryLion
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isErrorPage="true" errorPage="error.jsp" %>        
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>  
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html" charset="utf-8">
        <title>Account Settings - Inquiryhere</title>
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png" />
        <link rel="canonical" href="https://www.inquiryhere.com" />

        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">

        <script type="text/javascript">
            function showCommentBox() {
                var div = document.getElementById('email');
                div.className = 'visible';
            }
        </script>

    </head>

    <body>
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp"/>
            <c:if test="${message ne null}">
                <div class="clear-fix" align="center" style="font-size: 20px;color: green;background-color: yellow;">
                    ${message}
                </div>
            </c:if>

            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="themeBox">
                                <div class="row">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        Settings
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <ul>
                                            <li><a href="settings?tab=Account">Account</a></li>
                                            <li><a href="settings?tab=Privacy">Privacy</a></li>
                                            <li><a href="settings?tab=Notification">Email and notification</a></li>
                                        </ul> 
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row" id="account">
                                <div class="themeBox" style="height:auto;">
                                    <div align="left" style="font-size: 15px;font-family: serif;">
                                        Primary Email: 
                                    </div>
                                    <div class="boxHeading marginbot10">
                                        <c:if test="${email ne null and not empty email}">
                                            Registred Email Address :  ${email}
                                        </c:if>   
                                        <br> <a href="javascript:void(0)" value="email" onclick="showCommentBox()">Add Another Email Address</a>
                                        <form action="settings" method="post">
                                            <div class="hidden" id="email">
                                                <input type="hidden" name="action" value="addEmail">
                                                <input type="hidden" name="session_userid" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                                <input type="email" name="newEmail">
                                                <input type="submit" value="Add">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="themeBox" style="height:auto;">
                                    <div align="left" style="font-size: 15px;font-family: serif;">
                                        Change Password
                                    </div>
                                    <div class="boxHeading marginbot10">
                                        <form action="settings" method="post">
                                            <input type="hidden" name="action" value="chagePassword">
                                            <input type="hidden" name="session_userid" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                            Old Password: <input type="password" name="oldPassword">
                                            New Password: <input type="password" name="newPassword">
                                            New Password: <input type="password" name="confirmNewPassword">
                                            <input type="submit" value="Change">
                                        </form>
                                    </div>
                                </div>
                                <div class="themeBox" style="height:auto;">
                                    <c:if test="${mobileNumberByuserId ne null and not empty mobileNumberByuserId and mobileNumberByuserId ne 0}">
                                        Registred Mobile Number :- ${mobileNumberByuserId}
                                    </c:if>
                                    <div align="left" style="font-size: 15px;font-family: serif;">
                                        Add New Phone Number
                                    </div>
                                    <div class="boxHeading marginbot10">
                                        <form action="settings" method="post">
                                            <input type="hidden" name="action" value="addPhone">
                                            <input type="hidden" name="session_userid" value="<c:out value="${sessionScope.Session_id_of_user}"/>">
                                            <input type="tel" name="telNum">
                                            <input type="submit" value="Add Phone">
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <br><br>
                            <div class="row" id="Privacy">
                                <div class="themeBox" style="height:auto;">
                                    <div align="left" style="font-size: 15px;font-family: serif;">
                                        Hide/Display Your Mail
                                    </div>
                                    <div class="boxHeading marginbot10">
                                        Current email status: ${emailDisplayStatus}
                                    </div>
                                </div>
                                <div class="themeBox" style="height:auto;">
                                    <div align="left" style="font-size: 15px;font-family: serif;">
                                        Allow guest to comment on your profile
                                    </div>
                                    <div class="boxHeading marginbot10">
                                        Allow
                                    </div>
                                </div>
                                <div class="themeBox" style="height:auto;">
                                    <div align="left" style="font-size: 15px;font-family: serif;">
                                        Delete your acount
                                    </div>
                                    <div class="boxHeading marginbot10">
                                        Click here to Delete
                                    </div>
                                </div>
                            </div>
                            <br><br>
                            <div class="row" id="Notification">
                                <div class="themeBox" style="height:auto;">
                                    <div align="left" style="font-size: 15px;font-family: serif;">
                                        Send me the email when i got the answer of question
                                    </div>
                                    <div class="boxHeading marginbot10">
                                        done
                                    </div>
                                </div>

                            </div>

                        </div>
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