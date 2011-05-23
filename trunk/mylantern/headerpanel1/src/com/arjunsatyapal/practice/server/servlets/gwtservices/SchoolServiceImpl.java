package com.arjunsatyapal.practice.server.servlets.gwtservices;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.arjunsatyapal.practice.client.rpc.SchoolService;
import com.arjunsatyapal.practice.server.domain.SchoolDao;
import com.arjunsatyapal.practice.server.persistence.PMF;
import com.arjunsatyapal.practice.shared.dtos.SchoolDto;
import com.arjunsatyapal.practice.shared.dtos.SchoolSummaryDto;
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
  public ArrayList<SchoolSummaryDto> getAllSchools() {
    ArrayList<SchoolSummaryDto> listOfDtos = new ArrayList<SchoolSummaryDto>();
    
    PersistenceManager pm = PMF.getPersistenceManager();
    Query query = pm.newQuery(SchoolDao.class);
    
    try {
      @SuppressWarnings("unchecked")
      List<SchoolDao> listOfDao = (List<SchoolDao>) query.execute();
      for(SchoolDao currDao : listOfDao) {
        listOfDtos.add(currDao.toSummaryDto());
      }
    } catch (Throwable caught) {
      //TODO(arjuns): Handle exception properly.
      caught.printStackTrace();
    } finally {
      query.closeAll();
      pm.close();
    }
    
    return listOfDtos;
  }
}
