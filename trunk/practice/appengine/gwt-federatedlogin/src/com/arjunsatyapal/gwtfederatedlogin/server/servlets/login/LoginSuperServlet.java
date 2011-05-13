package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjunsatyapal.gwtfederatedlogin.server.domain.UserAccount;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public abstract class LoginSuperServlet extends HttpServlet {
  private static final long serialVersionUID = -6400274188671229519L;
  
  public LoginSuperServlet() {
    super();
  }
  
  protected String buildCallBackURL(HttpServletRequest request) {
    StringBuffer requestURL = request.getRequestURL();
    String callbackURL = requestURL.toString();
    callbackURL += "Callback";
    // System.out.println("callback url: " + callbackURL);
    return callbackURL;
  }
  
  public void
    redirectForLogin(Logger log, HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationProvider authenticationProvider) throws IOException {
    Set<String> attributes = new HashSet();
    String callbackURL = buildCallBackURL(request);
    UserService userService = UserServiceFactory.getUserService();
    String loginUrl =
      userService.createLoginURL(callbackURL, null,
        authenticationProvider.getProviderUrl(), attributes);
    log
      .info("Going to " + authenticationProvider.name() + " URL : " + loginUrl);
    response.sendRedirect(loginUrl);
  }
  
  public void
    handleCallbackAfterLogin(Logger log, HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationProvider authenticationProvider) throws IOException {
    
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      // update or create user
      
      UserAccount u =
        new UserAccount(user.getNickname(), user.getEmail(),
          authenticationProvider);
      UserAccount connectr =
        new LoginHelper().loginStarts(request.getSession(), u);
      
      log.warning(connectr.toString());
    }
    response.sendRedirect(LoginHelper.getApplicationURL(request));
  }
}
