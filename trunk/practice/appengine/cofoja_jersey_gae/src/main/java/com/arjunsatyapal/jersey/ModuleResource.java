package com.arjunsatyapal.jersey;

import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/module")
public class ModuleResource {
  Logger log = Logger.getLogger(ModuleResource.class.getName());

  @GET
  @Path("/xml")
  @Produces({"application/json", "text/xml"})
  public String getPlayerByNameInXML() {
    return "hello";
  }
  
  @GET
  @Path("/json")
  @Produces({"application/json", "text/xml"})
  public Foo getFoo() {
    Foo foo = new Foo();
    foo.set(-1);
    
    return foo;
  }
}
