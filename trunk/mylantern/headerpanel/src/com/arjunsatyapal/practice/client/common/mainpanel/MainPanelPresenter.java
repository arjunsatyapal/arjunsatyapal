package com.arjunsatyapal.practice.client.common.mainpanel;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.admin.registerloginproviders.RegisterLoginPresenter;
import com.arjunsatyapal.practice.client.admin.registerloginproviders.RegisterLoginProviderView;
import com.arjunsatyapal.practice.client.common.mvpinterfaces.Presenter;

public class MainPanelPresenter implements Presenter {
  private final MainPanelDisplay display;

  public MainPanelPresenter(MainPanelDisplay mainPanelDisplay) {
    this.display = mainPanelDisplay;
  }

  public void bind() {
  }

  @Override
  public void go(HasWidgets container) {
    this.display.getMenuItemAdmin().setCommand(new Command() {
      @Override
      public void execute() {
        RegisterLoginPresenter registerLoginPresenter = new RegisterLoginPresenter(
            new RegisterLoginProviderView());
        registerLoginPresenter.go(display.getWorkspace());
//        long test = System.currentTimeMillis();
//        Button button = new Button("Click Me : " + test);
//        display.getWorkspace().clear();
//        display.getWorkspace().add(button);
      }
    });
    container.clear();
    container.add(display.asWidget());
    bind();
  }
}
