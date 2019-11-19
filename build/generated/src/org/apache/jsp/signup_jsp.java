package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class signup_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    String DB_URL_ = "jdbc:mysql://localhost/bharat?useUnicode=true&characterEncoding=utf-8";
    String DB_USERNAME_ = "root";
    String DB_PASSWORD_ = null;
    String DB_AJAX_PATH = "http://localhost:8084/inquiryhere";

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/googleAnalytics.jsp");
    _jspx_dependants.add("/site.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
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

      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        ");
      out.write("<!-- This is Google Analytice code -->");
      out.write("\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("        ");

            if (session.getAttribute("Session_id_of_user") != null) {
                response.sendRedirect("index.jsp");
            }
        
      out.write("\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <!-- For IE -->\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("\n");
      out.write("        <!-- For Resposive Device -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("\n");
      out.write("        <title>SignUp - inquiryhere.com</title>\n");
      out.write("        <meta property=\"og:url\" content=\"https://www.inquiryhere.com/signup.jsp\">\n");
      out.write("        <meta property=\"og:site_name\" content=\"www.inquiryhere.com\" />\n");
      out.write("        <meta property=\"og:image\" content=\"https://www.inquiryhere.com/images/inquiryhere_Logo.PNG\" />\n");
      out.write("        <meta property=\"og:type\" content=\"website\">\n");
      out.write("        <meta property=\"og:title\" content=\"Create an account\" />\n");
      out.write("        <meta property=\"og:description\" content=\"Creata an account and get every thing at one place\"/>\n");
      out.write("        <meta property=\"og:locale\" content=\"en_US\">\n");
      out.write("        <link rel=\"icon\" href=\"https://www.inquiryhere.com/images/inquiryhere_Logo.PNG\" type=\"image/png\">\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <!-- responsive style sheet -->\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/responsive.css\">\n");
      out.write("        <style>\n");
      out.write("            input[type=text] {\n");
      out.write("                width: 100%;\n");
      out.write("                padding: 4px 8px;\n");
      out.write("                margin: 4px 0;\n");
      out.write("                box-sizing: border-box;\n");
      out.write("                border: 1px solid red;\n");
      out.write("                border-radius: 2px;\n");
      out.write("            }\n");
      out.write("            .button {\n");
      out.write("                background-color: #4CAF50; /* Green */\n");
      out.write("                border: none;\n");
      out.write("                color: white;\n");
      out.write("                padding: 15px 32px;\n");
      out.write("                text-align: center;\n");
      out.write("                text-decoration: none;\n");
      out.write("                display: inline-block;\n");
      out.write("                font-size: 16px;\n");
      out.write("                margin: 4px 2px;\n");
      out.write("                cursor: pointer;\n");
      out.write("            }\n");
      out.write("            .button1 {width: 250px;}\n");
      out.write("        </style>\n");
      out.write("        <script language=\"Javascript\" type=\"text/javascript\">\n");
      out.write("\n");
      out.write("            function onlyAlphabets(e, t) {\n");
      out.write("                try {\n");
      out.write("                    if (window.event) {\n");
      out.write("                        var charCode = window.event.keyCode;\n");
      out.write("                    } else if (e) {\n");
      out.write("                        var charCode = e.which;\n");
      out.write("                    } else {\n");
      out.write("                        return true;\n");
      out.write("                    }\n");
      out.write("                    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode === 32))\n");
      out.write("                        return true;\n");
      out.write("                    else\n");
      out.write("                        return false;\n");
      out.write("                } catch (err) {\n");
      out.write("                    alert(err.Description);\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("            function userNameValidation(e, t) {\n");
      out.write("                try {\n");
      out.write("                    if (window.event) {\n");
      out.write("                        var charCode = window.event.keyCode;\n");
      out.write("                    } else if (e) {\n");
      out.write("                        var charCode = e.which;\n");
      out.write("                    } else {\n");
      out.write("                        return true;\n");
      out.write("                    }\n");
      out.write("                    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode > 47 && charCode < 58))\n");
      out.write("                        return true;\n");
      out.write("                    else\n");
      out.write("                        return false;\n");
      out.write("                } catch (err) {\n");
      out.write("                    alert(err.Description);\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function take_value(email) {\n");
      out.write("                //alert(email.value);\n");
      out.write("                var http = new XMLHttpRequest();\n");
      out.write("                http.open(\"post\", \"");
      out.print(DB_AJAX_PATH);
      out.write("/validateUserName.jsp?userName=\" + email.value, true);\n");
      out.write("                http.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n");
      out.write("                http.send();\n");
      out.write("                http.onload = function () {\n");
      out.write("                    http.responseText;\n");
      out.write("                    document.getElementById(\"demo\").innerHTML = http.responseText;\n");
      out.write("                };\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"main-page-wrapper\">\n");
      out.write("            <header class=\"home-page\">\n");
      out.write("                <div class=\"container clear-fix\">\n");
      out.write("                    <div class=\"col-lg-3 col-md-3 col-sm-12 col-xs-12\" style=\"padding-left:0px;\">\n");
      out.write("                        <div class=\"logo float-left\">\n");
      out.write("                            <a href=\"index.jsp?ref=signup\" style=\"vertical-align:middle;\">\n");
      out.write("                                <h4>\n");
      out.write("                                    <div class=\"logotext\">\n");
      out.write("                                        inquiryhere.com\n");
      out.write("                                    </div>\n");
      out.write("\n");
      out.write("                                </h4>\n");
      out.write("                            </a>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 col-md-4 col-sm-12 col-xs-12 serachhere\" style=\"display:inline-block;\">\n");
      out.write("                        <div style=\"overflow: hidden; padding-right: .5em;\">\n");
      out.write("                            <form action=\"search.jsp\">\n");
      out.write("                                <input type=\"text\" style=\"width: 100%;\" name=\"q\" required=\"\" >\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"col-lg-2 col-md-2 col-sm-12 col-xs-12 float-right textalign-right\">\n");
      out.write("                        <a  href=\"index.jsp\" class=\"helpicon\" style=\"color: white; width: 50px;\">Home</a>\n");
      out.write("                        <a  href=\"login.jsp\" class=\"helpicon\" style=\"color: white; width: 50px;\">Login</a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </header>\n");
      out.write("            <div class=\"clear-fix\"></div>\n");
      out.write("            <div class=\"bodydata\">\n");
      out.write("                <div class=\"container clear-fix\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-lg-3 col-md-3 col-sm-12 col-xs-12\">\n");
      out.write("\n");
      out.write("                        </div> \n");
      out.write("                        <div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\">\n");
      out.write("                            ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                            <div class=\"themeBox\" style=\"height:470px;\">\n");
      out.write("                                ");

                                    String ErrorMsg = request.getParameter("Error");
                                    if (ErrorMsg != null) {
                                        out.println("<center><b style=color:red;>" + ErrorMsg + "</b></center>");
                                    }
                                
      out.write("\n");
      out.write("                                <div class=\"boxHeading\">\n");
      out.write("                                    <form action=\"createNewUser\" method=\"post\" name=\"newUser\">\n");
      out.write("                                        <label for=\"fname\">Full Name &#8628;</label> <a href=\"help.jsp#fullname\">&#10067;</a>                                                                  \n");
      out.write("                                        <input type=\"text\" id=\"fname\" name=\"firstname\" onkeypress=\"return onlyAlphabets(event, this);\" required=\"\">\n");
      out.write("                                        <label for=\"fname\">Email/Phone &#8628;</label><a href=\"help.jsp#emailorphone\">&#10067;</a>\n");
      out.write("                                        <div class=\"boxHeading\">\n");
      out.write("                                            <input type=\"text\"  name=\"email\" required=\"\">\n");
      out.write("                                        </div>\n");
      out.write("                                        <label for=\"lname\">Password &#8628;</label><a href=\"help.jsp#sign-up-password\">&#10067;</a>\n");
      out.write("                                        <div class=\"boxHeading\">\n");
      out.write("                                            <input type=\"password\"  name=\"password\" pattern=\".{6,}\" title=\"Six or more characters\" required=\"\">\n");
      out.write("                                        </div> \n");
      out.write("                                        <br>\n");
      out.write("                                        <center>\n");
      out.write("                                            <button type=\"submit\" class=\"button button1\" data-toggle=\"modal\"  >Create Account</button>\n");
      out.write("                                        </center>\n");
      out.write("                                    </form>\n");
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                                <center>\n");
      out.write("                                    <div>\n");
      out.write("                                        <form action=\"forgotpassword.jsp\" method=\"post\" name=\"forgetPassword\">\n");
      out.write("                                            <button class=\"button button1\" style=\"background-color: red;\">Forget Password</button>\n");
      out.write("                                        </form>\n");
      out.write("                                    </div>\n");
      out.write("                                </center>\n");
      out.write("                            </div> \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <script type=\"text/javascript\" src=\"vendor/jquery-2.1.4.js\"></script>\n");
      out.write("        <!-- Bootstrap JS -->\n");
      out.write("        <script type=\"text/javascript\" src=\"vendor/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("        <!-- Bootstrap Select JS -->\n");
      out.write("        <script type=\"text/javascript\" src=\"vendor/bootstrap-select/dist/js/bootstrap-select.js\"></script>\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message ne null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                <div class=\"themeBox\" style=\"font-family: serif;font-variant-position: super;font-size: 25px;text-align: center;background-color: green;color: white;\">\n");
        out.write("                                        ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\n");
        out.write("                                    </div>\n");
        out.write("                            ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }
}
