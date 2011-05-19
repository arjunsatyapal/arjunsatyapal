package com.arjunsatyapal.practice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.history.HistoryHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HeaderPanel implements EntryPoint {
  RootLayoutPanel root;

  @Override
  public void onModuleLoad() {
    History.addValueChangeHandler(HistoryHandler.getInstance());

    HistoryHandler.loginIfRequired(History.getToken());
  }
}
