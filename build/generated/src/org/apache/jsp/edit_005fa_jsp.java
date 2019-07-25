package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class edit_005fa_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    String DB_URL_ = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    String DB_USERNAME_ = "root";
    String DB_PASSWORD_ = null;
    String DB_AJAX_PATH = "http://localhost:8080/inquiryhere";

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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        \n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta http-equiv=\"content-type\" content=\"text/html\" charset=\"utf-8\">\n");
      out.write("        ");

        if(session.getAttribute("Session_id_of_user")== null){
            response.sendRedirect("Login.jsp");
        }
        
      out.write("\n");
      out.write("\n");
      out.write("      \n");
      out.write("        ");
  //need to validate here
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

        
      out.write("\n");
      out.write("        <script src=\"ckeditor/ckeditor.js\"></script>\n");
      out.write("        <script async src=\"https://www.googletagmanager.com/gtag/js?id=UA-128307055-1\"></script>\n");
      out.write("        <script>\n");
      out.write("            window.dataLayer = window.dataLayer || [];\n");
      out.write("            function gtag() {\n");
      out.write("                dataLayer.push(arguments);\n");
      out.write("            }\n");
      out.write("            gtag('js', new Date());\n");
      out.write("            gtag('config', 'UA-128307055-1');\n");
      out.write("        </script> \n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <!-- For IE -->\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("\n");
      out.write("        <!-- For Resposive Device -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <!-- responsive style sheet -->\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/responsive.css\">\n");
      out.write("        \n");
      out.write("         \n");
      out.write("        ");
      out.write("\n");
      out.write("<sql:setDataSource var=\"dbsource\" \n");
      out.write("                   driver=\"com.mysql.jdbc.Driver\"                           \n");
      out.write("                   url=\"jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8\"\n");
      out.write("                   user=\"root\"  \n");
      out.write("                   password=\"\"/>");
      out.write("\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <div class=\"main-page-wrapper\">\n");
      out.write("            <!-- Header _________________________________ -->\n");
      out.write("            ");
      out.write("\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("            <div class=\"clear-fix\"></div>\n");
      out.write("            <div class=\"bodydata\">\n");
      out.write("                <div class=\"container clear-fix\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-lg-3 col-md-3 col-sm-12 col-xs-12\">\n");
      out.write("\n");
      out.write("\n");
      out.write("                            <div class=\"clear-fix\"></div>\n");
      out.write("                            <div class=\"clear-fix\"></div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\">\n");
      out.write("\n");
      out.write("                            <div class=\"row\">\n");
      out.write("                                ");

                                    Connection connection = null;
                                    ResultSet resultSet = null;
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
                                
      out.write("\n");
      out.write("\n");
      out.write("                                <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n");
      out.write("\n");
      out.write("                                    <div class=\"themeBox\" style=\"height:auto;\">\n");
      out.write("\n");
      out.write("                                        <div class=\"boxHeading marginbot10\">\n");
      out.write("                                            ");

                                                String storedQuestion = "";
                                                int question_id = Integer.valueOf(request.getParameter("q_id"));
                                                int ans_id = Integer.valueOf(request.getParameter("ans_id"));
                                                int ans_by_id = Integer.valueOf(request.getParameter("ans_by_id"));
                                                //out.println(question_id+ " "+ans_id+" "+ans_by_id);
                                                String sql = "select question from question where q_id =?";
                                                preparedStatement = connection.prepareStatement(sql);
                                                preparedStatement.setInt(1, question_id);
                                                resultSet = preparedStatement.executeQuery();
                                                while(resultSet.next()){
                                                    storedQuestion = resultSet.getString("question");
                                                }
                                            
      out.write("\n");
      out.write("\n");
      out.write("                                            Ques : ");
      out.print(storedQuestion);
      out.write(" ?\n");
      out.write("                                        </div>\n");
      out.write("                                        ");

                                            String storedAnswer = "";
                                            try {
                                                String sqlA = "select answer from answer where a_id =?";
                                               preparedStatement = connection.prepareStatement(sqlA);
                                               preparedStatement.setInt(1, ans_id);
                                               resultSet = preparedStatement.executeQuery();
                                               while(resultSet.next()){
                                                   storedAnswer = resultSet.getString("answer");
                                               }

                                            } catch (Exception e) {
                                                out.println("Unable to retrieve!!" + e);
                                            }
                                        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"boxHeading marginbot10\">Answer:</div>\n");
      out.write("                                    <form name=\"submitquestion\" method=\"post\" action=\"update_a.jsp\">\n");
      out.write("                                        <input type=\"hidden\" name=\"answer_id\" value=\"");
      out.print(ans_id);
      out.write("\">\n");
      out.write("                                        <input type=\"hidden\" name=\"question_id\" value=\"");
      out.print(question_id);
      out.write("\">\n");
      out.write("                                        <textarea class=\"ckeditor\" name=\"answer\" required=\"\" >");
      out.print(storedAnswer);
      out.write("</textarea>\n");
      out.write("                                        <input type=\"submit\" name=\"Post\" value=\"Submit\"> \n");
      out.write("                                    </form>\n");
      out.write("\n");
      out.write("                                    <div class=\"clear-fix\"></div>\n");
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"clear-fix\"></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"clear-fix\"></div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"clear-fix\"></div>\n");
      out.write("            </div>\n");
      out.write("            ");

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
            
      out.write("\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/jquery-2.1.4.js\"></script>\n");
      out.write("            <!-- Bootstrap JS -->\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("            <!-- Bootstrap Select JS -->\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/bootstrap-select/dist/js/bootstrap-select.js\"></script>\n");
      out.write("        </div> <!-- /.main-page-wrapper -->\n");
      out.write("\n");
      out.write("    </body></html>");
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
