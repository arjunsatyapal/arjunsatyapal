package com.arjunsatyapal.practice.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RedirectToRegisterOAuthProviderEvent extends
    GwtEvent<RedirectToRegisterOAuthProviderEventHandler> {
  public static Type<RedirectToRegisterOAuthProviderEventHandler> TYPE =
      new Type<RedirectToRegisterOAuthProviderEventHandler>();

  @Override
  public GwtEvent.Type<RedirectToRegisterOAuthProviderEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(RedirectToRegisterOAuthProviderEventHandler handler) {
    handler.redirectToRegisterOAuthProvider(this);
  }
}
