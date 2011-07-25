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
package com.arjunsatyapal.rome.server.atompub;

import static com.arjunsatyapal.rome.utils.PrettyXmlOutputter.prettyXmlOutputElement;

import com.arjunsatyapal.rome.enums.CustomMediaTypes;
import com.arjunsatyapal.rome.enums.ServletPaths;
import com.arjunsatyapal.rome.utils.Services;
import com.sun.syndication.feed.atom.Category;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.common.Categories;
import com.sun.syndication.propono.atom.server.AtomException;

import java.io.IOException;

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
@Path(ServletPaths.AP_CATEGORIES_DOCUMENT_PATH)
public class CategoriesDocument {
    private final String scheme = ServletPaths.ATOMPUB_URL; 
    @GET
    // @Produces(CustomMediaTypes.APPLICATION_ATOMSVC_XML)
    @Produces(CustomMediaTypes.APPLICATION_ATOM_XML)
    @Path(ServletPaths.AP_CATEGORIES_DOCUMENT_GET_PATH)
    public Response getServiceDocument(@Context HttpServletRequest req,
            @Context HttpServletResponse res) throws AtomException, IOException {
        // TODO(arjuns) : Add exception handling.
        AtomService service = Services.getServiceDocumentService(req, res);
        
        Category resourceCategory = new Category();
        resourceCategory.setLabel(ServletPaths.AP_COLLECTION_RESOURCE_REL_PATH);
        resourceCategory.setScheme(ServletPaths.AP_COLLECTINO_RESOURCE_ABS_PATH);
        resourceCategory.setTerm(ServletPaths.AP_COLLECTION_RESOURCE_REL_PATH);
        
        
        Categories categories = new Categories();
        categories.addCategory(resourceCategory);

        
        return Response.ok()
                .entity(prettyXmlOutputElement(categories.categoriesToElement())).build();
    }
}