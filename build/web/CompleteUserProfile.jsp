<%@page import="com.connect.DatabaseConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@page import="java.sql.*"%>

<%
    if (session.getAttribute("Session_id_of_user") != null) {

        int SessionUserId = (Integer) session.getAttribute("Session_id_of_user");

        DatabaseConnection con = new DatabaseConnection();

        Connection connection = DatabaseConnection.makeConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {

            String higher_edu = null;
            String best_achievement = null;
            String ImagePath = null;
            String bio = null;

            boolean b_higher_edu = true;
            boolean b_best_achievement = true;
            boolean b_bio = true;

            int userId = 0;
            int TotalQuestionCount = 0;
            int TotalAnswerCount = 0;

            try {

                String v = "SELECT * FROM newuser WHERE id = ?";
                preparedStatement = connection.prepareStatement(v);
                preparedStatement.setInt(1, SessionUserId);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    userId = resultSet.getInt("ID");
                    higher_edu = resultSet.getString("higher_edu");
                    best_achievement = resultSet.getString("best_achievement");
                    bio = resultSet.getString("bio");
                    ImagePath = resultSet.getString("imagepath");
                }

                if (higher_edu == null || higher_edu.length() <= 0) {
                    out.println("<br><a href=UpdateUserProfile.jsp>Insert your higher_edu</a>");
                    b_higher_edu = false;
                }

                if (best_achievement == null || best_achievement.length() <= 0) {
                    out.println("<br><a href=UpdateUserProfile.jsp>Insert your best_achievement</a>");
                    b_best_achievement = false;
                }

                if (bio == null || bio.length() <= 0) {
                    out.println("<br><a href=UpdateUserProfile.jsp>Insert your bio</a>");
                    b_bio = false;
                }

                if (ImagePath == null) {
                    out.println("<br><a href=UpdateUserProfile.jsp>Update your image profile</a>");
                }

            } catch (Exception e) {
                out.println("Error" + e);
            }

            try {// If user not posted any question

                String sql = "SELECT COUNT(*) FROM question WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, userId);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    TotalQuestionCount = resultSet.getInt("COUNT(*)");
                }

                if (TotalQuestionCount == 0) {
                    out.println("<br><a href=>Post at least one question</a>");
                }

            } catch (Exception error) {
                out.println(error);
            }

            try {// If user not given any answer

                String sql = "SELECT COUNT(*) FROM answer WHERE answer_by_id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, userId);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    TotalAnswerCount = resultSet.getInt("COUNT(*)");
                }

                if (TotalAnswerCount == 0) {
                    out.println("<br><a href=>Give at least one answer of any question</a>");
                }

            } catch (Exception error) {
                out.println(error);
            }

            if (b_higher_edu && b_best_achievement && b_bio && ImagePath != null && TotalAnswerCount != 0 && TotalQuestionCount != 0) {
                out.println("<br><a href=UpdateUserProfile.jsp>Your profile is up to date</a>");
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

            if (resultSet != null || !resultSet.isClosed()) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    out.println("Exception in closing resulatset " + e);
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
        response.sendRedirect("Login.jsp");
    }
%>
