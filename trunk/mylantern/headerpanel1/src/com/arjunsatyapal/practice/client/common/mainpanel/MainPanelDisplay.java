package com.arjunsatyapal.practice.client.common.mainpanel;

import com.google.gwt.user.client.ui.LayoutPanel;

import com.arjunsatyapal.practice.client.common.mvpinterfaces.Display;

public interface MainPanelDisplay extends Display {
  LayoutPanel getWorkspace();
}
