package com.arjunsatyapal.practice.client.gwtui.admin.registeroauthproviders;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;

public interface RegisterLoginDisplay extends Display {
  ListBox getListBoxOauthProvider();

  TextBox getTextBoxConsumerSecret();

  TextBox getTextBoxConsumerKey();

  Button getButtonSave();
}
