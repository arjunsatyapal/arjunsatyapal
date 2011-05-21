package com.arjunsatyapal.practice.client.gwtui.admin.registerschool;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;

public interface RegisterSchoolDisplay extends Display {
  TextBox getTextBoxSchoolName();

  TextBox getTextBoxAddress1();

  TextBox getTextBoxAddress2();

  TextBox getTextBoxCity();

  TextBox getTextBoxState();

  TextBox getTextBoxZip();

  TextBox getTextBoxAdminEmail();

  Button getButtonSave();

  Button getButtonCancel();
}
