package com.arjunsatyapal.practice.shared.dtos;

import com.arjunsatyapal.practice.shared.LoginCategory;

import java.io.Serializable;

public class UserAccountDTO implements Serializable {
  private static final long serialVersionUID = 7211918275846841369L;
  private String id;
  private String name;
  private String emailAddress;
  // This category is assigned by Server to the user.
  private LoginCategory loginCategory;

  public UserAccountDTO() {
  }

  public UserAccountDTO(String name, String emailAddress, LoginCategory loginCategory) {
    this.name = name;
    this.emailAddress = emailAddress;
    this.loginCategory = loginCategory;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LoginCategory getLoginCategory() {
    return loginCategory;
  }
}
