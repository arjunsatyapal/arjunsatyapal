package com.arjunsatyapal.practice.client.common.lanternheaderpanel;

import com.google.gwt.user.client.ui.MenuItem;

import com.arjunsatyapal.practice.client.common.mvpinterfaces.Display;

public interface LanternHeaderPanelDisplay extends Display {
  void bind();

  MenuItem getMenuItemAdmin();
}
