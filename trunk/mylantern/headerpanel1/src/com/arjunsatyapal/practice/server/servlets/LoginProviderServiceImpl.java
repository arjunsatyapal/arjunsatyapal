package com.arjunsatyapal.practice.server.servlets;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.arjunsatyapal.practice.client.rpc.LoginProviderService;
import com.arjunsatyapal.practice.shared.dtos.LoginProviderDto;

public class LoginProviderServiceImpl extends RemoteServiceServlet implements LoginProviderService{
  @Override
  public LoginProviderDto addLoginProvider(LoginProviderDto loginProviderDto) {
    return new LoginProviderDto.Builder()
        .setLoginProvider("arjun")
        .setConsumerKey("key")
        .setConsumerSecret("secret")
        .build();
  }
}
