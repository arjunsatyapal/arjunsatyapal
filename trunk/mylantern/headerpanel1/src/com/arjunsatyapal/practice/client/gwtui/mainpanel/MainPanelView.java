package com.arjunsatyapal.practice.client.gwtui.mainpanel;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainPanelView extends View implements MainPanelDisplay {

  @UiField
  LayoutPanel workspace;

  @UiField
  LanternHeaderPanelView lanternHeaderPanel;

  @Override
  public LayoutPanel getWorkspace() {
    return workspace;
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return lanternHeaderPanel;
  }

  private static MainPanelUiBinder uiBinder = GWT
      .create(MainPanelUiBinder.class);

  interface MainPanelUiBinder extends UiBinder<Widget, MainPanelView> {
  }

  public MainPanelView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
