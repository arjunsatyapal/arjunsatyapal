package com.arjunsatyapal.practice.client.gwtui.mvpinterfaces;

import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelDisplay;

public abstract class Presenter {
  public abstract void go(final HasWidgets container);
  public abstract void bind();

  LanternHeaderPanelDisplay lanternHeaderPanelDisplay;
  protected Presenter(LanternHeaderPanelDisplay lanternHeaderPanelDisplay) {
    this.lanternHeaderPanelDisplay = lanternHeaderPanelDisplay;
    this.lanternHeaderPanelDisplay.bind();
  }
}