package com.arjunsatyapal.practice.oauth.docs.server.servlets.admin;

import static com.google.appengine.repackaged.com.google.common.base.StringUtil.isEmptyOrWhitespace;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleDomainKeyRegistrationServlet extends HttpServlet {
  private static final long serialVersionUID = -958972338994800138L;
  private final int TEXT_BOX_LEN = 40;
  private final String WEBSITE = "website";
  private final String PUBLIC_KEY = "public_key";
  private final String PRIVATE_KEY = "private_key";
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    response.setContentType("text/html");
    
    StringBuilder builder = new StringBuilder();
    
    builder.append("<html><head>");
    builder
      .append("<title>Add key details for registration with Google Domain.</title>");
    builder.append("</head><body>");
    builder.append("<form method=\"post\" action =\""
      + request.getRequestURI() + "\">");
    
    builder
      .append("<table border=\"0\"><tr>")
      .append("<td>Website</td>")
      .append(
        "<td><input type=\"text\" name=\"" + WEBSITE + "\" size=\""
          + TEXT_BOX_LEN + "\"> </td>").append("</tr>");
    
    builder
      .append("<tr><td>Public Key</td>")
      .append(
        "<td><input type=\"text\" name=\"" + PUBLIC_KEY + "\" size=\""
          + TEXT_BOX_LEN + "\"></td>").append("</tr>");
    
    builder
      .append("<tr><td>Private Key</td>")
      .append(
        "<td><input type=\"text\" name=\"" + PRIVATE_KEY + "\" size=\""
          + TEXT_BOX_LEN + "\"></td>").append("</tr>");
    
    builder
      .append("<tr><td><input type=\"submit\" value=\"Submit Info\"></td></tr>");
    
    builder.append("</table></form>");
    builder.append("</body></html>");
    System.out.println(builder.toString());
    response.getWriter().println(builder.toString());
  }
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    Map paramMap = request.getParameterMap();
    
    StringBuilder builder = new StringBuilder();
    
    if (paramMap == null || paramMap.keySet().size() != 3) {
      builder.append("Expected Parameters : " + WEBSITE + ", " + PUBLIC_KEY
        + ", " + PRIVATE_KEY);
    } else {
      String website = request.getParameter(WEBSITE);
      String publicKey = request.getParameter(PUBLIC_KEY);
      String privateKey = request.getParameter(PRIVATE_KEY);
      
      if (isEmptyOrWhitespace(website) || isEmptyOrWhitespace(publicKey)
        || isEmptyOrWhitespace(privateKey)) {
        builder.append("Expected Parameters : " + WEBSITE + ", " + PUBLIC_KEY
        + ", " + PRIVATE_KEY);
      } else {
        builder.append("<html><body>");
        builder.append("Website = ").append(website);
        builder.append("<p>Publickey = ").append(publicKey);
        builder.append("<p>Privatekey = ").append(privateKey);
        builder.append("</html></body>");
      }
    }
    
    response.getWriter().println(builder.toString());
  }
}
