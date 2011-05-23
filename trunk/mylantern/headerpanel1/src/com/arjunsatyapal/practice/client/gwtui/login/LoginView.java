package com.arjunsatyapal.practice.client.gwtui.login;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends View implements LoginDisplay {
  @UiField
  LanternHeaderPanelView lanternHeaderPanel;
  @UiField
  PushButton aolButton;
  @UiField
  PushButton facebookButton;
  @UiField
  PushButton googleButton;
  @UiField
  PushButton myopenidButton;
  @UiField
  PushButton twitterButton;
  @UiField
  PushButton yahooButton;

  interface UserBadgeUiBinder extends UiBinder<Widget, LoginView> {
  }

  private static UserBadgeUiBinder uiBinder = GWT.create(UserBadgeUiBinder.class);


  public LoginView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public PushButton getAolButton() {
    return aolButton;
  }

  @Override
  public PushButton getFacebookButton() {
    return facebookButton;
  }

  @Override
  public PushButton getGoogleButton() {
    return googleButton;
  }

  @Override
  public PushButton getMyOpenIdButton() {
    return myopenidButton;
  }


  @Override
  public PushButton getTwitterButton() {
    return twitterButton;
  }

  @Override
  public PushButton getYahooButton() {
    return yahooButton;
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return lanternHeaderPanel;
  }
}
