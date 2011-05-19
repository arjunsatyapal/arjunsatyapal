package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;

@RemoteServiceRelativePath("admin/oauthProviderService")
public interface OAuthProviderService extends RemoteService {
  OAuthProviderDto addOAuthProvider(OAuthProviderDto oAuthProviderDto);
}
