package com.arjunsatyapal.practice.server.domain;

import com.arjunsatyapal.practice.shared.LoginCategory;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDto;
import com.arjunsatyapal.practice.shared.exceptions.InvalidClientInputException;

public class UserAccountDso implements AbstractDso {
  private static final long serialVersionUID = 7211918275846841369L;
  private String id;
  private String name;
  private String emailAddress;
  private LoginCategory loginCategory;

  @Override
  public StringBuilder getStringBuilder() {
    StringBuilder builder = new StringBuilder("UserAccountDso[");
    builder.append("id:").append(id);
    builder.append(", name:").append(name);
    builder.append(", emailAddress:").append(emailAddress);
    return builder;
  }

  @Override
  public String toString() {
    return getStringBuilder().toString();
  }

  private UserAccountDso() {
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

  // TODO(arjuns) : This should be probably temporary. See if it can be removed.
  public void setId(String id) {
    this.id = id;
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

  public static UserAccountDso fromUserAccountDto(UserAccountDto dto) {
    UserAccountDso userAccountDso = new UserAccountDso();

    if (!dto.validate().isEmpty()) {
      throw new InvalidClientInputException(
          "Invalid UserAccountDto with Error : [" + dto.validate() + "].");
    }
    if (dto.getId() != null) {
      throw new InvalidClientInputException(
          "Invalid Id[" + dto.getId() + "]. Only server is allowed to set the id.");
    }
    if (dto.getLoginCategory() != null) {
      throw new InvalidClientInputException("Invalid LoginCategory[" + dto.getLoginCategory()
          + "]. Only server is allowed to set the loginCategory.");
    }

    userAccountDso.name = dto.getName();
    userAccountDso.emailAddress = dto.getEmailAddress();
    return userAccountDso;
  }

  public UserAccountDto toUserAccountDto() {
    return new UserAccountDto.Builder()
        .setId(id)
        .setName(name)
        .setEmailAddress(emailAddress)
        .setLoginCategory(loginCategory)
        .build();
  }
}
