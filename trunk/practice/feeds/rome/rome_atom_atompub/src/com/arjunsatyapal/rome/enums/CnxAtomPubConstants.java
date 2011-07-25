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
package com.arjunsatyapal.rome.enums;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Arjun Satyapal
 */
public class CnxAtomPubConstants {
    // TODO(arjuns) : Make figuring out parent url dynamic.
    /** Path for REST URL for ATOMPUB API. */
    public final String atomPubRestUrl;
    
    public CnxAtomPubConstants(HttpServletRequest request) {
        
        //TODO(arjuns) : Fix this hacky stuff.
        String url = request.getRequestURL().toString();
        if (url.startsWith("http://localhost")) {
            atomPubRestUrl = "http://localhost:8888/atompub";
        } else {
            atomPubRestUrl = "http://arjuns-test.appspot.com/atompub";
        }
    }

    /** Path for Service Document relative to {@link #atomPubRestUrl}. */
    public static final String SERVICE_DOCUMENT_PATH = "/service_document";
    public static final String SERVICE_DOCUMENT_GET_PATH = "/";

    /** Path for CategoryDocument relative to {@link #atomPubRestUrl}. */
    public static final String CATEGORIES_DOCUMENT_PATH = "/category_document";
    public static final String CATEGORIES_DOCUMENT_GET_PATH = "/";
    
    /** Get URI for CategoryDocument */
    public String getCategoryDocumentAbsPath() {
        return atomPubRestUrl + CATEGORIES_DOCUMENT_PATH;
    }
    
    /** At present, path for CategoryDocument itself is used as a scheme. */
    public String getSchemeAbsUrl() {
        return getCategoryDocumentAbsPath();
    }

    // Constants for Resources.
    /** Name for AtomPub collection for CnxResources. */
    public static final String COLLECTION_RESOURCE_NAME = "Resource Collection";
    /** Path for Resource AtomPub collection relative to {@link #atomPubRestUrl}. */
    public static final String COLLECTION_RESOURCE_REL_PATH = "/resource";
    /** Path for GET operation relative to {@link #COLLECTION_RESOURCE_REL_PATH}. */
    public static final String COLLECTION_RESOURCE_GET_PATH = "/";
    /** Get URI for AtomPub collection for CNX Resources. */
    public String getCollectionResourcesAbsPath() {
        return atomPubRestUrl + COLLECTION_RESOURCE_REL_PATH;
    }
    
    // Constants for Modules.
    /** Name for AtomPub collection for CnxModules. */
    public static final String COLLECTION_MODULE_NAME = "Resource Collection";

    /** Path for Module AtomPub Collection relative to {@link #atomPubRestUrl}. */
    public static final String COLLECTION_MODULE_REL_PATH = "/module";
    /** Path for GET operation relative to {@link #COLLECTION_MODULE_REL_PATH}. */
    public static final String COLLECTION_MODULE_GET_PATH = "/";
    /** Get URI for AtomPub collection for CNX Modules. */
    public String getCollectionModulesAbsPath() {
        return atomPubRestUrl + COLLECTION_MODULE_REL_PATH;
    }
    
    // Constants for Collections.
    /** Name for AtomPub collection for CnxCollections.. */
    public static final String COLLECTION_CNX_COLLECTION_NAME = "Resource Collection";

    /** Path for CnxCollection AtomPub collection relative to {@link #atomPubRestUrl}. */
    public static final String COLLECTION_CNX_COLLECTION_REL_PATH = "/collection";
    /** Path for GET operation relative to {@link #COLLECTION_CNX_COLLECTION_REL_PATH}. */
    public static final String COLLECTION_CNX_COLLECTION_GET_PATH = "/";
    /** Get URI for AtomPub collection for CNX Collections. */
    public String getCollectionCnxCollectionsAbsPath() {
        return atomPubRestUrl + COLLECTION_CNX_COLLECTION_REL_PATH;
    }
}
