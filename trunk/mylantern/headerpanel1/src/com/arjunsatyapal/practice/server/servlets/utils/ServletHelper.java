package com.arjunsatyapal.practice.server.servlets.utils;

import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.arjunsatyapal.practice.shared.exceptions.NotLoggedInException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
public class ServletHelper extends RemoteServiceServlet {

  private static final long serialVersionUID = -6362867182441202099L;

  public HttpSession getSessionIfLoggedOrThrowException() throws NotLoggedInException {
    HttpSession session = null;
    if (session == null)
      throw new NotLoggedInException();

    @SuppressWarnings("unused")
    Object userIdS = session.getAttribute("UserId");

    if (userIdS == null)
      throw new NotLoggedInException();

    Long userid = new Long((String) session.getAttribute("UserId"));

    if (userid == null || userid == -1)
      throw new NotLoggedInException();

    return session;
  }

  /**
   * @return true if the app is in dev mode, false otherwise.
   * @param request
   */
  public static boolean isDevelopment(HttpServletRequest request) {
    return SystemProperty.environment.value() != SystemProperty.Environment.Value.Production;
  }
}
