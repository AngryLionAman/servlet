<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="com.fun.FunHelpingFunction" id="fun" scope="page"/>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>        
        <meta http-equiv="X-UA-Compatible" content="IE=edge"><!-- For IE -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- For Resposive Device -->
        <title>Upload everything</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">        
        <link rel="stylesheet" type="text/css" href="css/responsive.css"><!-- responsive style sheet -->
        <script src="ckeditor/ckeditor.js"></script>
        <meta property="og:description" content="इंडिया का सबसे पहले मज़े वाला वेबसाइट जहाँ पर आप हिंदी में शयारी ,जोक, कहानी,कबिता, कोट्स ,रहस्यमयी बातें इत्यादि के बारे में पढ़ और लिख सकते हैं" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <meta property="og:title" content="इंडिया का सबसे पहले मज़े वाला वेबसाइट जहाँ पर आप हिंदी में शयारी ,जोक, कहानी,कबिता, कोट्स ,रहस्यमयी बातें इत्यादि के बारे में पढ़ और लिख सकते हैं" />
        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="inquiryhere.com" />
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">
        <script type="text/javascript">
            function CheckCat(val) {
                var element = document.getElementById('category1');
                if (val === 'others')
                    element.style.display = 'block';
                else
                    element.style.display = 'none';
            }

        </script> 
        <script type="text/javascript">
            function CheckType(val) {
                var element = document.getElementById('type1');
                if (val === 'others')
                    element.style.display = 'block';
                else
                    element.style.display = 'none';
            }

        </script> 
        <script type="text/javascript">
            function CheckBaesdOn(val) {
                var element = document.getElementById('basedon1');
                if (val === 'others')
                    element.style.display = 'block';
                else
                    element.style.display = 'none';
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

                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" >
                            <div class="row">                                
                                <div style="height:auto;background-color: #e1e8e8;">
                                    <form name="saveFunData" action="saveFunData" method="post">
                                        <input type="hidden" name="userId" value="${sessionScope.Session_id_of_user}"/>
                                        <div id="category" style="background-color: #72f8e0; padding-bottom: 8px;padding-top: 8px;text-align: center;">
                                            <div class="boxHeading">
                                                What you want to upload
                                            </div>
                                            <div>
                                                <select name="category" onchange='CheckCat(this.value);'>
                                                    <c:forEach items="${fun.funCategory}" var="cat">
                                                        <option value="${cat}">${word.convertStringUpperToLower(cat)}</option>
                                                    </c:forEach>
                                                    <option value="others">Others</option>
                                                </select><br><br>
                                                <input type="text" name="category1" id="category1" style='display:none;'/>
                                            </div>
                                        </div>
                                        <div id="type" style="background-color: #f5eda1;padding-bottom: 8px;padding-top: 8px;text-align: center;">
                                            <div class="boxHeading">
                                                Type of the content
                                            </div>
                                            <div>
                                                <select name="type" onchange='CheckType(this.value);'>
                                                    <option value="" selected="">None</option>
                                                    <c:forEach items="${fun.funType}" var="t">
                                                        <option value="${t}">${word.convertStringUpperToLower(t)}</option>
                                                    </c:forEach>
                                                    <option value="others">Others</option>
                                                </select><br><br>
                                                <input type="text" name="type1" id="type1" style='display:none;'/>
                                            </div>
                                        </div>
                                        <div id="based on" style="background-color: #f6c7d4;padding-bottom: 8px;padding-top: 8px;text-align: center;">
                                            <div class="boxHeading">
                                                This is based on
                                            </div>
                                            <div>
                                                <select name="basedon" onchange='CheckBaesdOn(this.value);'>
                                                    <option value="" selected="">None</option>
                                                    <c:forEach items="${fun.funBasedOn}" var="t">
                                                        <option value="${t}">${word.convertStringUpperToLower(t)}</option>
                                                    </c:forEach>
                                                    <option value="others">Others</option>
                                                </select><br><br>
                                                <input type="text" name="basedon1" id="basedon1" style='display:none;'/>
                                            </div>
                                        </div>
                                        <div id="title" style="background-color: #d7ccca;padding-bottom: 8px;padding-top: 8px;text-align: left;">
                                            <div class="boxHeading" style="padding-left: 10px;">
                                                Title
                                            </div>
                                            <div>
                                                <input type="text" name="title"/>
                                            </div>
                                        </div>
                                        <div id="desc" style="background-color: #c6f7f9;padding-bottom: 8px;padding-top: 8px;text-align: left;">
                                            <div class="boxHeading" style="padding-left: 10px;">
                                                Description
                                            </div>
                                            <div>
                                                <textarea name="description" class="ckeditor" required=""></textarea>
                                            </div>
                                        </div>
                                        <div id="desc" style="background-color: #c6f7f9;padding-bottom: 8px;padding-top: 8px;text-align: left;">
                                            <input type="submit" value="Upload">
                                        </div>
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
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div>

    </body></html>