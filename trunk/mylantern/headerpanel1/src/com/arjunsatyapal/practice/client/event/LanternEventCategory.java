package com.arjunsatyapal.practice.client.event;

import com.arjunsatyapal.practice.shared.LoginCategory;
import com.arjunsatyapal.practice.shared.exceptions.InvalidURLParamsException;

import java.util.EnumSet;

public enum LanternEventCategory {
  HOME("home", null),
  LOGIN_UI("login", null),

  // Admin only Tokens.
  ADMIN_UI("adminui", EnumSet.of(LoginCategory.ADMIN)),
  REGISTER_OAUTH_PROVIDER("register_oauth_provider", EnumSet.of(LoginCategory.ADMIN));

  private String token;
  private EnumSet<LoginCategory> restrictedToLoginCategories;

  private LanternEventCategory(String token, EnumSet<LoginCategory> restrictedLoginCategories) {
    this.token = token;
    this.restrictedToLoginCategories = restrictedLoginCategories;
  }

  public String getToken() {
    return token;
  }

  public boolean requiresLogin() {
    if (restrictedToLoginCategories == null) {
      return false;
    }

    return true;
  }

  public static LanternEventCategory getHistoryEventCategoryByToken(String token)
      throws InvalidURLParamsException {
    String[] params = token.split("\\?");

    // Check for Login.
    for (LanternEventCategory currEvent : LanternEventCategory.values()) {
      if (currEvent.token.equalsIgnoreCase(params[0])) {
        return currEvent;
      }
    }

    throw new InvalidURLParamsException("Invalid History Token : " + token);
  }


}
