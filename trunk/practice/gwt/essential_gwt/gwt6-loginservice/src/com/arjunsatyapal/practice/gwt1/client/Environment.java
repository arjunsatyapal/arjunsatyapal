package com.arjunsatyapal.practice.gwt1.client;

import com.arjunsatyapal.practice.gwt1.client.login.LoginFormPresenter;
import com.arjunsatyapal.practice.gwt1.client.login.LoginFormView;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class Environment {
  protected class HistoryCommand
      implements Command {
    String historyToken;

    public HistoryCommand(final String newToken) {
      historyToken = newToken;
    }

    public void execute() {
      launch(historyToken);
    }
  }

  final Model model;
  String startingToken;

  String currentUser;
  String currentKey;
  String currentPassword;

  abstract class MyRunAsyncCallback
      implements RunAsyncCallback {

    String myOwnArgs;
    Panel myOwnPanel;
    Environment myOwnEnvironment;
    String myOwnErrorMessage;

    public MyRunAsyncCallback(
        final String args, final Panel panel,
        final Environment environment, final String errorMessage) {

      myOwnArgs = args;
      myOwnPanel = panel;
      myOwnEnvironment = environment;
      myOwnErrorMessage = errorMessage;
    }

    @Override
    public void onFailure(final Throwable reason) {
      myOwnEnvironment.showAlert(myOwnErrorMessage);
    }
  }

  Command sorry = new Command() {
    @Override
    public void execute() {
      showAlert("Sorry, this isn't ready yet.");
    }
  };

  public Environment(final Model aModel, final String aToken) {
    model = aModel;
    startingToken = aToken;
  }

  
  public String getCurrentSessionKey() {
    return currentKey;
  }

  public String getCurrentUserName() {
    return currentUser;
  }

  public String getCurrentUserPassword() {
    return currentPassword;
  }

  public Model getModel() {
    return model;
  }

  public void launch(final String token) {
    launch(token, null);
  }

  public void launch(String token, Panel panel) {
    /*
     * There could be parameters after the "#token" in the classic form
     * "?key1=value1&key2=value2..."; for more on this, check
     * http://www.w3.org/TR/hash-in-uri/.
     */
    
    Window.alert("Something to launch.");
    String args = "";
    final int question = token.indexOf("?");
    if (question != -1) {
      args = token.substring(question + 1);
      token = token.substring(0, question);
    }

    if (token.isEmpty()) {
      // no need to do anything...
    } else if (token.equals(LoginFormPresenter.PLACE)) {
      showLogin(RootPanel.get());
    } else {
      Window.alert("Unrecognized token=" + token);
    }
  }

  public void setCurrentSessionKey(final String s) {
    currentKey = s;
  }

  public void setCurrentUserName(final String s) {
    currentUser = s;
  }

  public void setCurrentUserPassword(final String s) {
    currentPassword = s;
  }

  public void showAlert(final String alertText) {
    Window.alert(alertText);
  }

  private void showLogin(final Panel panel) {
    setCurrentUserName("");

    final LoginFormPresenter loginForm = new LoginFormPresenter("",
        new LoginFormView(), this,
        new SimpleCallback<String>() {
          @Override
          public void goBack(final String result) {
            setCurrentUserName(result);
            showAlert("UserNaem = " + result);
          }
        });

    panel.clear();
    panel.add(loginForm.getDisplay().asWidget());
  }

  private void showMainMenu() {
    Window.alert("ShowMainMenu");
  }
}
