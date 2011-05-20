package com.arjunsatyapal.practice.shared;

public enum ValidParams {
  HASH("#"),
  CLIENT_CALLBACK_TOKEN("clientCallbackToken"),
  INVALID_URL("invalidUrl");

  private String paramKey;

  public String getParamKey() {
    return paramKey;
  }

  private ValidParams(String paramKey) {
    this.paramKey = paramKey;
  }
}
