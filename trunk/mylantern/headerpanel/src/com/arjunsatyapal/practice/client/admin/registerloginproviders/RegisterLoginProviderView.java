package com.arjunsatyapal.practice.client.admin.registerloginproviders;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RegisterLoginProviderView extends Composite implements RegisterLoginDisplay {
  @UiField
  ListBox listBoxOauthProvider;
  @UiField
  TextBox textBoxConsumerKey;
  @UiField
  TextBox textBoxConsumerSecret;
  @UiField
  Button buttonSave;

  @Override
  public ListBox getListBoxOauthProvider() {
    return listBoxOauthProvider;
  }

  @Override
  public TextBox getTextBoxConsumerSecret() {
    return textBoxConsumerSecret;
  }

  @Override
  public TextBox getTextBoxConsumerKey() {
    return textBoxConsumerKey;
  }

  @Override
  public Button getButtonSave() {
    return buttonSave;
  }

  private static RegisterLoginProvidersUiBinder uiBinder = GWT
      .create(RegisterLoginProvidersUiBinder.class);

  interface RegisterLoginProvidersUiBinder extends
      UiBinder<Widget, RegisterLoginProviderView> {
  }

  public RegisterLoginProviderView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
