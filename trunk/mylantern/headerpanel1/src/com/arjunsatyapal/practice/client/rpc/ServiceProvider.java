package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.core.client.GWT;


public class ServiceProvider {
  private LoginServiceAsync loginService;
  private OAuthProviderServiceAsync oAuthProviderServiceAsync;
  private static ServiceProvider instance = new ServiceProvider();

  private ServiceProvider() {
    loginService = GWT.create(LoginService.class);
    oAuthProviderServiceAsync = GWT.create(OAuthProviderService.class);
  }

  public OAuthProviderServiceAsync getOAuthProviderService() {
    return oAuthProviderServiceAsync;
  }

  public LoginServiceAsync getLoginService() {
    return loginService;
  }

  public static ServiceProvider getServiceProvider() {
    return instance;
  }
}
