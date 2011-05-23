package com.arjunsatyapal.practice.client.gwtui.admin.schoolshowedit;

import java.util.ArrayList;

import com.arjunsatyapal.practice.client.event.HistoryHandler;
import com.arjunsatyapal.practice.client.event.LanternEventCategory;
import com.arjunsatyapal.practice.client.gwtui.mvpinterfaces.Presenter;
import com.arjunsatyapal.practice.client.rpc.ServiceProvider;
import com.arjunsatyapal.practice.shared.dtos.SchoolSummaryDto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.thirdparty.guava.common.collect.ImmutableMap;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class SchoolShowEditPresenter extends Presenter {
  private SchoolShowEditView display;
  private ImmutableMap<Integer, SchoolSummaryDto> map;
  
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
    display.getButtonCancel().setVisible(false);
    display.getButtonCancel().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        // TODO(arjuns) : On pressing cancel, reload with currently highlighted
        // school instead
        // of default school.
        HistoryHandler.handleNewToken(LanternEventCategory.SCHOOL_SHOW_EDIT
          .getToken());
      }
    });
    
    display.getButtonEditSave().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        if (display.getButtonCancel().isVisible()) {
          // This means user has clicked on save.
          // TODO(arjuns) : See if instead of relying on state of button, a
          // session variable
          // can be implemented.
          // TODO(arjuns) : save the edited content.
        } else {
          display.getButtonCancel().setVisible(true);
          display.getButtonEditSave().setText("Save");
        }
      }
    });
    
    display.getListBoxSchoolList().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        int selectedIndex = display.getListBoxSchoolList().getSelectedIndex();
        if (selectedIndex < 0) {
          return;
        }
        
        SchoolSummaryDto selectedDto = map.get(selectedIndex);
        Window.alert("Id = " + selectedDto.getId());
      }
    });
    
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
          if (result.size() == 0) {
            map.clear();
            return;
          }
          
          ImmutableMap.Builder<Integer, SchoolSummaryDto> mapBuilder =
            new ImmutableMap.Builder<Integer, SchoolSummaryDto>();
          int index = 0;
          for (SchoolSummaryDto currSummaryDto : result) {
            display.getListBoxSchoolList().addItem(
              currSummaryDto.getId() + ":" + currSummaryDto.getSchoolName());
            mapBuilder.put(index++, currSummaryDto);
          }
          
          // TODO(arjuns) : Fix this and see how index passed as part of token
          // can be selected by default.
          display.getListBoxSchoolList().setSelectedIndex(0);
          map = mapBuilder.build();
        }
      };
    
    ServiceProvider.getServiceProvider().getSchoolService()
      .getAllSchools(callback);
  }
}
