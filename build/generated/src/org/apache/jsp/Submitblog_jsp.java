package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class Submitblog_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    String DB_URL_ = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    String DB_USERNAME_ = "root";
    String DB_PASSWORD_ = null;
    String DB_AJAX_PATH = "http://localhost:8084/inquiryhere";

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/site.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      out.write('\n');

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


    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
