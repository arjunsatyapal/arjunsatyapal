package com.arjunsatyapal.practice.shared.dtos;

import com.arjunsatyapal.practice.shared.LoginCategory;

public class UserAccountDto implements AbstractDto {
  private static final long serialVersionUID = 7211918275846841369L;
  private String id;
  private String name;
  private String emailAddress;
  // This category is assigned by Server to the user.
  private LoginCategory loginCategory;

  @Override
  public StringBuilder getStringBuilder() {
    StringBuilder builder = new StringBuilder("UserAccountDto[");
    builder.append("id:").append(id);
    builder.append(", name:").append(name);
    builder.append(", emailAddress:").append(emailAddress);
    return builder;
  }

  @Override
  public String toString() {
    return getStringBuilder().toString();
  }

  protected UserAccountDto() {
  }

  @Override
  public String validate() {
    StringBuilder builder = new StringBuilder("");

    // id will be set only by server. So not checking it.

    if (name.isEmpty()) {
      builder.append("name is empty.\n");
    }
    if (emailAddress.isEmpty()) {
      builder.append("emailAddress is empty.\n");
    }
    if (loginCategory == null) {
      builder.append("loginCategory is null.\n");
    }

    return builder.toString();
  }

  public String getId() {
    return id;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getName() {
    return name;
  }

  public LoginCategory getLoginCategory() {
    return loginCategory;
  }

  public static class Builder {
    private String id;
    private String name;
    private String emailAddress;
    private LoginCategory loginCategory;

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
      return this;
    }

    public Builder setLoginCategory(LoginCategory loginCategory) {
      this.loginCategory = loginCategory;
      return this;
    }

    public UserAccountDto build() {
      UserAccountDto dto = new UserAccountDto();
      dto.id = this.id;
      dto.name = this.name;
      dto.emailAddress = this.emailAddress;
      dto.loginCategory = this.loginCategory;
      return dto;
    }
  }
}
