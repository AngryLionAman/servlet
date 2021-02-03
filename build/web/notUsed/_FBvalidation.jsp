<%-- 
    Document   : FBvalidation
    Created on : 5 May, 2019, 12:05:10 PM
    Author     : AngryLion
--%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%
    out.println(request.getParameter("fullname")+"<br>");
    out.println(request.getParameter("email"));
    if (request.getParameter("fullname") != null && request.getParameter("email") != null) {
        String name = request.getParameter("fullname").replaceAll("_", " ");
        String email = request.getParameter("email");
//        out.println("<br>" + name);
//        out.println("<br>" + email);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = null;
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = null;
            connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
            String sql = "select firstname,id,email from newuser where email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            boolean userFound = false;
            int Session_id_of_user = 0;

            while (resultSet.next()) {
                userFound = true;
                Session_id_of_user = resultSet.getInt("id");
            }
            if (userFound) {
                session.setAttribute("email", email);
                session.setAttribute("Session_id_of_user", Session_id_of_user);
                response.sendRedirect("index.jsp?a_type=old");
            } else {
                String sql_insert = "insert into newuser (username,firstname,email,password,email_s,imagepath)values(?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql_insert);
                preparedStatement.setString(1, name.replaceAll(" ", ""));
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, "userFromFacebook");
                preparedStatement.setInt(5, 0);
                preparedStatement.setString(6, "inquiryhere_Logo.PNG");
                boolean success = preparedStatement.execute();
                if (success) {
                    out.println("found some error");
                } else {
                    String sql_again = "select firstname,id,email from newuser where email = ?";
                    preparedStatement = connection.prepareStatement(sql_again);
                    preparedStatement.setString(1, email);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Session_id_of_user = resultSet.getInt("id");
                    }
                    session.setAttribute("email", email);
                    session.setAttribute("Session_id_of_user", Session_id_of_user);
                   
                    response.sendRedirect("index.jsp?a_type=new");
                }
            }
            out.println("Work done");
        } catch (Exception msg) {
            out.println(msg);
        }
    } else {
        out.println("You can't access this page directly............");
    }


%>