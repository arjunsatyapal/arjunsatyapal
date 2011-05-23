package com.arjunsatyapal.practice.server.servlets.login;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.arjunsatyapal.practice.server.domain.UserAccountDao;
import com.arjunsatyapal.practice.server.servlets.utils.ServletHelper;
import com.arjunsatyapal.practice.server.servlets.utils.ServletUtils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginHelper extends RemoteServiceServlet {
  private static final long serialVersionUID = 6690557021950311538L;
  private static Logger logger = Logger.getLogger(LoginHelper.class.getName());
  private static HashMap<Long, UserAccountDao> hashMap =
    new HashMap<Long, UserAccountDao>();
  
  static public String getApplicationURL(HttpServletRequest request) {
    
    if (ServletHelper.isDevelopment(request)) {
      return "http://127.0.0.1:8888/Headerpanel.html?gwt.codesvr=127.0.0.1:9997";
    } else {
      // TODO(arjuns) : Fix this.
      return ServletUtils.getBaseUrl(request);
    }
  }
  
  static public UserAccountDao getLoggedInUser(HttpSession session) {
    if (session == null)
      return null; // user not logged in
      
    String userId = (String) session.getAttribute("userId");
    if (userId == null)
      return null; // user not logged in
      
    Long id = Long.parseLong(userId.trim());
    
    UserAccountDao u = hashMap.get(id);
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
  
  public UserAccountDao loginStarts(HttpSession session,
    UserAccountDao userAccountDao) {
    // TODO(arjuns) : See more here for the session management. Now doing hack
    // with putting long as hashId of email.
    // long id = new Long(user.getOAuthProviderEnum().getProviderId());
    long id = new Long(userAccountDao.getEmailAddress().hashCode());
    userAccountDao.setId(Long.toString(id));
    session.setAttribute("userId", String.valueOf(userAccountDao.getId()));
    session.setAttribute("loggedin", true);
    hashMap.put(id, userAccountDao);
    return userAccountDao;
  }
}
