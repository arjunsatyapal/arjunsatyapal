package com.arjunsatyapal.practice.client.gwtui.admin.adminlinks;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class AdminUiView extends View implements AdminUiDisplay {
  @UiField
  LanternHeaderPanelView lanternHeaderPanel;
  @UiField
  Button buttonAddOAuthProvider;
  @UiField
  Button buttonRegisterSchool;
  @UiField
  Button buttonShowEditSchool;

  private static AdminViewUiBinder uiBinder = GWT.create(AdminViewUiBinder.class);

  interface AdminViewUiBinder extends UiBinder<Widget, AdminUiView> {
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

  @Override
  public Button getButtonShowEditSchool() {
    return buttonShowEditSchool;
  }
}
