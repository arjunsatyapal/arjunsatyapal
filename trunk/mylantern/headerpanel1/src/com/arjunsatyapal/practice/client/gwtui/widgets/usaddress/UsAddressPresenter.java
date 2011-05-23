package com.arjunsatyapal.practice.client.gwtui.widgets.usaddress;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;
import com.google.gwt.user.client.ui.HasWidgets;

public class UsAddressPresenter extends Presenter {
  private UsAddressView display;
  public UsAddressPresenter(
      UsAddressView display, String historyToken) {
    super(display.getLanternHeaderPanel(), historyToken);
    this.display = display;
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
    bind();
  }

  @Override
  public void bind() {
  }
}
