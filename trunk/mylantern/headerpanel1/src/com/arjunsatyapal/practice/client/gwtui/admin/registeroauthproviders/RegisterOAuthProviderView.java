package com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class RegisterOAuthProviderView extends View implements RegisterLoginDisplay {
  @UiField
  LanternHeaderPanelView lanternHeaderPanel;
  @UiField
  ListBox listBoxOauthProvider;
  @UiField
  TextBox textBoxConsumerKey;
  @UiField
  TextBox textBoxConsumerSecret;
  @UiField
  Button buttonSave;
  @UiField
  Button buttonCancel;


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

  @Override
  public Button getButtonCancel() {
    return buttonCancel;
  }

  private static RegisterOAuthProviderUiBinder uiBinder = GWT
      .create(RegisterOAuthProviderUiBinder.class);

  interface RegisterOAuthProviderUiBinder extends
      UiBinder<Widget, RegisterOAuthProviderView> {
  }

  public RegisterOAuthProviderView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return lanternHeaderPanel;
  }
}
