<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.string.WordFormating" id="fun" scope="page"/>
<jsp:useBean class="com.notifications.displayNotification" id="nt" scope="page"/>
<jsp:useBean class="com.notifications.NotificationExtraSupportingClass" id="n_t" scope="page"/>

<header class="home-page">
    <div class="container clear-fix">
        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" style="padding-left:0px;">
            <div class="logo float-left">
                <a href="index" style="vertical-align:middle;">
                    <h4>
                        <div class="logotext">
                            inquiryhere.com
                        </div>
                    </h4>
                </a>
            </div>
        </div>
        <script type="text/javascript">
            function validateForm()
            {
                var a = document.forms["Form"]["q"].value;
                if (a === null || a === "", a.trim() === "")
                {
                    return false;
                } else
                {
                    /* var a = document.forms["Form"]["q"].value;
                     var http = new XMLHttpRequest();
                     http.open("POST", "saveSearchedQuaryServlet?query=" + a + "&id=${sessionScope.Session_id_of_user}", true);
                     http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                     http.send();*/
                    return true;
                }
            }
        </script>
        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 serachhere" style="display:inline-block;">
            <div style="overflow: hidden; padding-right: .5em;">
                <form action="search" name="Form" onsubmit="return validateForm()">
                    <input type="text" style="width: 100%;" name="q" required="">
                </form>
            </div>
        </div>

        <form id="myform" method="post" action="<%=request.getContextPath()%>/inbox?id=${sessionScope.Session_id_of_user}"></form>
        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 float-right textalign-right">          
            <a href="#" data-toggle="modal" data-target="#myModal2" class="helpicon"  style="color: white;background-color: red; padding-left: 10px;padding-right: 80px; white-space: nowrap;">Ask Question</a>           
            <c:choose>
                <c:when test="${sessionScope.Session_id_of_user ne null}">
                    <c:catch var="m">
                        <a href="logout" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">Logout</a>

                        <a href="" onclick="document.getElementById('myform').submit(); return false;" class="helpicon" style="display: inline; color: white;padding-left: 10px;padding-right: 10px;">
                            Inbox 
                            <c:if test="${nt.countNotificationByUserId(sessionScope.Session_id_of_user) gt 0}">
                                <span style="display: inline; top: -10px;right: -10px;padding: 5px 10px;border-radius: 50%;background-color: red;color: white;"> ${nt.countNotificationByUserId(sessionScope.Session_id_of_user)} </span>
                            </c:if>
                        </a>
                        <a href="profile?user=${n_t.getUserNameById(sessionScope.Session_id_of_user)}&id=${sessionScope.Session_id_of_user}" class="helpicon" style="color: white;padding-left: 10px;padding-right: 20px;">
                            <b>${fun.firstName(n_t.getFullNameById(sessionScope.Session_id_of_user))}</b>
                        </a>        
                    </c:catch>
                    <c:if test="${m ne null}">
                        ${m}
                    </c:if>
                </c:when>
                <c:otherwise>
                    <a href="signup.jsp" class="helpicon"  style="color: white;padding-left: 10px;padding-right: 30px;">SIgnUp</a>
                    <a href="login.jsp" class="helpicon" style="color: white;padding-left: 10px;padding-right: 30px;">Login</a>
                </c:otherwise>
            </c:choose>
            <a href="index" class="helpicon" style="color: white;">Home</a>
        </div>
    </div>
</header>