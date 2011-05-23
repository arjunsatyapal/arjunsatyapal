package com.arjunsatyapal.practice.client.gwtui.admin.schoolshowedit;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.widgets.usaddress.UsAddressDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class SchoolShowEditView extends View implements SchoolShowEditDisplay {
  @UiField
  LanternHeaderPanelDisplay lanternHeaderPanel;
  @UiField
  ListBox listBoxSchoolList;
  @UiField
  UsAddressDisplay widgetUsAddress;
  @UiField 
  Button buttonEditSave;
  @UiField
  Button buttonCancel;

  private static ShowSchoolViewUiBinder uiBinder = GWT
      .create(ShowSchoolViewUiBinder.class);

  interface ShowSchoolViewUiBinder extends
      UiBinder<Widget, SchoolShowEditView> {
  }

  public SchoolShowEditView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return lanternHeaderPanel;
  }

  @Override
  public UsAddressDisplay getWidgetUsAddress() {
    return widgetUsAddress;
  }

  @Override
  public ListBox getListBoxSchoolList() {
    return listBoxSchoolList;
  }

  @Override
  public Button getButtonEditSave() {
    return buttonEditSave;
  }

  @Override
  public Button getButtonCancel() {
    return buttonCancel;
  }
}
