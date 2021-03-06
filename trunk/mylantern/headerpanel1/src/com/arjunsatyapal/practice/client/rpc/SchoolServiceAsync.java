package com.arjunsatyapal.practice.client.rpc;

import java.util.ArrayList;

import com.arjunsatyapal.practice.shared.dtos.SchoolDto;
import com.arjunsatyapal.practice.shared.dtos.SchoolSummaryDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SchoolServiceAsync {
  void registerSchool(SchoolDto schoolDto, AsyncCallback<SchoolDto> callback);

  void getAllSchools(AsyncCallback<ArrayList<SchoolSummaryDto>> callback);
}
