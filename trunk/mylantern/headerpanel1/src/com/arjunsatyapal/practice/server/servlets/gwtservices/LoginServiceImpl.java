package com.arjunsatyapal.practice.server.servlets.gwtservices;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.arjunsatyapal.practice.client.rpc.LoginService;
import com.arjunsatyapal.practice.server.domain.UserAccountDso;
import com.arjunsatyapal.practice.server.servlets.login.LoginHelper;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDTO;

import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
  @Override
  public UserAccountDTO getLoggedInUserDTO() {
    UserAccountDTO userDTO;
    HttpSession session = getThreadLocalRequest().getSession();

    UserAccountDso u = LoginHelper.getLoggedInUser(session);
    if (u == null) return null;
    userDTO = UserAccountDso.toDTO(u);
    return userDTO;
  }
}
