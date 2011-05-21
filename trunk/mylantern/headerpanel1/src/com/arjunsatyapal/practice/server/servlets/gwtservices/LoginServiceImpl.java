package com.arjunsatyapal.practice.server.servlets.gwtservices;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.arjunsatyapal.practice.client.rpc.LoginService;
import com.arjunsatyapal.practice.server.domain.UserAccountDso;
import com.arjunsatyapal.practice.server.servlets.login.LoginHelper;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDto;

import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
  @Override
  public UserAccountDto getLoggedInUserDTO() {
    UserAccountDto userAccountDto;
    HttpSession session = getThreadLocalRequest().getSession();

    UserAccountDso userAccountDso = LoginHelper.getLoggedInUser(session);
    if (userAccountDso == null) return null;
    userAccountDto = userAccountDso.toUserAccountDto();
    return userAccountDto;
  }
}
