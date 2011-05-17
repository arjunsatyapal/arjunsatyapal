package com.arjunsatyapal.practice.gdata.doclist;

import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.GoogleService.CaptchaRequiredException;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.util.AuthenticationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyDocService {
  private final String email;
  private final String password;
  private final UserToken userToken;
  private final DocsService client;

  public String getEmail() {
    return email;
  }

  public UserToken getUserToken() {
    return userToken;
  }

  public String getUserTokenAsString() {
    return userToken.getValue();
  }

  public DocsService getClient() {
    return client;
  }

  public MyDocService(String email, String password) throws AuthenticationException, IOException {
    this.email = email;
    this.password = password;

    client = new DocsService("com.arjunsatyapal.practice.gdata");

    try {
      client.setUserCredentials(email, password);
    } catch (CaptchaRequiredException e) {
      System.out.println("Please visit " + e.getCaptchaUrl());
      System.out.print("Answer to the challenge? ");
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String answer = in.readLine();
      client.setUserCredentials(email, password, e.getCaptchaToken(), answer);
    }

    userToken = (UserToken) client.getAuthTokenFactory().getAuthToken();
  }
}
