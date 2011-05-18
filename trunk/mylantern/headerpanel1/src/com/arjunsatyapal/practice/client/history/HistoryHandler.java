package com.arjunsatyapal.practice.client.history;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.admin.registerloginproviders.RegisterLoginPresenter;
import com.arjunsatyapal.practice.client.admin.registerloginproviders.RegisterLoginProviderView;
import com.arjunsatyapal.practice.client.common.mainpanel.MainPanelPresenter;
import com.arjunsatyapal.practice.client.common.mainpanel.MainPanelView;

public class HistoryHandler implements ValueChangeHandler<String> {
  private static HistoryHandler instance = new HistoryHandler();

  public static HistoryHandler getInstance() {
    return instance;
  }

  // Private constructor to have singleton.
  private HistoryHandler() {
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    HistoryEvent historyEvent = HistoryEvent.getHistoryEventByToken(event.getValue());

    switch (historyEvent) {
      case RELOAD :
        /*
         *  Do nothing. Next event will be fired soon. This is done so that tokens can be reloaded.
         */
        return;
      case HOME :
        MainPanelPresenter mainPanelPresenter = new MainPanelPresenter(
            new MainPanelView());
        mainPanelPresenter.go(RootLayoutPanel.get());
        break;
      case REGISTER_OAUTH_PROVIDER :
        RegisterLoginPresenter registerLoginPresenter = new RegisterLoginPresenter(
            new RegisterLoginProviderView());
        registerLoginPresenter.go(RootLayoutPanel.get());
        break;
      default:
        break;

    }
  }

}
