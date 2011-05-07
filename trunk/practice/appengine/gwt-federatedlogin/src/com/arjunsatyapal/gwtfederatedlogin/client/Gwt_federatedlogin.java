package com.arjunsatyapal.gwtfederatedlogin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.gwtfederatedlogin.client.login.LoginPresenter;
import com.arjunsatyapal.gwtfederatedlogin.client.login.LoginView;

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
