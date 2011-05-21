package com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel;

import com.google.gwt.user.client.ui.MenuItem;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;

public interface LanternHeaderPanelDisplay extends Display {
  void bind();

  MenuItem getMenuItemLogin();

  // Menu Items for Student.
  MenuItem getMenuItemStudentRegister();

  // Menu Items for NonStudent
  MenuItem getMenuItemAdmin();
}
