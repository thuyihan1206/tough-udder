package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class events_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/header.jsp");
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
      response.setContentType("text/html; charset=ISO-8859-1");
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n");
      out.write("\t\t<title>Tough Udder: Obstacle Race</title>\n");
      out.write("\t\t<link href=\"newcss.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t\t<div class=\"outborder\">\n");
      out.write("\t\t\t");
      out.write("<img id=\"banner\" src=\"images/logo.jpg\" alt=\"Tough Udder logo\">\n");
      out.write(" <div id='menu'>\n");
      out.write("     <ul>\n");
      out.write("         <li><a href='#'>COMPANY PROFILE</a></li>\n");
      out.write("         <li><a href='#'>FIND AN EVENT</a></li>\n");
      out.write("         <li><a href='#'>OBSTACLES</a></li>\n");
      out.write("         <li><a href='#'>MY ACCOUNT</a></li>\n");
      out.write("         <li><a href='#'>MY CART</a></li>\n");
      out.write("     </ul>\n");
      out.write(" </div>\n");
      out.write(" <hr>");
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("\t</body>\n");
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
