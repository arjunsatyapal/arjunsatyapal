package com.arjunsatyapal.practice.client.gwtui.admin.adminlinks;

import com.google.gwt.user.client.ui.Button;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Display;

public interface AdminUiDisplay extends Display {
  Button getButtonAddOAuthProvider();
  
  Button getButtonRegisterSchool();
  
  Button getButtonShowEditSchool();
}
