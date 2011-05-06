/**
 * Copyright 2011 Google Inc.
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
 */
package com.google.lantern.client.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.lantern.client.common.Presenter;

public class LoginPresenter implements Presenter {
  private final LoginDisplay loginDisplay;

  public LoginPresenter(LoginDisplay display) {
    this.loginDisplay = display;
  }

  public void bind() {
    this.loginDisplay.getGoogleButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginGoogle();
      }
    });

    this.loginDisplay.getTwitterButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginTwitter();
      }
    });

    this.loginDisplay.getFacebookButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        doLoginFacebook();
      }
    });
  }

  @Override
  public void go(final HasWidgets container) {
    container.clear();
    container.add(loginDisplay.asWidget());
    bind();
  }

  private void doLoginFacebook() {
    Window.Location.assign("/loginfacebook");
  }

  private void doLoginGoogle() {
    Window.Location.assign("/logingoogle");
  }

  private void doLoginTwitter() {
    Window.Location.assign("/logintwitter");
  }

}
