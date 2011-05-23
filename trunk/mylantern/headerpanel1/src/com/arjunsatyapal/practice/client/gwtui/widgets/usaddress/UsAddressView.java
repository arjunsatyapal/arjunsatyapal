package com.arjunsatyapal.practice.client.gwtui.widgets.usaddress;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UsAddressView extends View implements UsAddressDisplay {
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

  private static RegisterSchoolViewUiBinder uiBinder = GWT
      .create(RegisterSchoolViewUiBinder.class);

  interface RegisterSchoolViewUiBinder extends
      UiBinder<Widget, UsAddressView> {
  }

  public UsAddressView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    // TODO(arjuns) : See if this problem can be eliminated.
    throw new UnsupportedOperationException("Widgets do not support this.");
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
}
