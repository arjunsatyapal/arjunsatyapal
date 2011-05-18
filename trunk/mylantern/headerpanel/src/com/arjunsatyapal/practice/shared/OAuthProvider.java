package com.arjunsatyapal.practice.shared;

public enum OAuthProvider {
  AOL(1, "aol.com"),
  GOOGLE(2, "google.com/accounts/o8/id"),
  MYOPENID(3, "myopenid.com"),
  YAHOO(4, "yahoo.com");

  private int providerId;
  private String providerUrl;
//  private Class loginCallback;

  public int getProviderId() {
    return providerId;
  }

  public String getProviderUrl() {
    return providerUrl;
  }

//  public String getCallbackUrl() {
//    return loginCallback.getName();
//  }

  public static OAuthProvider getByOrdinalId(int ordinalId) {
    OAuthProvider[] providers = OAuthProvider.values();
    if (ordinalId < 0 || ordinalId > providers.length) {
      return null;
    }
    return (OAuthProvider.values())[ordinalId];
  }

  private OAuthProvider(int providerId, //Class loginCallback,
    String providerUrl) {
    this.providerId = providerId;
//    this.loginCallback = loginCallback;
    this.providerUrl = providerUrl;
  }
}
