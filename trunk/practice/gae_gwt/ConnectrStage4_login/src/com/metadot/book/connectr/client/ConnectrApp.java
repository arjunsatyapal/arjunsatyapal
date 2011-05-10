/**
 * Copyright 2010 Daniel Guermeur and Amy Unruh
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * See http://connectrapp.appspot.com/ for a demo, and links to more information
 * about this app and the book that it accompanies.
 */
package com.metadot.book.connectr.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.metadot.book.connectr.client.event.LoginEvent;
import com.metadot.book.connectr.client.helper.RPCCall;
import com.metadot.book.connectr.client.presenter.BusyIndicatorPresenter;
import com.metadot.book.connectr.client.presenter.LoginPresenter;
import com.metadot.book.connectr.client.service.LoginService;
import com.metadot.book.connectr.client.service.LoginServiceAsync;
import com.metadot.book.connectr.client.view.BusyIndicatorView;
import com.metadot.book.connectr.client.view.LoginView;
import com.metadot.book.connectr.shared.UserAccountDTO;

public class ConnectrApp implements EntryPoint {

  interface ConnectrAppUiBinder extends UiBinder<DockLayoutPanel, ConnectrApp> {
  }

  SerializationStreamFactory pushServiceStreamFactory;

  @UiField
  HeaderPanel headerPanel;
  @UiField
  ScrollPanel mainPanel;
  @UiField
  VerticalPanel friendListPanel;

  RootLayoutPanel root;

  private static ConnectrApp singleton;
  private UserAccountDTO currentUser;
  private SimpleEventBus eventBus = new SimpleEventBus();
  BusyIndicatorPresenter busyIndicator = new BusyIndicatorPresenter(eventBus,
      new BusyIndicatorView("Working hard..."));

  // RPC services
  private LoginServiceAsync loginService = GWT.create(LoginService.class);;

  /**
   * Gets the singleton application instance.
   */
  public static ConnectrApp get() {
    return singleton;
  }

  private static final ConnectrAppUiBinder binder = GWT.create(ConnectrAppUiBinder.class);

  public void onModuleLoad() {
    singleton = this;

    getLoggedInUser();

  }

  private void getLoggedInUser() {
    new RPCCall<UserAccountDTO>() {
      @Override
      protected void callService(AsyncCallback<UserAccountDTO> cb) {
        loginService.getLoggedInUserDTO(cb);
      }

      @Override
      public void onSuccess(UserAccountDTO loggedInUserDTO) {
        if (loggedInUserDTO == null) {
          // nobody is logged in
          showLoginView();
        } else {
          // user is logged in
          setCurrentUser(loggedInUserDTO);
          createUI();
        }
      }

      @Override
      public void onFailure(Throwable caught) {
        Window.alert("Error: " + caught.getMessage());
      }
    }.retry(3);
  }

  public void showLoginView() {
    root = RootLayoutPanel.get();
    root.clear();
    LoginPresenter loginPresenter = new LoginPresenter(eventBus, new LoginView());
    loginPresenter.go(root);
  }

  private void goAfterLogin() {

    DockLayoutPanel outer = binder.createAndBindUi(this);
    root = RootLayoutPanel.get();
    root.clear();
    root.add(outer);

//    appViewer.go();
  }

  public SimpleEventBus getEventBus() {
    return eventBus;
  }

  private void createUI() {

    GWT.runAsync(new RunAsyncCallback() {
      @Override
      public void onFailure(Throwable reason) {
        Window.alert("Code download error: " + reason.getMessage());
      }

      @Override
      public void onSuccess() {
        goAfterLogin();
        eventBus.fireEvent(new LoginEvent(currentUser));
      }
    });
  }

  void setCurrentUser(UserAccountDTO currentUser) {
    this.currentUser = currentUser;
  }

  UserAccountDTO getCurrentUser() {
    return currentUser;
  }

  /**
   * @return the mainPanel
   */
  ScrollPanel getMainPanel() {
    return mainPanel;
  }

  /**
   * @return the friendList
   */
  VerticalPanel getFriendListPanel() {
    return friendListPanel;
  }
}
