
package com.arjunsatyapal.practice.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.arjunsatyapal.practice.shared.dtos.LoginProviderDto;

public interface LoginProviderServiceAsync {
  void addLoginProvider(LoginProviderDto loginProviderDto,
      AsyncCallback<LoginProviderDto> callback);
}
