package com.arjunsatyapal.practice.client.event;

public enum LanternEvents {
  HOME("home"),
  LOGIN("login"),
  REGISTER_OAUTH_PROVIDER("register_oauth_provider"),
  RELOAD("reload");

  private String token;

  public String getToken() {
    return token;
  }

  public static LanternEvents getHistoryEventByToken(String token) {
    // Check for Login.
    for (LanternEvents currEvent : LanternEvents.values()) {
      if (currEvent.token.equalsIgnoreCase(token)) {
        return currEvent;
      }
    }

    return null;
  }

  private LanternEvents(String token) {
    this.token = token;
  }
}
