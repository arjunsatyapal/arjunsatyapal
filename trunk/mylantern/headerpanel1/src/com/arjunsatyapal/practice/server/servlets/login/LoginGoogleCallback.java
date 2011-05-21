package com.arjunsatyapal.practice.server.servlets.login;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.arjunsatyapal.practice.server.OAuthProviderEnum;
import com.arjunsatyapal.practice.server.domain.UserAccountDso;
import com.arjunsatyapal.practice.shared.LoginCategory;
import com.arjunsatyapal.practice.shared.ValidParams;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDto;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginGoogleCallback extends HttpServlet {
  private static final long serialVersionUID = -8166659832825203335L;
  private static Logger log = Logger.getLogger(LoginGoogleCallback.class
      .getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {


    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      // update or create user

      // TODO(arjuns) : See how session has to be managed.

      LoginCategory loginCategory = userService.isUserAdmin() ? LoginCategory.ADMIN : LoginCategory.OAUTH_LOGIN;

      UserAccountDto userAccountDto = new UserAccountDto.Builder()
          .setName(user.getNickname()).setEmailAddress(user.getEmail()).build();

      UserAccountDso userAccountDso = UserAccountDso
          .fromUserAccountDto(userAccountDto);

      UserAccountDso connectr = new LoginHelper().loginStarts(
          request.getSession(), userAccountDso);

      log.warning(connectr.toString());
    }
    String redirectURL = LoginHelper.getApplicationURL(request);
    String redirectToken = request
        .getParameter(ValidParams.CLIENT_CALLBACK_TOKEN.getParamKey());
    if (redirectToken != null) {
      redirectURL += "" + ValidParams.HASH.getParamKey() + redirectToken;
    }

    response.sendRedirect(redirectURL);
  }
}
