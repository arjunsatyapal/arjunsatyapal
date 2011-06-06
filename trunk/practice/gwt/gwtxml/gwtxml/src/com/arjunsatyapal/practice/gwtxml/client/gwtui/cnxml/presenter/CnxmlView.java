package com.arjunsatyapal.practice.gwtxml.client.gwtui.cnxml.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.gwtxml.client.gwtui.mvpinterfaces.View;

public class CnxmlView extends View implements CnxmlDisplay {
  @UiField
  LayoutPanel layoutPanel;

  private static CnxmlWidgetUiBinder uiBinder = GWT
      .create(CnxmlWidgetUiBinder.class);

  interface CnxmlWidgetUiBinder extends UiBinder<Widget, CnxmlView> {
  }

  public CnxmlView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public LayoutPanel getLayoutPanel() {
    return layoutPanel;
  }

  @Override
  public void setEnabled(boolean enabled) {
  }
}
