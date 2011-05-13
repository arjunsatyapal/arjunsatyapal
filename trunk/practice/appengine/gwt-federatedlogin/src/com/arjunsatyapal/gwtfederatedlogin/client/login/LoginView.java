package com.arjunsatyapal.gwtfederatedlogin.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite implements LoginDisplay {
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

  private static UserBadgeUiBinder uiBinder = GWT.create(UserBadgeUiBinder.class);

  interface UserBadgeUiBinder extends UiBinder<Widget, LoginView> {
  }

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
}
