package com.arjunsatyapal.practice.client.gwtui.signup.student;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import com.arjunsatyapal.practice.client.gwtui.lanternheaderpanel.LanternHeaderPanelDisplay;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;

public class StudentSignupPresenter extends Presenter implements StudentSignupDisplay {
  private StudentSignupDisplay display;

  public StudentSignupPresenter(
      StudentSignupDisplay display, String historyToken) {
    super(display.getLanternHeaderPanel(), historyToken);
    this.display = display;
  }

  @Override
  public Widget asWidget() {
    // TODO(arjuns): Auto-generated method stub
    return null;
  }

  @Override
  public LanternHeaderPanelDisplay getLanternHeaderPanel() {
    // TODO(arjuns): Auto-generated method stub
    return null;
  }

  @Override
  public void go(HasWidgets container) {
    // TODO(arjuns): Auto-generated method stub

  }

  @Override
  public void bind() {
    // TODO(arjuns): Auto-generated method stub

  }

}
