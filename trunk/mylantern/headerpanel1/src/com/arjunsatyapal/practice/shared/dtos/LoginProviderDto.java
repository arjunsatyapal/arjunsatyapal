package com.arjunsatyapal.practice.shared.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LoginProviderDto implements IsSerializable {

  protected String loginProvider;
  protected String consumerKey;
  protected String consumerSecret;

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("LoginProvider[")
        .append(loginProvider)
        .append("], consumerKey[")
        .append(consumerKey)
        .append("], consumerSecret[")
        .append(consumerSecret)
        .append("].");
    return builder.toString();
  }

  public String getLoginProvider() {
    return loginProvider;
  }

  public String getConsumerKey() {
    return consumerKey;
  }

  public String getConsumerSecret() {
    return consumerSecret;
  }

  public String validate() {
    StringBuilder builder = new StringBuilder("");

    if (loginProvider.isEmpty()) {
      builder.append("loginProvider is empty.\n");
    }

    if (consumerKey.isEmpty()) {
      builder.append("consumerKey is empty.\n");
    }

    if (consumerSecret.isEmpty()) {
      builder.append("consumerSecret is empty.\n");
    }

    return builder.toString();
  }
  /*
   * Default constructor for GWT Serialization.
   */
  @SuppressWarnings("unused")
  protected LoginProviderDto() {
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

    public LoginProviderDto build() {
      LoginProviderDto dto = new LoginProviderDto();
      dto.loginProvider = this.loginProvider;
      dto.consumerKey = this.consumerKey;
      dto.consumerSecret = this.consumerSecret;
      dto.validate();
      return dto;
    }
  }
}
