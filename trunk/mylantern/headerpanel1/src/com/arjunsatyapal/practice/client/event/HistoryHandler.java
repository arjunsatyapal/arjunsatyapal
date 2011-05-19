package com.arjunsatyapal.practice.client.event;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterLoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterOAuthProviderView;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelPresenter;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelView;

public class HistoryHandler implements ValueChangeHandler<String> {
  private static HistoryHandler instance = new HistoryHandler();

  public static HistoryHandler getInstance() {
    return instance;
  }

  // Private constructor to have singleton.
  private HistoryHandler() {
  }

  public static void handleNewToken(String newToken) {
    String historyToken = History.getToken();

    // Special handling to redirect to #home when user comes to main site from anywhere.
    if (historyToken.isEmpty()) {
      newToken = LanternEvents.HOME.getToken();
      History.newItem(newToken);
      History.fireCurrentHistoryState();
      return;
    }

    if (newToken.equals(historyToken)) {
      History.fireCurrentHistoryState();
    } else {
      History.newItem(newToken);
    }
  }

  @Override
  public void onValueChange(final ValueChangeEvent<String> event) {
    LanternEvents historyEvent = LanternEvents.getHistoryEventByToken(event.getValue());

    // TODO : replace this with event bus.
    switch (historyEvent) {
      case HOME:
        MainPanelPresenter mainPanelPresenter =
            new MainPanelPresenter(new MainPanelView());
        mainPanelPresenter.go(RootLayoutPanel.get());
        break;

      case REGISTER_OAUTH_PROVIDER:
        RegisterLoginPresenter registerLoginPresenter =
            new RegisterLoginPresenter(new RegisterOAuthProviderView());
        registerLoginPresenter.go(RootLayoutPanel.get());
        break;
      default:
        Window.alert("Invalid event : " + historyEvent);
        break;
    }  }
}
