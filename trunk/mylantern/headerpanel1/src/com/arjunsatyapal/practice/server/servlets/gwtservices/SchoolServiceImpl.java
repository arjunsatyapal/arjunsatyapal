package com.arjunsatyapal.practice.server.servlets.gwtservices;

import javax.jdo.PersistenceManager;

import com.arjunsatyapal.practice.client.rpc.SchoolService;
import com.arjunsatyapal.practice.server.domain.SchoolDao;
import com.arjunsatyapal.practice.server.persistence.PMF;
import com.arjunsatyapal.practice.shared.dtos.SchoolDto;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SchoolServiceImpl extends RemoteServiceServlet implements
  SchoolService {
  
  @Override
  public SchoolDto registerSchool(SchoolDto schoolDto) {
    SchoolDao detached = null;
    SchoolDao schoolDao = SchoolDao.fromDto(schoolDto, true /*isCreate*/);
    
    PersistenceManager pm = PMF.getPersistenceManager();
    try {
      pm.makePersistent(schoolDao);
      detached = pm.detachCopy(schoolDao);
    } catch (Throwable caught) {
      //TODO(arjuns) : handle exception properly.
      caught.printStackTrace();
    } finally {
      pm.close();
    }
    
    return detached.toDto();
  }

  @Override
  public SchoolDto[] getAllSchools() {
    // TODO Auto-generated method stub
    return null;
  }
}
