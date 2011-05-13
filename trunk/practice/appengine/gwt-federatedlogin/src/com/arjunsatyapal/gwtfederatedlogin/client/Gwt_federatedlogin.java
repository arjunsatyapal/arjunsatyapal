package com.arjunsatyapal.gwtfederatedlogin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import com.arjunsatyapal.gwtfederatedlogin.client.login.LoginPresenter;
import com.arjunsatyapal.gwtfederatedlogin.client.login.LoginView;
import com.arjunsatyapal.gwtfederatedlogin.client.service.LoginService;
import com.arjunsatyapal.gwtfederatedlogin.client.service.LoginServiceAsync;
import com.arjunsatyapal.gwtfederatedlogin.shared.dto.UserAccountDTO;

public class Gwt_federatedlogin implements EntryPoint {
  RootLayoutPanel root;
  private LoginServiceAsync loginService = GWT.create(LoginService.class);;

  @Override
  public void onModuleLoad() {
    final AsyncCallback callback = new AsyncCallback<UserAccountDTO>() {
      @Override
      public void onSuccess(UserAccountDTO result) {
        if (result == null) {
          Window.alert("OnSuccess wth null.");
          showLoginView();
        } else {
          StringBuilder builder = new StringBuilder();
          builder.append("\nemail = ").append(result.getEmailAddress());
          builder.append("\nname = ").append(result.getName());
          Window.alert("OnSuccess : " + builder.toString());
        }
      }

      @Override
      public void onFailure(Throwable caught) {

        Window.alert("OnFailure : " + caught.getMessage());
        showLoginView();
      }
    };

    Command cmd = new Command() {
      @Override
      public void execute() {
        loginService.getLoggedInUserDTO(callback);
      }
    };

    cmd.execute();
  }


  private void showLoginView() {
    root = RootLayoutPanel.get();
    root.clear();
    LoginPresenter loginPresenter = new LoginPresenter(new LoginView());
    loginPresenter.go(root);
  }
}
