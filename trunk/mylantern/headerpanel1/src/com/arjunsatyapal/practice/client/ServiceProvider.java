package com.arjunsatyapal.practice.client;

import com.google.gwt.core.client.GWT;

import com.arjunsatyapal.practice.client.rpc.LoginProviderService;
import com.arjunsatyapal.practice.client.rpc.LoginProviderServiceAsync;

public class ServiceProvider {
  private static LoginProviderServiceAsync loginProviderServiceAsync;

  public static LoginProviderServiceAsync getLoginProviderService() {
    if (loginProviderServiceAsync == null) {
      loginProviderServiceAsync = GWT.create(LoginProviderService.class);
    }

    return loginProviderServiceAsync;
  }
}
