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

/**
 * 
 * @author Arjun Satyapal
 */
public class ServletPaths {
    // TODO(arjuns) : Make figuring out parent url dynamic.
    /** Path for REST URL for ATOMPUB API. */
    public static final String ATOMPUB_URL = "http://cnx-repo.appspot.com/atompub";

    /** Path for Service Document relative to {@link #ATOMPUB_URL}. */
    public static final String AP_SERVICE_DOCUMENT_PATH = "/servicedocument";
    public static final String AP_SERVICE_DOCUMENT_GET_PATH = "/";

    /** Path for Categories Docuemnt relative to {@link #ATOMPUB_URL}. */
    public static final String AP_CATEGORIES_DOCUMENT_PATH = "/categorydocument";
    public static final String AP_CATEGORIES_DOCUMENT_GET_PATH = "/";

    /** Path for Resource AtomPub collection relative to {@link #ATOMPUB_URL}. */
    public static final String AP_COLLECTION_RESOURCE_REL_PATH = "/resources";
    /** Absolute path for Resource AtomPub collection." */
    public static final String AP_COLLECTINO_RESOURCE_ABS_PATH = ATOMPUB_URL
            + AP_COLLECTION_RESOURCE_REL_PATH;

    public static final String AP_COLLECTION_RESOURCE_GET_PATH = "/";
}
