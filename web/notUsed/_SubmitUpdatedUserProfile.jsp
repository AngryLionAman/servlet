<%@page import="com.profile.userProfile" %>
<%
    if (session.getAttribute("email") != null) {
        String fullname, email, HigherQualification, BestAchievement, bio;
        fullname = request.getParameter("fullname");
        email = request.getParameter("email");
        HigherQualification = request.getParameter("HigherQualification");
        BestAchievement = request.getParameter("BestAchievement");
        bio = request.getParameter("bio");
        if (fullname != null && email != null) {
           userProfile obj = new userProfile();
           Boolean value = obj.saveUserProfile(email, HigherQualification, BestAchievement, bio);
           if(value){
               response.sendRedirect("profile.jsp");
           }
        } else {
                out.println("It seem like you are logedin but trying to access page directly!!<br>Value can't be Null");
            }

        } else {
            out.println("You can't access this page directly<br>You have to loged in first");
        }

    %>

