package com.arjunsatyapal.practice.client.event;

import com.arjunsatyapal.practice.shared.exceptions.InvalidURLParamsException;

public enum LanternToken {
  HOME("home"), LOGIN("login"), REGISTER_OAUTH_PROVIDER(
      "register_oauth_provider"), RELOAD("reload");

  private String token;

  public String getToken() {
    return token;
  }

  public static LanternToken getHistoryEventCategoryByToken(String token)
    throws InvalidURLParamsException {
    String[] params = token.split("\\?");

    // Check for Login.
    for (LanternToken currEvent : LanternToken.values()) {
      if (currEvent.token.equalsIgnoreCase(params[0])) {
        return currEvent;
      }
    }

    throw new InvalidURLParamsException("Invalid History Token : "
        + token);
  }

  private LanternToken(String token) {
    this.token = token;
  }
}
