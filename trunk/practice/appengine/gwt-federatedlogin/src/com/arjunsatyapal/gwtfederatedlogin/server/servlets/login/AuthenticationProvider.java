package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

public enum AuthenticationProvider {
  AOL(1, LoginAolCallback.class, "aol.com"),
  GOOGLE(2, LoginGoogleCallback.class, "google.com/accounts/o8/id"),
  MYOPENID(3, LoginMyOpenIdCallback.class, "myopenid.com"),
  YAHOO(4, LoginYahooCallback.class, "yahoo.com");
  
  private int providerId;
  private String providerUrl;
  private Class loginCallback;
  
  public int getProviderId() {
    return providerId;
  }
  
  public String getProviderUrl() {
    return providerUrl;
  }
  
  public String getCallbackUrl() {
    return loginCallback.getName();
  }
  
  private AuthenticationProvider(int providerId, Class loginCallback,
    String providerUrl) {
    this.providerId = providerId;
    this.loginCallback = loginCallback;
    this.providerUrl = providerUrl;
  }
}
