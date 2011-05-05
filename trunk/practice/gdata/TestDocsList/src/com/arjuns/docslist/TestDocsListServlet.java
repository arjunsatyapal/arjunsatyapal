package com.arjuns.docslist;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.docs.*;
import com.google.gdata.client.authn.oauth.*;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TestDocsListServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    if (user != null) {
      resp.setContentType("text/plain");
      resp.getWriter().println("Hello, " + user.getNickname());
    } else {
      resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
    }

    // App Secrets.
    String CONSUMER_KEY = "  arjunsatyapaldocslist2.appspot.com";
    String CONSUMER_SECRET = "W-N9BFFJHLLxgahIdV2poVDN";

    // Fetching a Request Token.
    GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
    oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
    oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
    oauthParameters.setScope("https://docs.google.com/feeds/");
    oauthParameters.setOAuthCallback("http://arjunsataypaldocslist2.appspot.com/testdocslist");

    GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(new OAuthHmacSha1Signer());
    try {
      oauthHelper.getUnauthorizedRequestToken(oauthParameters);

    String approvalPageUrl = oauthHelper.createUserAuthorizationUrl(oauthParameters);
    System.out.println(approvalPageUrl);


    oauthHelper.getOAuthParametersFromCallback(req.getQueryString(), oauthParameters);
    String accessToken = oauthHelper.getAccessToken(oauthParameters);
      // You can also pull the OAuth token string from the oauthParameters:
      // String accessToken = oauthParameters.getOAuthToken();
      System.out.println("OAuth Access Token: " + accessToken);

      String accessTokenSecret = oauthParameters.getOAuthTokenSecret();
      System.out.println("OAuth Access Token's Secret: " + accessTokenSecret);

      GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
      oauthParameters.setOAuthConsumerKey(CONSUMER_KEY);
      oauthParameters.setOAuthConsumerSecret(CONSUMER_SECRET);
      oauthParameters.setOAuthToken(ACCESS_TOKEN);
      oauthParameters.setOAuthTokenSecret(TOKEN_SECRET);

      DocsService client = new DocsService("yourCompany-YourAppName-v1");
      client.setOAuthCredentials(oauthParameters, new OAuthHmacSha1Signer());

      URL feedUrl = new URL("https://docs.google.com/feeds/default/private/full");
      DocumentListFeed resultFeed = client.getFeed(feedUrl, DocumentListFeed.class);
      for (DocumentListEntry entry : resultFeed.getEntries()) {
        System.out.println(entry.getTitle().getPlainText());
      }
    } catch (OAuthException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
