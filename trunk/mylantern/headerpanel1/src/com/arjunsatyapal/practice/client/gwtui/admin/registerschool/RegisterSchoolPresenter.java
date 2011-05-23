package com.arjunsatyapal.practice.client.gwtui.admin.registerschool;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import com.arjunsatyapal.practice.client.event.HistoryHandler;
import com.arjunsatyapal.practice.client.event.LanternEventCategory;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.client.rpc.ServiceProvider;
import com.arjunsatyapal.practice.shared.dtos.SchoolDto;

public class RegisterSchoolPresenter extends Presenter {
  private RegisterSchoolView display;
  public RegisterSchoolPresenter(
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
        SchoolDto schoolDto = new SchoolDto.Builder()
            .setSchoolName(display.getTextBoxSchoolName().getText())
            .setAddress1(display.getTextBoxAddress1().getText())
            .setAddress2(display.getTextBoxAddress2().getText())
            .setCity(display.getTextBoxCity().getText())
            .setState(display.getTextBoxState().getText())
            .setZip(display.getTextBoxZip().getText())
            .setAdminEmail(display.getTextBoxAdminEmail().getText())
            .build();

        AsyncCallback<SchoolDto> callback = new AsyncCallback<SchoolDto>() {
          @Override
          public void onFailure(Throwable caught) {
           caught.printStackTrace();
           Window.alert(caught.getLocalizedMessage());
            Window.alert("Failed to register school. Try again.");
          }

          @Override
          public void onSuccess(SchoolDto result) {
            Window.alert("Successfully Saved : " + result.toString());
            HistoryHandler.handleNewToken(LanternEventCategory.ADMIN_UI.getToken());
          }
        };
        
        ServiceProvider.getServiceProvider().getSchoolService().registerSchool(schoolDto, callback);
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
