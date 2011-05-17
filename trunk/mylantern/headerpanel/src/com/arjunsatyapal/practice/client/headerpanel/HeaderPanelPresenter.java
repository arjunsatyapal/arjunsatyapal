package com.arjunsatyapal.practice.client.headerpanel;

import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.common.Presenter;

public class HeaderPanelPresenter implements Presenter {
  private final HeaderPanelDisplay headerPanelDisplay;

  public HeaderPanelPresenter(HeaderPanelDisplay display) {
    this.headerPanelDisplay = display;
  }

  public void bind() {
    this.headerPanelDisplay.
  }

  /* (non-Javadoc)
   * @see com.arjunsatyapal.practice.client.common.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
   */
  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add(headerPanelDisplay.asWidget());
    bind();
  }

}
