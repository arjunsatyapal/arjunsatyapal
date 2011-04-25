package com.arjunsatyapal.practice.gwt1.client.dummyOne;

import com.arjunsatyapal.practice.gwt1.client.Display;
//import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;
import com.google.gwt.user.client.ui.PopupPanel;

public interface DummyOneDisplay
    extends Display {
  public void setPepeValue(String s);

  public PopupPanel getPopupPanel();

  public void showPopupPanel();

//  public void setClickCallback(SimpleCallback<Object> scb);
}