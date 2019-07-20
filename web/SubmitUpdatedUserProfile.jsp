<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%
    String sl = request.getParameter("sl");
    if (sl == null) {
        sl = "en";
    }
    if (session.getAttribute("email") != null) {
        String fullname, email, HigherQualification, BestAchievement, bio;
        fullname = request.getParameter("fullname");
        email = request.getParameter("email");
        HigherQualification = request.getParameter("HigherQualification");
        BestAchievement = request.getParameter("BestAchievement");
        bio = request.getParameter("bio");
        String UserEmail = (String) session.getAttribute("email");
        if (fullname != null && email != null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                if (connection == null || connection.isClosed()) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        out.println("Exception in loading the class forname Driver" + ex);
                    }
                    connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                }
                try {
                    String UpdateQuiry = "update newuser set higher_edu = ?,best_achievement = ? ,bio = ? where email = ?";
                    //String UpdateQuiry = "update newuser set higher_edu = '" + HigherQualification + "',best_achievement = '" + BestAchievement + "' ,bio = '" + bio + "' where email = '" + UserEmail + "'";
                    preparedStatement = connection.prepareStatement(UpdateQuiry);
                    preparedStatement.setString(1, HigherQualification);
                    preparedStatement.setString(2, BestAchievement);
                    preparedStatement.setString(3, bio);
                    preparedStatement.setString(4, UserEmail);
                    preparedStatement.executeUpdate();
                    

%>
<script>window.alert("Profile has been successfull updated");
    window.location = "<%=DB_AJAX_PATH%>/profile.jsp?sl=<%=sl%>";</script>
    <%
                    } catch (Exception e) {
                        out.println("Error : " + e);
                    }
                } catch (Exception e) {
                    out.println("Error in main try block:-" + e);
                } finally {

                    if (connection != null || !connection.isClosed()) {
                        try {
                            connection.close();
                        } catch (Exception e) {
                            out.println("Exception in closing connection " + e);
                        }
                    }
                    if (preparedStatement != null || !preparedStatement.isClosed()) {
                        try {
                            preparedStatement.close();
                        } catch (Exception e) {
                            out.println("Exception in closing preparedStatement " + e);
                        }
                    }
                }
            } else {
                out.println("It seem like you are logedin but trying to access page directly!!<br>Value can't be Null");
            }

        } else {
            out.println("You can't access this page directly<br>You have to loged in first");
        }

    %>

