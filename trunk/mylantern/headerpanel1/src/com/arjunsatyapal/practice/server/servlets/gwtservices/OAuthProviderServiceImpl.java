package com.arjunsatyapal.practice.server.servlets.gwtservices;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.arjunsatyapal.practice.client.rpc.OAuthProviderService;
import com.arjunsatyapal.practice.shared.OAuthProviderEnum;
import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;

import java.util.ArrayList;

public class OAuthProviderServiceImpl extends RemoteServiceServlet implements
    OAuthProviderService {
  @Override
  public OAuthProviderDto addOAuthProvider(OAuthProviderDto oAuthProviderDto) {
    return new OAuthProviderDto.Builder().setOAuthProvider("arjun")
        .setConsumerKey("key").setConsumerSecret("secret").build();
  }

  // TODO : See if unauthorized exception can be thrown as exception and handled on client side.
  // TODO : see if servlet persists data across calls.
  @Override
  public ArrayList<String> getOAuthProviderList() {
    ArrayList<String> list = new ArrayList<String>();
    UserService userService = UserServiceFactory.getUserService();
    if (!userService.isUserAdmin()) {
      list.add("Unauthorized.");
      return list;
    }

    for (OAuthProviderEnum currEnum : OAuthProviderEnum.values()) {
      list.add(currEnum.name());
    }
    return list;
  }
}
