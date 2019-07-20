package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.io.*;
import java.net.*;

public final class javatest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


String msg = "Hello, World.";

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
      response.setContentType("text/html; charset=iso-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html><head><title>JSP Test</title>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');

String hostName=request.getServerName();

      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<table>\n");
      out.write("        <thead>\n");
      out.write("            <tr>\n");
      out.write("                <th colspan=\"2\" align=\"left\" class=\"info-header\">Environment</th>\n");
      out.write("            </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"c1\">Java Version:  \t </td>\n");
      out.write("                <td class=\"c2\">\n");
      out.write("                    ");

                        String vmName = System.getProperty("java.vm.name");
                        if (vmName == null) {
                            vmName = "";
                        }
                        else {
                            vmName = " -- " + vmName;
                        }
                    
      out.write("\n");
      out.write("                    ");
      out.print( System.getProperty("java.version") );
      out.write(' ');
      out.print( System.getProperty("java.vendor") );
      out.print( vmName );
      out.write("\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"c1\">Appserver:</td>\n");
      out.write("                <td class=\"c2\">\n");
      out.write("                    ");
      out.print( application.getServerInfo() );
      out.write("\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"c1\">\n");
      out.write("                    Host Name:\n");
      out.write("                </td>\n");
      out.write("                <td class=\"c2\">\n");
      out.write("                    ");
      out.print(hostName);
      out.write("\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"c1\">OS / Hardware:</td>\n");
      out.write("                <td class=\"c2\">\n");
      out.write("                    ");
      out.print( System.getProperty("os.name") );
      out.write(" / ");
      out.print( System.getProperty("os.arch") );
      out.write("\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"c1\">Locale / Timezone:</td>\n");
      out.write("\n");
      out.write("                <td class=\"c2\">\n");
      out.write("\t\t");
      out.print( Locale.getDefault() );
      out.write("\n");
      out.write("\t\t/\n");
      out.write("\t\t");
      out.print( TimeZone.getDefault().getDisplayName(Locale.getDefault()));
      out.write("\n");
      out.write("\t\t (");
      out.print( (TimeZone.getDefault().getRawOffset()/1000/60/60) );
      out.write(" GMT)\n");
      out.write("\t\t \n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td class=\"c1\">Java Memory</td>\n");
      out.write("                <td>\n");
      out.write("                ");
    // The java runtime
                    Runtime runtime = Runtime.getRuntime();

                    double freeMemory = (double)runtime.freeMemory()/(1024*1024);
                    double maxMemory = (double)runtime.maxMemory()/(1024*1024);
                    double totalMemory = (double)runtime.totalMemory()/(1024*1024);
                    double usedMemory = totalMemory - freeMemory;
                    double percentFree = ((maxMemory - usedMemory)/maxMemory)*100.0;
                    double percentUsed = 100 - percentFree;
                    int percent = 100-(int)Math.round(percentFree);

                    DecimalFormat mbFormat = new DecimalFormat("#0.00");
                    DecimalFormat percentFormat = new DecimalFormat("#0.0");
                
      out.write("\n");
      out.write("\n");
      out.write("                <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"300\">\n");
      out.write("                <tr valign=\"middle\">\n");
      out.write("                    <td width=\"99%\" valign=\"middle\">\n");
      out.write("                        <div class=\"bar\">\n");
      out.write("                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" style=\"border:1px #666 solid;\">\n");
      out.write("                        <tr>\n");
      out.write("                            ");
  if (percent == 0) { 
      out.write("\n");
      out.write("\n");
      out.write("                                <td width=\"100%\"><img src=\"images/percent-bar-left.gif\" width=\"100%\" height=\"8\" border=\"0\" alt=\"\"></td>\n");
      out.write("\n");
      out.write("                            ");
  } else { 
      out.write("\n");
      out.write("\n");
      out.write("                                ");
  if (percent >= 90) { 
      out.write("\n");
      out.write("\n");
      out.write("                                    <td width=\"");
      out.print( percent );
      out.write("%\" background=\"images/percent-bar-used-high.gif\"\n");
      out.write("                                        ><img src=\"images/blank.gif\" width=\"1\" height=\"8\" border=\"0\" alt=\"\"></td>\n");
      out.write("\n");
      out.write("                                ");
  } else { 
      out.write("\n");
      out.write("\n");
      out.write("                                    <td width=\"");
      out.print( percent );
      out.write("%\" background=\"images/percent-bar-used-low.gif\"\n");
      out.write("                                        ><img src=\"images/blank.gif\" width=\"1\" height=\"8\" border=\"0\" alt=\"\"></td>\n");
      out.write("\n");
      out.write("                                ");
  } 
      out.write("\n");
      out.write("                                <td width=\"");
      out.print( (100-percent) );
      out.write("%\" background=\"images/percent-bar-left.gif\"\n");
      out.write("                                    ><img src=\"images/blank.gif\" width=\"1\" height=\"8\" border=\"0\" alt=\"\"></td>\n");
      out.write("                            ");
  } 
      out.write("\n");
      out.write("                        </tr>\n");
      out.write("                        </table>\n");
      out.write("                        </div>\n");
      out.write("                    </td>\n");
      out.write("                    <td width=\"1%\" nowrap>\n");
      out.write("                        <div style=\"padding-left:6px;\" class=\"c2\">\n");
      out.write("                        ");
      out.print( mbFormat.format(usedMemory) );
      out.write(" MB of ");
      out.print( mbFormat.format(maxMemory) );
      out.write(" MB (");
      out.print( percentFormat.format(percentUsed) );
      out.write("%) used\n");
      out.write("                        </div>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("                </table>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("        </tbody>\n");
      out.write("\n");
      out.write("\n");
      out.write("</table>\n");
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
