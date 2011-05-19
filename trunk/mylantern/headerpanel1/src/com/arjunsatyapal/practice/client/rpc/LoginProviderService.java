package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.arjunsatyapal.practice.shared.dtos.LoginProviderDto;

@RemoteServiceRelativePath("loginProviderService")
public interface LoginProviderService extends RemoteService {
  LoginProviderDto addLoginProvider(LoginProviderDto loginProviderDto);
}
