package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjunsatyapal.gwtfederatedlogin.server.domain.UserAccount;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginGoogleCallbackServlet extends HttpServlet {
  private static final long serialVersionUID = -8166659832825203335L;
  private static Logger log = Logger.getLogger(LoginGoogleCallbackServlet.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      // update or create user

      UserAccount u = new UserAccount(user.getNickname(), user.getEmail(), AuthenticationProvider.GOOGLE);
      UserAccount connectr = new LoginHelper().loginStarts(request.getSession(), u);

      log.warning(connectr.toString());
    }
    response.sendRedirect(LoginHelper.getApplitionURL(request));
  }
}
