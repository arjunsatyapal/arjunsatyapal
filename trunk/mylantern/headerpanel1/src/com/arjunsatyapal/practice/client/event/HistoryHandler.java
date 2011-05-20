package com.arjunsatyapal.practice.client.event;

import static com.arjunsatyapal.practice.shared.Utils.RedirectionUtils.generateValidParamsToAttach;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.practice.client.gwtui.admin.adminlinks.AdminUiPresenter;
import com.arjunsatyapal.practice.client.gwtui.admin.adminlinks.AdminUiView;
import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterLoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterOAuthProviderView;
import com.arjunsatyapal.practice.client.gwtui.login.LoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.login.LoginView;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelPresenter;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelView;
import com.arjunsatyapal.practice.client.rpc.ServiceProvider;
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

  // Since history tokens can have parameters, so not using LanternEventCategory
  // enum
  // directly.
  public static void handleNewToken(String newToken) {
    String historyToken = History.getToken();
    Map<String, List<String>> map = Location.getParameterMap();

    if (historyToken.isEmpty()) {
      newToken = LanternEventCategory.HOME.getToken();
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
    LanternEventCategory historyEventCategory = null;

    try {
      historyEventCategory = LanternEventCategory
          .getHistoryEventCategoryByToken(event.getValue());
    } catch (InvalidURLParamsException e) {
      // TODO(arjuns) : Add timere here and then do auto redirect.
      Window.alert("Invalid URL. Redirecting to homepage..");
      historyEventCategory = LanternEventCategory.HOME;
    }

    //TODO(arjuns) : See if the category of login is Admin.
    // If Category requires login, then validate that user is logged in. If not, then redirect
    // the user to login with currentToken as clientCallbackToken.
    // If not, then proceed as normal.
    if (historyEventCategory.requiresLogin()) {
      final LanternEventCategory categoryToBeHandled = historyEventCategory;
      AsyncCallback<UserAccountDTO> callback = new AsyncCallback<UserAccountDTO>() {
        @Override
        public void onFailure(Throwable caught) {
          // Redirect the user to Login Screen with current even as
          // callbackToken.
          handleNewToken(createTokenWithParams(
              LanternEventCategory.LOGIN_UI,
              generateValidParamsToAttach(ValidParams.CLIENT_CALLBACK_TOKEN,
                  categoryToBeHandled)));

        }

        @Override
        public void onSuccess(UserAccountDTO result) {
          // Successfully got the result for UserAccountDto.
          if (result == null) {
            // User is not signed in.
            //TODO(arjuns) : Find better way to handle this as user has not logged in.
            onFailure(null);
          } else {
            //TODO(arjuns) :  Validate that current category is correct.
            // e.g. for Admin only pages, see that user is Admin.
            handleEventCategory(categoryToBeHandled, event.getValue());
          }
        }
      };
      ServiceProvider.getServiceProvider().getLoginService()
          .getLoggedInUserDTO(callback);
    } else {
      handleEventCategory(historyEventCategory, event.getValue());
    }
  }

  public void handleEventCategory(LanternEventCategory historyEventCategory,
      final String historyToken) {
    switch (historyEventCategory) {
      case HOME:
        // TODO(arjuns) : see if we should truncate the parameters. Not sure if there is
        // any benefit.
        MainPanelPresenter mainPanelPresenter = new MainPanelPresenter(
            new MainPanelView(), historyToken);
        mainPanelPresenter.go(RootLayoutPanel.get());
        break;

      case LOGIN_UI:
        LoginPresenter loginPresenter = new LoginPresenter(new LoginView(),
            historyToken);
        loginPresenter.go(RootLayoutPanel.get());
        break;

      case ADMIN_UI:
        AdminUiPresenter adminUiPresenter = new AdminUiPresenter(
            new AdminUiView(), historyToken);
        adminUiPresenter.go(RootLayoutPanel.get());
        break;

      case REGISTER_OAUTH_PROVIDER:
        RegisterLoginPresenter registerLoginPresenter = new RegisterLoginPresenter(
            new RegisterOAuthProviderView(), historyToken);
        registerLoginPresenter.go(RootLayoutPanel.get());
        break;
      default:
        Window.alert("Invalid event : " + historyEventCategory);
        break;
    }
  }

  private String createTokenWithParams(LanternEventCategory token, String params) {
    return LanternEventCategory.LOGIN_UI.getToken() + "?" + params;
  }
}
