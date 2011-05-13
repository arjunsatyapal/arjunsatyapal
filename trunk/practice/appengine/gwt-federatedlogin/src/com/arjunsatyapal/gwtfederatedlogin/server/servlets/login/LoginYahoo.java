package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginYahoo extends LoginSuperServlet {
  private static final long serialVersionUID = -8560562656711735362L;
  private static Logger log = Logger.getLogger(LoginYahoo.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    redirectForLogin(log, request, response, AuthenticationProvider.YAHOO);

  }
}
