<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fun Zone By INQUIRYHERE.COM</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <meta property="og:description" content="इंडिया का सबसे पहले मज़े वाला वेबसाइट जहाँ पर आप हिंदी में शयारी ,जोक, कहानी,कबिता, कोट्स ,रहस्यमयी बातें इत्यादि के बारे में पढ़ और लिख सकते हैं" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="इंडिया का सबसे पहले मज़े वाला वेबसाइट जहाँ पर आप हिंदी में शयारी ,जोक, कहानी,कबिता, कोट्स ,रहस्यमयी बातें इत्यादि के बारे में पढ़ और लिख सकते हैं" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="inquiryhere.com" />
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
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

                                <c:if test="${message ne null}">
                                    <div class="themeBox" style="font-family: serif;font-variant-position: super;font-size: 25px;text-align: center;background-color: green;color: white;">
                                        ${message}
                                    </div>
                                </c:if>

                                <c:if test="${gotException ne null}">
                                    <div class="clear-fix" align="center" style="font-size: 20px;color: red;background-color: white;">
                                        ${'Got some probelm, Please refresh this page or visit after some time'}
                                    </div>
                                </c:if>

                                <div class="themeBox" onclick="location.href = 'uploadfun.jsp'" style="font-family: serif;font-variant-position: super;font-size: 25px;text-align: center;background-color: #70f29c;cursor: pointer;">
                                    Upload SomeThing
                                </div>
                                <c:catch var="ex">
                                    <c:forEach var="obj" items="${funDataByCategory}">
                                        <div class="themeBox" style="height:auto; background-color: #f5f4f4;">
                                            <div class="boxHeading" style="font-size: 18px;background-color: #cccccc;">
                                                <c:if test="${obj.category ne null}">
                                                    <a href="fun?category=${obj.category}">${word.convertStringUpperToLower(obj.category)}</a>
                                                </c:if>
                                                <c:if test="${obj.type ne null}">
                                                    ,<a href="fun?type=${obj.type}">${word.convertStringUpperToLower(obj.type)}</a>
                                                </c:if>
                                                <c:if test="${obj.basedOn ne null}">
                                                    ,<a href="fun?basedOn=${obj.basedOn}">${word.convertStringUpperToLower(obj.basedOn)}</a>
                                                </c:if>

                                            </div><br>
                                            <div>
                                                <c:if test="${obj.title ne null}">
                                                    <div style="background-color: #ccccff;">
                                                        ${obj.title}
                                                    </div>                                                   
                                                </c:if>
                                                <c:if test="${obj.desc ne null}">
                                                    ${obj.desc}
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:forEach>                                    
                                </c:catch>
                                <c:if test="${ex ne null}">
                                    ${ex}
                                </c:if>


                                <c:if test="${totalNumberOfpage ne null}">
                                    <c:catch var="msg">
                                        <c:set value="1" var="pageNo"/>
                                        <c:if test="${param.p ne null}">
                                            <c:set value="${param.p}" var="pageNo"/>
                                        </c:if>
                                        <c:if test="${pageNo gt 1}">
                                            <a href="fun?p=${pageNo - 1}">Pre</a>&nbsp;
                                        </c:if>
                                        <c:if test="${totalNumberOfpage <= 15}">
                                            <c:forEach begin="1" end="${totalNumberOfpage}" step="1" varStatus="loop">
                                                <a href="fun?p=${loop.count}">${loop.count}</a>&nbsp;
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${totalNumberOfpage > 15}">
                                            <c:forEach begin="1" end="8" step="1" varStatus="loop">
                                                <a href="fun?p=${loop.count}">${loop.count}</a>&nbsp;
                                            </c:forEach>
                                            ......
                                            <c:set scope="page" value="${totalNumberOfpage - 8}" var="startFrom"/>
                                            <c:forEach begin="${startFrom}" end="${totalNumberOfpage}" step="1">
                                                <a href="fun?p=${startFrom}">${startFrom}</a>&nbsp;
                                                <c:set scope="page" value="${startFrom + 1}" var="startFrom"/>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${pageNo lt totalNumberOfpage}">
                                            <a href="fun?p=${pageNo + 1}">Next</a>&nbsp;
                                        </c:if>
                                    </c:catch>
                                    <c:if test="${msg ne null}">
                                        ${msg}
                                    </c:if>
                                </c:if>                                   

                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <c:if test="${funCategory ne null and not empty funCategory}">
                                <div class="themeBox" style="height:auto;background-color: #f5f4f4;">
                                    <div class="boxHeading">
                                        Category
                                    </div>
                                    <div>
                                        <ul>
                                            <li><a href="fun">All</a></li>
                                                <c:catch var="msg">
                                                    <c:forEach items="${funCategory}" var="m">
                                                    <li><a href="fun?category=${m}">${word.convertStringUpperToLower(m)}</a></li>
                                                    </c:forEach>
                                                </c:catch>
                                                <c:if test="${msg ne null}">
                                                    ${msg}
                                                </c:if>
                                        </ul>
                                    </div>
                                </div>  
                            </c:if>


                            <c:if test="${funType ne null and not empty funType}">
                                <div class="themeBox" style="height:auto;background-color: #f5f4f4;">
                                    <div class="boxHeading">
                                        Type
                                    </div>
                                    <div>
                                        <ul>
                                            <li><a href="fun">All</a></li>
                                                <c:catch var="msg">
                                                    <c:forEach items="${funType}" var="m">
                                                    <li><a href="fun?type=${m}">${word.convertStringUpperToLower(m)}</a></li>
                                                    </c:forEach>
                                                </c:catch>
                                                <c:if test="${msg ne null}">
                                                    ${msg}
                                                </c:if>
                                        </ul>
                                    </div>
                                </div>
                            </c:if>


                            <c:if test="${funBasedOn ne null and not empty funBasedOn}">
                                <div class="themeBox" style="height:auto; background-color: #f5f4f4;">
                                    <div class="boxHeading">
                                        Based on
                                    </div>
                                    <div>
                                        <ul>                                        
                                            <li><a href="fun">All</a></li>
                                                <c:catch var="msg">
                                                    <c:forEach items="${funBasedOn}" var="m">
                                                    <li><a href="fun?basedOn=${m}">${word.convertStringUpperToLower(m)}</a></li>
                                                    </c:forEach>
                                                </c:catch>
                                                <c:if test="${msg ne null}">
                                                    ${msg}
                                                </c:if>
                                        </ul>
                                    </div>
                                </div>
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
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script><!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script><!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> 
    </body>
</html>