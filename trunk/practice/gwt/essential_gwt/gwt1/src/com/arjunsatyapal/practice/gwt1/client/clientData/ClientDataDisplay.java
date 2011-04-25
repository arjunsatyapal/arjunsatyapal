package com.arjunsatyapal.practice.gwt1.client.clientData;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;
import com.google.gwt.user.client.ui.PopupPanel;

public interface ClientDataDisplay
    extends Display {
  public void setClient(String s);

  public void setSalute(String s);

  public void setName(String s);

  public void setAddress(String s);

  public void setCity(String s);

  public void setZip(String s);

  public void setState(String s);

  public String getClient();

  public String getSalute();

  public String getName();

  public String getAddress();

  public String getCity();

  public String getZip();

  public String getState();

  public PopupPanel getPopupPanel();

  public void hidePopupPanel();

  public void showPopupPanel();

  public void setSearchClickCallback(SimpleCallback<Object> scb);
}