// Copyright 2011 Google Inc. All Rights Reserved.

package com.arjunsatyapal.practice.client.common.mainpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.common.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.common.lanternheaderpanel.LanternHeaderPanelView;

/**
 * @author arjuns@google.com (Arjun Satyapal)
 *
 */
public class MainPanelView extends Composite implements MainPanelDisplay {

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
