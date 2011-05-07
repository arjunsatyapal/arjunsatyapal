package com.arjunsatyapal.gwtfederatedlogin.client.login;

import com.google.gwt.event.dom.client.HasClickHandlers;

import com.arjunsatyapal.gwtfederatedlogin.client.common.Display;

public interface LoginDisplay extends Display {
  HasClickHandlers getGoogleButton();

  HasClickHandlers getTwitterButton();

  HasClickHandlers getFacebookButton();
}
