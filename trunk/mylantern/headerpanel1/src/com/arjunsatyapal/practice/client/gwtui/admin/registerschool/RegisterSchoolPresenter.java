package com.arjunsatyapal.practice.client.gwtui.admin.registerschool;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.event.HistoryHandler;
import com.arjunsatyapal.practice.client.event.LanternEventCategory;
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
    display.getButtonSave().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        HistoryHandler.handleNewToken(LanternEventCategory.REGISTER_SCHOOL.getToken());
      }
    });

    display.getButtonCancel().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        HistoryHandler.handleNewToken(LanternEventCategory.ADMIN_UI.getToken());
      }
    });
  }
}
