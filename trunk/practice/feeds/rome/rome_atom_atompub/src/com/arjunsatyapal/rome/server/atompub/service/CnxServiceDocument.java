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
package com.arjunsatyapal.rome.server.atompub.service;

import static com.arjunsatyapal.rome.utils.PrettyXmlOutputter.prettyXmlOutputDocument;

import com.arjunsatyapal.rome.atompubimpl.CnxAtomHandlerEnum;
import com.arjunsatyapal.rome.enums.CnxAtomPubConstants;
import com.arjunsatyapal.rome.enums.CustomMediaTypes;
import com.arjunsatyapal.rome.utils.CnxAtomPubServices;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.server.AtomException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * REST Resource for fetching ServiceDocument.
 * 
 * @author Arjun Satyapal
 */
@Path(CnxAtomPubConstants.SERVICE_DOCUMENT_PATH)
public class CnxServiceDocument {

    @GET
    // @Produces(CustomMediaTypes.APPLICATION_ATOMSVC_XML)
    @Produces(CustomMediaTypes.APPLICATION_ATOM_XML)
    @Path(CnxAtomPubConstants.SERVICE_DOCUMENT_GET_PATH)
    public Response getServiceDocument(@Context HttpServletRequest req,
            @Context HttpServletResponse res) throws AtomException {
        // TODO(arjuns) : Add exception handling.
        AtomService service = new CnxAtomPubServices().getServiceDocumentService(req, res,
                CnxAtomHandlerEnum.SERVICE);

        return Response.ok().entity(prettyXmlOutputDocument(service.serviceToDocument())).build();
    }
}