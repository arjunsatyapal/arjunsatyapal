package com.arjunsatyapal.jersey;

import javax.ws.rs.Produces;

import javax.ws.rs.GET;

import com.google.inject.Injector;

import java.util.logging.Logger;

import javax.ws.rs.Path;

//Import statements ommited
//........
//...........

@Path("/players")
public class PlayersResource {
  Logger log = Logger.getLogger(PlayersResource.class.getName());
  private Injector injector;

  @GET
  @Path("/xml")
  @Produces("text/plain")
  public String getPlayerByNameInXML() {
    return "Hello world";
  }
}
