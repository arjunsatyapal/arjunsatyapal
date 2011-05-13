package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class LoginSuperServlet extends HttpServlet {
  private static final long serialVersionUID = -6400274188671229519L;

  public LoginSuperServlet() {
    super();
  }

  protected String buildCallBackURL(HttpServletRequest request) {
    StringBuffer requestURL = request.getRequestURL();
    String callbackURL = requestURL.toString();
    callbackURL += "callback";
    // System.out.println("callback url: " + callbackURL);
    return callbackURL;
  }

}