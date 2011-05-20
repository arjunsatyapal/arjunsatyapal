package com.arjunsatyapal.practice.server.servlets.login;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet to allow admins to Login.
 */
@SuppressWarnings("serial")
public class LoginAdmin extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      resp.setContentType("text/plain");
      resp.getWriter().println("Hello , " + user.getNickname());
      if (userService.isUserAdmin()) {
        resp.getWriter().println("Admin");
      } else {
        resp.getWriter().println("Non-Admin");
      }
    } else {

      resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
    }
  }
}
