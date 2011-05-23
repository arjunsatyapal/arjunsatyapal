package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.core.client.GWT;


public class ServiceProvider {
  private static ServiceProvider instance = new ServiceProvider();
  private LoginServiceAsync loginService;
  private OAuthProviderServiceAsync oAuthProviderService;
  private SchoolServiceAsync schoolService;
  
  private ServiceProvider() {
    loginService = GWT.create(LoginService.class);
    oAuthProviderService = GWT.create(OAuthProviderService.class);
    schoolService = GWT.create(SchoolService.class);
  }

  public static ServiceProvider getServiceProvider() {
    return instance;
  }
  public OAuthProviderServiceAsync getOAuthProviderService() {
    return oAuthProviderService;
  }

  public LoginServiceAsync getLoginService() {
    return loginService;
  }

  public SchoolServiceAsync getSchoolService() {
    return schoolService;
  }
  
}
