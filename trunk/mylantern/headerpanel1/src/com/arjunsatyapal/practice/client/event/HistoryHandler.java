package com.arjunsatyapal.practice.client.event;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.gwtui.login.LoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.login.LoginView;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelPresenter;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelView;
import com.arjunsatyapal.practice.shared.exceptions.InvalidURLParamsException;

import java.util.List;
import java.util.Map;

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
    Map<String, List<String>> map = com.google.gwt.user.client.Window.Location.getParameterMap();

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
    LanternEvents historyEventCategory = null;

    try {
      historyEventCategory = LanternEvents.getHistoryEventCategoryByToken(event.getValue());
    } catch (InvalidURLParamsException e) {
      Window.alert("Invalid URL. Redirecting to homepage..");
      historyEventCategory = LanternEvents.HOME;
    }


    switch (historyEventCategory) {
      case HOME:
        //TODO : see if we should truncate the parameters. Not sure if there is any benefit.
        MainPanelPresenter mainPanelPresenter =
            new MainPanelPresenter(new MainPanelView(), event.getValue());
        mainPanelPresenter.go(RootLayoutPanel.get());
        break;

      case LOGIN :
        // TODO : See if event can have redirection parameters.
        LoginPresenter loginPresenter = new LoginPresenter(new LoginView(), event.getValue());
        loginPresenter.go(RootLayoutPanel.get());
        break;
      case REGISTER_OAUTH_PROVIDER:
//        RegisterLoginPresenter registerLoginPresenter =
//            new RegisterLoginPresenter(new RegisterOAuthProviderView());
//        registerLoginPresenter.go(RootLayoutPanel.get());
        Window.Location.assign("/loginAdmin");
        break;
      default:
        Window.alert("Invalid event : " + historyEventCategory);
        break;
    }  }
}
