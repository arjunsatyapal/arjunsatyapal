package com.arjunsatyapal.practice.gwt1.client;

import com.arjunsatyapal.practice.gwt1.client.rpc.LoginService;
import com.arjunsatyapal.practice.gwt1.client.rpc.LoginServiceAsync;
import com.arjunsatyapal.practice.gwt1.client.rpc.XhrProxy;
import com.arjunsatyapal.practice.gwt1.client.rpc.XhrProxyAsync;
import com.google.gwt.core.client.GWT;

/**
 * @author fkereki
 */
public class Model {
  private LoginServiceAsync loginService;
  private XhrProxyAsync xhrProxy;

  
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

  /**
   * Provide a remote XHR proxy handle; Use lazy evaluation for extra speed.
   * 
   * @return
   */
  public XhrProxyAsync getRemoteXhrProxy() {
    if (xhrProxy == null) {
      xhrProxy = GWT.create(XhrProxy.class);
    }
    return xhrProxy;
  }
}