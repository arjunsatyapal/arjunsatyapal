/*
 * Copyright (C) Google Inc.
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
package com.google.lantern.server.persistence.entities;

import static com.google.lantern.shared.utils.misc.PreconditionUtils.checkNonEmptyString;

import java.io.Serializable;
import javax.persistence.Id;

/**
 * 
 * @author Arjun Satyapal
 */
@SuppressWarnings("serial")
public class ParentSitesPageEntity implements Serializable {

    // Id returned by Google sites when Parent Page is creted.
    @Id
    String id;

    // Title for parent Page.
    String title;

    // Domain (e.g. google.com).
    String domain;

    // Name of site.
    String sitesName;

    // Self link returned by Sites as part of the atom entry.
    String atomSelfLink;
    
    // For Objectify.
    @SuppressWarnings("unused")
    private ParentSitesPageEntity() {
    }

    public ParentSitesPageEntity(String id, String title, String domain,
            String sitesName, String atomSelfLink) {
        this.id = checkNonEmptyString(id);
        this.title = checkNonEmptyString(title);
        this.domain = checkNonEmptyString(domain);
        this.sitesName = checkNonEmptyString(sitesName);
        this.atomSelfLink = checkNonEmptyString(atomSelfLink);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDomain() {
        return domain;
    }

    public String getSitesName() {
        return sitesName;
    }
    
    public String getAtomSelfLink() {
        return atomSelfLink;
    }
}
