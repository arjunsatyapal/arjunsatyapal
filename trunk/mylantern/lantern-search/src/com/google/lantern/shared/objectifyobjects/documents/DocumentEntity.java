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
package com.google.lantern.shared.objectifyobjects.documents;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

import javax.persistence.Transient;

import com.google.common.base.Strings;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.lantern.shared.enums.select.DocumentType;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.SitesPage;
import javax.persistence.Embedded;
import javax.persistence.Id;

/**
 *
 * @author Arjun Satyapal
 */
public class DocumentEntity implements IsSerializable {
    // TODO(arjuns) : Add publish time.
    @Id Long id;
    DocumentType type;

    @Embedded
    SitesPage sitesPage;
    String creatorEmail;

    @Embedded
    SearchTags searchTags;
    
    @Transient
    boolean dirty;

    // For objectify.
    protected DocumentEntity() {
    }

    public DocumentEntity(String creatorEmail, SitesPage sitesPage, SearchTags searchTags) {
        this.type = DocumentType.GOOGLE_SITES;
        this.sitesPage = checkNotNull(sitesPage);
        
        checkArgument(!Strings.isNullOrEmpty(creatorEmail), "creatorEmail cannot be null.");
        this.creatorEmail = creatorEmail;
        
        this.searchTags = checkNotNull(searchTags);
    }
    
    public Long getDocumentId() {
        return id;
    }

    public DocumentType getType() {
        return type;
    }

    // TODO(arjuns) : This has to be made generic so that different document types can exist.
    public String getContent() {
        return sitesPage.getContent();
    }
    
    public boolean isModified(String newEtag) {
        return sitesPage.isModified(newEtag);
    }

    // TODO(arjuns) : Replace this with generic object.
    public SitesPage getSitesPage() {
        return sitesPage;
    }
    
    public DocumentEntity setSitesPage(SitesPage sitesPage) {
        this.sitesPage = sitesPage;
        return this;
    }
    
    public String getCreatorEmail() {
        return creatorEmail;
    }
    
    public SearchTags getSearchTags() {
        return searchTags;
    }
    
    public void updateSearchTags(SearchTags newSearchTags) {
        if (Objects.equal(this.searchTags, newSearchTags)) {
            return;
        }
        
        this.searchTags = newSearchTags;
        setDirty(true);
    }
    
    // TODO(arjuns) : Move title field to parent.
    public String getTitle() {
        return sitesPage.getTitle();
    }
    
    // TODO(arjuns) : Fix this
    @Deprecated
    public String getWebUrl() {
        throw new RuntimeException("this should not be called.");
//        return sitesPage.getAtomSelfLink();
    }
    
    public boolean isDirty() {
        return dirty && sitesPage.isDirty();
    }
    
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
}
