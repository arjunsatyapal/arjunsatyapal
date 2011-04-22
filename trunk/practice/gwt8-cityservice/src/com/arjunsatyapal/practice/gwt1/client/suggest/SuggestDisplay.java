package com.arjunsatyapal.practice.gwt1.client.suggest;

import com.arjunsatyapal.practice.gwt1.client.Display;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

public interface SuggestDisplay
    extends Display {

  String getCityName();

  void setCitiesOracle(MultiWordSuggestOracle oracle);
}
