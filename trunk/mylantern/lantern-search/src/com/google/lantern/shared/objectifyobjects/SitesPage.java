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
package com.google.lantern.shared.objectifyobjects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.lantern.shared.utils.misc.PreconditionUtils.checkNonEmptyString;

import javax.persistence.Transient;

import javax.persistence.Id;

/**
 * Storage Object for Google Sites Document.
 * 
 * @author Arjun Satyapal
 */
public class SitesPage implements DocumentObjectInterface {
    @Id
    String sitesId;

    String parentSitesPageId;
    String title;

    String atomSelfLink;

    String content;
    String etag;
    
    @Transient
    boolean dirty;

    // For GWT Serialization.
    protected SitesPage() {
    }

    public SitesPage(String sitesId, String parentSitesPageId, String title, 
            String atomSelfLink, String content, String etag) {
        this.sitesId = checkNonEmptyString(sitesId);
        this.parentSitesPageId = checkNonEmptyString(parentSitesPageId);
        this.title = checkNonEmptyString(title);
        this.atomSelfLink = checkNonEmptyString(atomSelfLink);
        this.content = checkNonEmptyString(content);
        this.etag = checkNotNull(etag);
    }

    public boolean isModified(String newEtag) {
        return !etag.equals(newEtag);
    }

    public String getSitesId() {
        return sitesId;
    }

    public String getParentSitesPageId() {
        return parentSitesPageId;
    }

    public String getTitle() {
        return title;
    }

    public String getAtomSelfLink() {
        return atomSelfLink;
    }

    public String getContent() {
        return content;
    }

    public String getEtag() {
        return etag;
    }

    public void updateFrom(SitesPage updatedSitesPage) {
        this.title = checkNonEmptyString(updatedSitesPage.getTitle());
        this.content = checkNonEmptyString(updatedSitesPage.getContent());
        this.etag = checkNonEmptyString(updatedSitesPage.getEtag());
        setDirty(true);
    }
    
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
    
    public boolean isDirty() {
        return dirty;
    }
}
