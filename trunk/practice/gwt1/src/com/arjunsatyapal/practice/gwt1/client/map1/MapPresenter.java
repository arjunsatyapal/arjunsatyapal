package com.arjunsatyapal.practice.gwt1.client.map1;

import com.arjunsatyapal.practice.gwt1.client.Environment;
import com.arjunsatyapal.practice.gwt1.client.Presenter;

public class MapPresenter
    extends Presenter<MapDisplay> {

  public static String PLACE = "map";

  public MapPresenter(
      final String params, final MapDisplay mapDisplay,
      final Environment environment) {

    super(params, mapDisplay, environment);
  }
}
