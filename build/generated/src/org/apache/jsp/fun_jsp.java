package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class fun_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    String DB_URL_ = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    String DB_USERNAME_ = "root";
    String DB_PASSWORD_ = null;
    String DB_AJAX_PATH = "http://localhost:8080/inquiryhere";


    public String firstName(String str) {
        try {
            if (str != null && str.length() > 0 && !(str.trim()).equals("null") && !str.equals("") && !str.equals(" ")) {

                String[] final_word = str.split("\\s");
                return final_word[0].substring(0, 1).toUpperCase() + final_word[0].substring(1).toLowerCase();
            } else {
                return null;
            }

        } catch (Exception msg) {
            return msg.toString();
        }
    }


    public String convertStringUpperToLower(String sentence) {
        String finalSentenct = "";
        try {

            //If sentence having the multiple space
            //To remive the middle sentence white space if having
            //This can also remove the pre word and post word white spaces but
            // removies the last char of the sentence
            sentence = sentence.trim();
            char[] c = sentence.toCharArray();
            String str1 = "";
            //If you don't use the '=' (equals) then will missed the last char
            for (int i = 0; i < sentence.length(); i++) {
                if ((c[i] == ' ' && c[i + 1] != ' ') || (c[i] != ' ')) {
                    str1 += c[i];
                }
            }
            //Splitin the sentence into words
            String[] word = str1.split(" ");
            //Captlizing the every word
            for (int i = 0; i < word.length; i++) {
                word[i] = word[i].substring(0, 1).toUpperCase() + word[i].substring(1).toLowerCase();
                finalSentenct += word[i] + " ";
            }

//Remove the last white space if having
//in my case , last char is defenetaly has a white space,just look at the two line up
            finalSentenct = finalSentenct.trim();

//end of the script
        } catch (Exception msg) {
            finalSentenct = msg.toString();
        }
        return finalSentenct;
    }

    public int nullIntconvert(String str) {
        int num = 0;
        if (str == null) {
            str = "0";
        } else if ((str.trim()).equals("null")) {
            str = "0";
        } else if (str.equals("")) {
            str = "0";
        }
        try {
            num = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return num;
    }

    public String nullStringconvert(String category) {
        String cat = "All";

        if (category == null) {
            category = "All";
        } else if ((category.trim()).equals("null")) {
            category = "All";
        } else if (category.equals("")) {
            category = "All";
        }
        cat = category;
        return cat;
    }

            String WELCOME_NOTES = "";
        
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/site.jsp");
    _jspx_dependants.add("/validator.jsp");
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
      out.write("        <script src=\"https://code.jquery.com/jquery-1.12.4.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                var maxLength = 100;\n");
      out.write("                $(\".show-read-more\").each(function () {\n");
      out.write("                    var myStr = $(this).text();\n");
      out.write("                    if ($.trim(myStr).length > maxLength) {\n");
      out.write("                        var newStr = myStr.substring(0, maxLength);\n");
      out.write("                        var removedStr = myStr.substring(maxLength, $.trim(myStr).length);\n");
      out.write("                        $(this).empty().html(newStr);\n");
      out.write("                        $(this).append(' <a href=\"javascript:void(0);\" class=\"read-more\">read more...</a>');\n");
      out.write("                        $(this).append('<span class=\"more-text\">' + removedStr + '</span>');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("                $(\".read-more\").click(function () {\n");
      out.write("                    $(this).siblings(\".more-text\").contents().unwrap();\n");
      out.write("                    $(this).remove();\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("            .show-read-more .more-text{\n");
      out.write("                display: none;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <!-- For IE -->\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("\n");
      out.write("        <!-- For Resposive Device -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("\n");
      out.write("        <title>Poem List | InquiryHere.com</title>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <!-- responsive style sheet -->\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/responsive.css\">\n");
      out.write("        \n");
      out.write("        <meta property=\"og:description\" content=\"Read poem in hindi and english\" />\n");
      out.write("        <meta property=\"og:image\" content=\"https://www.inquiryhere.com/images/logo/inquiryhere_Logo.PNG\" />\n");
      out.write("        <meta property=\"og:type\" content=\"website\">\n");
      out.write("        <meta property=\"og:locale\" content=\"en_US\">\n");
      out.write("        <meta property=\"og:title\" content=\"Read poem in hindi and english\" />\n");
      out.write("        <meta property=\"og:url\" content=\"https://www.inquiryhere.com/\">\n");
      out.write("        <meta property=\"og:site_name\" content=\"https://www.inquiryhere.com/\" />\n");
      out.write("         \n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("        ");

            //String page_name = request.getParameter("page");
            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                WELCOME_NOTES = "inquiryhere.com में आपकी रुचि के लिए धन्यवाद";
            } else {
                WELCOME_NOTES = "Thanks for your interest in Inquiryhere.com";
            }
        
      out.write("\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <div class=\"main-page-wrapper\">\n");
      out.write("            <!-- Header _________________________________ -->\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("            ");

                Connection connection = null;
                ResultSet resultSet = null;
                ResultSet resultSet1 = null;
                PreparedStatement preparedStatement = null;
                PreparedStatement preparedStatement1 = null;
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
      out.write("            <div class=\"clear-fix\"></div>\n");
      out.write("            <div class=\"bodydata\">\n");
      out.write("                <div class=\"container clear-fix\">\n");
      out.write("                    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n");
      out.write("                        ");

                            String get_page_title = "select distinct page from everything";

                            preparedStatement = connection.prepareStatement(get_page_title);

                            resultSet = preparedStatement.executeQuery();

                            while (resultSet.next()) {

                                String pageName = resultSet.getString("page");
                                out.println("<br>--------------------------<br>|&nbsp;&nbsp;" + pageName + "&nbsp;&nbsp;|<br>-----------------------------<br>");
                                try { //to get the top 10 result

                                    String sql_by_page_name = "select * from everything where page = ? limit 10";

                                    preparedStatement1 = connection.prepareStatement(sql_by_page_name);

                                    preparedStatement1.setString(1, pageName);

                                    resultSet1 = preparedStatement1.executeQuery();
                        
      out.write("<div class=\"themeBox\" style=\"height:auto;\">");

                            while (resultSet1.next()) {
                                if (resultSet1.getString("subject") != null && resultSet1.getString("subject").length() > 0) {
                                    //out.println("<br>subject -->" + resultSet1.getString("subject"));
                            
      out.write("\n");
      out.write("                            <div class=\"boxHeading marginbot10\">\n");
      out.write("                                @");
      out.print(resultSet1.getString("subject"));
      out.write("\n");
      out.write("                            </div>       \n");
      out.write("                            ");

                                    }   
      out.write("<div class=\"questionArea\">");

                                    if (resultSet1.getString("description") != null && resultSet1.getString("description").length() > 0) {
                                        //out.println("<br>description -->" + resultSet1.getString("description"));
                                        
      out.write("<p class=\"show-read-more\">");
      out.print(resultSet1.getString("description"));
      out.write("</p>");

                                                                    }
                                                                    if (resultSet1.getString("author") != null && resultSet1.getString("author").length() > 0) {
                                                                        //out.println("<br>author -->" + resultSet1.getString("author"));

      out.write("<div class=\"postedBy\">Posted By : ");
      out.print(resultSet1.getString("author"));
      out.write("</div>");

                                        }
                                
      out.write("       </div>\n");
      out.write("                                ");

                                        //                                    if (resultSet1.getString("category") != null && resultSet1.getString("category").length() > 0) {
                                        //
                                        //                                        out.println("<br>Category -->" + resultSet1.getString("category"));
                                        //
                                        //                                    }
                                        //
                                        //                                    if (resultSet1.getString("based_on") != null && resultSet1.getString("based_on").length() > 0) {
                                        //
                                        //                                        out.println("<br>based on -->" + resultSet1.getString("based_on"));
                                        //
                                        //                                    }
                                        //                                    out.println(resultSet1.getString("page"));
                                        //
                                        //                                    out.println(resultSet1.getString("description"));
                                        out.println("<br>");
                                    }
                                
      out.write("</div>");

                                        } catch (Exception msg) {
                                            out.println("<br>error in geting the 10 result" + msg);
                                        }
                                    }
                            
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <div class=\"clear-fix\"></div>\n");
      out.write("                    ");

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
                            /*if (resultSet != null || !resultSet.isClosed()) {
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
                            }*/
                        }
                    
      out.write("\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("                    <script type=\"text/javascript\" src=\"vendor/jquery-2.1.4.js\"></script>\n");
      out.write("                    <!-- Bootstrap JS -->\n");
      out.write("                    <script type=\"text/javascript\" src=\"vendor/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("                    <!-- Bootstrap Select JS -->\n");
      out.write("                    <script type=\"text/javascript\" src=\"vendor/bootstrap-select/dist/js/bootstrap-select.js\"></script>\n");
      out.write("                </div> \n");
      out.write("            </div>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
