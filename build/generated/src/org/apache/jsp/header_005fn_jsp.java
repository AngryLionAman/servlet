package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_005fn_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("<head>\n");
      out.write("\t<title>inquiryhere.com | header</title>\n");
      out.write("\t<meta charset=\"utf-8\">\n");
      out.write("   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("   <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n");
      out.write("   <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n");
      out.write("   <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n");
      out.write("   <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n");
      out.write("   <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.8.1/css/all.css\" integrity=\"sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf\" crossorigin=\"anonymous\">\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css\\header.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<header class=\"fixed-top\">\n");
      out.write("\t\t<div class=\"logo\">INQUIRYHERE.COM</div>\n");
      out.write("\t\t<nav>\n");
      out.write("\t\t\t<ul>\n");
      out.write("\t\t\t\t<li><div><input class=\"searchmobileinput\" type=\"text\" name=\"\" placeholder=\"Search...\"></div>\n");
      out.write("\t\t\t\t\t<div class=\"searchbar\">\n");
      out.write("          \t\t\t\t<input class=\"search_input\" type=\"text\" name=\"\" placeholder=\"Search...\">\n");
      out.write("          \t\t\t\t<a href=\"#\" class=\"search_icon\"><i class=\"fas fa-search\"></i></a>\n");
      out.write("   \t\t\t\t\t</div>\n");
      out.write("      \t\t\t</li>\n");
      out.write("\t\t\t\t<li><a href=\"index.php\">HOME</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"about.php\">ABOUT US</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"contact.php\">CONTACT US</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"signup.php\">SIGN UP</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"login.php\">LOG IN</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"#\">HINDI</a></li>\n");
      out.write("\t\t\t\t<li><a href=\"#\" style=\"display: none;\">USERNAME</a></li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t</nav>\n");
      out.write("\t\t<div class=\"menu-toggle\"><i class=\"fa fa-bars\" aria-hidden=\"true\"></i></div>\n");
      out.write("\t</header>\n");
      out.write("\t<script src=\"https://code.jquery.com/jquery-3.3.1.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\t$(document).ready(function(){\n");
      out.write("\t\t\t$('.menu-toggle').click(function(){\n");
      out.write("\t\t\t\t$('nav').toggleClass('active');\n");
      out.write("\t\t\t});\n");
      out.write("\t\t}); \n");
      out.write("\t</script>\n");
      out.write("</body>\n");
      out.write("</html> ");
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
