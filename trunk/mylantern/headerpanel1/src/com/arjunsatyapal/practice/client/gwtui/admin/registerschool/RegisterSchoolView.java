package com.arjunsatyapal.practice.client.gwtui.admin.registerschool;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;

public class RegisterSchoolView extends View implements RegisterSchoolDisplay {
  @UiField
  LanternHeaderPanelDisplay lanternHeaderPanel;
  @UiField
  TextBox textBoxSchoolName;
  @UiField
  TextBox textBoxAddress1;
  @UiField
  TextBox textBoxAddress2;
  @UiField
  TextBox textBoxCity;
  @UiField
  TextBox textBoxState;
  @UiField
  TextBox textBoxZip;
  @UiField
  TextBox textBoxAdminEmail;
  @UiField
  Button buttonSave;
  @UiField
  Button buttonCancel;

  private static RegisterSchoolViewUiBinder uiBinder = GWT
      .create(RegisterSchoolViewUiBinder.class);

  interface RegisterSchoolViewUiBinder extends
      UiBinder<Widget, RegisterSchoolView> {
  }

  public RegisterSchoolView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return lanternHeaderPanel;
  }

  @Override
  public TextBox getTextBoxSchoolName() {
    return textBoxSchoolName;
  }

  @Override
  public TextBox getTextBoxAddress1() {
    return textBoxAddress1;
  }

  @Override
  public TextBox getTextBoxAddress2() {
    return textBoxAddress2;
  }

  @Override
  public TextBox getTextBoxCity() {
    return textBoxCity;
  }

  @Override
  public TextBox getTextBoxState() {
    return textBoxState;
  }

  @Override
  public TextBox getTextBoxZip() {
    return textBoxZip;
  }

  @Override
  public TextBox getTextBoxAdminEmail() {
    return textBoxAdminEmail;
  }

  @Override
  public Button getButtonSave() {
    return buttonSave;
  }

  @Override
  public Button getButtonCancel() {
    return buttonCancel;
  }
}
