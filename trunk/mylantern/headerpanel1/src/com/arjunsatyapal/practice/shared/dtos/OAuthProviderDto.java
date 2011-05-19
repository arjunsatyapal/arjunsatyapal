package com.arjunsatyapal.practice.shared.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OAuthProviderDto implements IsSerializable {

  protected String oAuthProvider;
  protected String consumerKey;
  protected String consumerSecret;

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("OAuthProvider[")
        .append(oAuthProvider)
        .append("], consumerKey[")
        .append(consumerKey)
        .append("], consumerSecret[")
        .append(consumerSecret)
        .append("].");
    return builder.toString();
  }

  public String getOAuthProvider() {
    return oAuthProvider;
  }

  public String getConsumerKey() {
    return consumerKey;
  }

  public String getConsumerSecret() {
    return consumerSecret;
  }

  public String validate() {
    StringBuilder builder = new StringBuilder("");

    if (oAuthProvider.isEmpty()) {
      builder.append("oAuthProvider is empty.\n");
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
  protected OAuthProviderDto() {
  }

  public static class Builder {
    @SuppressWarnings("hiding")
    private String oAuthProvider;

    @SuppressWarnings("hiding")
    private String consumerKey;

    @SuppressWarnings("hiding")
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

    public OAuthProviderDto build() {
      OAuthProviderDto dto = new OAuthProviderDto();
      dto.oAuthProvider = this.oAuthProvider;
      dto.consumerKey = this.consumerKey;
      dto.consumerSecret = this.consumerSecret;
      dto.validate();
      return dto;
    }
  }
}
