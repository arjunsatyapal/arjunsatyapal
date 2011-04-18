package com.arjunsatyapal.practice.gwt1.client;

import com.arjunsatyapal.practice.gwt1.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt1 implements EntryPoint, ValueChangeHandler<String> {
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    String startingToken = History.getToken();

    /*
     * Set up the history management, and start by showing the login form.
     */
    History.addValueChangeHandler(this);
    History.newItem(startingToken, true);
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    executeInPanel(RootPanel.get(), event.getValue());
  }

  private void executeInPanel(RootPanel panel, String token) {
    String args = "";
    int question = token.indexOf("?");
    if (question != -1) {
      args = token.substring(question + 1);
      token = token.substring(0, question);
    }
  }
}
