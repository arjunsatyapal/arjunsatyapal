package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

public enum AuthenticationProvider {
  GOOGLE(1);

  private int providerId;

  public int getProviderId() {
    return providerId;
  }

  private AuthenticationProvider(int providerId) {
    this.providerId = providerId;
  }
}
