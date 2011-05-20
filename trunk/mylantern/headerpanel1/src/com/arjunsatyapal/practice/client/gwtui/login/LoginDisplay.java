package com.arjunsatyapal.practice.client.gwtui.login;

import com.google.gwt.user.client.ui.PushButton;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;

public interface LoginDisplay extends Display {
  PushButton getAolButton();

  PushButton getFacebookButton();

  PushButton getGoogleButton();

  PushButton getMyOpenIdButton();

  PushButton getTwitterButton();

  PushButton getYahooButton();
}
