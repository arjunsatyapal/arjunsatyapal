// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.arjunsatyapal.practice.shared.dtos.UserAccountDTO;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public interface LoginServiceAsync {

  /**
   * 
   * @see com.arjunsatyapal.practice.client.rpc.LoginService#getLoggedInUserDTO()
   */
  void getLoggedInUserDTO(AsyncCallback<UserAccountDTO> callback);

}
