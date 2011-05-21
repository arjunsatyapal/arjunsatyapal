package com.arjunsatyapal.practice.client.gwtui.admin.registerschool;

import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;

public class RegisterSchoolPresenter extends Presenter {
  private RegisterSchoolView display;
  protected RegisterSchoolPresenter(
      RegisterSchoolView display, String historyToken) {
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
