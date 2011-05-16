package com.arjunsatyapal.practice.gdata;

import com.arjunsatyapal.practice.gdata.clientlogin.ClientLogin;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws ClientProtocolException, IOException {
    String authToken = ClientLogin.getAuthToken();
    System.out.println(authToken);
  }
}
