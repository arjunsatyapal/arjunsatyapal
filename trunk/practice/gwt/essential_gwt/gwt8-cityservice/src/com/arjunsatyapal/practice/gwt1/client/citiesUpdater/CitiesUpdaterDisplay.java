/**
 * 
 */
package com.arjunsatyapal.practice.gwt1.client.citiesUpdater;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;

/**
 * @author fkereki
 */
public interface CitiesUpdaterDisplay extends Display {
  
  void clearAllCities();
  
  String getCityNameStart();
  
  int getCityPopulation(final int i);
  
  void setCityData(final int i, final String cityName,
    final String countryName, final String stateName, final int population);
  
  void setOnCityNameStartChangeCallback(SimpleCallback<Object> acb);
  
  void setOnGetCitiesClickCallback(SimpleCallback<Object> acb);
  
  void setOnUpdateCitiesClickCallback(SimpleCallback<Object> acb);
}
