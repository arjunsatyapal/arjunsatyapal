package com.arjunsatyapal.practice.client.history;

public enum HistoryEvent {
  HOME("home"),
  LOGIN("login"),
  REGISTER_OAUTH_PROVIDER("register_oauth_provider"),
  RELOAD("reload");

  private String token;

  public String getToken() {
    return token;
  }

  public static HistoryEvent getHistoryEventByToken(String token) {
    // Check for Login.
    for (HistoryEvent currEvent : HistoryEvent.values()) {
      if (currEvent.token.equalsIgnoreCase(token)) {
        return currEvent;
      }
    }

    return null;
  }

  private HistoryEvent(String token) {
    this.token = token;
  }
}
