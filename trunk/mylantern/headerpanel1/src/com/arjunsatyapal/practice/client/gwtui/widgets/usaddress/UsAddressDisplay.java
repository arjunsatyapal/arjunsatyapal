package com.arjunsatyapal.practice.client.gwtui.widgets.usaddress;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;
import com.google.gwt.user.client.ui.TextBox;

public interface UsAddressDisplay extends Display {
  TextBox getTextBoxSchoolName();

  TextBox getTextBoxAddress1();

  TextBox getTextBoxAddress2();

  TextBox getTextBoxCity();

  TextBox getTextBoxState();

  TextBox getTextBoxZip();

  TextBox getTextBoxAdminEmail();
}
