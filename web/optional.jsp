<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean class="com.optionalQuestion.supportingFunction" id="opt_fun" scope="page"/>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@include file="googleAnalytics.jsp" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>        
        <meta http-equiv="X-UA-Compatible" content="IE=edge"><!-- For IE -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- For Resposive Device -->
        <title>Upload Objective Question - inquiryhere.com</title>
        <link rel="canonical" href="https://www.inquiryhere.com/optional.jsp" />
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
            function CheckType(val) {
                var element = document.getElementById('onTopic1');
                if (val === 'others')
                    element.style.display = 'block';
                else
                    element.style.display = 'none';
            }

        </script> 
        <script>
            var room = 1;
            function add_fields(val) {
                var elem = document.getElementById("ttt");
                elem.parentNode.removeChild(elem);
                for (i = 0; i < val.value; i++) {
                    room++;
                    var objTo = document.getElementById('room_fileds');
                    var divtest = document.createElement("div");
                    divtest.innerHTML = '<div id="ttt"></div><div class="col-lg-6 col-md-6 col-sm-12 col-xs-12"  style="padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;"><input type="text" required="" name="option[]" placeholder="option ' + (room - 1) + '"> </div></div>';
                    objTo.appendChild(divtest);
                }
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
                                <c:if test="${param.msg ne null}">
                                    <div class="themeBox" style="font-family: serif;font-variant-position: super;font-size: 25px;text-align: center;background-color: #00ff5c;color: white;">
                                        ${param.msg}
                                    </div>
                                </c:if>
                                <div style="height:auto;background-color: #e1e8e8;">
                                    <form name="optionalQuestion" action="<%=request.getContextPath()%>/saveOptionalQuestion" method="post">
                                        <input type="hidden" name="userId" value="${sessionScope.Session_id_of_user}"/>                                       
                                        <div id="Question" style="background-color: #c6f7f9;padding-bottom: 8px;padding-top: 8px;text-align: left;">
                                            <div class="boxHeading" style="padding-left: 10px;">
                                                Question
                                            </div>
                                            <div>
                                                <textarea name="o_questin" class="ckeditor"></textarea>
                                            </div>
                                        </div>
                                        <div id="onTopicId" style="background-color: #fff9fc;padding-bottom: 8px;padding-top: 8px;text-align: left;">
                                            <div class="boxHeading" style="padding-left: 10px;">
                                                On Topic
                                                <select name="onTopic" onchange="CheckType(this.value);">
                                                    <option value="uncategorized" selected="">Uncategorized</option>
                                                    <c:forEach items="${opt_fun.onTopicName()}" var="t">
                                                        <option value="${t}">${word.convertStringUpperToLower(t)}</option>
                                                    </c:forEach>
                                                    <option value="others">Others</option>                                               
                                                </select>
                                                <input type="text" name="onTopic1" id="onTopic1" style="display:none;"/>
                                            </div>                                            
                                        </div>
                                        <div id="optional" style="background-color: #d7ccca;padding-bottom: 8px;padding-top: 8px;text-align: left;">
                                            <div class="boxHeading" style="padding-left: 10px;">
                                                How many option you want to have : 
                                                <select name="nuberOfOption" onchange="add_fields(this);">
                                                    <option value="2">2</option>    
                                                    <option value="3">3</option>    
                                                    <option value="4" selected="">4</option>    
                                                    <option value="5">5</option>    
                                                    <option value="6">6</option>    
                                                    <option value="7">7</option>    
                                                    <option value="8">8</option>    
                                                    <option value="9">9</option>    
                                                    <option value="10">10</option>    
                                                </select>
                                            </div>                                            
                                        </div>
                                        <div id="optional" style="background-color: #fffdbe;padding-bottom: 8px;padding-top: 8px;text-align: left;">

                                            <div class="row" style="padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
                                                <div id="room_fileds">               
                                                    <div id="ttt">			
                                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12"  style="padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
                                                            <input type="text" name="option[]" placeholder="option 1" required="">
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
                                                            <input type="text" name="option[]" placeholder="option 2" required="">
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
                                                            <input type="text" name="option[]" placeholder="option 3" required="">
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12" style="padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;">
                                                            <input type="text" name="option[]" placeholder="option 4" required="">
                                                        </div>
                                                    </div>
                                                </div>                                                
                                            </div>
                                        </div>
                                        <div id="correctAnswer" style="background-color: #e0e0e0;padding-bottom: 8px;padding-top: 8px;text-align: left;">
                                            <div class="boxHeading" style="padding-left: 10px;">
                                                Correct Answer is : <input type="text" name="correctAnswer">                                                 
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