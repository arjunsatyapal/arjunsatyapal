package com.arjunsatyapal.practice.client.gwtui.admin.schoolshowedit;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;
import com.arjunsatyapal.practice.client.gwtui.widgets.usaddress.UsAddressDisplay;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;

public interface SchoolShowEditDisplay extends Display {
  ListBox getListBoxSchoolList();
  
  UsAddressDisplay getWidgetUsAddress();
  
  Button getButtonEditSave();
  
  Button getButtonCancel();
}
