package com.arjunsatyapal.practice.gwtxml.client.gwtui.mvpinterfaces;

import com.google.gwt.user.client.ui.Widget;

public interface Display {
  public Widget asWidget();

  void setEnabled(boolean enabled);
}