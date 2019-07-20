<html lang="en">
    <%@include file="site.jsp" %>
    <%@page language="java" %>
    <%@page import="java.sql.*" %> 
    <%  if (session.getAttribute("email") != null) { %>
    <head>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta charset="UTF-8">
        <%!            String EMAIL = "";
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
            String UPDATE_YOUR_PROFILE_IMAGE = "";
            String CLICK_HERE_TO_UPDATE = "";
            String FULL_NAME = "";
            String NOT_EDITABLE = "";
            String HIGER_QUALIFICATION = "";
            String BEST_ACHIEVEMENT = "";
            String BIO = "";
            String UPDATE_PROFILE = "";
            String HIGHER_QUALIFICATION_PLACEHOLDER = "";
            String BEST_ACHIEVEMENT_PLACEHOLDER = "";
            String BIO_PLACEHOLDER = "";
        %>

        <%
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                EMAIL = "ईमेल";
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
                UPDATE_YOUR_PROFILE_IMAGE = "अपनी प्रोफ़ाइल छवि को अपडेट करें";
                CLICK_HERE_TO_UPDATE = "ये सुविधा अभी चालू नहीं हैं";
                FULL_NAME = "पूरा नाम";
                NOT_EDITABLE = "बदल नहीं सकते";
                HIGER_QUALIFICATION = "उच्च योग्यता";
                BEST_ACHIEVEMENT = "सबसे अच्छी उपलब्धि";
                BIO = "अपने बारे में";
                UPDATE_PROFILE = "प्रोफ़ाइल अपडेट करें";
                HIGHER_QUALIFICATION_PLACEHOLDER = "अपनी उच्च योग्यता डालें, Ex: B.Tech, BSC, BA";
                BEST_ACHIEVEMENT_PLACEHOLDER = "अपनी सर्वश्रेष्ठ उपलब्धि डालें";
                BIO_PLACEHOLDER = "अपने बारे में कुछ लिखो";
            } else {
                EMAIL = "Email";
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
                UPDATE_YOUR_PROFILE_IMAGE = "Update your profile image";
                CLICK_HERE_TO_UPDATE = "These features are not currently operational";
                FULL_NAME = "Full Name";
                NOT_EDITABLE = "Not Editable";
                HIGER_QUALIFICATION = "Higher Qualification";
                BEST_ACHIEVEMENT = "Best Achievement";
                BIO = "Bio";
                UPDATE_PROFILE = "Update Profile";
                HIGHER_QUALIFICATION_PLACEHOLDER = "Insert Your Higher Qualification, Ex: B.Tech,BSC,BA";
                BEST_ACHIEVEMENT_PLACEHOLDER = "Insert Your Best Achievement";
                BIO_PLACEHOLDER = "Write something about Yourself";
            }
        %>
        <!-- For IE -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- For Resposive Device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Update Profile | InquiryHere.com</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <!-- responsive style sheet -->
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            input[type=text] {
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid red;
                border-radius: 2px;
            }
            textarea[type=text]{
                width: 100%;
                padding: 4px 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid red;
                border-radius: 2px;
            }
            .button {
                background-color: #4CAF50; /* Green */
                border: 1px solid red;
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
        <div class="main-page-wrapper">
            <jsp:include page="header.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <div class="clear-fix"></div>
            <div class="bodydata">
                <div class="container clear-fix">
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">

                        </div>
                        <%
                            String name = null;
                            String Storedemail = null;
                            String bio = null;
                            String BestAchievement = null;
                            String higher_colification = null;
                            String ImagePath = null;
                            //int id_of_user = 0;
                            //int topic_id = 0;
                            String email = (String) session.getAttribute("email");
                            Statement stmt;
                            Connection con;
                            ResultSet rs;
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                                stmt = con.createStatement();
                                String p = "SELECT * FROM newuser WHERE email = '" + email + "'";
                                rs = stmt.executeQuery(p);
                                while (rs.next()) {
                                   // id_of_user = rs.getInt("id");
                                    name = rs.getString("firstname");
                                    Storedemail = rs.getString("email");
                                    higher_colification = rs.getString("higher_edu");
                                    bio = rs.getString("bio");
                                    BestAchievement = rs.getString("best_achievement");
                                    ImagePath = rs.getString("imagepath");
                                }
                                rs.close();
                                stmt.close();
                                con.close();
                            } catch (Exception e) {
                                out.println("Unable to retrieve!!" + e);
                            }
                        %>
                        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">                                                   
                            <div class="row"><center>                                                           
                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="themeBox" style="height:auto;">

                                            <label for="fname"><%=UPDATE_YOUR_PROFILE_IMAGE%></label>
                                            <div class="boxHeading">
                                                <div>
                                                    <img src="images/<%=ImagePath%>" alt="Update your profile" style="width:20%; margin:10px 0px 0px; border:1px solid #ddd;">
                                                </div>
                                                <div>
                                                    <form action="imageUpload" method="post" enctype="multipart/form-data">
                                                        <input type="file" name="photo" required="">
                                                        <input type="submit" onclick="return false" value="<%=CLICK_HERE_TO_UPDATE%>">
                                                    </form>
                                                </div>
                                            </div>
                                            <form action="SubmitUpdatedUserProfile.jsp" method="post" >
                                                <input type="hidden" name="sl" value="<%=sl%>">
                                                <label for="fname"><%=FULL_NAME%> (<%=NOT_EDITABLE%>)</label>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" name="fullname" value="<%=name%>" readonly="" required="">
                                                </div>
                                                <label for="fname"><%=EMAIL%> (<%=NOT_EDITABLE%>)</label>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" name="email" value="<%=Storedemail%>" readonly="" required="">
                                                </div>
                                                <label for="fname"><%=HIGER_QUALIFICATION%></label>
                                                <% if (higher_colification != null) {%>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" name="HigherQualification" value="<%=higher_colification%>" >
                                                </div><% } else {%>
                                                <div class="boxHeading">
                                                    <input type="text" id="fname" name="HigherQualification" placeholder="<%=HIGHER_QUALIFICATION_PLACEHOLDER%>" >
                                                </div><% }%>
                                                <label for="fname"><%=BEST_ACHIEVEMENT%></label>
                                                <% if (BestAchievement != null) {%>
                                                <div class="boxHeading">
                                                    <div class="boxHeading">
                                                        <input type="text" id="fname" name="BestAchievement" value="<%=BestAchievement%>" >
                                                    </div><% } else {%>
                                                    <div class="boxHeading">
                                                        <input type="text" id="fname" name="BestAchievement" placeholder="<%=BEST_ACHIEVEMENT_PLACEHOLDER%>" >
                                                    </div><% }%>
                                                    <label for="fname"><%=BIO%></label>
                                                    <% if (bio != null) {%>
                                                    <div class="boxHeading">
                                                        <textarea type="text" class="anstext" name="bio"  ><%=bio%></textarea>
                                                    </div><% } else {%>
                                                    <div class="boxHeading">
                                                        <input type="text" id="fname" name="bio" placeholder="<%=BIO_PLACEHOLDER%>" >
                                                    </div><% }%>
                                                    <div class="float-right margintop20" style="vertical-align:bottom">
                                                        <button type="submit" class="btn" data-toggle="modal" ><%=UPDATE_PROFILE%></button>
                                                    </div>

                                            </form>


                                        </div>
                                    </div>

                                </center> </div>


                        </div>

                        <div class="clear-fix"></div>
                    </div>
                    <div class="clear-fix"></div>
                </div>
                <div class="clear-fix"></div>
            </div>
            <div class="clear-fix"></div>
           <%@include file="notificationhtml.jsp" %>
            <jsp:include page="footer.jsp">
                <jsp:param name="sl" value="<%=sl%>"/>
            </jsp:include>
            <script type="text/javascript" src="vendor/jquery-2.1.4.js"></script>
            <!-- Bootstrap JS -->
            <script type="text/javascript" src="vendor/bootstrap/bootstrap.min.js"></script>
            <!-- Bootstrap Select JS -->
            <script type="text/javascript" src="vendor/bootstrap-select/dist/js/bootstrap-select.js"></script>

        </div> 
    </body>
    <%
        } else
            out.println("You can't access this page directly");
    %>
</html>