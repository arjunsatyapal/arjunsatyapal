package com.arjunsatyapal.practice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.common.mainpanel.MainPanelPresenter;
import com.arjunsatyapal.practice.client.common.mainpanel.MainPanelView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HeaderPanel implements EntryPoint {
  RootLayoutPanel root;

  @Override
  public void onModuleLoad() {
    root = RootLayoutPanel.get();
    root.clear();
    MainPanelPresenter mainPanelPresenter = new MainPanelPresenter(
        new MainPanelView());
    mainPanelPresenter.go(root);
  }
}
