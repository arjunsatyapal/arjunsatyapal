package com.arjunsatyapal.practice.server.servlets.errorservlets;

import static com.arjunsatyapal.practice.shared.ValidParams.INVALID_URL;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User is redirected here if he reached to an unauthorized zone.
 */
public class NotAuthorizedExceptionServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    String invalidUrl = req.getParameter(INVALID_URL.getParamKey());
    resp.setContentType("text/plain");
    String email = "user";
    if (user != null) {
      email= user.getEmail();
    }
    resp.getWriter().write(email + " is not allowed to access : " + invalidUrl);
  }
}
