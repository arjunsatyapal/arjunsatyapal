package com.arjunsatyapal.oauth2.login;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class OAuthLoginCallbackServlet extends HttpServlet {
  private static final Logger logger = Logger.getLogger(OAuthLoginCallbackServlet.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    logger.info("requestUrl = " + request.getRequestURL());
    logger.info("requestUri = " + request.getRequestURI());
    StringBuffer buf = request.getRequestURL();
    if (request.getQueryString() != null) {
      buf.append('?').append(request.getQueryString());
    }
    logger.info(buf.toString());
    AuthorizationCodeResponseUrl responseUrl = new AuthorizationCodeResponseUrl(buf.toString());
    String code = responseUrl.getCode();
    logger.info("code = " + code);
    if (responseUrl.getError() != null) {
      onError(request, response, responseUrl);
    } else if (code == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().print("Missing authorization code");
    } else {
//      String redirectUri = "http://localhost:8888/oauthLoginCallback";
//      
      HttpRequestFactory requestFactory = OAuthLoginServlet.HTTP_TRANSPORT.createRequestFactory();
//     
//      Map<String, String> map = ImmutableMap.<String, String>builder()
//          .put("code", code)
//          .put("client_id", CLIENT_ID)
//          .put("client_secret", CLIENT_SECRET)
//          .put("redirect_uri", redirectUri)
//          .put("grant_type", "authorization_code")
//          .put("scope", SCOPE)
//          .build();
//          
//      
//      UrlEncodedContent content = new UrlEncodedContent(map);
//      HttpRequest tokenRequest = requestFactory.buildPostRequest(new GenericUrl(TOKEN_SERVER_URL), content);
//      HttpResponse tokenResponse = tokenRequest.execute();
      
      AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(
          BearerToken.authorizationHeaderAccessMethod(),
          OAuthLoginServlet.HTTP_TRANSPORT,
          OAuthLoginServlet.JSON_FACTORY,
          new GenericUrl(OAuthLoginServlet.TOKEN_SERVER_URL), 
          new ClientParametersAuthentication(OAuthLoginServlet.CLIENT_ID, OAuthLoginServlet.CLIENT_SECRET),
          OAuthLoginServlet.CLIENT_ID,
          OAuthLoginServlet.AUTHORIZATION_SERVER_URL).setScopes(OAuthLoginServlet.SCOPE).build();
      String redirectUri = "http://localhost:8888/oauthLoginCallback";
      
      TokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
      
      logger.info("accessToken = " + tokenResponse.getAccessToken());
      String encodedToken = URLEncoder.encode(tokenResponse.getAccessToken(), Charsets.UTF_8.displayName()); 

      GenericUrl tokenUrl = new GenericUrl("https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=" + encodedToken);
      logger.info("tempUrl = " + tokenUrl);
      HttpRequest tokenInfoRequest = requestFactory.buildGetRequest(tokenUrl);
      HttpResponse tokenInfoResponse = tokenInfoRequest.execute();
          
      String tokenInfo = CharStreams.toString(new InputStreamReader(tokenInfoResponse.getContent(), Charsets.UTF_8));
      logger.info("UserInfoResponse = \n" + tokenInfo);
      
      GenericUrl userInfoUrl = new GenericUrl("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + encodedToken);
      HttpRequest userInfoRequest = requestFactory.buildGetRequest(userInfoUrl);
      
      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", encodedToken);
      userInfoRequest.setHeaders(headers);
      
      HttpResponse userInfoResponse = userInfoRequest.execute();
      String userInfoString = CharStreams.toString(new InputStreamReader(userInfoResponse.getContent(), Charsets.UTF_8));
      logger.info("UserInfoResponse = \n" + userInfoString);
      
      onSuccess(request, response);
    }
  }

  /**
   * @param request
   * @param response
   * @throws IOException
   */
  private void onSuccess(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.getWriter().println("success");
  }

  /**
   * @param request
   * @param response
   * @param responseUrl
   * @throws IOException
   */
  private void onError(HttpServletRequest request, HttpServletResponse response,
      AuthorizationCodeResponseUrl responseUrl) throws IOException {
    // TODO(arjuns): Auto-generated method stub

    response.getWriter().println("error.");
  }
}
