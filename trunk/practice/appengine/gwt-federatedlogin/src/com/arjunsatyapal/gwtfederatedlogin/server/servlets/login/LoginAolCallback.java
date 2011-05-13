package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAolCallback extends LoginSuperServlet {
  private static final long serialVersionUID = -8166659832825203335L;
  private static Logger log = Logger.getLogger(LoginAolCallback.class
    .getName());
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    handleCallbackAfterLogin(log, request, response, AuthenticationProvider.AOL);
  }
}
