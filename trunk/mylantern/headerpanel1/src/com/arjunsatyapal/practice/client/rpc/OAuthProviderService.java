package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.arjunsatyapal.practice.shared.dtos.OAuthProviderDto;

import java.util.ArrayList;

@RemoteServiceRelativePath("admin/oAuthProviderService")
public interface OAuthProviderService extends RemoteService {
  ArrayList<String> getOAuthProviderList();
  OAuthProviderDto addOAuthProvider(OAuthProviderDto oAuthProviderDto);
}
