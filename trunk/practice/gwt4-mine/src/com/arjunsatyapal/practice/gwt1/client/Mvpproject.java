package com.arjunsatyapal.practice.gwt1.client;

import com.arjunsatyapal.practice.gwt1.client.login.LoginFormPresenter;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

public class Mvpproject implements EntryPoint{
  public static native String getUserAgent() /*-{
    return navigator.userAgent.toLowerCase();
  }-*/;

  private void showBrowser() {
    if (getUserAgent().toLowerCase().contains("chrome")) {
      showAlert("You are using chrome.");
    } else if (getUserAgent().toLowerCase().contains("gecko")) {
      showAlert("You are using Firefox.");
    } else {
      showAlert("unknown browser");
    }
  }
  
  Environment environment;

  public void onModuleLoad() {
    DOM.removeChild(RootPanel.getBodyElement(), DOM
        .getElementById("loading"));
    environment = new Environment(new Model(), History.getToken());
    environment.launch(LoginFormPresenter.PLACE);
  }

  
  final Grid rootDisplay = new Grid(2, 10);
  final MenuBar runMenuBar = new MenuBar(true);
  final HorizontalPanel runPanel = new HorizontalPanel();

  private void showMainMenu() {
    // TODO Use user information for menu configuration

    runMenuBar.clearItems();
    runMenuBar.setWidth("1%");
    createMenu(runMenuBar);

    rootDisplay.setWidth("100%");
    rootDisplay.setWidget(0, 0, runMenuBar);
    rootDisplay.setWidget(1, 0, runPanel);

    RootPanel.get().clear();
    RootPanel.get().add(rootDisplay);
  }
  
  private void createMenu(final MenuBar mb) {
    final MenuBar mb2 = new MenuBar(true);
    mb2.addItem("subitem1", sorry);
    mb2.addItem("subitem2", sorry);
    mb2.addItem("subitem3", sorry);
    mb2.addItem("subitem4", sorry);
    mb.addItem("submenu", mb2);

    final MenuBar mb3 = new MenuBar(true);
    mb.addItem("Cities", mb3);
    mb3.addItem("city1", sorry);
//    mb3.addItem("Delhi", new Hyperlink("asdf", "xyz"))
  }

  Command sorry = new Command() {
    @Override
    public void execute() {
      showAlert("Sorry, this isn't ready yet.");
    }
  };
  
  public void showAlert(final String alertText) {
    Window.alert(alertText);
  }
}