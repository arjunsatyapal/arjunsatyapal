package com.arjunsatyapal.practice.gwt1.client.countryState;

import java.util.LinkedHashMap;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;

public interface CountryStateDisplay
    extends Display, HasValueChangeHandlers<Object> {

  String getCountry();

  String getState();

  void setCountryList(LinkedHashMap<String, String> cl);

  void setOnCountryChangeCallback(SimpleCallback<Object> acb);

  void setOnStateChangeCallback(SimpleCallback<Object> acb);

  void setStateList(LinkedHashMap<String, String> sl);
}
