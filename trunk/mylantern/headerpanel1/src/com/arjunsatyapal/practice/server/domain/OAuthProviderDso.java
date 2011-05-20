package com.arjunsatyapal.practice.server.domain;

import com.google.gwt.user.client.rpc.IsSerializable;

import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;

public class OAuthProviderDso extends OAuthProviderDto implements IsSerializable {

  @Override
  public String validate() {
    return super.validate();
  }

  public static class Builder {
    private String oAuthProvider;
    private String consumerKey;
    private String consumerSecret;

    public Builder setOAuthProvider(String oAuthProvider) {

      this.oAuthProvider = oAuthProvider;
      return this;
    }

    public Builder setConsumerKey(String consumerKey) {
      this.consumerKey = consumerKey;
      return this;
    }

    public Builder setConsumerSecret(String consumerSecret) {
      this.consumerSecret = consumerSecret;
      return this;
    }

    public OAuthProviderDso build() {
      OAuthProviderDso dso = new OAuthProviderDso();
      dso.oAuthProvider = this.oAuthProvider;
      dso.consumerKey = this.consumerKey;
      dso.consumerSecret = this.consumerSecret;
      dso.validate();
      return dso;
    }
  }

  public OAuthProviderDto toOAuthProviderDto() {
    return new OAuthProviderDto.Builder()
        .setOAuthProvider(oAuthProvider)
        .setConsumerKey(consumerKey)
        .setConsumerSecret(consumerSecret)
        .build();
  }
}
