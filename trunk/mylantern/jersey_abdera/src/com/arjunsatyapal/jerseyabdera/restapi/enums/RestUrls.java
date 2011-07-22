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
package com.arjunsatyapal.jerseyabdera.restapi.enums;

/**
 * Enums to encapsulate REST Urls.
 * 
 * @author Arjun Satyapal
 */
public class RestUrls {
    public static class CnxResources {
        /** BaseURL for REST API for CNX Resources. */
        public final static String BASE_URL = "/resource";

        /** Annotation for resourceId. */
        public final static String RESOURCE_ID = "resourceId";
        
        /** URL CNX Resources relative to {@link #BASE_URL}. */
        public final static String RESOURCE_URL = "/{" + RESOURCE_ID + "}";
    }

    public static class CnxModules {
        /** BaseURL for REST API for CNX Modules. */
        public final static String BASE_URL = "/module";
        
        /** Annotation for moduleId. */
        public final static String MODULE_ID = "moduleId";
        
        /** URL CNX Resources relative to {@link #BASE_URL}. */
        public final static String MODULE_URL = "/{" + MODULE_ID + "}";
    }

    public static class CnxCollections {
        /** BaseURL for REST API for CNX Collections. */
        public final static String BASE_URL = "/collection";
        
        /** Annotation for collectionId. */
        public final static String COLLECTION_ID = "collectionId";
        
        /** URL CNX Resources relative to {@link #BASE_URL}. */
        public final static String COLLECTION_URL = "/{" + COLLECTION_ID + "}";
    }
}
