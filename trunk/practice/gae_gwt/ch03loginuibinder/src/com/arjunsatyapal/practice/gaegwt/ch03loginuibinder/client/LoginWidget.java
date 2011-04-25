/**
 * 
 */
package com.arjunsatyapal.practice.gaegwt.ch03loginuibinder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author arjunsatyapal
 */
public class LoginWidget extends Composite {
  
  private static LoginUiBinderUiBinder uiBinder = GWT
    .create(LoginUiBinderUiBinder.class);
  
  interface LoginUiBinderUiBinder extends UiBinder<Widget, LoginWidget> {
  }
  
  public LoginWidget() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  @UiHandler("loginButton")
  void onLoginClick(ClickEvent e) {
    Window.alert("You clicked on Login.");
  }
}
