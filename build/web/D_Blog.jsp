<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="site.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <%@include file="googleAnalytics.jsp" %>
        <script src="ckeditor/ckeditor.js"></script>
        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-128307055-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-128307055-1');
        </script> 


        <c:catch var="ex">
            <c:if test="${param.Blog_Id eq null or empty param.Blog_Id}">
                <c:redirect url="blog.jsp"/>
            </c:if>
            <c:if test="${param.Blog_Id ne null and not empty param.Blog_Id}">
                <sql:query var="blog" dataSource="jdbc/mydatabase">
                    SELECT b.blog_subject,b.total_view,b.blog_id,b.blog,
                    user.firstname,user.username,user.id FROM blog b right join newuser user on b.blog_posted_by = user.Id  
                    WHERE blog_id = ?;
                    <sql:param value="${param.Blog_Id}"/>
                </sql:query>
                <c:forEach items="${blog.rows}" var="blog">
                    <c:set value="${blog.blog_id}" var="blogId"/>
                    <c:set value="${blog.blog_subject}" var="blogSub"/>
                    <c:set value="${blog.blog}" var="blogDes"/>
                    <c:set value="${blog.total_view}" var="blogView"/>
                    <c:set value="${blog.firstname}" var="fullName"/>
                    <c:set value="${blog.username}" var="userName"/>
                    <c:set value="${blog.id}" var="userId"/>
                    <sql:update dataSource="jdbc/mydatabase" var="iView">
                        UPDATE blog SET total_view = total_view + 1 WHERE blog_id =?;
                        <sql:param value="${blog.blog_id}"/>
                    </sql:update>
                    <title>${blog.blog_subject}</title>
                    <meta property="og:title" content="${blog.blog_subject}" />
                    <meta property="og:description" content="<c:out value="${fn:substring(blog.blog, 0, 300)}" escapeXml="false"/>"/>
                </c:forEach>
            </c:if>
        </c:catch>
        <c:if test="${ex ne null}">
            ${ex}
        </c:if>


        <meta property="og:url" content="https://www.inquiryhere.com/">
        <meta property="og:site_name" content="https://www.inquiryhere.com/" />
        <meta property="og:image" content="https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG" />
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
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

                            <div class="row">

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                                    <div class="themeBox" style="height:auto;">

                                        <div class="boxHeading marginbot10">

                                            [ ${blogView} ]  Subject :  ${blogSub}
                                        </div>
                                        <div class="questionArea">

                                            <div class="postedBy">Posted by :<a href="profile.jsp?user=${userName}&ID=${userId}">${fullName}</a></div>

                                        </div>
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
                                                    <a href="profile.jsp?user=${cmt.fullname}&ID=${cmt.user_id}">${cmt.fullname}</a>    
                                                </c:otherwise>  
                                            </c:choose>                                                                                     
                                            <br>________________________________<br>
                                        </c:forEach>

                                    </div>
                                    <%
                                        if (session.getAttribute("Session_id_of_user") == null) {
                                    %>
                                    <div align="center">
                                        <button type="submit" class="button button1" onclick="showCommentBox()">Comment as Guest</button> 
                                    </div>
                                    <form action="saveBlogComment.jsp" method="get">
                                        <div class="hidden" id="comment">
                                            <input type="hidden" name="blogSub" value="${blogSub}">
                                            <input type="hidden" name="blog_id" value="${blogId}">
                                            <input type="hidden" name="bloggerUserId" value="${userId}">
                                            <textarea name="comments" rows="3" cols="30" required="" placeholder="What you think..."></textarea>
                                            <input type="submit" name="sub" value="Send Comment">
                                        </div>
                                    </form>
                                    <div align="center">
                                        <button type="submit" class="button button1" onclick="location.href = 'login.jsp?URL=D_Blog.jsp?Blog_Id=${blogId}';" >Login to comment</button> 
                                    </div>
                                    <% } else {%>
                                    <form action="saveBlogComment.jsp" method="get">
                                        <input type="hidden" name="blogSub" value="${blogSub}">
                                        <input type="hidden" name="blog_id" value="${blogId}">
                                        <input type="hidden" name="sessionUserId" value="${sessionScope.Session_id_of_user}">
                                        <input type="hidden" name="bloggerUserId" value="${userId}">
                                        <textarea name="comments" rows="3" cols="30" required="" placeholder="What you think..."></textarea>
                                        <input type="submit" name="sub" value="Send Comment">
                                    </form>
                                    <% }%>
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