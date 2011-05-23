package com.arjunsatyapal.practice.server.servlets.gwtservices;
import javax.servlet.http.HttpSession;

import com.arjunsatyapal.practice.client.rpc.LoginService;
import com.arjunsatyapal.practice.server.domain.UserAccountDao;
import com.arjunsatyapal.practice.server.servlets.login.LoginHelper;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDto;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
  @Override
  public UserAccountDto getLoggedInUserDTO() {
    UserAccountDto userAccountDto;
    HttpSession session = getThreadLocalRequest().getSession();

    UserAccountDao userAccountDao = LoginHelper.getLoggedInUser(session);
    
    if (userAccountDao == null) return null;
    userAccountDto = userAccountDao.toUserAccountDto();
    return userAccountDto;
  }
}
