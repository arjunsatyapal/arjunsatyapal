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

import static com.arjunsatyapal.rome.utils.PrettyXmlOutputter.prettyXmlOutputDocument;

import com.arjunsatyapal.rome.atompubimpl.CnxAtomHandlerEnum;
import com.arjunsatyapal.rome.enums.CnxAtomPubConstants;
import com.arjunsatyapal.rome.enums.CustomMediaTypes;
import com.arjunsatyapal.rome.utils.CnxAtomPubServices;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.server.AtomException;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.Writer;

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
public class CnxResource {
    @POST
    @Produces(CustomMediaTypes.APPLICATION_ATOM_XML)
    @Path(CnxAtomPubConstants.COLLECTION_MODULE_POST_PATH)
    public Response getServiceDocument(@Context HttpServletRequest req,
            @Context HttpServletResponse res) throws AtomException {
        // TODO(arjuns) : Add exception handling.
        AtomService service = new CnxAtomPubServices().getServiceDocumentService(req, res,
                CnxAtomHandlerEnum.RESOURCE);

//        AtomHandler handler = new CnxAtomPubServices().getAtomHandler(req, res);
//
//        Feed col = handler.getCollection(areq);
//        col.setFeedType(FEED_TYPE);
//        WireFeedOutput wireFeedOutput = new WireFeedOutput();
//        Document feedDoc = wireFeedOutput.outputJDom(col);
//        res.setContentType("application/atom+xml; charset=utf-8");
//        Writer writer = res.getWriter();
//        XMLOutputter outputter = new XMLOutputter();
//        outputter.setFormat(Format.getPrettyFormat());
//        outputter.output(feedDoc, writer);
//        writer.close();
//        res.setStatus(HttpServletResponse.SC_OK);

        return Response.ok().entity(prettyXmlOutputDocument(service.serviceToDocument())).build();
    }
}
