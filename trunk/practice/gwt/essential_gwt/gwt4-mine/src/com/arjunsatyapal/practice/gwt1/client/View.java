package com.arjunsatyapal.practice.gwt1.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author fkereki
 */
public abstract class View extends Composite {

  @Override
  public final Widget asWidget() {
    return this;
  }
}
