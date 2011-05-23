package com.arjunsatyapal.practice.client.gwtui.admin.schoolregister;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.widgets.usaddress.UsAddressDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class SchoolRegisterView extends View implements SchoolRegisterDisplay {
  @UiField
  LanternHeaderPanelDisplay lanternHeaderPanel;
  @UiField
  UsAddressDisplay widgetUsAddress;
  @UiField
  Button buttonSave;
  @UiField
  Button buttonCancel;

  private static RegisterSchoolViewUiBinder uiBinder = GWT
      .create(RegisterSchoolViewUiBinder.class);

  interface RegisterSchoolViewUiBinder extends
      UiBinder<Widget, SchoolRegisterView> {
  }

  public SchoolRegisterView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return lanternHeaderPanel;
  }

  @Override
  public Button getButtonSave() {
    return buttonSave;
  }

  @Override
  public Button getButtonCancel() {
    return buttonCancel;
  }

  @Override
  public UsAddressDisplay getWidgetUsAddress() {
    return widgetUsAddress;
  }
}
