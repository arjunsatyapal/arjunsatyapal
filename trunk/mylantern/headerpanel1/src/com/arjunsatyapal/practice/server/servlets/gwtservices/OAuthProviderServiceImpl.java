package com.arjunsatyapal.practice.server.servlets.gwtservices;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.arjunsatyapal.practice.client.rpc.OAuthProviderService;
import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;

public class OAuthProviderServiceImpl extends RemoteServiceServlet implements OAuthProviderService{
  @Override
  public OAuthProviderDto addOAuthProvider(OAuthProviderDto oAuthProviderDto) {
    return new OAuthProviderDto.Builder()
        .setOAuthProvider("arjun")
        .setConsumerKey("key")
        .setConsumerSecret("secret")
        .build();
  }
}
