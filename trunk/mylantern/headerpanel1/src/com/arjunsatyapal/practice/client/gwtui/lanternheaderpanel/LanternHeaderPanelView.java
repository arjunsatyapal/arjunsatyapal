package com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.event.HistoryHandler;
import com.arjunsatyapal.practice.client.event.LanternEvents;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.View;

public class LanternHeaderPanelView extends View implements
    LanternHeaderPanelDisplay {
  @UiField
  MenuItem menuItemAdmin;
  @UiField
  MenuItem menuItemLogin;

  @Override
  public MenuItem getMenuItemAdmin() {
    return menuItemAdmin;
  }

  private static LanternHeaderPanelViewUiBinder uiBinder = GWT
      .create(LanternHeaderPanelViewUiBinder.class);

  interface LanternHeaderPanelViewUiBinder extends
      UiBinder<Widget, LanternHeaderPanelView> {
  }

  public LanternHeaderPanelView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void bind() {
    menuItemLogin.setCommand(new Command() {
      @Override
      public void execute() {
        HistoryHandler.handleNewToken(LanternEvents.LOGIN.getToken());
      }
    });

    menuItemAdmin.setCommand(new Command() {
      @Override
      public void execute() {
        HistoryHandler.handleNewToken(LanternEvents.REGISTER_OAUTH_PROVIDER.getToken());
      }
    });
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    return this;
  }

  @Override
  public MenuItem getMenuItemLogin() {
    return menuItemLogin;
  }
}
