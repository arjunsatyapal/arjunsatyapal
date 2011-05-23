package com.arjunsatyapal.practice.client.gwtui.admin.adminlinks;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.event.HistoryHandler;
import com.arjunsatyapal.practice.client.event.LanternEventCategory;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;

public class AdminUiPresenter extends Presenter {
  private final AdminUiDisplay display;

  public AdminUiPresenter(AdminUiDisplay display, String historyToken) {
    super(display.getLanternHeaderPanel(), historyToken);
    this.display = display;
  }

  @Override
  public void bind() {
    display.getButtonAddOAuthProvider().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        HistoryHandler.handleNewToken(LanternEventCategory.REGISTER_OAUTH_PROVIDER.getToken());
      }
    });

    display.getButtonRegisterSchool().addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        HistoryHandler.handleNewToken(LanternEventCategory.SCHOOL_REGISTER.getToken());
      }
    });
    
    display.getButtonShowEditSchool().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        HistoryHandler.handleNewToken(LanternEventCategory.SCHOOL_SHOW_EDIT.getToken());
      }
    });
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
    bind();
  }
}
