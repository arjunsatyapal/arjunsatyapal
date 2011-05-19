package com.arjunsatyapal.practice.client.history;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.ServiceProvider;
import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterLoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterOAuthProviderView;
import com.arjunsatyapal.practice.client.gwtui.login.LoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.login.LoginView;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelPresenter;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelView;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDTO;

public class HistoryHandler implements ValueChangeHandler<String> {
  private static HistoryHandler instance = new HistoryHandler();

  public static HistoryHandler getInstance() {
    return instance;
  }

  // Private constructor to have singleton.
  private HistoryHandler() {
  }

  public static class LoginCallbackWrapper {
    private String historyToken;

    public LoginCallbackWrapper(String historyToken) {
      if (historyToken.isEmpty() || historyToken.equals(HistoryEvent.LOGIN.getToken()) ){
        historyToken = HistoryEvent.HOME.getToken();
      }

      this.historyToken = historyToken;
    }

    private AsyncCallback<UserAccountDTO> callback =
        new AsyncCallback<UserAccountDTO>() {
          @Override
          public void onFailure(Throwable caught) {
            Window.alert("Failed to login.");
          }

          @Override
          public void onSuccess(UserAccountDTO result) {
            if (result == null) {
              LoginPresenter loginPresenter = new LoginPresenter(new LoginView());
              loginPresenter.go(RootLayoutPanel.get());
            } else {
              History.newItem(historyToken);
            }
          }
        };

    public AsyncCallback<UserAccountDTO> getCallback() {
      return callback;
    }
  }

  public static void loginIfRequired(String historyToken) {
    LoginCallbackWrapper wrapper = new LoginCallbackWrapper(historyToken);
    ServiceProvider.getLoginService().getLoggedInUserDTO(wrapper.getCallback());
  }

  private static void showUiForEvent(String event) {
    HistoryEvent historyEvent = HistoryEvent.getHistoryEventByToken(event);

    // TODO : replace this with event bus.
    switch (historyEvent) {
      case HOME:
        MainPanelPresenter mainPanelPresenter =
            new MainPanelPresenter(new MainPanelView());
        mainPanelPresenter.go(RootLayoutPanel.get());
        break;

      case LOGIN:
        // After logging in, redirect to home.
        loginIfRequired(HistoryEvent.HOME.getToken());
        break;
      case REGISTER_OAUTH_PROVIDER:
        RegisterLoginPresenter registerLoginPresenter =
            new RegisterLoginPresenter(new RegisterOAuthProviderView());
        registerLoginPresenter.go(RootLayoutPanel.get());
        break;
      default:
        Window.alert("Invalid event : " + historyEvent);
        break;
    }
  }

  @Override
  public void onValueChange(final ValueChangeEvent<String> event) {
    showUiForEvent(event.getValue());
  }
}
