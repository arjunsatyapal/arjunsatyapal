package com.arjunsatyapal.gwtfederatedlogin.client.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.gwtfederatedlogin.client.common.Presenter;

public class LoginPresenter implements Presenter {
  private final LoginDisplay loginDisplay;


  public LoginPresenter(LoginDisplay display) {
    this.loginDisplay = display;
  }

  public void bind() {
    this.loginDisplay.getGoogleButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginGoogle();
      }
    });

    this.loginDisplay.getTwitterButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginTwitter();
      }
    });

    this.loginDisplay.getFacebookButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginFacebook();
      }
    });
  }

  @Override
  public void go(final HasWidgets container) {
    container.clear();
    container.add(loginDisplay.asWidget());
    bind();
  }

  private void doLoginFacebook() {
    Window.Location.assign("/loginfacebook");
  }

  private void doLoginGoogle() {
    Window.Location.assign("/logingoogle");
  }

  private void doLoginTwitter() {
    Window.Location.assign("/logintwitter");
  }

}
