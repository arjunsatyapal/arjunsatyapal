package com.arjunsatyapal.practice.client.event;

import static com.arjunsatyapal.practice.shared.Utils.RedirectionUtils.generateValidParamsToAttach;

import com.arjunsatyapal.practice.client.event.LanternEventCategory;
import com.arjunsatyapal.practice.client.gwtui.admin.adminlinks.AdminUiPresenter;
import com.arjunsatyapal.practice.client.gwtui.admin.adminlinks.AdminUiView;
import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterLoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders.RegisterOAuthProviderView;
import com.arjunsatyapal.practice.client.gwtui.admin.registerschool.RegisterSchoolPresenter;
import com.arjunsatyapal.practice.client.gwtui.admin.registerschool.RegisterSchoolView;
import com.arjunsatyapal.practice.client.gwtui.login.LoginPresenter;
import com.arjunsatyapal.practice.client.gwtui.login.LoginView;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelPresenter;
import com.arjunsatyapal.practice.client.gwtui.mainpanel.MainPanelView;
import com.arjunsatyapal.practice.client.rpc.ServiceProvider;
import com.arjunsatyapal.practice.shared.LoginCategory;
import com.arjunsatyapal.practice.shared.ServletPaths;
import com.arjunsatyapal.practice.shared.ValidParams;
import com.arjunsatyapal.practice.shared.dtos.UserAccountDto;
import com.arjunsatyapal.practice.shared.exceptions.InvalidURLParamsException;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

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
      historyEventCategory =
        LanternEventCategory.getHistoryEventCategoryByToken(event.getValue());
    } catch (InvalidURLParamsException e) {
      // TODO(arjuns) : Add timere here and then do auto redirect.
      Window.alert("Invalid URL. Redirecting to homepage..");
      historyEventCategory = LanternEventCategory.HOME;
    }
    
    // TODO(arjuns) : See if the category of login is Admin.
    // If Category requires login, then validate that user is logged in. If not,
    // then force user to login with currentToken as clientCallbackToken.
    // If not, then proceed as normal.
    if (historyEventCategory.requiresLogin()) {
      forceLoginIfNotAlreadyLoggedIn(historyEventCategory, event.getValue());
    } else {
      // User does not require login.
      handleEventCategory(historyEventCategory, event.getValue(), null /* userAccountDto */);
    }
  }
  
  private void forceLoginIfNotAlreadyLoggedIn(
    final LanternEventCategory historyEventCategory, final String historyToken) {
    AsyncCallback<UserAccountDto> callback =
      new AsyncCallback<UserAccountDto>() {
        @Override
        public void onFailure(Throwable caught) {
          // Redirect the user to Login Screen with current even as
          // callbackToken.
          handleNewToken(createTokenWithParams(
            LanternEventCategory.LOGIN_UI,
            generateValidParamsToAttach(ValidParams.CLIENT_CALLBACK_TOKEN,
              historyEventCategory)));
        }
        
        @Override
        public void onSuccess(UserAccountDto result) {
          // Successfully got the result for UserAccountDto.
          if (result == null) {
            // User is not signed in.
            // TODO(arjuns) : Find better way to handle this as user has not
            // logged in.
            onFailure(null);
          } else {
            // TODO(arjuns) : Validate that current category is correct.
            // e.g. for Admin only pages, see that user is Admin.
            handleEventCategory(historyEventCategory, historyToken, result);
          }
        }
      };
    ServiceProvider.getServiceProvider().getLoginService()
      .getLoggedInUserDTO(callback);
  }
  
  public void handleEventCategory(LanternEventCategory historyEventCategory,
    final String historyToken, final UserAccountDto userAccountDTO) {
    LoginCategory currUserLoginCategory = null;
    if (userAccountDTO == null) {
      currUserLoginCategory = LoginCategory.GUEST;
    } else {
      currUserLoginCategory = userAccountDTO.getLoginCategory();
    }
    
    if (historyEventCategory.isUserAllowed(currUserLoginCategory)) {
      switch (historyEventCategory) {
        case HOME:
          // TODO(arjuns) : see if we should truncate the parameters. Not sure
          // if there is any benefit.
          MainPanelPresenter mainPanelPresenter =
            new MainPanelPresenter(new MainPanelView(), historyToken);
          mainPanelPresenter.go(RootLayoutPanel.get());
          break;
        
        case LOGIN_UI:
          LoginPresenter loginPresenter =
            new LoginPresenter(new LoginView(), historyToken);
          loginPresenter.go(RootLayoutPanel.get());
          break;
        
        case ADMIN_UI:
          AdminUiPresenter adminUiPresenter =
            new AdminUiPresenter(new AdminUiView(), historyToken);
          adminUiPresenter.go(RootLayoutPanel.get());
          break;
        case REGISTER_OAUTH_PROVIDER:
          RegisterLoginPresenter registerLoginPresenter =
            new RegisterLoginPresenter(new RegisterOAuthProviderView(),
              historyToken);
          registerLoginPresenter.go(RootLayoutPanel.get());
          break;
        case REGISTER_SCHOOL:
          RegisterSchoolPresenter registerSchoolPresenter =
            new RegisterSchoolPresenter(new RegisterSchoolView(), historyToken);
          registerSchoolPresenter.go(RootLayoutPanel.get());
          break;
        default:
          Window.alert("Invalid event : " + historyEventCategory);
          break;
      }
    } else {
      // User is not authorized. So redirecting to error page.
      Window.Location.assign(ServletPaths.NOT_AUTHORIZED.getRelativePath());
    }
  }
  
  private String
    createTokenWithParams(LanternEventCategory token, String params) {
    return LanternEventCategory.LOGIN_UI.getToken() + "?" + params;
  }
}
