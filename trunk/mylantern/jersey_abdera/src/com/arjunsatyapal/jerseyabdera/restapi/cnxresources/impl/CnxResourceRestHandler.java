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
package com.arjunsatyapal.jerseyabdera.restapi.cnxresources.impl;


import com.arjunsatyapal.jerseyabdera.restapi.enums.RestUrls.CnxResources;

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
@Path(CnxResources.BASE_URL)
public class CnxResourceRestHandler {
    @POST
    @Produces(MediaType.APPLICATION_ATOM_XML)
    @Path("/")
    public Response createUploadUrl() {
        // TODO(arjuns) : convert this to atom.
//        ResourceEntity entity = ResourceUtil.createNewResource();
//        Response.ResponseBuilder respBuilder = Response.created(entity.getUploadUrl());
//
//        StringBuilder strBuilder = new StringBuilder("resourceId=")
//            .append(entity.getResourceId())
//            .append("\nuploadUrl=")
//            .append(entity.getUploadUrl())
//            .append("\n");
//        
//        respBuilder.entity(strBuilder.toString());
//        return respBuilder.build();
        return null;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path(CnxResources.RESOURCE_URL)
    public Response getResource(@PathParam(CnxResources.RESOURCE_ID) String resourceId,
            @Context HttpServletResponse response){
        
    

//        String contentType = null;
//        // Serve the resource from Blobstore.
//        try {
//            //TODO(arjuns) : Add blobkey on cache.
////            Services.blobstore.serve(resourceId, response);
////            
////            BlobInfo blobInfo = Services.blobInfoFactory.loadBlobInfo(blobKey);
////            contentType = blobInfo.getContentType();
//        } catch (IOException e) {
//            // TODO(arjuns): Auto-generated catch block
//            e.printStackTrace();
//        }
        return null;
//        return Response.status(200).header("Content-Type", contentType).build();
    }
}