package org.apache.jsp.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;

public final class user_005fedit_005fupdate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


String DB_URL_ = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
String DB_USERNAME_ = "root";
String DB_PASSWORD_ = null;
String DB_AJAX_PATH = "http://localhost:8084/inquiryhere";

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/Admin/site.jsp");
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Update the user</title>\n");
      out.write("    </head>\n");
      out.write("    <body><center>\n");
      out.write("        <h1>Edit the selected user!!</h1>\n");
      out.write("        ");

            String s_id = request.getParameter("id");
            int data = Integer.parseInt(request.getParameter("id"));
            int id = 0;
            String firstname = null;
            String lastname = "";
            String email = "";
            String higher_edu = "";
            String best_achievement = "";
            String bio = "";
            String password = "";
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL_, DB_USERNAME_, DB_PASSWORD_);
                String sql = "SELECT * FROM newuser where ID = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, s_id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    firstname = resultSet.getString("firstname");
                    lastname = resultSet.getString("lastname");
                    email = resultSet.getString("email");
                    higher_edu = resultSet.getString("higher_edu");
                    best_achievement = resultSet.getString("best_achievement");
                    bio = resultSet.getString("bio");
                    password = resultSet.getString("password");
                }

            } catch (Exception e) {
                out.println(e);
            }
        
      out.write("\n");
      out.write("        <form>\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <th>ID</th> <td><input type=\"text\" name=\"id\" value=\"");
      out.print(id);
      out.write("\" readonly=\"\" /></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Firstname</th> <td><input type=\"text\" name=\"firstname_edit\" value=\"");
      out.print(firstname);
      out.write("\" /></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Lastname</th> <td><input type=\"text\" name=\"lastname_edit\" value=\"");
      out.print(lastname);
      out.write("\" /></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Email</th> <td><input type=\"text\" name=\"email_edit\" value=\"");
      out.print(email);
      out.write("\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Password</th> <td><input type=\"text\" name=\"password_edit\" value=\"");
      out.print(password);
      out.write("\" /></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Higher Education</th> <td><input type=\"text\" name=\"h_edu_edit\" value=\"");
      out.print(higher_edu);
      out.write("\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Best achivment</th> <td> <textarea type=\"text\" name=\"b_ach_edit\" >");
      out.print(best_achievement);
      out.write("</textarea></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Bio</th> <td><input type=\"text\" name=\"bio_edit\" value=\"");
      out.print(bio);
      out.write("\"/></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <th></th> <td><input type=\"submit\" value=\"Update\" name=\"update\"/></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("        ");


            if (request.getParameter("update") != null) {
                try {
                    String firstname_edit = request.getParameter("firstname_edit");
                    String lastname_edit =  request.getParameter("lastname_edit");
                    String email_edit =  request.getParameter("email_edit");
                    String higher_edu_edit = request.getParameter("h_edu_edit");;
                    String best_achievement_edit =  request.getParameter("b_ach_edit");
                    String bio_edit =  request.getParameter("bio_edit");
                    String password_edit =  request.getParameter("password_edit");
                    String sql = "update newuser set "
                            + "firstname = '" + firstname_edit + "'"
                            + ",lastname = '" + lastname_edit + "'"
                            + ",email = '" + email_edit + "'"
                            + ",password = '" + password_edit + "'"
                            + ",higher_edu = '" + higher_edu_edit + "'"
                            + ",bio = '" + bio_edit + "'"
                            + ",best_achievement = '" + best_achievement_edit + "'"
                            + "where ID = '" + s_id + "'";
                    preparedStatement = connection.prepareStatement(sql);
                    /*preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, higher_edu);
                preparedStatement.setString(6, bio);
                preparedStatement.setString(7, best_achievement);
                //preparedStatement.setString(8, s_id);
                preparedStatement.setInt(8, data);*/

                    preparedStatement.executeUpdate();

                    out.println("sdone");
                    connection.close();
                    preparedStatement.close();
                    response.sendRedirect("TotalRegistredUser.jsp");
                } catch (Exception msg) {
                    out.println(msg);
                }
            } else {
                out.println("some thing happen");
            }


        
      out.write("\n");
      out.write("    </center> </body>\n");
      out.write("</html>\n");
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
