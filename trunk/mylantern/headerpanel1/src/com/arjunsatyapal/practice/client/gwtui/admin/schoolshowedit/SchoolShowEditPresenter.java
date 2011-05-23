package com.arjunsatyapal.practice.client.gwtui.admin.schoolshowedit;

import java.util.ArrayList;

import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.client.rpc.ServiceProvider;
import com.arjunsatyapal.practice.shared.dtos.SchoolSummaryDto;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class SchoolShowEditPresenter extends Presenter {
  private SchoolShowEditView display;
  
  public SchoolShowEditPresenter(SchoolShowEditView display, String historyToken) {
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
    AsyncCallback<ArrayList<SchoolSummaryDto>> callback =
      new AsyncCallback<ArrayList<SchoolSummaryDto>>() {
        @Override
        public void onFailure(Throwable caught) {
          // TODO(arjuns) : do proper handling.
          caught.printStackTrace();
          Window.alert("Failed to fetch list from server.");
        }
        
        @Override
        public void onSuccess(ArrayList<SchoolSummaryDto> result) {
          for (SchoolSummaryDto currSummaryDto : result) {
            display.getListBoxSchoolList().addItem(
              currSummaryDto.getId() + ":" + currSummaryDto.getSchoolName());
          }
        }
      };
      
    ServiceProvider.getServiceProvider().getSchoolService()
      .getAllSchools(callback);
  }
}
