package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;

import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;

import java.util.ArrayList;

public interface OAuthProviderService extends RemoteService {
  ArrayList<String> getOAuthProviderList();
  OAuthProviderDto addOAuthProvider(OAuthProviderDto oAuthProviderDto);
}
