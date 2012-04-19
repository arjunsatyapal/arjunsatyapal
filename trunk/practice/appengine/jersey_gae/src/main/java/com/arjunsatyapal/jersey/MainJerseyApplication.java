package com.arjunsatyapal.jersey;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class MainJerseyApplication extends Application {
  public Set<Class<?>> getClasses() {
    Set<Class<?>> s = new HashSet<Class<?>>();
    s.add(PlayersResource.class);
    return s;
  }
}
