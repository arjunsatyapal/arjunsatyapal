package com.arjunsatyapal.practice.client.gwtui.admin.schoolregister;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;
import com.arjunsatyapal.practice.client.gwtui.widgets.usaddress.UsAddressDisplay;
import com.google.gwt.user.client.ui.Button;

public interface SchoolRegisterDisplay extends Display {
  UsAddressDisplay getWidgetUsAddress();
  
  Button getButtonSave();

  Button getButtonCancel();
}
