<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.string.name" id="word" scope="page"/>
<jsp:useBean class="com.optionalQuestion.supportingFunction" id="opt_fun" scope="page"/>
<%@ include file="site.jsp" %>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Objective Question By INQUIRYHERE.COM</title>
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
        <script>
            function CheckAnswer(color, selectedOptionId, selectedOption, cAns, HTML_id, dis_id) {
                var http = new XMLHttpRequest();
                http.open("POST", "<%=request.getContextPath()%>/updateVoteOfAnswerById?answerId=" + selectedOptionId, true);
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.send();
                if (cAns !== "") {
                    if (selectedOption === cAns) {
                        color.style.background = "green";
                        color.style.color = "white";
                        document.getElementById(HTML_id).innerHTML = "<font style='background-color: green;padding: 5px; color:white; font-family: serif;font-size: 20px;'>Correct Answer</font>";
                    } else {
                        color.style.background = "red";
                        color.style.color = "white";
                        document.getElementById(HTML_id).innerHTML = "<font style='background-color: #cad7ce;padding: 5px; color:red; font-family: serif;font-size: 20px;'>Correct Answer is : " + cAns.toUpperCase() + "</font>";
                    }
                } else {
                    document.getElementById(HTML_id).innerHTML = "<font style='background-color: green;padding: 5px; color:white; font-family: serif;font-size: 20px;'>Your vote has been added</font>";
                }
                var x = document.getElementsByClassName(dis_id);
                for (var i = 0; i < x.length; i++) {
                    x[i].style.display = 'block';
                }
                //d.style.display = 'block';
            }
        </script>
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
                                <c:if test="${param.msg ne null}">
                                    <div class="themeBox" style="font-family: serif;font-variant-position: super;font-size: 25px;text-align: center;background-color: green;color: white;">
                                        ${param.msg}
                                    </div>
                                </c:if>

                                <div class="themeBox" onclick="location.href = 'optional.jsp'" style="font-family: serif;font-variant-position: super;font-size: 25px;text-align: center;background-color: #70f29c;cursor: pointer;">
                                    Upload Objective Question
                                </div>
                                <c:catch var="ex">
                                    <c:forEach var="obj" items="${list}" varStatus="loop1">
                                        <div class="themeBox" style="height:auto; background-color: #f5f4f4;">
                                            <div class="boxHeading" style="font-size: 18px;background-color: #cccccc;">
                                                <c:if test="${obj.onTopic ne null}">
                                                    On Topic : <a href="optionalquestion?onTopic=${obj.onTopic}">${word.convertStringUpperToLower(obj.onTopic)}</a>,
                                                </c:if>
                                                <c:if test="${obj.totalOption ne null}">
                                                    Option : <a href="optionalquestion?option=${obj.totalOption}">${obj.totalOption}</a>
                                                </c:if>
                                            </div>
                                            <div style="background-color: #fbe7cc;padding-top: 5px; padding-left: 5px;font-size: 20px;">                                                
                                                <c:if test="${obj.question ne null}">
                                                    ${obj.question}
                                                </c:if>
                                            </div>     
                                            <div class="row" style="padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">

                                                <c:forEach items="${opt_fun.optionOfQuestinById(obj.id)}" var="o" varStatus="loop">                                                 
                                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" onclick="CheckAnswer(this, '${o.aId}', '${o.option}', '${obj.correctAnswer}', 'myText${loop1.count}', 'p_dis${loop1.count}')"  style="font-family: sans-serif;margin-bottom: 10px; cursor: pointer;background-color: #d8c6ee; border-style: groove; padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
                                                        ${loop.count}. ${word.convertStringUpperToLower(o.option)} <div id="p_dis${loop1.count}" class="p_dis${loop1.count}" style="color: white; display: none;float: right;">${opt_fun.showPrecentage(obj.id, o.vote)}%</div>
                                                    </div>                                                      
                                                </c:forEach>
                                                <span id="myText${loop1.count}"></span>
                                            </div>

                                        </div>
                                    </c:forEach>
                                    <c:if test="${totalNumberOfpage ne null}">
                                        <c:catch var="msg">
                                            <c:set value="1" var="pageNo"/>
                                            <c:if test="${param.p ne null}">
                                                <c:set value="${param.p}" var="pageNo"/>
                                            </c:if>
                                            <c:if test="${pageNo gt 1}">
                                                <a href="optionalquestion?p=${pageNo - 1}">Pre</a>&nbsp;
                                            </c:if>
                                            <c:if test="${totalNumberOfpage <= 15}">
                                                <c:forEach begin="1" end="${totalNumberOfpage}" step="1" varStatus="loop">
                                                    <a href="optionalquestion?p=${loop.count}">${loop.count}</a>&nbsp;
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${totalNumberOfpage > 15}">
                                                <c:forEach begin="1" end="8" step="1" varStatus="loop">
                                                    <a href="optionalquestion?p=${loop.count}">${loop.count}</a>&nbsp;
                                                </c:forEach>
                                                ......
                                                <c:set scope="page" value="${totalNumberOfpage - 8}" var="startFrom"/>
                                                <c:forEach begin="${startFrom}" end="${totalNumberOfpage}" step="1">
                                                    <a href="optionalquestion?p=${startFrom}">${startFrom}</a>&nbsp;
                                                    <c:set scope="page" value="${startFrom + 1}" var="startFrom"/>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${pageNo lt totalNumberOfpage}">
                                                <a href="optionalquestion?p=${pageNo + 1}">Next</a>&nbsp;
                                            </c:if>
                                        </c:catch>
                                    </c:if>                                   
                                    <c:if test="${msg ne null}">
                                        ${msg}
                                    </c:if>
                                </c:catch>

                                <c:if test="${ex ne null}">
                                    ${ex}
                                </c:if>

                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <div class="themeBox" style="height:auto;background-color: #f5f4f4;">
                                <div class="boxHeading">
                                    On Topic
                                </div>
                                <div>
                                    <ul>
                                        <li><a href="optionalquestion?onTopic=all">All</a></li>
                                        <li><a href="optionalquestion?onTopic=uncategorized">Uncategorized</a></li>
                                            <c:catch var="msg">
                                                <c:forEach items="${opt_fun.onTopicName()}" var="t">
                                                <li> <a href="optionalquestion?onTopic=${t}">${word.convertStringUpperToLower(t)}</a></li>
                                                </c:forEach>
                                            </c:catch>
                                            <c:if test="${msg ne null}">
                                                ${msg}
                                            </c:if>

                                    </ul>
                                </div>
                            </div> 
                            <div class="themeBox" style="height:auto;background-color: #f5f4f4;">
                                <div class="boxHeading">
                                    No. Of Option
                                </div>
                                <div>
                                    <ul>
                                        <c:catch var="msg">
                                            <c:forEach items="${opt_fun.totalNumberOfOption()}" var="m">
                                                <li><a href="optionalquestion?option=${m}">${word.convertStringUpperToLower(m)}</a></li>
                                                </c:forEach>
                                            </c:catch>
                                            <c:if test="${msg ne null}">
                                                ${msg}
                                            </c:if>

                                    </ul>
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