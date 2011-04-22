package com.arjunsatyapal.practice.gwt1.client.login;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;

public interface LoginFormDisplay extends Display {
  String getName();
  void setName(String s);
  String getPassword();
  void setPassword(String s);
  
  /**
   * Initialize the login callback, which shall be executed when the user clicks
   * the "Login" button
   * 
   * @param acb
   *          Set the login callback to acb. The Presenter will have to get the
   *          Name and Password fields (by using the methods above) and perform
   *          the needed checks.
   */
  void setLoginCallback(SimpleCallback<Object> acb);
}
