package com.arjunsatyapal.practice.client.gwtui.mvpinterfaces;

import com.arjunsatyapal.practice.client.gwtui.widgets.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.google.gwt.user.client.ui.Widget;

public interface Display {
  public Widget asWidget();
  LanternHeaderPanelDisplay getLanternHeaderPanel();
}
