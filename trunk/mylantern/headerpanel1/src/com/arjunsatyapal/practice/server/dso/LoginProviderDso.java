package com.arjunsatyapal.practice.server.dso;

import com.google.gwt.user.client.rpc.IsSerializable;

import com.arjunsatyapal.practice.shared.dtos.LoginProviderDto;

public class LoginProviderDso extends LoginProviderDto implements IsSerializable {

  @Override
  public String validate() {
    return super.validate();
  }

  public static class Builder {
    @SuppressWarnings("hiding")
    private String loginProvider;

    @SuppressWarnings("hiding")
    private String consumerKey;

    @SuppressWarnings("hiding")
    private String consumerSecret;

    public Builder setLoginProvider(String loginProvider) {

      this.loginProvider = loginProvider;
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

    public LoginProviderDso build() {
      LoginProviderDso dso = new LoginProviderDso();
      dso.loginProvider = this.loginProvider;
      dso.consumerKey = this.consumerKey;
      dso.consumerSecret = this.consumerSecret;
      dso.validate();
      return dso;
    }
  }

  public LoginProviderDto toLoginProviderDto() {
    return new LoginProviderDto.Builder()
        .setLoginProvider(loginProvider)
        .setConsumerKey(consumerKey)
        .setConsumerSecret(consumerSecret)
        .build();
  }
}
