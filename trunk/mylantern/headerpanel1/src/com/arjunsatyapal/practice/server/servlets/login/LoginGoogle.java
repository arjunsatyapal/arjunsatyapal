package com.arjunsatyapal.practice.server.servlets.login;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.arjunsatyapal.practice.shared.OAuthProviderEnum;
import com.arjunsatyapal.practice.shared.ValidParams;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginGoogle extends HttpServlet {
  private static final long serialVersionUID = -5708611292779495446L;
  private static Logger log = Logger.getLogger(LoginGoogle.class
    .getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    Set<String> attributes = new HashSet<String>();
    // Point to LoginGoogleCallback.
    // TODO : improve this. May be put in properties.

    String callbackURL = request.getRequestURL().toString() + "Callback";
    String redirectHash = request.getParameter(ValidParams.REDIRECT_HASH.getParamKey());
    if (redirectHash != null) {
      callbackURL += "?" + ValidParams.REDIRECT_HASH.getParamKey() + "=" + redirectHash;
    }

    UserService userService = UserServiceFactory.getUserService();
    String loginUrl =
      userService.createLoginURL(callbackURL, null, OAuthProviderEnum.GOOGLE.getProviderUrl(), attributes);
    log.info("Going to " + OAuthProviderEnum.GOOGLE.name() + " URL : " + loginUrl);
    response.sendRedirect(loginUrl);
  }
}
