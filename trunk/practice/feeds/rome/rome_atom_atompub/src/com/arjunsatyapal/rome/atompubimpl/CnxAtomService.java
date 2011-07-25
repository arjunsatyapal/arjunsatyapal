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
package com.arjunsatyapal.rome.atompubimpl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import com.google.common.base.Strings;

import com.arjunsatyapal.rome.enums.ServletPaths;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.common.Collection;
import com.sun.syndication.propono.atom.common.Workspace;

/**
 * 
 * @author Arjun Satyapal
 */
public class CnxAtomService extends AtomService {
    // e.g. http://cnx-repo.appspot.com/atompub
    private String baseUri;

    public CnxAtomService(String baseUri) {
        checkArgument(!isNullOrEmpty(baseUri), "baseUri cannot be empty/null.");
        this.baseUri = baseUri;

        /*
         * For Connexions repository, there is only one workspace. Each
         * workspace will have three AtomPubcollections : 1. Resources 2.
         * Modules 3. Collections.
         */
        Workspace workSpace = new Workspace("Connexions Workspace", "text");
        getWorkspaces().add(workSpace);

        workSpace.addCollection(getCollectionForResources());
    }

    private Collection getCollectionForResources() {
        Collection collection = new Collection("Resource Collection", "text",
                ServletPaths.AP_COLLECTION_RESOURCE_ABS_PATH);
        // TODO(arjuns) : ADd categories.
//        collection.addCategories(new Categories())
        
        return collection;
    }
}
