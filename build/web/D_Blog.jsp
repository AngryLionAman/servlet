<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="com.fun.helpingFunction" id="fun" scope="page"/>
<jsp:useBean class="com.string.WordFormating" id="word" scope="page"/>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <script src="ckeditor/ckeditor.js"></script>
        <meta charset="UTF-8">        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <c:catch var="ex">
            <c:if test="${blogByBlogId eq null}">
                <c:choose>
                    <c:when test="${param.id ne null and not empty param.id}">
                        <c:redirect url="blog?id=${param.id}"/> 
                    </c:when>
                    <c:otherwise>
                        <c:redirect url="blog"/>
                    </c:otherwise>   
                </c:choose>                             
            </c:if>
            <c:if test="${blogByBlogId ne null}">

                <c:forEach items="${blogByBlogId}" var="blog">
                    <c:set value="${blog.uniqueId}" var="blogId"/>
                    <c:set value="${blog.subject}" var="blogSub"/>
                    <c:set value="${blog.desc}" var="blogDes"/>
                    <c:set value="${blog.view}" var="blogView"/>
                    <c:set value="${blog.fullName}" var="fullName"/>
                    <c:set value="${blog.userName}" var="userName"/>
                    <c:set value="${blog.userId}" var="userId"/>                  

                </c:forEach>
            </c:if>
        </c:catch>
        <c:if test="${ex ne null}">
            ${ex}
        </c:if>
        <title>${blogSub}</title>
        <meta property="og:title" content="${blogSub} - inquiryhere.com" />
        <meta property="og:description" content="<c:out value="${fn:substring(blogDes, 0, 300)}" escapeXml="false"/>"/>

        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <script type="text/javascript">

            function showCommentBox() {
                var div = document.getElementById('comment');
                div.className = 'visible';
            }
        </script>
        <style>
            input[type=text] {
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid #b3b3b3;
                border-radius: 2px;
            }
            .button {
                background-color: #4CAF50; /* Green */
                border: none;
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
        <!-- Header _________________________________ -->
        <jsp:include page="header.jsp"/>
        <div class="main-page-wrapper">

            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <c:if test="${message ne null}">
                            <div class="themeBox" align="center" style="font-size: 20px;color: green;background-color: yellow;">
                                ${message}
                            </div>
                        </c:if>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

                            <div class="row">

                                <div class="themeBox" style="height:auto;">

                                    <div class="boxHeading marginbot10">
                                        [ ${blogView} ]  Subject :  ${blogSub}
                                    </div>
                                    <c:if test="${userName ne null}">
                                        <div class="questionArea">

                                            <div class="postedBy">Posted by :<a href="profile?user=${userName}&id=${userId}&ref=b">${fullName}</a></div>

                                        </div>
                                    </c:if>

                                </div>
                                <div class="boxHeading marginbot10">Description</div>

                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading marginbot10" style="font-size: 15px;font-family: Arial, Helvetica, sans-serif;">
                                        ${blogDes}
                                    </div>
                                </div>

                                <div class="clear-fix"></div>
                                Comments....<br>
                                <div align="right">
                                    <sql:query dataSource="jdbc/mydatabase" var="comment">
                                        SELECT unique_id,user_id,(SELECT firstname FROM newuser WHERE id = user_id )AS fullname,q_id,comments,time FROM comments WHERE blog_id = ? ;
                                        <sql:param value="${blogId}"/>
                                    </sql:query>
                                    <c:forEach items="${comment.rows}" var="cmt">
                                        <c:out value="${cmt.comments}"/>:
                                        <c:choose>
                                            <c:when test="${cmt.fullname eq null or empty cmt.fullname}">
                                                <font color="green"> <c:out value="Guest User"/> </font>  
                                            </c:when>
                                            <c:otherwise>
                                                <a href="profile?user=${cmt.fullname}&id=${cmt.user_id}&ref=bc">${cmt.fullname}</a>    
                                            </c:otherwise>  
                                        </c:choose>                                                                                     
                                        <br>________________________________<br>
                                    </c:forEach>
                                </div>

                                <form name="form_name_blog_save_comment" action="SaveBlogComment" method="post">
                                    <input type="hidden" name="blogSub" value="${blogSub}">
                                    <input type="hidden" name="blog_id" value="${blogId}">
                                    <input type="hidden" name="sessionUserId" value="${sessionScope.Session_id_of_user}">
                                    <input type="hidden" name="bloggerUserId" value="${userId}">
                                    <textarea name="comments" rows="3" cols="30" required="" placeholder="What you think..."></textarea>
                                    <input type="submit" name="sub" value="Send Comment">
                                </form>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" >
                            <div class="row">
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        Also Read
                                    </div>
                                    <div>
                                        <c:catch var="msg">
                                            <c:forEach items="${blogByLimit}" var="m">
                                                <a href="blog?id=${m.uniqueId}&sub=${word.UrlFormat(m.subject)}">${m.subject}</a><br><br>
                                            </c:forEach>
                                        </c:catch>
                                        <c:if test="${msg ne null}">
                                            ${msg}
                                        </c:if>
                                    </div>
                                </div>
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        Fun Zone
                                    </div>
                                    <div>
                                        <ul>
                                            <c:catch var="msg">
                                                <c:forEach items="${fun.CategoryDetail()}" var="m">
                                                    <li><a href="fun?category=${m}">${word.convertStringUpperToLower(m)}</a></li>
                                                    </c:forEach>
                                                </c:catch>
                                                <c:if test="${msg ne null}">
                                                    ${msg}
                                                </c:if>
                                        </ul>
                                    </div>
                                </div>
                                <div class="themeBox" style="height:auto;">
                                    <div class="boxHeading" style="text-align: center; background-color: gold;">
                                        Shortcut Key
                                    </div>
                                    <div>
                                        <ul>
                                            <li><a href="UserProfile.jsp">Complete User List</a></li>
                                            <li><a href="moretopic">Complete Topic List</a></li>
                                            <li><a href="blog">Read Blog</a></li>
                                            <li><a href="WriteBlogHere.jsp">Write a Blog</a></li>
                                            <li><a href="optionalquestion">Read Objective Question</a></li>
                                        </ul>
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
        <!-- Bootstrap JS -->
        <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
        <!-- Bootstrap Select JS -->
        <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>

    </body>
</html>