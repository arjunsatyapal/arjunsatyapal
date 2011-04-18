package com.arjunsatyapal.practice.gwt1.client.i18n;

import com.arjunsatyapal.practice.gwt1.client.Environment;
import com.arjunsatyapal.practice.gwt1.client.Presenter;

public class I18nPresenter
    extends Presenter<I18nDisplay> {
  public static String PLACE = "i18n";

  public I18nPresenter(
      final String params, final I18nDisplay I18nDisplay,
      final Environment environment) {

    super(params, I18nDisplay, environment);
  }
}