package com.arjunsatyapal.practice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HeaderPanel implements EntryPoint {
  RootLayoutPanel root;

  @Override
  public void onModuleLoad() {
    AppController appController = new AppController();
    appController.go();
  }
}
