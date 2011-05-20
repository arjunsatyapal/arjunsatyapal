package com.arjunsatyapal.practice.client.event;

import com.arjunsatyapal.practice.shared.exceptions.InvalidURLParamsException;

public enum LanternEvents {
  HOME("home"), LOGIN("login"), REGISTER_OAUTH_PROVIDER(
      "register_oauth_provider"), RELOAD("reload");

  private String token;

  public String getToken() {
    return token;
  }

  public static LanternEvents getHistoryEventCategoryByToken(String historyToken)
    throws InvalidURLParamsException {
    String[] params = historyToken.split("\\?");

    // Check for Login.
    for (LanternEvents currEvent : LanternEvents.values()) {
      if (currEvent.token.equalsIgnoreCase(params[0])) {
        return currEvent;
      }
    }

    throw new InvalidURLParamsException("Invalid History Token : "
        + historyToken);
  }

  private LanternEvents(String token) {
    this.token = token;
  }
}
