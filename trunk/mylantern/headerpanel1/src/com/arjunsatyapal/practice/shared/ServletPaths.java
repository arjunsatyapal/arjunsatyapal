package com.arjunsatyapal.practice.shared;

public enum ServletPaths {
  INVALID_URL("/invalidUrl"),
  LOGIN_ADMIN("/loginAdmin"),
  LOGIN_GOOGLE("/loginGoogle"),
  LOGIN_GOOGLE_CALLBACK("/loginGoogleCallback"),
  LOGIN_SERVICE("/loginService"),
  NOT_AUTHORIZED("/notAuthorized");

  private String relativePath;

  public String getRelativePath() {
    return relativePath;
  }

  private ServletPaths(String relativePath) {
    this.relativePath = relativePath;
  }

}
