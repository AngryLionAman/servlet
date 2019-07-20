package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ContactUs_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


            String DESCRIPTION = "";
            String WELCOME_NOTES = "";
            String NEED_HELP = "";
            String HELP_US = "";

        
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/notificationhtml.jsp");
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

      out.write("<html lang=\"en\"><head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <!-- For IE -->\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("\n");
      out.write("        <!-- For Resposive Device -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("\n");
      out.write("        <title>Contect Us | InquiryHere.com</title>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <!-- responsive style sheet -->\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/responsive.css\">\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("        ");

            String sl = request.getParameter("sl");
            if (sl == null) {
                sl = "en";
            }
            if (sl.equalsIgnoreCase("hi")) {
                DESCRIPTION = "प्रिय उपयोगकर्ता, अगर आपके पास inquiryhere.com के बारे में कोई प्रश्न और सुझाव है। तो आप inquiry@inquiryhere.com पर एक मेल छोड़ सकते हैं या आप सीधे अनुभाग से नीचे पोस्ट कर सकते हैं। हम जल्द से जल्द आपकी मदद करेंगे।";
                WELCOME_NOTES = "inquiryhere.com में आपकी रुचि के लिए धन्यवाद";
                NEED_HELP = "अगर आपको कोई मदद चाहिए"; 
                HELP_US = "यदि आपके पास हमारे लिए कोई सुझाव है";
            
            } else {
                DESCRIPTION = "Dear user, If you have any query and suggestion about inquiryhere.com then you can drop a mail on inquiry@inquiryhere.com or you can directly post from below section.We will help you as soon as possible.";
                WELCOME_NOTES = "Thanks for your interest in Inquiryhere.com";
                 NEED_HELP = "If you need any help"; 
                HELP_US = "If you have any suggestion for us";
            }
        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <div class=\"main-page-wrapper\">\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("sl", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(sl), request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("            <div class=\"clear-fix\"></div>\n");
      out.write("            <div class=\"bodydata\">\n");
      out.write("                <div class=\"container clear-fix\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-lg-3 col-md-3 col-sm-12 col-xs-12\">\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\">\n");
      out.write("                            <div class=\"row\"><center>\n");
      out.write("                                    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n");
      out.write("                                        <div class=\"themeBox\" style=\"height:auto;\">\n");
      out.write("                                            <div class=\"boxHeading\">\n");
      out.write("                                                ");
      out.print(WELCOME_NOTES);
      out.write("\n");
      out.write("                                            </div>\n");
      out.write("                                            <div class=\"boxHeading\">\n");
      out.write("                                            ");
      out.print(DESCRIPTION);
      out.write("\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("\n");
      out.write("                                </center> </div>\n");
      out.write("                            <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n");
      out.write("                                <div class=\"themeBox\" style=\"height:auto;\">\n");
      out.write("                                    <center><div class=\"boxHeading\">\n");
      out.write("                                            ");
      out.print(NEED_HELP);
      out.write("\n");
      out.write("                                        </div></center>\n");
      out.write("                                    <form action=\"submit_help.jsp\">\n");
      out.write("                                        <div>Name: <input type=\"text\"  placeholder=\"Type your Name\" name=\"Name_h\" width=\"auto\" required=\"\"></div>\n");
      out.write("                                        <div>Email: <input type=\"email\"  placeholder=\"Type your Valid mail\" name=\"Email_h\" required=\"\"></div>\n");
      out.write("                                        <div><textarea type=\"text\" class=\"anstext\" placeholder=\"write your query here\" name=\"Q_h\" required=\"\"></textarea></div>\n");
      out.write("\n");
      out.write("                                        <div class=\"float-right margintop20\" style=\"vertical-align:bottom\">\n");
      out.write("                                            <button type=\"submit\" class=\"btn\" data-toggle=\"modal\" >ASK FOR HELP</button>\n");
      out.write("                                            <!-- btn-info btn-lg -->\n");
      out.write("                                        </div>\n");
      out.write("                                    </form>\n");
      out.write("                                    <div class=\"clear-fix\"></div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n");
      out.write("                                <div class=\"themeBox\" style=\"height:auto;\">\n");
      out.write("                                    <center><div class=\"boxHeading\">\n");
      out.write("                                            ");
      out.print(HELP_US);
      out.write("\n");
      out.write("                                        </div></center>\n");
      out.write("                                    <form action=\"submit_help.jsp\"> \n");
      out.write("                                        <div>Name: <input type=\"text\"  placeholder=\"Type your Name\" name=\"Name_s\" width=\"auto\" required=\"\"></div>\n");
      out.write("                                        <div>Email: <input type=\"email\"  placeholder=\"Type your Valid mail\" name=\"Email_s\" width=\"auto\" required=\"\"></div>\n");
      out.write("                                        <div><textarea type=\"text\" class=\"anstext\" placeholder=\"write your suggestion here\" name=\"S_s\" required=\"\"></textarea></div>\n");
      out.write("\n");
      out.write("                                        <div class=\"float-right margintop20\" style=\"vertical-align:bottom\">\n");
      out.write("                                            <button type=\"submit\" class=\"btn\" data-toggle=\"modal\" >SUGGEST US</button>\n");
      out.write("                                            <!-- btn-info btn-lg -->\n");
      out.write("                                        </div>\n");
      out.write("                                    </form>\n");
      out.write("                                    <div class=\"clear-fix\"></div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"clear-fix\"></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"clear-fix\"></div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"clear-fix\"></div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"clear-fix\"></div>\n");
      out.write("            ");
      out.write("<div class=\"modal fade\" id=\"myModalN\" role=\"dialog\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <!-- Modal content-->\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\n");
      out.write("                <h4 class=\"modal-title\">Your current notification</h4>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <div>\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "NodificationScript.jsp", out, false);
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\">                                                    \n");
      out.write("                <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">close</button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>");
      out.write("\n");
      out.write("             ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("sl", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(sl), request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/jquery-2.1.4.js\"></script>\n");
      out.write("            <!-- Bootstrap JS -->\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("            <!-- Bootstrap Select JS -->\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/bootstrap-select/dist/js/bootstrap-select.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <!-- Theme js -->\n");
      out.write("            <!-- <script type=\"text/javascript\" src=\"js/theme.js\"></script> -->\n");
      out.write("\n");
      out.write("        </div> <!-- /.main-page-wrapper -->\n");
      out.write("\n");
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
