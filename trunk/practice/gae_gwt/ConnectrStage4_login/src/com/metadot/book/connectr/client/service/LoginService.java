package com.metadot.book.connectr.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.metadot.book.connectr.shared.UserAccountDTO;
import com.metadot.book.connectr.shared.exception.NotLoggedInException;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 */
@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {
  UserAccountDTO getLoggedInUserDTO();

  void logout() throws NotLoggedInException;
}
