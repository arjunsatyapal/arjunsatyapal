package com.arjunsatyapal.practice.gwt1.client.dummyOne;

import com.arjunsatyapal.practice.gwt1.client.Environment;
import com.arjunsatyapal.practice.gwt1.client.Presenter;
import com.arjunsatyapal.practice.gwt1.client.SimpleCallback;

public class DummyOnePresenter
    extends Presenter<DummyOneDisplay> {
  public static String PLACE = "foo";

  public DummyOnePresenter(
      String params, DummyOneDisplay dummyOneDisplay,
      Environment environment) {

    super(params, dummyOneDisplay, environment);
    dummyOneDisplay.setPepeValue(getKvm().get("pepe"));
    dummyOneDisplay.setClickCallback(new SimpleCallback<Object>() {

      @Override
      public void goBack(Object result) {
        DummyOnePresenter.this.getDisplay().showPopupPanel();
        DummyOnePresenter.this.getEnvironment().launch("baz",
            DummyOnePresenter.this.getDisplay().getPopupPanel());
      }
    });
  }
}
