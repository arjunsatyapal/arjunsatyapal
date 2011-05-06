package com.google.lantern.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.lantern.client.login.LoginPresenter;
import com.google.lantern.client.login.LoginView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_federatedlogin implements EntryPoint {
  RootLayoutPanel root;

  /**
   * This is the entry point method.
   */
  @Override
  public void onModuleLoad() {
    showLoginView();
  }
  public void showLoginView() {
    root = RootLayoutPanel.get();
    root.clear();
    LoginPresenter loginPresenter = new LoginPresenter(new LoginView());
    loginPresenter.go(root);
  }
}
