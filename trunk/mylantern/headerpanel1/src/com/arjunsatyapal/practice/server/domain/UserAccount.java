package com.arjunsatyapal.practice.server.domain;

import com.arjunsatyapal.practice.shared.OAuthProviderEnum;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDTO;

public class UserAccount {
  private Long id;
  private String name;
  private String emailAddress;
  private OAuthProviderEnum oAuthProvider;

  public UserAccount() {
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("UserAccountDetails : ");
    builder.append("id:").append(id);
    builder.append(", name:").append(name);
    builder.append(", emailAddress:").append(emailAddress);
    builder.append(", oAuthProvider:").append(oAuthProvider);

    return builder.toString();
  }

  public UserAccount(String name, String emailAddress,
      OAuthProviderEnum oAuthProvider) {
    this();
    this.setName(name);
    this.setEmailAddress(emailAddress);
    this.oAuthProvider = oAuthProvider;
  }

  public void setBasicInfo(String name, String emailAddress, String uniqueId) {
    this.name = name;
    this.emailAddress = emailAddress;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public OAuthProviderEnum getOAuthProviderEnum() {
    return oAuthProvider;
  }

  public static UserAccountDTO toDTO(UserAccount user) {
    if (user == null) {
      return null;
    }
    UserAccountDTO accountDTO = new UserAccountDTO(user.getEmailAddress(), user.getName());
    return accountDTO;
  }
}
