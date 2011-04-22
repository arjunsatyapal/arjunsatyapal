package com.arjunsatyapal.practice.gwt1.client.cityCreator;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;
import com.arjunsatyapal.practice.gwt1.client.countryState.CountryStateDisplay;

public interface CityCreatorDisplay
    extends Display {

  String getAccentedCityName();

  String getCityName();

  CountryStateDisplay getCountryState();

  float getLatitude();

  float getLongitude();

  int getPopulation();

  void setCityNameCssStyle(String css);

  void setOnAddClickCallback(SimpleCallback<Object> acb);

  void setOnCityNameChangeCallback(SimpleCallback<Object> acb);

  void setOnCountryStateChangeCallback(SimpleCallback<Object> acb);
}
