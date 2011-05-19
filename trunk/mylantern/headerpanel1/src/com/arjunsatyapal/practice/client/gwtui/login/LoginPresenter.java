package com.arjunsatyapal.practice.client.gwtui.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.event.LanternEvents;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.shared.StringConstants;

public class LoginPresenter extends Presenter {
  private final LoginDisplay loginDisplay;

  public LoginPresenter(LoginDisplay loginDisplay) {
    super(loginDisplay.getLanternHeaderPanel());
    this.loginDisplay = loginDisplay;
  }

  @Override
  public void bind() {
    this.loginDisplay.getAolButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginAol();
      }
    });

    this.loginDisplay.getFacebookButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginFacebook();
      }
    });

    this.loginDisplay.getGoogleButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginGoogle();
      }
    });

    this.loginDisplay.getMyOpenIdButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginMyOpenId();
      }
    });

    this.loginDisplay.getTwitterButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginTwitter();
      }
    });

    this.loginDisplay.getYahooButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginYahoo();
      }
    });

  }

  @Override
  public void go(final HasWidgets container) {
    container.clear();
    container.add(loginDisplay.asWidget());
    bind();
  }

  private void doLoginAol() {
    Window.Location.assign("/loginAol");
  }

  private void doLoginFacebook() {
    Window.Location.assign("/loginFacebook");
  }

  private void doLoginGoogle() {
    Window.Location.assign("/loginGoogle");
  }

  private void doLoginMyOpenId() {
    Window.Location.assign("/loginMyOpenId");
  }

  private void doLoginTwitter() {
    Window.Location.assign("/loginTwitter");
  }

  private void doLoginYahoo() {
    Window.Location.assign("/loginYahoo");
  }
}
