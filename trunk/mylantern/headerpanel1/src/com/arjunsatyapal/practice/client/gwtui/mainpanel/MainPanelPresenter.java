package com.arjunsatyapal.practice.client.gwtui.mainpanel;

import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;

public class MainPanelPresenter extends Presenter {
  private final MainPanelDisplay display;

  public MainPanelPresenter(MainPanelDisplay mainPanelDisplay, String historyToken) {
    super(mainPanelDisplay.getLanternHeaderPanel(), historyToken);
    this.display = mainPanelDisplay;
  }

  @Override
  public void bind() {
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
    bind();
  }
}
