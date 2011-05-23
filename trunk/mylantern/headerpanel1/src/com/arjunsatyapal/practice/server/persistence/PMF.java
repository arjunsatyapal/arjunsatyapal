package com.arjunsatyapal.practice.server.persistence;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

/**
 * Singleton wrapper over JDO PersistenceManagerFactory.
 */
public class PMF {
  // TODO(arjuns) : See other possible values for PersistenceManagerFactory.
  private static final PersistenceManagerFactory instance = JDOHelper
    .getPersistenceManagerFactory("transactions-optional");
  
  private PMF() {
  }
  
  public static PersistenceManager getPersistenceManager() {
    return instance.getPersistenceManager();
  }
}
