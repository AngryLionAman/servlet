package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class optional_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/googleAnalytics.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
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

      out.write('\n');
      com.optionalQuestion.supportingFunction opt_fun = null;
      synchronized (_jspx_page_context) {
        opt_fun = (com.optionalQuestion.supportingFunction) _jspx_page_context.getAttribute("opt_fun", PageContext.PAGE_SCOPE);
        if (opt_fun == null){
          opt_fun = new com.optionalQuestion.supportingFunction();
          _jspx_page_context.setAttribute("opt_fun", opt_fun, PageContext.PAGE_SCOPE);
        }
      }
      out.write('\n');
      com.string.name word = null;
      synchronized (_jspx_page_context) {
        word = (com.string.name) _jspx_page_context.getAttribute("word", PageContext.PAGE_SCOPE);
        if (word == null){
          word = new com.string.name();
          _jspx_page_context.setAttribute("word", word, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        ");
      out.write("<!-- This is Google Analytice code -->");
      out.write("\n");
      out.write("                \n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!-- For IE -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!-- For Resposive Device -->\n");
      out.write("        <title>Upload Objective Question</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">        \n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/responsive.css\"><!-- responsive style sheet -->\n");
      out.write("        <script src=\"ckeditor/ckeditor.js\"></script>\n");
      out.write("        <meta property=\"og:description\" content=\"इंडिया का सबसे पहले मज़े वाला वेबसाइट जहाँ पर आप हिंदी में शयारी ,जोक, कहानी,कबिता, कोट्स ,रहस्यमयी बातें इत्यादि के बारे में पढ़ और लिख सकते हैं\" />\n");
      out.write("        <meta property=\"og:image\" content=\"https://www.inquiryhere.com/images/inquiryhere_Logo.PNG\" />\n");
      out.write("        <meta property=\"og:type\" content=\"website\">\n");
      out.write("        <meta property=\"og:locale\" content=\"en_US\">\n");
      out.write("        <meta property=\"og:title\" content=\"इंडिया का सबसे पहले मज़े वाला वेबसाइट जहाँ पर आप हिंदी में शयारी ,जोक, कहानी,कबिता, कोट्स ,रहस्यमयी बातें इत्यादि के बारे में पढ़ और लिख सकते हैं\" />\n");
      out.write("        <meta property=\"og:url\" content=\"https://www.inquiryhere.com/\">\n");
      out.write("        <meta property=\"og:site_name\" content=\"inquiryhere.com\" />\n");
      out.write("        <link rel=\"icon\" href=\"https://www.inquiryhere.com/images/inquiryhere_Logo.PNG\" type=\"image/png\">\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            function CheckType(val) {\n");
      out.write("                var element = document.getElementById('onTopic1');\n");
      out.write("                if (val === 'others')\n");
      out.write("                    element.style.display = 'block';\n");
      out.write("                else\n");
      out.write("                    element.style.display = 'none';\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </script> \n");
      out.write("        <script>\n");
      out.write("            var room = 1;\n");
      out.write("            function add_fields(val) {\n");
      out.write("                var elem = document.getElementById(\"ttt\");\n");
      out.write("                elem.parentNode.removeChild(elem);\n");
      out.write("                for (i = 0; i < val.value; i++) {\n");
      out.write("                    room++;\n");
      out.write("                    var objTo = document.getElementById('room_fileds');\n");
      out.write("                    var divtest = document.createElement(\"div\");\n");
      out.write("                    divtest.innerHTML = '<div id=\"ttt\"></div><div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\"  style=\"padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;\"><input type=\"text\" required=\"\" name=\"option[]\" placeholder=\"option ' + (room - 1) + '\"> </div></div>';\n");
      out.write("                    objTo.appendChild(divtest);\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"main-page-wrapper\">\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("            <div class=\"clear-fix\"></div>\n");
      out.write("            <div class=\"bodydata\">\n");
      out.write("                <div class=\"container clear-fix\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-lg-3 col-md-3 col-sm-12 col-xs-12\">\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\" >\n");
      out.write("                            <div class=\"row\">   \n");
      out.write("                                ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                <div style=\"height:auto;background-color: #e1e8e8;\">\n");
      out.write("                                    <form name=\"optionalQuestion\" action=\"");
      out.print(request.getContextPath());
      out.write("/saveOptionalQuestion\" method=\"post\">\n");
      out.write("                                        <input type=\"hidden\" name=\"userId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.Session_id_of_user}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/>                                       \n");
      out.write("                                        <div id=\"Question\" style=\"background-color: #c6f7f9;padding-bottom: 8px;padding-top: 8px;text-align: left;\">\n");
      out.write("                                            <div class=\"boxHeading\" style=\"padding-left: 10px;\">\n");
      out.write("                                                Question\n");
      out.write("                                            </div>\n");
      out.write("                                            <div>\n");
      out.write("                                                <textarea name=\"o_questin\" class=\"ckeditor\"></textarea>\n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                        <div id=\"onTopicId\" style=\"background-color: #fff9fc;padding-bottom: 8px;padding-top: 8px;text-align: left;\">\n");
      out.write("                                            <div class=\"boxHeading\" style=\"padding-left: 10px;\">\n");
      out.write("                                                On Topic\n");
      out.write("                                                <select name=\"onTopic\" onchange=\"CheckType(this.value);\">\n");
      out.write("                                                    <option value=\"uncategorized\" selected=\"\">Uncategorized</option>\n");
      out.write("                                                    ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                                    <option value=\"others\">Others</option>                                               \n");
      out.write("                                                </select>\n");
      out.write("                                                <input type=\"text\" name=\"onTopic1\" id=\"onTopic1\" style=\"display:none;\"/>\n");
      out.write("                                            </div>                                            \n");
      out.write("                                        </div>\n");
      out.write("                                        <div id=\"optional\" style=\"background-color: #d7ccca;padding-bottom: 8px;padding-top: 8px;text-align: left;\">\n");
      out.write("                                            <div class=\"boxHeading\" style=\"padding-left: 10px;\">\n");
      out.write("                                                How many option you want to have : \n");
      out.write("                                                <select name=\"nuberOfOption\" onchange=\"add_fields(this);\">\n");
      out.write("                                                    <option value=\"2\">2</option>    \n");
      out.write("                                                    <option value=\"3\">3</option>    \n");
      out.write("                                                    <option value=\"4\" selected=\"\">4</option>    \n");
      out.write("                                                    <option value=\"5\">5</option>    \n");
      out.write("                                                    <option value=\"6\">6</option>    \n");
      out.write("                                                    <option value=\"7\">7</option>    \n");
      out.write("                                                    <option value=\"8\">8</option>    \n");
      out.write("                                                    <option value=\"9\">9</option>    \n");
      out.write("                                                    <option value=\"10\">10</option>    \n");
      out.write("                                                </select>\n");
      out.write("                                            </div>                                            \n");
      out.write("                                        </div>\n");
      out.write("                                        <div id=\"optional\" style=\"background-color: #fffdbe;padding-bottom: 8px;padding-top: 8px;text-align: left;\">\n");
      out.write("\n");
      out.write("                                            <div class=\"row\" style=\"padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;\">\n");
      out.write("                                                <div id=\"room_fileds\">               \n");
      out.write("                                                    <div id=\"ttt\">\t\t\t\n");
      out.write("                                                        <div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\"  style=\"padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;\">\n");
      out.write("                                                            <input type=\"text\" name=\"option[]\" placeholder=\"option 1\" required=\"\">\n");
      out.write("                                                        </div>\n");
      out.write("                                                        <div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\" style=\"padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;\">\n");
      out.write("                                                            <input type=\"text\" name=\"option[]\" placeholder=\"option 2\" required=\"\">\n");
      out.write("                                                        </div>\n");
      out.write("                                                        <div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\" style=\"padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;\">\n");
      out.write("                                                            <input type=\"text\" name=\"option[]\" placeholder=\"option 3\" required=\"\">\n");
      out.write("                                                        </div>\n");
      out.write("                                                        <div class=\"col-lg-6 col-md-6 col-sm-12 col-xs-12\" style=\"padding-left: 10px; padding-right: 10px;padding-top: 10px;padding-bottom: 10px;\">\n");
      out.write("                                                            <input type=\"text\" name=\"option[]\" placeholder=\"option 4\" required=\"\">\n");
      out.write("                                                        </div>\n");
      out.write("                                                    </div>\n");
      out.write("                                                </div>                                                \n");
      out.write("                                            </div>\n");
      out.write("                                        </div>\n");
      out.write("                                        <div id=\"correctAnswer\" style=\"background-color: #e0e0e0;padding-bottom: 8px;padding-top: 8px;text-align: left;\">\n");
      out.write("                                            <div class=\"boxHeading\" style=\"padding-left: 10px;\">\n");
      out.write("                                                Correct Answer is : <input type=\"text\" name=\"correctAnswer\">                                                 \n");
      out.write("                                            </div>                                            \n");
      out.write("                                        </div>\n");
      out.write("                                        <div id=\"desc\" style=\"background-color: #c6f7f9;padding-bottom: 8px;padding-top: 8px;text-align: left;\">\n");
      out.write("                                            <input type=\"submit\" value=\"Upload\">\n");
      out.write("                                        </div>\n");
      out.write("                                    </form>\n");
      out.write("                                </div>   \n");
      out.write("                            </div>\n");
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/jquery-2.1.4.js\"></script>\n");
      out.write("            <!-- Bootstrap JS -->\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/bootstrap/bootstrap.min.js\"></script>\n");
      out.write("            <!-- Bootstrap Select JS -->\n");
      out.write("            <script type=\"text/javascript\" src=\"vendor/bootstrap-select/dist/js/bootstrap-select.js\"></script>\n");
      out.write("        </div>\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.msg ne null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                    <div class=\"themeBox\" style=\"font-family: serif;font-variant-position: super;font-size: 25px;text-align: center;background-color: #00ff5c;color: white;\">\n");
        out.write("                                        ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.msg}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\n");
        out.write("                                    </div>\n");
        out.write("                                ");
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

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${opt_fun.onTopicName()}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("t");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                                        <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${t}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${word.convertStringUpperToLower(t)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</option>\n");
          out.write("                                                    ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
