package com.arjunsatyapal.practice.client.rpc;

import com.arjunsatyapal.practice.shared.dtos.SchoolDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("admin/schoolService")
public interface SchoolService extends RemoteService {
  SchoolDto registerSchool(SchoolDto schoolDto);
  SchoolDto[] getAllSchools();
}
