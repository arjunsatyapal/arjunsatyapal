package com.arjunsatyapal.practice.client.gwtui.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelView;

public class LoginView extends Composite implements LoginDisplay {
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
  public Widget asWidget() {
    return this;
  }

  @Override
  public HasClickHandlers getAolButton() {
    return aolButton;
  }

  @Override
  public HasClickHandlers getFacebookButton() {
    return facebookButton;
  }

  @Override
  public HasClickHandlers getGoogleButton() {
    return googleButton;
  }

  @Override
  public HasClickHandlers getMyOpenIdButton() {
    return myopenidButton;
  }


  @Override
  public HasClickHandlers getTwitterButton() {
    return twitterButton;
  }

  @Override
  public HasClickHandlers getYahooButton() {
    return yahooButton;
  }

  /* (non-Javadoc)
   * @see com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display#getLanternHeaderPanel()
   */
  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return lanternHeaderPanel;
  }
}