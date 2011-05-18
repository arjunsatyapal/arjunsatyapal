package com.arjunsatyapal.practice.client.common.mvpinterfaces;

import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.common.lanternheaderpanel.LanternHeaderPanelDisplay;

public interface Display {
  public Widget asWidget();
  LanternHeaderPanelDisplay getLanternHeaderPanel();
}
