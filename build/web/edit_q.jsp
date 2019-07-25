<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <c:if test="${sessionScope.Session_id_of_user eq null}">
            <c:redirect url="login.jsp"/>
        </c:if>
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
            <c:catch var="ex">
                <c:if test="${param.Id eq null and empty param.Id}">
                    <c:redirect url="index.jsp"/>
                </c:if>
                <c:if test="${param.Id ne null and not empty param.Id}">
                    <sql:query var="question" dataSource="jdbc/mydatabase">
                        select q_id,question from question where q_id=?;
                        <sql:param value="${param.Id}"/>
                    </sql:query>  
                    <c:forEach items="${question.rows}" var="q">
                        <c:set value="${q.question}" var="question"/>
                        <c:set value="${q.q_id}" var="questionId"/>
                    </c:forEach>
                </c:if>
            </c:catch>
            <c:if test="${ex ne null}">
                ${ex}
            </c:if>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12"></div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <form name="submitquestion" method="post" action="update_q.jsp">
                                        <input type="hidden" name="question_id" value="${questionId}">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                Edit your question here
                                            </div>
                                            <div>
                                                <textarea type="text" class="anstext" name="question" placeholder="Question">${question}</textarea>
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