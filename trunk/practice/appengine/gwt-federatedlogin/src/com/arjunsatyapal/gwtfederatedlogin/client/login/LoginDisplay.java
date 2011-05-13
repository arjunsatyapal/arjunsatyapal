package com.arjunsatyapal.gwtfederatedlogin.client.login;

import com.arjunsatyapal.gwtfederatedlogin.client.common.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface LoginDisplay extends Display {
  HasClickHandlers getAolButton();
  
  HasClickHandlers getFacebookButton();
  
  HasClickHandlers getGoogleButton();
  
  HasClickHandlers getMyOpenIdButton();
  
  HasClickHandlers getTwitterButton();
  
  HasClickHandlers getYahooButton();
}
