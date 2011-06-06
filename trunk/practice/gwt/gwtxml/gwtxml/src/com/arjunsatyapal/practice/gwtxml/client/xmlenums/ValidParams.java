package com.arjunsatyapal.practice.gwtxml.client.xmlenums;

public enum ValidParams {
  HASH("#"),
  CLIENT_CALLBACK_TOKEN("clientCallbackToken"),
  INVALID_URL("invalidUrl"),
  APPLICATION_ID("applicationId");

  private String paramKey;

  public String getParamKey() {
    return paramKey;
  }

  private ValidParams(String paramKey) {
    this.paramKey = paramKey;
  }
}