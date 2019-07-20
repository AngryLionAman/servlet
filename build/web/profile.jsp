<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%!            public boolean validateUser(String username, String password) {
        boolean found = false;
        try {
            String cookiesMail = username;
            String cookiesPassword = password;

            Connection connection = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);

            String password1;
            try {

                String v = "SELECT ID,email,password FROM newuser WHERE email = ?";

                preparedStatement = connection.prepareStatement(v);
                preparedStatement.setString(1, cookiesMail);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    password1 = resultSet.getString("password");
                    //Session_id_of_user = resultSet.getInt("ID");
                    if (cookiesPassword.equals(password1)) {
                        found = true;
                    }
                }
                connection.close();
                resultSet.close();
                preparedStatement.close();
            } catch (Exception e) {
                //  out.println("Error in main try block:-" + e);
            }
        } catch (Exception msg) {

        }
        return found;
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head> 
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <link rel="icon" href="https://www.inquiryhere.com/images/inquiryhere_Logo.PNG" type="image/png">

        <meta charset="UTF-8">
        <%@page language="java" %>
        <%@page import="java.sql.*" %> 
        <%@include file="validator.jsp" %>
        <%@include file="site.jsp" %>
        <%
            String EMAIL = "";
            String PASSWORD = "";
            String HOME = "";
            String LOGIN = "";
            String SIGNUP = "";
            String SEARCH = "";
            String PROFILE = "";
            String CONTACT_US = "";
            String ABOUT_US = "";
            String LOGOUT = "";
            String SELECT = "";
            String PROFILE_DETAILS = "";
            String UPDATE_YOUR_PROFILE_IMAGE = "";
            String NAME = "";
            String MAIL_ID = "";
            String HIGHER_QUALIFICATION = "";
            String BEST_ACHIEVEMENT = "";
            String BIO = "";
            String COMPLETE_YOUR_PROFILE = "";
            String YOUR_ACTIVITY = "";
            String QUESTION = "";
            String ANSWER = "";
            String TOPIC_FOLLOWED = "";
            String FOLLOWING = "";
            String FOLLOWERS = "";
            String BLOG = "";
            String QUOTES = "";
            String ADD_MORE_QUESTION = "";
            String FOLLOW_MORE_TOPIC = "";
            String NOT_FOLLOWING_ANY_USER = "";
            String FOLLOW_MORE_USER = "";
            String NO_BLOG_POSTED_YET = "";
            String BLOG_ABOUT_SOMETHING = "";
            String NO_QUESTES_POSTED_YET = "";
            String ADD_MORE_QUOTES = "";
            String NOT_FOLLOWED_BY_ANY_USER = "";
            String POST_YOUR_QUESTION_HERE = "";
            String PLEASE_LOGIN_FIRST = "";
            String CLOSE = "";
            String CLICK_HERE_TO_LOGIN = "";
            String FOLLOWED_TOPIC = "";
            String CLICK_HERE_TO_MORE_TOPIC = "";
            String PUT_YOUR_QUESTION_HERE = "";
            String EX = "";
            String TAG_SUGGESTION_DESCRIPTION = "";
            String TAG_EXMAPLE = "";
            String POST = "";
        %>

        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            } else {

            }
            if (sl.equalsIgnoreCase("hi")) {
                EMAIL = "ईमेल/Phone";
                PASSWORD = "पासवर्ड";
                HOME = "होम";
                LOGIN = "लॉग इन करें";
                SIGNUP = "नया खाता बनाएँ";
                SEARCH = "खोजे";
                PROFILE = "प्रोफ़ाइल";
                CONTACT_US = "हमसे संपर्क करें";
                ABOUT_US = "हमारे बारे में";
                LOGOUT = "बाहर जाये";
                SELECT = "चयन करें";
                PROFILE_DETAILS = "प्रोफ़ाइल का विवरण";
                UPDATE_YOUR_PROFILE_IMAGE = "अपनी प्रोफ़ाइल छवि को अपडेट करें";
                NAME = "नाम";
                MAIL_ID = "ईमेल आईडी/Phone";
                HIGHER_QUALIFICATION = "उच्च योग्यता";
                BEST_ACHIEVEMENT = "सबसे अच्छी उपलब्धि";
                BIO = "आपके बारे में";
                COMPLETE_YOUR_PROFILE = "अपनी प्रोफाइल पूरी कीजिए";
                YOUR_ACTIVITY = "आपकी गतिविधि";
                QUESTION = "प्रशन";
                ANSWER = "उत्तर";
                TOPIC_FOLLOWED = "विषय का पालन किया";
                FOLLOWING = "आपने जिसको अनुशरण किया";
                FOLLOWERS = "अनुसरण करने वाले शिष्य";
                BLOG = "ब्लॉग";
                QUOTES = "उल्लेख";
                ADD_MORE_QUESTION = "एक और सवाल जोड़ें";
                FOLLOW_MORE_TOPIC = "अधिक विषय का पालन करें";
                NOT_FOLLOWING_ANY_USER = "किसी भी उपयोगकर्ता का अनुसरण नहीं कर रहे है";
                FOLLOW_MORE_USER = "अधिक उपयोगकर्ता का पालन करें";
                NO_BLOG_POSTED_YET = "अभी तक कोई ब्लॉग पोस्ट नहीं किया गया";
                BLOG_ABOUT_SOMETHING = "किसी चीज के बारे में ब्लॉग";
                NO_QUESTES_POSTED_YET = "अभी तक कोई उद्धरण पोस्ट नहीं किया गया है";
                ADD_MORE_QUOTES = "अधिक उद्धरण जोड़ें";
                NOT_FOLLOWED_BY_ANY_USER = "किसी भी उपयोगकर्ता द्वारा पीछा नहीं किया गया";
                POST_YOUR_QUESTION_HERE = "यहाँ अपना प्रश्न डाले";
                PLEASE_LOGIN_FIRST = "पहले प्रवेश करें";
                CLOSE = "बंद करे";
                CLICK_HERE_TO_LOGIN = "लॉग इन करने के लिए यहां क्लिक करें";
                FOLLOWED_TOPIC = "विषय जो आपको पसंद है";
                CLICK_HERE_TO_MORE_TOPIC = "अधिक विषय के लिए यहां क्लिक करें";
                PUT_YOUR_QUESTION_HERE = "अपना प्रश्न यहाँ रखें";
                EX = "उदाहरण :कैसे है ,क्या है ";
                TAG_SUGGESTION_DESCRIPTION = "अपने प्रश्न से संबंधित कम से कम दो टैग प्रदान करें। कोमा (,) का उपयोग करके अलग करे";
                TAG_EXMAPLE = "उदाहरण :विज्ञान,भौतिक , रसायन विज्ञान  ";
                POST = "post";

            } else {
                EMAIL = "Email/Phone";
                PASSWORD = "Password";
                HOME = "Home";
                LOGIN = "Login";
                SIGNUP = "SignUp";
                SEARCH = "Search";
                PROFILE = "Profile";
                CONTACT_US = "contact Us";
                ABOUT_US = "About Us";
                LOGOUT = "Logout";
                SELECT = "Select";
                PROFILE_DETAILS = "Profile Details ";
                UPDATE_YOUR_PROFILE_IMAGE = "Update your profile image";
                NAME = "Name ";
                MAIL_ID = "Mail Id/Phone";
                HIGHER_QUALIFICATION = "Higher Qualification ";
                BEST_ACHIEVEMENT = "Best Achievement";
                BIO = "Bio ";
                COMPLETE_YOUR_PROFILE = "Complete your profile";
                YOUR_ACTIVITY = "User Activity ";
                QUESTION = "Question";
                ANSWER = "Answer";
                TOPIC_FOLLOWED = "Topic Followed";
                FOLLOWING = "Following";
                FOLLOWERS = "Followers";
                BLOG = "Blog";
                QUOTES = "Quotes";
                ADD_MORE_QUESTION = "Add more question";
                FOLLOW_MORE_TOPIC = "Follow more topic";
                NOT_FOLLOWING_ANY_USER = "Not following any user";
                FOLLOW_MORE_USER = "Follow More User";
                NO_BLOG_POSTED_YET = "No blog posted yet";
                BLOG_ABOUT_SOMETHING = "Blog about something";
                NO_QUESTES_POSTED_YET = "No quotes posted yet";
                ADD_MORE_QUOTES = "Add more quotes";
                NOT_FOLLOWED_BY_ANY_USER = "Not followed by any user";
                POST_YOUR_QUESTION_HERE = "Post Your Question Here";
                PLEASE_LOGIN_FIRST = "Please Lgin First";
                CLOSE = "Close";
                CLICK_HERE_TO_LOGIN = "Click here to login";
                FOLLOWED_TOPIC = "Followed Topic";
                CLICK_HERE_TO_MORE_TOPIC = "Click here to more topic";
                PUT_YOUR_QUESTION_HERE = "Put Your Question Here";
                EX = "Ex: What is,How to..";
                TAG_SUGGESTION_DESCRIPTION = "Provide at least two tag related to your question. separate tag using Coma(,)";
                TAG_EXMAPLE = "Ex:Java,Database,c language";
                POST = "post";
            }
        %>
        <%              if (session.getAttribute("email") == null) {
                Cookie[] cookies = request.getCookies();
                String username = "";
                String password = "";
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        Cookie cookie = cookies[i];
                        if (cookie.getName().equals("username-cookie")) {
                            username = cookie.getValue();
                        } else if (cookie.getName().equals("password-cookie")) {
                            password = cookie.getValue();
                        }
                    }
                }
                if (username != "" && password != "") {
                    boolean found = validateUser(username, password);
                    if (found) {
                        String URL = request.getRequestURL() + "?" + request.getQueryString();
        %>
        <jsp:forward page="validate.jsp">
            <jsp:param name="email" value="<%=username%>"/>
            <jsp:param name="password" value="<%=password%>"/>
            <jsp:param name="URL" value="<%=URL%>"/>
        </jsp:forward>>
        <%
                    }
                }
            }
        %>

        <script type="text/javascript">

            function showCommentBox() {
            <% if (session.getAttribute("Session_id_of_user") != null) { %>
                var div = document.getElementById('comment');
                div.className = 'visible';
            <% } else { %>alert("Please login first to comment");
            <%  }  %>
                }
        </script>

        <meta charset="UTF-8">
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Profile | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <meta property="og:url" content="https://www.inquiryhere.com/" />
        <meta property="og:title" content="Explore the world with your knowladge" />
        <meta property="og:description" content="Explore the world with your knowladge. inquiryhere.com" />
        <meta property="og:type" content="website">
        <meta property="og:locale" content="en_US">
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-128307055-1"></script>
        <script>
                window.dataLayer = window.dataLayer || [];
                function gtag() {
                    dataLayer.push(arguments);
                }
                gtag('js', new Date());

                gtag('config', 'UA-128307055-1');
        </script> 
        <script type="text/javascript">

            function take_value(el, user_id, id_of_user) {
            <% if (session.getAttribute("email") == null) { %>
                alert("Please login first");<%
                } else {%>
                if (el.value === "follow") {
                    el.value = "followed";
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_follower_detail.jsp?val_topic=" + user_id + "&val2_topic=" + id_of_user + "&action=follow", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                } else {
                    el.value = "follow";
                    var http = new XMLHttpRequest();
                    http.open("POST", "<%=DB_AJAX_PATH%>/submit_follower_detail.jsp?val_topic=" + user_id + "&val2_topic=" + id_of_user + "&action=delete", true);
                    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    http.send();
                }
            <% }%>
            }
        </script>
        <script type="text/javascript">

            function hide_mail(el, session_id_of_user, action) {
            <% if (session.getAttribute("email") == null) { %>
                alert("Please login first");<%
                } else {%>

                var http = new XMLHttpRequest();
                http.open("POST", "<%=DB_AJAX_PATH%>/mail_serurity.jsp?session_id_of_user=" + session_id_of_user + "&action=" + action, true);
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.send();

            <% }%>
            }
        </script>
    </head>

    <body>
        <div class="main-page-wrapper">
            <!-- Header _________________________________ -->
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <div class="bodydata">
                <div class="container clear-fix">
                    <c:if test="${not empty param.ID and param.ID ne null}">
                        <c:set var="userid" scope="page" value="${param.ID}"/>
                    </c:if>
                    <c:if test="${not empty param.user and param.user ne null}">
                        <c:set var="username" scope="page" value="${param.user}"/>
                    </c:if>
                    <c:if test="${empty param.ID and param.ID eq null}">
                        <c:if test="${sessionScope.Session_id_of_user ne null}">
                            <c:set var="userid" scope="page" value="${sessionScope.Session_id_of_user}"/>
                        </c:if>  
                    </c:if>
                    <c:if test="${empty param.ID and not empty param.user}">
                        <sql:query dataSource="${dbsource}" var="f_user_id">
                            select id from newuser where username = ?;
                            <sql:param value="${param.user}"/>
                        </sql:query>
                        <c:forEach items="${f_user_id.rows}" var="user_id">
                            <c:set var="userid" value="${user_id.id}" scope="page"/>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty pageScope.username and pageScope.username eq null and empty pageScope.userid and pageScope.userid eq null}">
                        <c:redirect url="index.jsp"/>
                    </c:if>
                    <c:choose>
                        <c:when test="${not empty pageScope.userid and pageScope.userid ne null}">
                            <sql:query dataSource="${dbsource}" var="usersql" scope="page">
                                SELECT * FROM newuser WHERE ID = ?;
                                <sql:param value="${userid}" />
                            </sql:query>
                        </c:when>
                        <c:otherwise >
                            <sql:query dataSource="${dbsource}" var="usersql" scope="page"> 
                                SELECT * FROM newuser WHERE username = ?;
                                <sql:param value="${username}" />
                            </sql:query>                              
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${usersql ne null and not empty usersql}">
                        <c:forEach items="${usersql.rows}" var="user" >
                            <div class="row">

                                <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="min-height:1px;">
                                        <div class="boxHeading">
                                            <%=PROFILE_DETAILS%>[ View(<c:out value="${user.total_view}"/>) ]
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                            <img src="images/<c:out value="${user.imagepath}"/>" alt="Image" style="width:90%; margin:10px 0px 0px; border:1px solid #ddd;">
                                            <c:if test="${sessionScope.Session_id_of_user ne null and user.id eq sessionScope.Session_id_of_user}">
                                                <a href="UpdateUserProfile.jsp">UPDATE YOUR PROFILE IMAGE</a>
                                            </c:if>
                                            <%--
                                        <c:if test="${sessionScope.Session_id_of_user ne null}">
                                            <c:if test="${sessionScope.Session_id_of_user ne user.id}" ><!--If user watching there own profile they will not get the follow button-->
                                                <sql:query dataSource="${dbsource}" var="followbutton">
                                                    SELECT * FROM ak_follower_detail where followers_id = ?;
                                                    <sql:param value="${sessionScope.Session_id_of_user}"/>
                                                </sql:query>
                                                <c:forEach items="${followbutton.rows}" var="fb">
                                                    <c:if test="${fb.user_id eq user.id}">
                                                        <input type="button" class="float-right" value="UnFollow" id="myButton1" onclick="return take_value(this, '<c:out value="${user.id}"/>', '<c:out value="${sessionScope.Session_id_of_user}"/>');" />
                                                    </c:if>
                                                    <c:if test="${fb.user_id ne user.id}">
                                                        <input type="button" class="float-right" value="follow" id="myButton1" onclick="return take_value(this, '<c:out value="${user.id}"/>', '<c:out value="${sessionScope.Session_id_of_user}"/>');" />
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:if> --%>
                                        </div>

                                        <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                                            <table class="profiledetails">
                                                <tr>
                                                    <td><%=NAME%> </td>
                                                    <td>: <c:out value="${user.firstname}"/></td>
                                                </tr>
                                                <tr>
                                                    <td><%=MAIL_ID%> </td>
                                                    <td>: 
                                                        <c:choose>
                                                            <c:when test="${user.email_s eq 1}">
                                                                <c:choose>
                                                                    <c:when test="${user.id eq sessionScope.Session_id_of_user}">
                                                                        <c:out value="${user.email}"/>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:out value="Hidden by user"/>
                                                                    </c:otherwise>
                                                                </c:choose>                                                               
                                                            </c:when>
                                                            <c:otherwise >
                                                                <c:out value="${user.email}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        &nbsp;&nbsp;
                                                        <c:if test="${sessionScope.Session_id_of_user ne null}" >
                                                            <c:if test="${user.id eq sessionScope.Session_id_of_user}">
                                                                <c:choose>
                                                                    <c:when test="${user.email_s eq 0}">
                                                                        <a href="" onclick="return hide_mail(this, '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="hide"%>');">Hide your mail</a>
                                                                    </c:when>
                                                                    <c:when test="${user.email_s eq 1}">
                                                                        <a href="" onclick="return hide_mail(this, '<c:out value="${sessionScope.Session_id_of_user}"/>', '<%="show"%>');">Display your mail</a> 
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:if>

                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><%=HIGHER_QUALIFICATION%> </td>
                                                    <td>: 
                                                        <c:choose>
                                                            <c:when test="${user.higher_edu eq null or empty user.higher_edu}">
                                                                <c:out value="Not provided yet"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${user.higher_edu}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><%=BEST_ACHIEVEMENT%></td>
                                                    <td>:
                                                        <c:choose>
                                                            <c:when test="${user.best_achievement eq null or empty user.best_achievement}">
                                                                <c:out value="Not provided yet"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${user.best_achievement}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><%=BIO%> </td>
                                                    <td>: 
                                                        <c:choose>
                                                            <c:when test="${user.bio eq null or empty user.bio}">
                                                                <c:out value="Not provided yet"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${user.bio}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <c:if test="${sessionScope.Session_id_of_user ne null and sessionScope.Session_id_of_user eq user.id}">
                                                    <tr>                                                   
                                                        <td><a href="UpdateUserProfile.jsp"><%=COMPLETE_YOUR_PROFILE%></a></td>
                                                    </tr>
                                                </c:if>

                                                <tr>                                                   
                                                    <td>Appreciation ..</td>  
                                                </tr>
                                            </table>

                                            <div align="right">
                                                <sql:query dataSource="${dbsource}" var="profilecomment">
                                                    SELECT unique_id,user_id,(SELECT firstname FROM newuser WHERE id = comments.user_id )AS fullname,
                                                    q_id,comments,time FROM comments WHERE userprofileid = ?;
                                                    <sql:param value="${user.id}"/>
                                                </sql:query>
                                                <c:forEach items="${profilecomment.rows}" var="fc">
                                                    <c:out value="${fc.comments}"/>:-
                                                    <c:choose>
                                                        <c:when test="${username eq 'GuestUser'}">
                                                            <b style=color:red;><c:out value="${username}"/></b><br>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="profile.jsp?user=<c:out value="${fc.fullname}"/>&ID=<c:out value="${fc.user_id}"/>"><c:out value="${fc.fullname}"/></a><br>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:out value="__________________"/><br>
                                                </c:forEach>

                                                <c:if test="${sessionScope.Session_id_of_user ne null and user.id ne sessionScope.Session_id_of_user}">
                                                    <a href="javascript:void(0)" value="Comment" onclick="showCommentBox()">Write Good Thing About Him</a><br>
                                                </c:if>
                                                <c:if test="${sessionScope.Session_id_of_user eq null}">
                                                    <a href="javascript:void(0)" value="Comment" onclick="alert('Please Login To Comment');">Write Good Thing About Him</a><br>  
                                                </c:if>

                                            </div>
                                            <form action="SubmitUserProfileComment.jsp" method="get">
                                                <div class="hidden" id="comment">
                                                    <input type="hidden" name="OnCommentUserId" value="<c:out value="${user.id}"/>">
                                                    <textarea name="comments" rows="3" cols="30" required="" placeholder="Write about him and let the world know how good he is.."></textarea>
                                                    <input type="submit" name="sub" value="Send Comment">
                                                </div>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>


                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <%-- TO the number of user activity --%>
                            <%-- For the question --%>
                            <sql:query dataSource="${dbsource}" var="question">
                                SELECT count(*)cnt FROM question WHERE id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${question.rows}" var="q_count" >
                                <c:set value="${q_count.cnt}" var="q_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Answer --%>
                            <sql:query dataSource="${dbsource}" var="answer">
                                select count(*) as cnt from answer where  Answer_by_id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${answer.rows}" var="a_count" >
                                <c:set value="${a_count.cnt}" var="a_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Topic --%>
                            <sql:query dataSource="${dbsource}" var="topic">
                                select count(*) as cnt from topic_followers_detail where user_or_followers_id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${topic.rows}" var="t_count" >
                                <c:set value="${t_count.cnt}" var="t_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Following --%>
                            <sql:query dataSource="${dbsource}" var="following">
                                select count(*) as cnt from ak_follower_detail where followers_id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${following.rows}" var="fl_count" >
                                <c:set value="${fl_count.cnt}" var="fl_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Followers --%>
                            <sql:query dataSource="${dbsource}" var="followers">
                                select count(*) as cnt from ak_follower_detail where user_id = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${followers.rows}" var="fr_count" >
                                <c:set value="${fr_count.cnt}" var="fr_cnt" scope="page"/>
                            </c:forEach>
                            <%-- For the Blog --%>
                            <sql:query dataSource="${dbsource}" var="blog">
                                select count(*)as cnt from blog where blog_posted_by = ?;
                                <sql:param value="${userid}" />                                                                                                  
                            </sql:query>
                            <c:forEach items="${blog.rows}" var="blog_cnt" >
                                <c:set value="${blog_cnt.cnt}" var="b_cnt" scope="page"/>
                            </c:forEach>
                            <div class="themeBox" style="min-height:auto;">
                                <div class="boxHeading">
                                    <%=YOUR_ACTIVITY%>
                                </div>
                                <div>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&value=Question&ID=<c:out value="${userid}"/>"><%=QUESTION%>(<c:out value="${q_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&value=Answer&ID=<c:out value="${userid}"/>"><%=ANSWER%>(<c:out value="${a_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&value=Topic&ID=<c:out value="${userid}"/>"><%=TOPIC_FOLLOWED%>(<c:out value="${t_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&value=Following&ID=<c:out value="${userid}"/>"><%=FOLLOWING%>(<c:out value="${fl_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&value=Followers&ID=<c:out value="${userid}"/>"><%=FOLLOWERS%>(<c:out value="${fr_cnt}"/>)</a><br>
                                    <a href="profile.jsp?user=<c:out value="${username}"/>&value=Blog&ID=<c:out value="${userid}"/>"><%=BLOG%>(<c:out value="${b_cnt}"/>)</a><br>
                                </div>

                            </div>

                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="themeBox" style="height:auto;margin-bottom:0px;">
                                        <c:choose>
                                            <c:when test="${param.value ne null and not empty param.value}">
                                                <c:set value="${param.value}" scope="page" var="ParametrVariable"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set scope="page" value="Question" var="ParametrVariable"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${ParametrVariable eq 'Question'}">
                                                <center><div class=boxHeading>QUESTION</div></center>    
                                                    <sql:query dataSource="${dbsource}" var="question">
                                                    SELECT * FROM question WHERE id = ?;
                                                    <sql:param value="${userid}" />                                                                                                  
                                                </sql:query>
                                                <c:forEach items="${question.rows}" var="q">
                                                    <br>Q. <a href="Answer.jsp?q=<c:out value="${q.question}"/>&Id=<c:out value="${q.q_id}"/>&sl=<%=sl%>" ><c:out value="${q.question}"/> ?</a>
                                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                                    <c:if test="${sessionScope.Session_id_of_user ne null and q.id eq sessionScope.Session_id_of_user}">
                                                        <a href="edit_q.jsp?Id=<c:out value="${q.q_id}"/>&sl=<%=sl%>">edit</a>
                                                    </c:if>  
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Answer'}">
                                                <center><div class=boxHeading> ANSWER </div></center>
                                                    <sql:query dataSource="${dbsource}" var="answer">
                                                    SELECT q.question,q.q_id,SUBSTRING(ans.answer,1,200)short_ans,
                                                    ans.a_id,ans.Answer_by_id from answer ans right join question q 
                                                    on q.q_id = ans.q_id where Answer_by_id = ?;
                                                    <sql:param value="${userid}"/>
                                                </sql:query>
                                                <c:forEach items="${answer.rows}" var="ans">
                                                    <br> Q. <a href="Answer.jsp?q=<c:out value="${ans.question}"/>&Id=<c:out value="${ans.q_id}"/>&sl=<%=sl%>" ><c:out value="${ans.question}"/> ?</a>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <c:if test="${sessionScope.Session_id_of_user ne null and ans.Answer_by_id eq sessionScope.Session_id_of_user}">
                                                        <a href="edit_a.jsp?q_id=<c:out value="${ans.q_id}"/>&ans_id=<c:out value="${ans.a_id}"/>&ans_by_id=<c:out value="${ans.Answer_by_id}"/>">Edit your answer</a>
                                                    </c:if>
                                                    <br>Ans.</b>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${ans.short_ans}"/>  <a href="Answer.jsp?Id=<c:out value="${ans.q_id}"/>&q=<c:out value="${ans.question}"/>"> Continue Reading</a>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Topic'}">
                                                <center><div class=boxHeading>TOPIC FOLLOWED </div></center>
                                                    <sql:query dataSource="${dbsource}" var="topic">
                                                    select t.unique_id,t.topic_name from topic t 
                                                    right join topic_followers_detail de on t.unique_id = de.topic_id 
                                                    where user_or_followers_id= ? and t.unique_id is not null and t.topic_name is not null;
                                                    <sql:param value="${userid}"/>
                                                </sql:query>
                                                <c:forEach items="${topic.rows}" var="t" >
                                                    <br><a href="topic.jsp?t=<c:out value="${t.topic_name}"/>&id=<c:out value="${t.unique_id}"/>">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${t.topic_name}"/></a>
                                                </c:forEach>   
                                                <br><a href=FollowMoreTopic.jsp> Follow more topic</a>  
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Following'}">
                                                <center><div class=boxHeading> FOLLOWING </div></center>
                                                    <sql:query dataSource="${dbsource}" var="Following">
                                                    select user.ID,user.firstname,user.imagepath from newuser user 
                                                    right join ak_follower_detail ak on ak.user_id=user.ID where followers_id = ?;
                                                    <sql:param value="${userid}"/>
                                                </sql:query>
                                                <c:forEach items="${Following.rows}" var="follow">
                                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                                        <img src="images/<c:out value="${follow.imagepath}"/>" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                        <a href="profile.jsp?user=<c:out value="${follow.firstname}"/>&ID=<c:out value="${follow.ID}"/>&sl=<%=sl%>"><c:out value="${follow.firstname}"/></a>
                                                    </div> 
                                                </c:forEach>
                                                <br><a href=UserProfile.jsp> FOLLOW MORE USER </a>
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Followers'}">
                                                <center><div class=boxHeading> FOLLOWERS </div></center>
                                                    <sql:query dataSource="${dbsource}" var="followers">
                                                    select user.ID,user.firstname,user.imagepath from newuser user 
                                                    right join ak_follower_detail ak on ak.followers_id=user.ID where user_id = ?;
                                                    <sql:param value="${userid}" />
                                                </sql:query>
                                                <c:forEach items="${followers.rows}" var="followers" >
                                                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                                        <img src="images/<c:out value="${followers.imagepath}"/>" alt="" style="width:100%; border:1px solid #ddd;margin-top:20px;">
                                                        <a href="profile.jsp?user=<c:out value="${followers.firstname}"/>&ID=<c:out value="${followers.ID}"/>"><c:out value="${followers.firstname}"/></a>
                                                    </div>
                                                </c:forEach>
                                                <br><a href=UserProfile.jsp> FOLLOW MORE USER </a>
                                            </c:when>
                                            <c:when test="${ParametrVariable eq 'Blog'}">
                                                <center><div class=boxHeading> BLOG </div></center>
                                                    <sql:query dataSource="${dbsource}" var="blog">
                                                    SELECT * FROM blog WHERE blog_posted_by = ?;
                                                    <sql:param value="${userid}"/>
                                                </sql:query>
                                                <c:forEach items="${blog.rows}" var="blog">
                                                    <br><a href="D_Blog.jsp?sub=<c:out value="${blog.blog_subject}"/>&Blog_Id=<c:out value="${blog.blog_id}"/>"><c:out value="${blog.blog_subject}"/></a>
                                                </c:forEach> 
                                                <br><a href=WriteBlogHere.jsp> BLOG ABOUT SOMETHING </a>
                                            </c:when>
                                        </c:choose>
                                        <div class="clear-fix"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row margintop10"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title"><%=POST_YOUR_QUESTION_HERE%></h4>
                        </div>
                        <%if (session.getAttribute(
                                    "email") != null) {%>
                        <form name="submitquestion" method="post" action="SubmitQuestion.jsp">
                            <div class="modal-body">
                                <div>
                                    <div><%=PUT_YOUR_QUESTION_HERE%></div>                                                        
                                    <textarea type="text" class="anstext" name="question" placeholder="<%=EX%>" required=""></textarea></div>
                                <div class="margintop20">
                                    <div><%=TAG_SUGGESTION_DESCRIPTION%> </div>
                                    <textarea type="text" class="anstext" name="tag_of_question" placeholder="<%=TAG_EXMAPLE%>" required=""></textarea></div>
                                <!-- <p>Some text in the modal.</p> -->
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn"><%=POST%></button>
                                <button type="button" class="btn btn-default" data-dismiss="modal"><%=CLOSE%></button>
                            </div>
                        </form>
                        <%} else {%>
                        <div class="modal-body">
                            <div>
                                <div><h4 style="color: red;"><%=PLEASE_LOGIN_FIRST%>!!!</h4></div>
                                <div><a href="Login.jsp?sl=<%=sl%>"><%=CLICK_HERE_TO_LOGIN%></a></div>
                            </div>
                        </div>
                        <div class="modal-footer">                                                    
                            <button type="button" class="btn btn-default" data-dismiss="modal"><%=CLOSE%></button>
                        </div>

                        <%}%>

                    </div>

                </div>
            </div>
            <%@include file="notificationhtml.jsp" %>
            <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>

            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>

            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>
        </div> <!-- /.main-page-wrapper -->
    </body>
</html>