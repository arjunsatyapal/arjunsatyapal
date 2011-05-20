package com.arjunsatyapal.practice.shared;

public enum ValidParams {
  HASH("#"),
  REDIRECT_HASH("redirectHash");

  private String paramKey;

  public String getParamKey() {
    return paramKey;
  }

  private ValidParams(String paramKey) {
    this.paramKey = paramKey;
  }
}
