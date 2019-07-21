package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class terms_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Terms</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css\\terms.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.8.1/css/all.css\" integrity=\"sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf\" crossorigin=\"anonymous\">\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container-fluid\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <img src=\"image\\terms-conditions-banner.jpg\" class=\"img-fluid\">\n");
      out.write("            </div><br>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-xl-11 mx-auto text-center\">\n");
      out.write("                    <div class=\"section-title mb-100\">\n");
      out.write("                        <h4>Terms</h4>\n");
      out.write("                        <p>inquiryhere.com takes your privacy seriously. We do not sell or redistribute any personal information. The inquiryhre.com website do not collect, store information or activiry about individual users.</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div><br>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-xl-11 mx-auto\">\n");
      out.write("                    <div class=\"section-title mb-100\">\n");
      out.write("                        <h5>Website Content</h5>&nbsp;&nbsp;<i class=\"fas fa-arrow-circle-right\"></i>\n");
      out.write("                        <p>All content found on inquiryhere.com is property of site community.Information on this website is provided as a public reference, without any warranties.Each blog, question and answer is not varified by site community.whatever content found on inquiryherre.com is proviede by user and managed by administrator.Our community is not responcible for incorrect information.please contect us with any possible error.</p><br>\n");
      out.write("                        <h5>Copyright Information</h5>&nbsp;&nbsp;<i class=\"fas fa-arrow-circle-right\"></i>\n");
      out.write("                        <p>All definitions found on this website are provided by user, we consider as original content.Additionally, all webpage text, HTML code, web scripts, database information, images, and page design is original content and is protected by international copyright law. You may use information from the inquiryhere.com website for personal use.</p><br>\n");
      out.write("                        <h5 class=\"text-left\">Usage Policy</h5>&nbsp;&nbsp;<i class=\"fas fa-arrow-circle-right\"></i>\n");
      out.write("                        <p>You may use this website for the intended purpose of learning sharing information. You may not use this website for any other purpose</p>\n");
      out.write("                        <p>If you have questions regarding this privacy policy, you may <a href=\"contact.php\">Contact</a> inquiryhere.com.</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
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
