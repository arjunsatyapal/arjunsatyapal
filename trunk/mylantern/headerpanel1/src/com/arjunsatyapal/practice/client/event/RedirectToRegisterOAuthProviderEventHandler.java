package com.arjunsatyapal.practice.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface RedirectToRegisterOAuthProviderEventHandler extends EventHandler {
  void redirectToRegisterOAuthProvider(RedirectToRegisterOAuthProviderEvent event);
}
