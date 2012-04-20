package com.arjunsatyapal.jersey;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class MainJerseyApplication extends Application {
  public Set<Class<?>> getClasses() {
    Set<Class<?>> set = new HashSet<Class<?>>();
    set.add(ModuleResource.class);
    set.add(JAXBJsonContextResolver.class);

    return set;
  }
}
