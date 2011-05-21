package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;

import com.arjunsatyapal.practice.shared.dtos.UserAccountDto;

public interface LoginService extends RemoteService {
  UserAccountDto getLoggedInUserDTO();
}
