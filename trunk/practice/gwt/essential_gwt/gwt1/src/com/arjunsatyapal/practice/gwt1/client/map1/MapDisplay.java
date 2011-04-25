package com.arjunsatyapal.practice.gwt1.client.map1;

import com.arjunsatyapal.practice.gwt1.client.Display;

public interface MapDisplay
    extends Display {

  double getLatitude();

  double getLongitude();

  void setCoordinates(double latitude, double longitude);
}
