<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Quotes in Hindi | inquiryhere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                            <div class="row">
                                <c:catch var="ex">
                                    <c:set value="All" var="category"/> 
                                    <c:if test="${param.category ne null and not empty param.category}">
                                        <c:set value="${param.category}" var="category"/> 
                                    </c:if>
                                    <c:set var="rowsPerPage" value="20" />
                                    <c:set var="pageNumber" value="1" />
                                    <c:if test="${param.p ne null}">
                                        <c:set var="pageNumber" value="${param.p}" />
                                    </c:if>

                                    <c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
                                    <c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
                                    <sql:query dataSource="jdbc/mydatabase" var="quotes">
                                        <c:if test="${category eq 'All'}">
                                            SELECT * FROM quotes;
                                        </c:if>
                                        <c:if test="${category ne 'All'}">
                                            SELECT * FROM quotes where quotes_by = ?;
                                            <sql:param value="${category}"/>
                                        </c:if>
                                    </sql:query>
                                    <c:set var="a">
                                        <fmt:formatNumber value="${quotes.rowCount/rowsPerPage}" maxFractionDigits="0"/>
                                    </c:set>

                                    <c:set var="b" value="${quotes.rowCount/rowsPerPage}" />

                                    <c:choose>
                                        <c:when test="${a==0}">
                                            <c:set var="numberOfPages" value="1" scope="page"/>   
                                        </c:when>

                                        <c:when test="${b>a}">
                                            <c:set var="xxx" value="${b%a}"/>
                                            <c:if test="${xxx>0}">
                                                <c:set var="numberOfPages" value="${b-xxx+1}" scope="page"/>   
                                            </c:if>
                                        </c:when>

                                        <c:when test="${a>=b}">
                                            <c:set var="numberOfPages" value="${a}" scope="page"/>    
                                        </c:when>
                                    </c:choose>
                                    <c:forEach var="q" items="${quotes.rows}" begin="${start}" end="${stop}">
                                        <div class="themeBox" style="height:auto;">
                                            <div class="boxHeading">
                                                ${q.quotes}
                                            </div>
                                            <c:if test="${q.quotes_by ne null}">
                                                <div class="questionArea">
                                                    <div class="postedBy"><b>${q.quotes_by}</b></div>
                                                </div> 
                                            </c:if>

                                        </div>
                                    </c:forEach>
                                    <%--For displaying Previous link --%>
                                    <c:if test="${pageNumber gt 1}">
                                        <a href="quotes.jsp?p=${pageNumber - 1}">Previous</a>
                                    </c:if>
                                    <c:forEach begin="1" end="${numberOfPages}" var="i" >
                                        <c:choose>
                                            <c:when test="${i!=pageNumber}">
                                                <a href="quotes.jsp?p=<c:out value="${i}"/>"><c:out value="${i}"/></a>
                                            </c:when>
                                            <c:otherwise>
                                                <b style="color: red;"><c:out value="${i}"/></b>
                                            </c:otherwise>        
                                        </c:choose>       
                                    </c:forEach>  
                                    <%--For displaying Next link --%>
                                    <c:if test="${pageNumber lt numberOfPages}">
                                        <a href="quotes.jsp?p=${pageNumber + 1}">Next</a>
                                    </c:if>
                                </c:catch>
                                <c:if test="${ex ne null}">
                                    ${ex}
                                </c:if>

                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Category
                                </div>
                                <div>
                                    <sql:query dataSource="jdbc/mydatabase" var="cat">
                                        SELECT DISTINCT quotes_by FROM quotes WHERE quotes_by IS NOT NULL;
                                    </sql:query>
                                    <ul>
                                        <li><a href="quotes.jsp?category=All">All</a></li>
                                        <c:forEach items="${cat.rows}" var="c">
                                            <li><a href="quotes.jsp?category=${c.quotes_by}">${c.quotes_by}</a></li> 
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div> 
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Fun Zone
                                </div>
                                <div>
                                    <jsp:include page="funZoneList.jsp"/>
                                </div>
                            </div>
                            <div class="themeBox" style="height:auto;">
                                <div class="boxHeading">
                                    Education Zone
                                </div>
                                <div>
                                    <jsp:include page="eduZoneList.jsp"/>
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
        </div> 
    </body>
</html>