package com.arjunsatyapal.jersey;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceConfig extends GuiceServletContextListener {
  Logger log = Logger.getLogger(GuiceConfig.class.getName());

  @Override
  protected Injector getInjector() {

    final Map<String, String> params = new HashMap<String, String>();
    /*
     * The following line will scan ausbdsoccer.server.resources package for Jersey Resources
     */
    // params.put("com.sun.jersey.config.property.packages","ausbdsoccer.server.resources");

    /*
     * The following line will use MainJerseyApplication.java to define Jersey resources
     */
    params.put("javax.ws.rs.Application", "com.arjunsatyapal.jersey.MainJerseyApplication");
    params.put("com.sun.jersey.config.feature.DisableWADL", "true");

    return Guice.createInjector(
        new ServletModule() {
          @Override
          protected void configureServlets() {
            // bind(PlayersResource.class); //Works
            // bind(MainJerseyApplication.class); //Does not work
            serve("/rest/*").with(GuiceContainer.class, params);
          }
        });
  }
}
