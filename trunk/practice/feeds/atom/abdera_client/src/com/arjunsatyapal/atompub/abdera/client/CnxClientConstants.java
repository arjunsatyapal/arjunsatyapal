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
package com.arjunsatyapal.atompub.abdera.client;

/**
 * 
 * @author Arjun Satyapal
 */
public class CnxClientConstants {
    private String host;
    private String serviceDocumentUrl;

    // TODO(arjuns) : Share constants with server.
    public static final String CNX_WORKSPACE = "Connexions Workspace";
    public static final String COLLECTION_CNX_RESOURCE = "AtomPub Collection for CNX Resources.";
    public static final String COLLECTION_CNX_MODULE = "AtomPub Collection for CNX Modules.";
    public static final String COLLECTION_CNX_COLLECTION = "AtomPub Collection for Cnx Collections.";
    
    public CnxClientConstants(boolean isLocal) {
        if (isLocal) {
            this.host = "http://localhost:8888";
        } else {
            this.host = "http://100.cnx-repo.appspot.com";
        }
        
        this.serviceDocumentUrl = this.host + "/atompub/service_document";
    }

    public String getServiceDocumentUrl() {
        return serviceDocumentUrl;
    }
}
