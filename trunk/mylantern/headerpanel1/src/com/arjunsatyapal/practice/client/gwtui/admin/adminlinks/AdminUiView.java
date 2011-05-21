package com.arjunsatyapal.practice.client.gwtui.admin.adminlinks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelView;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;

public class AdminUiView extends View implements AdminUiDisplay {
  @UiField
  LanternHeaderPanelView lanternHeaderPanel;
  @UiField
  Button buttonAddOAuthProvider;
  @UiField
  Button buttonRegisterSchool;

  private static AdminUiUiBinder uiBinder = GWT.create(AdminUiUiBinder.class);

  interface AdminUiUiBinder extends UiBinder<Widget, AdminUiView> {
  }

  public AdminUiView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return lanternHeaderPanel;
  }

  @Override
  public Button getButtonAddOAuthProvider() {
    return buttonAddOAuthProvider;
  }

  @Override
  public Button getButtonRegisterSchool() {
    return buttonRegisterSchool;
  }
}
