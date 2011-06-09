package com.arjunsatyapal.practice.oauth.docs.server.servlets.admin;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleDomainKeyRegistrationServlet extends HttpServlet {
  private static final long serialVersionUID = -958972338994800138L;
  
  public void doGet(
    HttpServletRequest request, HttpServletResponse response) throws IOException{
    response.setContentType("text/html");
    
    StringBuilder builder = new StringBuilder();
    
    builder.append("<html><head>");
    builder
      .append("<title>Add key details for registration with Google Domain.");
    builder.append("</head><body>");
    builder.append("<form method=\"post\" action =\""
      + request.getContextPath() + this.getClass().getName() + ">");
    
    builder.append("<table border=\"0\"><tr><td valign=\"top\">");
    builder.append("Your first name: </td>  <td valign=\"top\">");
    builder.append("<input type=\"text\" name=\"firstname\" size=\"20\">");
    builder.append("</td></tr><tr><td valign=\"top\">");
    builder.append("Your last name: </td>  <td valign=\"top\">");
    builder.append("<input type=\"text\" name=\"lastname\" size=\"20\">");
    builder.append("</td></tr><tr><td valign=\"top\">");
    builder.append("Your email: </td>  <td valign=\"top\">");
    builder.append("<input type=\"text\" name=\"email\" size=\"20\">");
    builder.append("</td></tr><tr><td valign=\"top\">");

    builder.append("<input type=\"submit\" value=\"Submit Info\"></td></tr>");
    builder.append("</table></form>");
    
    builder.append("</body></html>");
    
    response.getWriter().print(builder.toString());
  }
}
