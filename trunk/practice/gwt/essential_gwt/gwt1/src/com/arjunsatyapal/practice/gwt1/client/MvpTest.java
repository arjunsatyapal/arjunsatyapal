package com.arjunsatyapal.practice.gwt1.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class MvpTest implements EntryPoint, ValueChangeHandler<String> {
  private String startingToken = "";
  
  @Override
  public void onModuleLoad() {
    // Do all kinds of initializing...
    /*
     * If the application is called with a token, we cannot just jumpt to it;
     * we need to go past the login form first.
     * 
     * After the user has logged in, showMainMenu(...) method --called 
     * in the login callback will take care of jumping to the appropriate place.
     */
    
    String startingToken = History.getToken();

    /*
     * Set up the history management, and start by showing the login form.
     */
    History.addValueChangeHandler(this);
    History.newItem(startingToken, true);
  }

  void showLogin() {
    // show login form
    // after a valid user has logged in
  }
  
  void showMainMenu() {
    // Use user information for menu configuration
    // and create the main screen and menu.
    
    /*
     * If the application was started with a token, now that the user is logged
     * in, its time to show it.
     * 
     * Don't forget to clear startingToken, or after a logout/login we will go back
     * to the token.
     */
    if (!startingToken.isEmpty()) {
      History.newItem(startingToken, true);
      startingToken = "";
    }
  }
  
  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    executeInPanel(RootPanel.get(), event.getValue());
  }
  
  public void executeInPanel(Panel myPanel, String token) {
    if (myPanel == null) {
      myPanel = RootPanel.get();
    }
    
    myPanel.clear();

    // depending on the value of token, do whatever you need.
    if (token.isEmpty()) {
      // show intial screen
    } else if (token.equals("login")) {
      // show login form.
    } else if (token.equals("some")) {
      // show some form.
    } else if (token.equals("other")) {
      // show other form.
    } else {
      Window.alert("Unrecognized token = " + token);
    }
    
  }
}
