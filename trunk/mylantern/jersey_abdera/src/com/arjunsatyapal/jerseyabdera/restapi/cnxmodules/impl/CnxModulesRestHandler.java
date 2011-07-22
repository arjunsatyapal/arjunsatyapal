/**
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.arjunsatyapal.jerseyabdera.restapi.cnxmodules.impl;

import com.arjunsatyapal.jerseyabdera.restapi.enums.RestUrls.CnxModules;

import org.apache.abdera.Abdera;
import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.ResponseContext;
import org.apache.abdera.protocol.server.impl.DefaultProvider;
import org.apache.abdera.protocol.server.impl.SimpleWorkspaceInfo;
import org.apache.abdera.protocol.server.servlet.ServletRequestContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Handler for Cnx Resources.
 * 
 * @author Arjun Satyapal
 */
@Path(CnxModules.BASE_URL)
public class CnxModulesRestHandler {
    Logger log = Logger.getLogger(CnxModulesRestHandler.class.getName());

    @Context
    private ServletContext context;
    private Abdera abdera = new Abdera();
    private CnxModuleCollectionAdapter adapter = new CnxModuleCollectionAdapter();
    private SimpleWorkspaceInfo wi = new SimpleWorkspaceInfo();
    private DefaultProvider provider = new DefaultProvider("/");
    @POST
    @Produces(MediaType.APPLICATION_ATOM_XML)
    @Path("/")
    public Response createNewModule(@Context HttpServletRequest request,
            @Context HttpServletResponse response) {
        URI uri = null;
        try {
            uri = new URI("http://www.google.com/tempLocation");
        } catch (Exception e) {
            // TODO(arjuns) : Proper exception handling here.
            e.printStackTrace();
        }

        adapter.setHref("module");
        wi.setTitle("Employee Directory Workspace");
        wi.addCollection(adapter);

        // provider.addWorkspace(wi);
        provider.init(abdera, null);

        RequestContext reqcontext = new ServletRequestContext(provider,
                request, context);
        ResponseContext responseContext = adapter.postEntry(reqcontext);

        ByteArrayOutputStream sos = new ByteArrayOutputStream();
        try {
            responseContext.writeTo(sos);
        } catch (IOException e) {
            // TODO(arjuns): Auto-generated catch block
            e.printStackTrace();
        }

        String content = sos.toString();

        return Response.created(uri)
                .header("Content-Type", "application/atom+xml;type=entry")
                .entity(content).build();// header("Content-, value)
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path(CnxModules.MODULE_URL)
    public Response getResource(
            @PathParam(CnxModules.MODULE_ID) String moduleId,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {
        URI uri = null;
        try {
            uri = new URI("http://www.google.com/tempLocation");
        } catch (Exception e) {
            // TODO(arjuns) : Proper exception handling here.
            e.printStackTrace();
        }
        
        adapter.setHref("module");
        wi.setTitle("Employee Directory Workspace");
        wi.addCollection(adapter);

        // provider.addWorkspace(wi);
        provider.init(abdera, null);

        RequestContext reqcontext = new ServletRequestContext(provider,
                request, context);
        ResponseContext responseContext = adapter.getEntry(reqcontext);

        ByteArrayOutputStream sos = new ByteArrayOutputStream();
        try {
            responseContext.writeTo(sos);
        } catch (IOException e) {
            // TODO(arjuns): Auto-generated catch block
            e.printStackTrace();
        }
        String content = sos.toString();

        return Response.created(uri)
                .header("Content-Type", "application/atom+xml;type=entry")
                .entity(content).build();// header("Content-, value)
    }
}
