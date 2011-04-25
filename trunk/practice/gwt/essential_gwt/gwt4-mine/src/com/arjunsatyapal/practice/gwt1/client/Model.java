package com.arjunsatyapal.practice.gwt1.client;

import com.arjunsatyapal.practice.gwt1.client.rpc.LoginService;
import com.arjunsatyapal.practice.gwt1.client.rpc.LoginServiceAsync;
import com.google.gwt.core.client.GWT;

/**
 * @author fkereki
 */
public class Model {
  private LoginServiceAsync loginService;

  
  /**
   * Provide a remote login service handle; Use lazy evaluation for extra speed.
   * 
   * @return
   */
  public LoginServiceAsync getRemoteLoginService() {
    if (loginService == null) {
      loginService = GWT.create(LoginService.class);
    }
    return loginService;
  }
}