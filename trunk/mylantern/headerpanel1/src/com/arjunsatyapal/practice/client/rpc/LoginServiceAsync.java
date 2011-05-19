package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.arjunsatyapal.practice.shared.dtos.UserAccountDTO;

public interface LoginServiceAsync {
  void getLoggedInUserDTO(AsyncCallback<UserAccountDTO> callback);
}
