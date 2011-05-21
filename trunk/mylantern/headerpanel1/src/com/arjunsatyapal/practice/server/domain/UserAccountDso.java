package com.arjunsatyapal.practice.server.domain;

import com.arjunsatyapal.practice.shared.LoginCategory;
import com.arjunsatyapal.practice.shared.OAuthProviderEnum;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDto;

public class UserAccountDso extends UserAccountDto implements AbstractDso {
  private Long id;
  private String name;
  private String emailAddress;
  private LoginCategory loginCategory;
  private OAuthProviderEnum oAuthProvider;

  public UserAccountDso() {
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("UserAccountDetails : ");
    builder.append("id:").append(id);
    builder.append(", name:").append(name);
    builder.append(", emailAddress:").append(emailAddress);
    builder.append(", loginCategory:").append(loginCategory);
    builder.append(", oAuthProvider:").append(oAuthProvider);

    return builder.toString();
  }

  public UserAccountDso(String name, String emailAddress,
      LoginCategory loginCategory, OAuthProviderEnum oAuthProvider) {
    this();
    this.name = name;
    this.emailAddress = emailAddress;
    this.loginCategory = loginCategory;
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

  public LoginCategory getLoginCategory() {
    return loginCategory;
  }

  public static UserAccountDto toDTO(UserAccountDso user) {
    if (user == null) {
      return null;
    }

    UserAccountDto accountDTO = new UserAccountDto(user.getName(),
        user.getEmailAddress(), user.getLoginCategory());
    return accountDTO;
  }
}
