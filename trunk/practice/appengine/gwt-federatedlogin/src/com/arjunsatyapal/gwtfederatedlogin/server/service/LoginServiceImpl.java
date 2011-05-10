package com.arjunsatyapal.gwtfederatedlogin.server.service;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.arjunsatyapal.gwtfederatedlogin.client.service.LoginService;
import com.arjunsatyapal.gwtfederatedlogin.server.domain.UserAccount;
import com.arjunsatyapal.gwtfederatedlogin.server.servlets.login.LoginHelper;
import com.arjunsatyapal.gwtfederatedlogin.shared.dto.UserAccountDTO;

import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
  @Override
  public UserAccountDTO getLoggedInUserDTO() {
    UserAccountDTO userDTO;
    HttpSession session = getThreadLocalRequest().getSession();

    UserAccount u = LoginHelper.getLoggedInUser(session);
    if (u == null) return null;
    userDTO = UserAccount.toDTO(u);
    return userDTO;
  }
}
