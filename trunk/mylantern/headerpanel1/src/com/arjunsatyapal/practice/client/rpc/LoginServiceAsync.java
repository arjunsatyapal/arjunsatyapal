package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.arjunsatyapal.practice.shared.dtos.UserAccountDto;

public interface LoginServiceAsync {
  void getLoggedInUserDTO(AsyncCallback<UserAccountDto> callback);
}
