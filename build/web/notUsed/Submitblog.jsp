<%@page language="java"%>
<%@page import="java.sql.*"%>
<%@include file="site.jsp" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    if (session.getAttribute("Session_id_of_user") != null) {
        try{
        String blog_sub = request.getParameter("blog_subject");
        String blog_description = request.getParameter("blog_description");
        int id_of_user = (Integer)session.getAttribute("Session_id_of_user");
        if (blog_sub != null && blog_description != null) {
            Connection connection = null;
            Statement statement = null;
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
                    statement = connection.createStatement();
                    String p = "insert into blog(blog_subject,blog,blog_posted_by) values('" + blog_sub + "','" + blog_description + "','" + id_of_user + "')";
                    statement.execute(p);
                    response.sendRedirect("profile.jsp?value=Blog");
                } catch (Exception e1) {
                    out.print("Error:-" + e1);
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
                if (statement != null || !statement.isClosed()) {
                    try {
                        statement.close();
                    } catch (Exception e) {
                        out.println("Exception in closing preparedStatement " + e);
                    }
                }
            }
        } else {
            out.println("It seem like you are logedin but trying to access this page diresctly");
        }
        }catch(Exception msg){
            out.println(msg);
        }
    } else {
        // session.setAttribute("URL", URL);
        //response.sendRedirect("Login.jsp?Error=Please Login");
        out.println("please login first");
    }

%>