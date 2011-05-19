package com.arjunsatyapal.practice.client.gwtui.login;

import com.google.gwt.event.dom.client.HasClickHandlers;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;

public interface LoginDisplay extends Display {
  HasClickHandlers getAolButton();

  HasClickHandlers getFacebookButton();

  HasClickHandlers getGoogleButton();

  HasClickHandlers getMyOpenIdButton();

  HasClickHandlers getTwitterButton();

  HasClickHandlers getYahooButton();
}
