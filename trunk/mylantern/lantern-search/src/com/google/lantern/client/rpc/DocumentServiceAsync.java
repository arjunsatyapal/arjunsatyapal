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
package com.google.lantern.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.enums.select.DocumentType;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper;

/**
 *
 * @author Arjun Satyapal
 */
public interface DocumentServiceAsync {
    void createDocument(DocumentType documentType, String title, String content,
            SearchTags associatedTags, AsyncCallback<DocumentEntity> callback);

    void searchDocument(SearchCriteria searchCriteria,
            AsyncCallback<DocumentSearchResultWrapper> callback);

    void getDocument(Long documentId, AsyncCallback<DocumentEntity> callback);

    void updateDocument(Long documentId, String content, SearchTags searchTags,
            AsyncCallback<DocumentEntity> callback);
}
