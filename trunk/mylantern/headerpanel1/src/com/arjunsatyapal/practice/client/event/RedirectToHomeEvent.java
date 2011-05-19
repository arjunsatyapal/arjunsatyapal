
package com.arjunsatyapal.practice.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RedirectToHomeEvent extends GwtEvent<RedirectToHomeEventHandler> {
  public static Type<RedirectToHomeEventHandler> TYPE = new Type<RedirectToHomeEventHandler>();
  @Override
  public GwtEvent.Type<RedirectToHomeEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(RedirectToHomeEventHandler handler) {
    handler.redirectToHome(this);
  }
}
