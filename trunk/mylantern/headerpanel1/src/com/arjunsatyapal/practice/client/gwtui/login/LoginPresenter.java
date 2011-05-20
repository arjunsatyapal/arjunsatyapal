package com.arjunsatyapal.practice.client.gwtui.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.shared.ValidParams;

import java.util.HashMap;

public class LoginPresenter extends Presenter {
  private final LoginDisplay loginDisplay;
  private HashMap<PushButton, String> loginUrlMap = new HashMap<PushButton, String>();

  public LoginPresenter(LoginDisplay loginDisplay, String historyToken) {
    super(loginDisplay.getLanternHeaderPanel(), historyToken);
    this.loginDisplay = loginDisplay;
  }

  @Override
  public void bind() {
    loginUrlMap.put(this.loginDisplay.getAolButton(), "/loginAol");
    this.loginDisplay.getAolButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginAol();
      }
    });

    loginUrlMap.put(this.loginDisplay.getFacebookButton(), "/loginFacebook");
    this.loginDisplay.getFacebookButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginFacebook();
      }
    });

    loginUrlMap.put(this.loginDisplay.getGoogleButton(), "/loginGoogle");
    this.loginDisplay.getGoogleButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginGoogle();
      }
    });

    loginUrlMap.put(this.loginDisplay.getMyOpenIdButton(), "/loginMyOpenId");
    this.loginDisplay.getMyOpenIdButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginMyOpenId();
      }
    });

    loginUrlMap.put(this.loginDisplay.getTwitterButton(), "/loginTwitter");
    this.loginDisplay.getTwitterButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginTwitter();
      }
    });

    loginUrlMap.put(this.loginDisplay.getYahooButton(), "/loginYahoo");
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
    String loginUrl = "/loginGoogle";
    String redirectToken = getEncodedRedirectHash();
    if (redirectToken != null) {
      loginUrl += "?" + ValidParams.CLIENT_CALLBACK_TOKEN.getParamKey() + "=" + redirectToken;
    }

    Window.Location.assign(loginUrl);
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
