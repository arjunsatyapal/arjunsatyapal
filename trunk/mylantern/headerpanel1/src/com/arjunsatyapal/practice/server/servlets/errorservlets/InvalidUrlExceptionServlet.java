package com.arjunsatyapal.practice.server.servlets.errorservlets;

import static com.arjunsatyapal.practice.shared.ValidParams.INVALID_URL;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User is redirected here if he reached to an unauthorized zone.
 */
@SuppressWarnings("serial")
public class InvalidUrlExceptionServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    String invalidUrl = req.getParameter(INVALID_URL.getParamKey());
    resp.setContentType("text/plain");
    resp.getWriter().write("Invalid URL : " + invalidUrl);
  }
}
