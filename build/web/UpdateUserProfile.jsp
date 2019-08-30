<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="site.jsp" %>
<%@page import="com.profile.userProfile" %>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">       
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Update User Profile | inquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            input[type=text] {
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid red;
                border-radius: 2px;
            }
            textarea[type=text]{
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid red;
                border-radius: 2px;
            }
            .button {
                background-color: #4CAF50; /* Green */
                border: 1px solid red;
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
            <jsp:include page="header.jsp"/>
            <c:catch var="ex">
                <c:if test="${sessionScope.Session_id_of_user eq null}">
                    <c:redirect url="login.jsp"/>
                </c:if>   
            </c:catch>
            <c:if test="${ex ne null}">
                ${ex}
            </c:if>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>                       
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">                                                   
                            <div class="row">
                                <center>           
                                    <c:if test="${sessionScope.Session_id_of_user ne null}">
                                        <sql:query dataSource="jdbc/mydatabase" var="userProfile">
                                            select * from newuser where id  = ?;
                                            <sql:param value="${sessionScope.Session_id_of_user}"/>
                                        </sql:query> 
                                        <c:forEach var="user" items="${userProfile.rows}">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                <div class="themeBox" style="height:auto;">
                                                    <label for="fname">Update your profile image</label>
                                                    <div class="boxHeading">
                                                        <div>
                                                            <img src="images/${user.imagepath}" alt="Update your profile" style="width:20%; margin:10px 0px 0px; border:1px solid #ddd;">
                                                        </div>
                                                        <div>
                                                            <form action="imageUpload" method="post" enctype="multipart/form-data">
                                                                <input type="file" name="photo" required="">
                                                                <input type="submit" onclick="return false" value="Click here to update">
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <form action="profile" method="post" >
                                                        <label for="fname">Full Name (Not editable)</label>
                                                        <div class="boxHeading">
                                                            <input type="text" id="fname" name="fullname" value="${user.firstname}" readonly="" required="">
                                                        </div>
                                                        <label for="fname">Email (Not editable)</label>
                                                        <div class="boxHeading">
                                                            <input type="text" id="fname" name="email" value="${user.email}" readonly="" required="">
                                                        </div>
                                                        <label for="fname">Higher Qualification</label>
                                                        <div class="boxHeading">
                                                            <c:choose>                                                            
                                                                <c:when test="${user.higher_edu ne null and not empty user.higher_edu}">
                                                                    <input type="text" id="fname" name="HigherQualification" value="${user.higher_edu}" >
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="text" id="fname" name="HigherQualification" placeholder="Insert Your Higher Qualification, Ex: B.Tech,BSC,BA" >
                                                                </c:otherwise>                                                           
                                                            </c:choose>
                                                        </div>
                                                        <label for="fname">Best Achievement</label>
                                                        <div class="boxHeading">
                                                            <c:choose>
                                                                <c:when test="${user.best_achievement ne null and not empty user.best_achievement}">
                                                                    <input type="text" id="fname" name="BestAchievement" value="${user.best_achievement}" >
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="text" id="fname" name="BestAchievement" placeholder="Insert Your Best Achievement" >
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <label for="fname">Bio</label>
                                                        <div class="boxHeading">
                                                            <c:choose>
                                                                <c:when test="${user.bio ne null and not empty user.bio}">
                                                                    <textarea type="text" class="anstext" name="bio"  >${user.bio}</textarea>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="text" id="fname" name="bio" placeholder="Write something about Yourself" >
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <div class="float-right margintop20" style="vertical-align:bottom">
                                                            <button type="submit" class="btn" data-toggle="modal" >Update Profile</button>
                                                        </div>                                                
                                                    </form>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </center> 
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
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <!-- Bootstrap JS -->
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <!-- Bootstrap Select JS -->
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>