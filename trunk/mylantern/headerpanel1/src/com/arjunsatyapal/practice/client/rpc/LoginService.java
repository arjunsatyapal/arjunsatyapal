package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.arjunsatyapal.practice.shared.dtos.UserAccountDTO;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {
  UserAccountDTO getLoggedInUserDTO();
}
