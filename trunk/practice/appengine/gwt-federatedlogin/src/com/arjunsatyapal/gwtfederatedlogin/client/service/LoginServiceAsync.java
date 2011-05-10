// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.gwtfederatedlogin.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.arjunsatyapal.gwtfederatedlogin.shared.dto.UserAccountDTO;

/**
 * @author arjuns@google.com (Your Name Here)
 *
 */
public interface LoginServiceAsync {

  /**
   *
   * @see com.arjunsatyapal.gwtfederatedlogin.client.service.LoginService#getLoggedInUserDTO()
   */
  void getLoggedInUserDTO(AsyncCallback<UserAccountDTO> callback);

}
