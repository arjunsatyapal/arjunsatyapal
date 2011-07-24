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
package com.arjunsatyapal.rome.server.servlets;

import com.arjunsatyapal.rome.enums.CustomMediaTypes;
import com.arjunsatyapal.rome.enums.ServletPaths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Resource for fetching ServiceDocument.
 * 
 * @author Arjun Satyapal
 */
@Path(ServletPaths.AP_SERVICE_DOCUMENT_PATH)
public class ServiceDocumentResource {

    @GET
//    @Produces(CustomMediaTypes.APPLICATION_ATOMSVC_XML)
    @Produces(CustomMediaTypes.APPLICATION_ATOM_XML)
    @Path(ServletPaths.AP_SERVICE_DOCUMENT_GET_PATH)
    public String getServiceDocument() {
        return "Hello World!" + System.currentTimeMillis();
    }
}