package com.arjunsatyapal.gwtfederatedlogin.server.domain;

import com.arjunsatyapal.gwtfederatedlogin.server.servlets.login.AuthenticationProvider;
import com.arjunsatyapal.gwtfederatedlogin.shared.dto.UserAccountDTO;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class UserAccount {
  private Long id;
  private String name;
  private String emailAddress;
  private AuthenticationProvider loginProvider;

  public UserAccount() {
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("UserAccountDetails : ");
    builder.append("id:").append(id);
    builder.append(", name:").append(name);
    builder.append(", emailAddress:").append(emailAddress);
    builder.append(", loginProvider:").append(loginProvider);

    return builder.toString();
  }

  public UserAccount(String name, String emailAddress,
      AuthenticationProvider authenticationProvider) {
    this();
    this.setName(name);
    this.setEmailAddress(emailAddress);
    loginProvider = authenticationProvider;
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

  public AuthenticationProvider getAuthenticationProvider() {
    return loginProvider;
  }

  public static UserAccountDTO toDTO(UserAccount user) {
    if (user == null) {
      return null;
    }
    UserAccountDTO accountDTO = new UserAccountDTO(user.getEmailAddress(), user.getName());
    return accountDTO;
  }
}
