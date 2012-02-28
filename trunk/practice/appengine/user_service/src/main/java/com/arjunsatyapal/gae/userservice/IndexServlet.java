package com.arjunsatyapal.gae.userservice;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();


    if (user != null) {
      StringBuilder builder = new StringBuilder();
      builder.append("<br> AuthDomain = " + user.getAuthDomain());
      builder.append("<br> Email = " + user.getEmail());
      builder.append("<br> FederatedIdentity = " + user.getFederatedIdentity());
      builder.append("<br> NickName = " + user.getNickname());
      builder.append("<br> UserId = " + user.getUserId() + "<br>");
      
      resp.setContentType("text/html");
      resp.getWriter().println(builder.toString());
    } else {
      resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
    }
  }
}
