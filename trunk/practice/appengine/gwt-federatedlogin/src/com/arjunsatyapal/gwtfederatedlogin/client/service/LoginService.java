package com.arjunsatyapal.gwtfederatedlogin.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.arjunsatyapal.gwtfederatedlogin.shared.dto.UserAccountDTO;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {
  UserAccountDTO getLoggedInUserDTO();
}
