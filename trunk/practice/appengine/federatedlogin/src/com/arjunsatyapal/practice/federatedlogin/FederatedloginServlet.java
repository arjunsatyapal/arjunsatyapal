package com.arjunsatyapal.practice.federatedlogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class FederatedloginServlet extends HttpServlet {
  private static Logger log = Logger.getLogger(FederatedloginServlet.class
      .getName());

  private static final Map<String, String> openIdProviders;
  static {
    openIdProviders = new HashMap<String, String>();
    openIdProviders.put("Google", "google.com/accounts/o8/id");
    openIdProviders.put("Yahoo", "yahoo.com");
    openIdProviders.put("MySpace", "myspace.com");
    openIdProviders.put("AOL", "aol.com");
    openIdProviders.put("MyOpenId.com", "myopenid.com");
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser(); // or req.getUserPrincipal()
    Set<String> attributes = new HashSet<String>();

    resp.setContentType("text/html");
    
    StringBuilder stringBuilder = new StringBuilder();
    PrintWriter out = resp.getWriter();

    if (user != null) {
      stringBuilder.append("<br>UserId = " + user.getUserId());
      stringBuilder.append("<br>Email = " + user.getEmail());
      stringBuilder.append("<br>AuthDomain = " + user.getAuthDomain());
      stringBuilder.append("<br>FederatedIdentity = " + user.getFederatedIdentity());
      stringBuilder.append("<br>NickName = " + user.getNickname() + "<br>");

      out.println(stringBuilder.toString());
      out
          .println("[<a href=\""
              + userService.createLogoutURL(req.getRequestURI())
              + "\">sign out</a>]");
    } else {
      out.println("Hello world123! Sign in at: ");
      for (String providerName : openIdProviders.keySet()) {
        String providerUrl = openIdProviders.get(providerName);
        String loginUrl =
            userService.createLoginURL(req.getRequestURI(), null, providerUrl,
                attributes);
        StringBuilder builder = new StringBuilder();
        builder.append("providerUrl : ").append(openIdProviders.get(providerName));
        builder.append(", requestURI : ").append(req.getRequestURI());
        builder.append(", attributes : ").append(attributes);
        builder.append(", loginUrl : ").append(loginUrl);
        log.severe("Hello : " + builder.toString());
        out.println("[<a href=\"" + loginUrl + "\">" + providerName + "</a>] ");
      }
    }
  }
}
