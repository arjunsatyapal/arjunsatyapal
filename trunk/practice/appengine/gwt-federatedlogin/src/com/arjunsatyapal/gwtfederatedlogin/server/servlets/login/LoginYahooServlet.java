package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginYahooServlet extends LoginSuperServlet {
  private static final long serialVersionUID = -8560562656711735362L;
  private static Logger log = Logger.getLogger(LoginYahooServlet.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String callbackURL = buildCallBackURL(request);
    UserService userService = UserServiceFactory.getUserService();
    String googleLoginUrl = userService.createLoginURL(callbackURL);
    log.info("Going to Google login URL: " + googleLoginUrl);
    response.sendRedirect(googleLoginUrl);
  }
}
