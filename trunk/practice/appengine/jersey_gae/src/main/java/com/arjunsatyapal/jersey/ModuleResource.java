package com.arjunsatyapal.jersey;

import javax.ws.rs.Produces;

import javax.ws.rs.GET;

import com.google.inject.Injector;

import java.util.logging.Logger;

import javax.ws.rs.Path;

//Import statements ommited
//........
//...........

@Path("/module")
public class ModuleResource {
  Logger log = Logger.getLogger(ModuleResource.class.getName());
  private Injector injector;

  @GET
  @Path("/xml")
  @Produces({"application/json", "text/xml"})
  public Foo getPlayerByNameInXML() {
    return new Foo();
  }
}
