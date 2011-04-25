/**
 * 
 */
package com.arjunsatyapal.practice.gwt1.client.citiesBrowser4;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;
import com.arjunsatyapal.practice.gwt1.client.countryState.CountryStateDisplay;

/**
 * @author fkereki
 */
public interface CitiesBrowserDisplay extends Display {
  
  CountryStateDisplay getCountryState();
  
  void setCityData(final int i, final String name, final String pop,
    final String lat, final String lon);
  
  void setOnCountryStateChangeCallback(SimpleCallback<Object> acb);
  
  void setOnFirstClickCallback(SimpleCallback<Object> acb);
  
  void setOnNextClickCallback(SimpleCallback<Object> acb);
  
  void setOnPreviousClickCallback(SimpleCallback<Object> acb);
}
