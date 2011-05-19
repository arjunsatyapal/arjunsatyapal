package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;

public interface OAuthProviderServiceAsync {
  void addOAuthProvider(OAuthProviderDto oAuthProviderDto,
      AsyncCallback<OAuthProviderDto> callback);
}
