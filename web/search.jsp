<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.string.name" id="fun" scope="page"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Search | inquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="main-page-wrapper">
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="row">
                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                                <div class="themeBox" style="min-height:available">
                                    <div class="boxHeading">
                                        Your Activity
                                    </div>
                                    <c:set scope="page" value="inquiryhere.com" var="query"/> 
                                    <c:if test="${param.q ne null and not empty param.q}">
                                        <c:set scope="page" value="${fn:trim(param.q)}" var="query"/>
                                    </c:if>
                                    <c:set scope="page" value="question" var="tab"/>
                                    <c:if test="${param.tab ne null and not empty param.tab}">
                                        <c:set scope="page" value="${param.tab}" var="tab"/> 
                                    </c:if>
                                    <c:catch var="exe">
                                        <c:if test="${not empty tab and not empty query}">
                                            <sql:query dataSource="jdbc/mydatabase" var="cq">
                                                SELECT count(*) as cq FROM question WHERE lower(question) LIKE '%${query}%';                                                                                               
                                            </sql:query>
                                            <c:forEach items="${cq.rows}" var="cq" >
                                                <c:set value="${cq.cq}" var="t_question" scope="page"/>
                                            </c:forEach>

                                            <sql:query dataSource="jdbc/mydatabase" var="ca">
                                                Select count(*)as ca  from question q right join answer ans on ans.q_id = q.q_id where lower(answer) LIKE '%${query}%';                                                                                               
                                            </sql:query>
                                            <c:forEach items="${ca.rows}" var="ca" >
                                                <c:set value="${ca.ca}" var="t_answer" scope="page"/>
                                            </c:forEach>

                                            <sql:query dataSource="jdbc/mydatabase" var="topic">
                                                SELECT count(*)as tc FROM topic WHERE lower(topic_name) LIKE '%${query}%';                                                                                               
                                            </sql:query>
                                            <c:forEach items="${topic.rows}" var="t" >
                                                <c:set value="${t.tc}" var="t_topic" scope="page"/>
                                            </c:forEach>

                                            <sql:query dataSource="jdbc/mydatabase" var="profile">
                                                SELECT count(*)as cf FROM newuser WHERE lower(firstname) LIKE '%${query}%';                                                                                               
                                            </sql:query>
                                            <c:forEach items="${profile.rows}" var="p" >
                                                <c:set value="${p.cf}" var="t_profile" scope="page"/>
                                            </c:forEach>
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${exe ne null}}">
                                        ${exe}
                                    </c:if>
                                    <div>
                                        <a href="search.jsp?tab=question&q=<c:out value="${query}"/>">Question (${t_question})</a><br>
                                        <a href="search.jsp?tab=answer&q=<c:out value="${query}"/>">Answer (${t_answer})</a><br>
                                        <a href="search.jsp?tab=topic&q=<c:out value="${query}"/>">Topic (${t_topic}) </a><br>
                                        <a href="search.jsp?tab=profile&q=<c:out value="${query}"/>">User Profile (${t_profile}) </a><br>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                                <div class="row">
                                    <div class="themeBox" style="height:auto;margin-bottom:0px;">
                                        <p id="demo"></p>
                                        <c:catch var="ex">

                                            <c:if test="${not empty tab and not empty query}">
                                                <c:choose>
                                                    <c:when test="${tab eq 'question'}">
                                                        <center><div class=boxHeading>Question</div></center>
                                                            <sql:query dataSource="jdbc/mydatabase" var="question">                                                            
                                                            SELECT * FROM question WHERE lower(question) LIKE '%${query}%';
                                                        </sql:query>

                                                        <c:forEach items="${question.rows}" var="q">
                                                            <br><a href="Answer.jsp?q=<c:out value="${fn:replace(q.question,' ','-')}"/>&Id=<c:out value="${q.q_id}"/>" ><h6><c:out value="${q.question}"/> ?</h6></a><br>
                                                        </c:forEach>
                                                        <c:if test="${t_question eq 0}">
                                                            No related question Found !!!!!!!!
                                                        </c:if>  
                                                    </c:when>
                                                    <c:when test="${tab eq 'answer'}">
                                                        <center><div class=boxHeading>Answer</div></center>
                                                            <sql:query dataSource="jdbc/mydatabase" var="answer">
                                                            Select q.question,q.q_id,ans.answer from question q right join answer ans on ans.q_id = q.q_id where lower(answer) LIKE '%${query}%';
                                                        </sql:query>
                                                        <c:forEach items="${answer.rows}" var="a">
                                                            Q.<a href="Answer.jsp?q=<c:out value="${fn:replace(a.question,' ','-')}"/>&Id=<c:out value="${a.q_id}"/>" ><c:out value="${a.question}"/> ?</a>
                                                            <br>Ans.<c:out value="${a.answer}" escapeXml="false"/><br>
                                                        </c:forEach>
                                                        <c:if test="${t_answer eq 0}">
                                                            No related answer Found !!!!!!!!
                                                        </c:if> 
                                                    </c:when>
                                                    <c:when test="${tab eq 'topic'}">
                                                        <center><div class=boxHeading>Topic</div></center>
                                                            <sql:query dataSource="jdbc/mydatabase" var="topic">
                                                            SELECT * FROM topic WHERE lower(topic_name) LIKE '%${query}%';
                                                        </sql:query>
                                                        <c:forEach var="t" items="${topic.rows}">
                                                            <br><a href="topic.jsp?t=<c:out value="${fn:replace(fun.convertStringUpperToLower(fn:trim(t.topic_name)),' ','-')}"/>&id=<c:out value="${t.unique_id}"/>"> <c:out value="${fun.convertStringUpperToLower(fn:trim(t.topic_name))}"/></a><br>
                                                        </c:forEach>
                                                        <c:if test="${t_topic eq 0}">
                                                            No related topic Found !!!!!!!!
                                                        </c:if> 
                                                    </c:when>
                                                    <c:when test="${tab eq 'profile'}">
                                                        <center><div class=boxHeading>User Profile</div></center>
                                                            <sql:query dataSource="jdbc/mydatabase" var="profile">
                                                            SELECT * FROM newuser WHERE lower(firstname) LIKE '%${query}%';
                                                        </sql:query>
                                                        <c:forEach items="${profile.rows}" var="p">
                                                            <br><a href="profile.jsp?user=<c:out value="${fn:replace(fun.convertStringUpperToLower(fn:trim(p.firstname)),' ','-')}"/>&ID=<c:out value="${p.ID}"/>"><c:out value="${fun.convertStringUpperToLower(fn:trim(p.firstname))}"/></a><br>
                                                        </c:forEach>
                                                        <c:if test="${t_profile eq 0}">
                                                            No related profile Found !!!!!!!!
                                                        </c:if> 
                                                    </c:when>
                                                </c:choose>
                                            </c:if>
                                        </c:catch>
                                        <c:if test="${ex ne null}">
                                            ${ex}
                                        </c:if>
                                        <div class="clear-fix"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- /.main-page-wrapper -->
        <jsp:include page="footer.jsp"/>
        <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
    </body>
</html>