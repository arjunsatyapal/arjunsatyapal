package com.arjunsatyapal.practice.client;

import com.google.gwt.core.client.GWT;

import com.arjunsatyapal.practice.client.rpc.LoginService;
import com.arjunsatyapal.practice.client.rpc.LoginServiceAsync;
import com.arjunsatyapal.practice.client.rpc.OAuthProviderService;
import com.arjunsatyapal.practice.client.rpc.OAuthProviderServiceAsync;

public class ServiceProvider {
  private static LoginServiceAsync loginService;
  private static OAuthProviderServiceAsync oAuthProviderServiceAsync;


  public static OAuthProviderServiceAsync getOAuthProviderService() {
    if (oAuthProviderServiceAsync == null) {
      oAuthProviderServiceAsync = GWT.create(OAuthProviderService.class);
    }
    return oAuthProviderServiceAsync;
  }

  public static LoginServiceAsync getLoginService() {
    if (loginService == null) {
      loginService = GWT.create(LoginService.class);
    }
    return loginService;
  }
}
