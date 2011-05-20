package com.arjunsatyapal.practice.server.servlets.login;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.arjunsatyapal.practice.server.domain.UserAccount;
import com.arjunsatyapal.practice.shared.OAuthProviderEnum;
import com.arjunsatyapal.practice.shared.ValidParams;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginGoogleCallback extends HttpServlet{
  private static final long serialVersionUID = -8166659832825203335L;
  private static Logger log = Logger.getLogger(LoginGoogleCallback.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {


    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      // update or create user
      UserAccount u =
          new UserAccount(user.getNickname(), user.getEmail(), OAuthProviderEnum.GOOGLE);
      UserAccount connectr =
          new LoginHelper().loginStarts(request.getSession(), u);

      log.warning(connectr.toString());
    }
    String redirectURL = LoginHelper.getApplicationURL(request);
    String redirectToken = request.getParameter(ValidParams.CLIENT_CALLBACK_TOKEN.getParamKey());
    if (redirectToken != null) {
      redirectURL += "" + ValidParams.HASH.getParamKey() + redirectToken;
    }

    response.sendRedirect(redirectURL);
  }
}
