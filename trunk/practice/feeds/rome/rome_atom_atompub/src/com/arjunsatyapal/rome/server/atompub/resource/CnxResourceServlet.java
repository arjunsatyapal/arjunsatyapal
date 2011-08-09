/*
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
package com.arjunsatyapal.rome.server.atompub.resource;

import com.google.common.base.Throwables;

import com.arjunsatyapal.rome.enums.CnxAtomPubConstants;
import com.arjunsatyapal.rome.enums.CustomMediaTypes;
import com.arjunsatyapal.rome.utils.CnxAtomPubServices;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.impl.Atom10Generator;
import com.sun.syndication.io.impl.Atom10Parser;
import com.sun.syndication.propono.atom.server.AtomException;
import com.sun.syndication.propono.atom.server.AtomRequest;
import com.sun.syndication.propono.atom.server.AtomRequestImpl;

import org.jdom.JDOMException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Arjun Satyapal
 */
@Path(CnxAtomPubConstants.COLLECTION_RESOURCE_REL_PATH)
public class CnxResourceServlet {
    private Logger logger = Logger.getLogger(CnxResourceServlet.class.getName());
    
    @POST
    @Produces(CustomMediaTypes.APPLICATION_ATOM_XML)
    @Path(CnxAtomPubConstants.COLLECTION_MODULE_POST_PATH)
    public Response getServiceDocument(@Context HttpServletRequest req,
            @Context HttpServletResponse res) throws AtomException, IllegalArgumentException, UnsupportedEncodingException, JDOMException, IOException, FeedException {
        // TODO(arjuns) : Add exception handling.
        CnxAtomPubServices services = new CnxAtomPubServices();
        CnxResourceAtomHandler handler = services.createCnxResourceAtomHandler(req,
                res);
        AtomRequest areq = new AtomRequestImpl(req);

        // Parse incoming entry
        Entry entry = Atom10Parser.parseEntry(
                new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8")), null);
        
        String strentry = entry.toString();
        logger.info("Entry = " + entry.toString());
        
        // call handler to post it
        Entry newEntry = handler.postEntry(areq, entry);
        
        // TODO(arjuns) : ADd validation for this.
        Link createdUri = (Link) newEntry.getOtherLinks().get(0);
        URI uri = null;
        try {
            String resolved = createdUri.getRel() + createdUri.getHref();
            uri = new URI(resolved);
        } catch (Exception e) {
            logger.severe(Throwables.getStackTraceAsString(e));
        }

        Writer writer = new BufferedWriter(res.getWriter());
        Atom10Generator.serializeEntry(newEntry, writer);
        
        return Response.created(uri).build();
    }
}
