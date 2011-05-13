package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginMyOpenId extends LoginSuperServlet {
  private static final long serialVersionUID = -5708611292779495446L;
  private static Logger log = Logger.getLogger(LoginMyOpenId.class
    .getName());
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    redirectForLogin(log, request, response, AuthenticationProvider.MYOPENID);
  }
}
