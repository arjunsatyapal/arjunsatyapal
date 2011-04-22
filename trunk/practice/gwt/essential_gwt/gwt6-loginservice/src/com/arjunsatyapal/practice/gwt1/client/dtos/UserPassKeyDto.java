package com.arjunsatyapal.practice.gwt1.client.dtos;

public class UserPassKeyDto {
  public String user;
  public String pass;
  public String key;

  public UserPassKeyDto() {
  }

  public UserPassKeyDto(final String u, final String p, final String k) {
    user = u;
    pass = p;
    key = k;
  }
}
