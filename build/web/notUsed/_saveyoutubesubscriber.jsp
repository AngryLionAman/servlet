<%@page import="java.sql.*" %> 
<%@include file="site.jsp" %>
<%
    if (request.getParameter("whatsapp_number") != null && request.getParameter("video_line") != null && request.getParameter("whatsapp_number") != null) {
        String YoutubeChannelLink = request.getParameter("channel_link");
        String YouTubeLatestVideoLink = request.getParameter("video_line");
        String WhatsAppNumberd = request.getParameter("whatsapp_number");
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
            //Start writing the main coad
            String sql = "INSERT INTO youtube(channel_link,latest_video_link,whatsapp_number) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, YoutubeChannelLink);
            preparedStatement.setString(2, YouTubeLatestVideoLink);
            preparedStatement.setString(3, WhatsAppNumberd);
            Boolean statue = preparedStatement.execute();
            if (!statue) {
                out.println("Value has been inserted");
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

   response.sendRedirect("getyoutubesubscriber.jsp");
    } else {
        out.println("You can't access this page directly<br>Please follow the procedure");
        out.println("<br>Please Visit <a href='getyoutubesubscriber.jsp'>Main</a> page");
    }


%>