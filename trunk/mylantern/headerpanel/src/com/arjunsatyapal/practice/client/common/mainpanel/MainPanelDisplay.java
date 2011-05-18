package com.arjunsatyapal.practice.client.common.mainpanel;

import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuItem;

import com.arjunsatyapal.practice.client.common.mvpinterfaces.Display;

public interface MainPanelDisplay extends Display {
  MenuItem getMenuItemAdmin();

  LayoutPanel getWorkspace();
}
