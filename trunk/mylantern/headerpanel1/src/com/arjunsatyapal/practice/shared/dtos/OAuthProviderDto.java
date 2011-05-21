package com.arjunsatyapal.practice.shared.dtos;


public class OAuthProviderDto implements AbstractDto {
  protected String oAuthProvider;
  protected String consumerKey;
  protected String consumerSecret;

  @Override
  public StringBuilder getStringBuilder() {
    StringBuilder builder = new StringBuilder();
    builder.append("OAuthProvider[").append(oAuthProvider);
    builder.append("], consumerKey[").append(consumerKey);
    builder.append("], consumerSecret[").append(consumerSecret);
    builder.append("].");

    return builder;
  }

  @Override
  public String toString() {
   return getStringBuilder().toString();
  }

  @Override
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

  protected OAuthProviderDto() {
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

  public static class Builder {
    private String oAuthProvider;
    private String consumerKey;
    private String consumerSecret;

    /*
     * TODO(arjuns) : Make error handling more friendly. Probably as part of the set,
     * pass the widget, and then within the Builder, store a list of Pair, which will
     * tell for what widget, what is the error string.
     */
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
