package com.arjunsatyapal.practice.client.gwtui.signup.student;

import com.arjunsatyapal.practice.client.gwtui.admin.schoolregister.SchoolRegisterDisplay;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasWidgets;

public class StudentSignupPresenter extends Presenter {
  private SchoolRegisterDisplay display;

  public StudentSignupPresenter(
    SchoolRegisterDisplay display, String historyToken) {
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
        
      }
    });
  }
}
