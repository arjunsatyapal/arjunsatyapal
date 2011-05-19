package com.arjunsatyapal.practice.client.gwtui.mainpanel;

import com.google.gwt.user.client.ui.LayoutPanel;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;

public interface MainPanelDisplay extends Display {
  LayoutPanel getWorkspace();
}
