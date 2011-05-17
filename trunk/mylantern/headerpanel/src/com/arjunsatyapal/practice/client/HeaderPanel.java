package com.arjunsatyapal.practice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.headerpanel.HeaderPanelPresenter;
import com.arjunsatyapal.practice.client.headerpanel.HeaderPanelView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HeaderPanel implements EntryPoint {
  RootLayoutPanel root;

  @Override
  public void onModuleLoad() {
    root = RootLayoutPanel.get();
    root.clear();
    HeaderPanelPresenter headerPanelPresenter = new HeaderPanelPresenter(
        new HeaderPanelView());
    headerPanelPresenter.go(root);
  }
}
