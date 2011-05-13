package com.arjunsatyapal.gwtfederatedlogin.server.servlets.login;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.arjunsatyapal.gwtfederatedlogin.server.domain.UserAccount;
import com.arjunsatyapal.gwtfederatedlogin.server.utils.ServletHelper;
import com.arjunsatyapal.gwtfederatedlogin.server.utils.ServletUtils;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginHelper extends RemoteServiceServlet {
  private static final long serialVersionUID = 6690557021950311538L;
  private static Logger logger = Logger.getLogger(LoginHelper.class.getName());
  private static HashMap<Long, UserAccount> hashMap = new HashMap<Long, UserAccount>();

  static public String getApplitionURL(HttpServletRequest request) {

    if (ServletHelper.isDevelopment(request)) {
      return "http://127.0.0.1:8888/Gwt_federatedlogin.html?gwt.codesvr=127.0.0.1:9997";
    } else {
      // todo(arjun) : Fix this.
      return ServletUtils.getBaseUrl(request);
    }
  }

  static public UserAccount getLoggedInUser(HttpSession session) {
    boolean localPM = false;

    if (session == null) return null; // user not logged in

    String userId = (String) session.getAttribute("userId");
    if (userId == null) return null; // user not logged in

    Long id = Long.parseLong(userId.trim());

    UserAccount u = hashMap.get(id);
    return u;
  }

  static public boolean isLoggedIn(HttpServletRequest req) {
    if (req == null)
      return false;
    else {
      HttpSession session = req.getSession();
      if (session == null) {
        logger.info("Session is null...");
        return false;
      } else {
        Boolean isLoggedIn = (Boolean) session.getAttribute("loggedin");
        if (isLoggedIn == null) {
          logger
              .info("Session found, but did not find loggedin attribute in it: user not logged in");
          return false;
        } else if (isLoggedIn) {
          logger.info("User is logged in...");
          return true;
        } else {
          logger.info("User not logged in");
          return false;
        }
      }
    }
  }

  public UserAccount loginStarts(HttpSession session, UserAccount user) {
    long id = new Long(user.getAuthenticationProvider().getProviderId());
    user.setId(id);
    session.setAttribute("userId", String.valueOf(user.getId()));
    session.setAttribute("loggedin", true);
    hashMap.put(id, user);
    return user;
  }
}
