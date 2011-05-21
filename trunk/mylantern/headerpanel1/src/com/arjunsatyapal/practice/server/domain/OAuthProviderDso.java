package com.arjunsatyapal.practice.server.domain;

import com.arjunsatyapal.practice.server.OAuthProviderEnum;
import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;
import com.arjunsatyapal.practice.shared.exceptions.InvalidClientInputException;


public class OAuthProviderDso implements AbstractDso {
  protected OAuthProviderEnum oAuthProvider;
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

    if (oAuthProvider == null) {
      builder.append("oAuthProvider cannot be null.\n");
    }
    if (consumerKey.isEmpty()) {
      builder.append("consumerKey is empty.\n");
    }
    if (consumerSecret.isEmpty()) {
      builder.append("consumerSecret is empty.\n");
    }
    return builder.toString();
  }

  private OAuthProviderDso() {
  }

  public OAuthProviderEnum getOAuthProvider() {
    return oAuthProvider;
  }

  public String getConsumerKey() {
    return consumerKey;
  }

  public String getConsumerSecret() {
    return consumerSecret;
  }

  public static OAuthProviderDso fromOAuthProviderDto(OAuthProviderDto dto) {
    OAuthProviderDso oAuthProviderDso = new OAuthProviderDso();

    if (!dto.validate().isEmpty()) {
      throw new InvalidClientInputException(
          "Invalid UserAccountDto with Error : [" + dto.validate() + "].");
    }

    OAuthProviderEnum oauthProviderEnum = null;
    try {
      oauthProviderEnum = OAuthProviderEnum.valueOf(dto.getOAuthProvider());
    } catch (IllegalArgumentException e) {
      throw new InvalidClientInputException("Invalid OAuthProvider[" + dto.getOAuthProvider() + "].");
    }

    oAuthProviderDso.oAuthProvider = oauthProviderEnum;
    oAuthProviderDso.consumerKey = dto.getConsumerKey();
    oAuthProviderDso.consumerSecret = dto.getConsumerSecret();
    return oAuthProviderDso;
  }

  //TODO(arjuns) : This is not implemented as all the values should be checked from AppConsole.
  // And values can be only overwritten. Cannot be modified.
//  public UserAccountDto toUserAccountDto() {
//  }
}
