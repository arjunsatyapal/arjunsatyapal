package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAol extends LoginSuperServlet {
  private static final long serialVersionUID = -5708611292779495446L;
  private static Logger log = Logger.getLogger(LoginAol.class
    .getName());
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    redirectForLogin(log, request, response, AuthenticationProvider.AOL);
  }
}
