package com.arjunsatyapal.practice.client.rpc;

import java.util.ArrayList;

import com.arjunsatyapal.practice.shared.dtos.SchoolDto;
import com.arjunsatyapal.practice.shared.dtos.SchoolSummaryDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("admin/schoolService")
public interface SchoolService extends RemoteService {
  SchoolDto registerSchool(SchoolDto schoolDto);
  
  ArrayList<SchoolSummaryDto> getAllSchools();
}
