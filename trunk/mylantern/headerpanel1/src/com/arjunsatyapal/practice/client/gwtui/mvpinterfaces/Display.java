package com.arjunsatyapal.practice.client.gwtui.mvpinterfaces;

import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelDisplay;

public interface Display {
  public Widget asWidget();
  LanternHeaderPanelDisplay getLanternHeaderPanel();
}
