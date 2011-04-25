package com.arjunsatyapal.practice.gaegwt.ch03loginjava.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ch03loginjava implements EntryPoint {
  public void onModuleLoad() {
    RootPanel.get("login").add(new LoginWidget());
  }
}