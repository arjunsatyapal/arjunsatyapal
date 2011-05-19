package com.arjunsatyapal.practice.client;

import com.google.gwt.user.client.History;

import com.arjunsatyapal.practice.client.event.HistoryHandler;

public class AppController {
  public AppController() {
    History.addValueChangeHandler(HistoryHandler.getInstance());
  }

  public void go() {
    String historyToken = History.getToken();
    HistoryHandler.handleNewToken(historyToken);
  }
}
