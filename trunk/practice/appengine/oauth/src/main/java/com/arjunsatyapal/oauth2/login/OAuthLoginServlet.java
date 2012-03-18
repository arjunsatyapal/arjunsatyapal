package com.arjunsatyapal.oauth2.login;

import com.google.api.client.http.BasicAuthentication;

import com.google.api.client.json.JsonFactory;

import com.google.api.client.http.HttpTransport;

import javax.servlet.http.HttpServlet;

import com.google.api.client.auth.oauth2.BearerToken;

import com.google.api.client.json.jackson.JacksonFactory;

import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.http.GenericUrl;

import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public class OAuthLoginServlet extends HttpServlet {
  private static final Logger logger = Logger.getLogger(OAuthLoginServlet.class.getName());

  /** OAuth 2 scope. */
  public static final String[] SCOPE = {
    "https://www.googleapis.com/auth/userinfo.email",
    "https://www.googleapis.com/auth/userinfo.profile",
  };

  /** Global instance of the HTTP transport. */
  public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  /** Global instance of the JSON factory. */
  public static final JsonFactory JSON_FACTORY = new JacksonFactory();

  public static final String TOKEN_SERVER_URL = "https://accounts.google.com/o/oauth2/token";
  public static final String AUTHORIZATION_SERVER_URL =
      "https://accounts.google.com/o/oauth2/auth";
  public static final String CLIENT_ID = "client.id";
  public static final String CLIENT_SECRET = "client.secret";
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(
        BearerToken.authorizationHeaderAccessMethod(),
        HTTP_TRANSPORT,
        JSON_FACTORY,
        new GenericUrl(TOKEN_SERVER_URL), 
        new BasicAuthentication(CLIENT_ID, CLIENT_SECRET),
        CLIENT_ID,
        AUTHORIZATION_SERVER_URL).setScopes(SCOPE).build();

    String redirectUri = "http://localhost:8888/oauthLoginCallback";
    
    String actualRedirectUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri).build();
    logger.info("actualRedirectUrl = " + actualRedirectUrl);
    response.sendRedirect(actualRedirectUrl);
  }
}
