package com.arjunsatyapal.practice.client.event;

import static com.arjunsatyapal.practice.client.rpc.ServiceProvider.getServiceProvider;
import static com.arjunsatyapal.practice.shared.Utils.RedirectionUtils.generateValidParamsToAttach;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterLoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterOAuthProviderView;
import com.arjunsatyapal.practice.client.gwtui.login.LoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.login.LoginView;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelPresenter;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelView;
import com.arjunsatyapal.practice.shared.ValidParams;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDTO;
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
    Map<String, List<String>> map =
        com.google.gwt.user.client.Window.Location.getParameterMap();

    if (historyToken.isEmpty()) {
      newToken = LanternToken.HOME.getToken();
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
    LanternToken historyEventCategory = null;

    try {
      historyEventCategory =
          LanternToken.getHistoryEventCategoryByToken(event.getValue());
    } catch (InvalidURLParamsException e) {
      Window.alert("Invalid URL. Redirecting to homepage..");
      historyEventCategory = LanternToken.HOME;
    }


    switch (historyEventCategory) {
      case HOME:
        // TODO : see if we should truncate the parameters. Not sure if there is
        // any benefit.
        MainPanelPresenter mainPanelPresenter =
            new MainPanelPresenter(new MainPanelView(), event.getValue());
        mainPanelPresenter.go(RootLayoutPanel.get());
        break;

      case LOGIN:
        LoginPresenter loginPresenter =
            new LoginPresenter(new LoginView(), event.getValue());
        loginPresenter.go(RootLayoutPanel.get());
        break;
      case REGISTER_OAUTH_PROVIDER:
        // TODO : See how code splitting can make this better. And see if there is any way
        // that admin pages are not downloaded on client till the time he has validated that yes.
        // Probably a dedicated servlet with a different module to handle that.
        AsyncCallback<UserAccountDTO> callback =
            new AsyncCallback<UserAccountDTO>() {

              @Override
              public void onSuccess(UserAccountDTO result) {
                if (result != null) {
                  RegisterLoginPresenter registerLoginPresenter =
                      new RegisterLoginPresenter(
                          new RegisterOAuthProviderView(), event.getValue());
                  registerLoginPresenter.go(RootLayoutPanel.get());
                } else {
                  handleNewToken(createTokenWithParams(
                      LanternToken.LOGIN,
                      generateValidParamsToAttach(ValidParams.CLIENT_CALLBACK_TOKEN,
                          LanternToken.REGISTER_OAUTH_PROVIDER)));
                }
              }

              @Override
              public void onFailure(Throwable caught) {
                Window.alert("Failed to do login.");
              }
            };

        getServiceProvider().getLoginService().getLoggedInUserDTO(callback);
        break;
      default:
        Window.alert("Invalid event : " + historyEventCategory);
        break;
    }
  }

  private String createTokenWithParams(LanternToken token, String params) {
    return LanternToken.LOGIN.getToken() + "?" + params;
  }
}
