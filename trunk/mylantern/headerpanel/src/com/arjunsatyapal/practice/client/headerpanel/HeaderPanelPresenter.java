package com.arjunsatyapal.practice.client.headerpanel;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.common.Presenter;

public class HeaderPanelPresenter implements Presenter {
  private final HeaderPanelDisplay display;

  public HeaderPanelPresenter(HeaderPanelDisplay headerPanelDisplay) {
    this.display = headerPanelDisplay;
  }

  public void bind() {
  }

  @Override
  public void go(HasWidgets container) {
    this.display.getMenuItemAdmin().setCommand(new Command() {
      @Override
      public void execute() {
        Window.alert("hellow Admin.");
      }
    });
    container.clear();
    container.add(display.asWidget());
    bind();
  }
}
