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
package com.google.lantern.server.gwtservices;

import com.google.lantern.client.rpc.DocumentService;
import com.google.lantern.server.managers.DocumentManager;
import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.enums.select.DocumentType;
import com.google.lantern.shared.exceptions.checked.common.GwtServiceException;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper;

/**
 * 
 * @author Arjun Satyapal
 */
@SuppressWarnings("serial")
public class DocumentServiceImpl extends AbstractBaseGwtService implements DocumentService {
    public DocumentEntity createDocument(DocumentType documentType, String title,
            String content, SearchTags searchTags) throws GwtServiceException {
        return DocumentManager.createDocument(documentType, title, content,
                searchTags);
    }

    public DocumentEntity getDocument(Long documentId) throws GwtServiceException {
        return DocumentManager.getDocument(documentId);
    }

    public DocumentEntity updateDocument(Long documentId, String content, SearchTags searchTags)
            throws GwtServiceException {
        return DocumentManager.updateDocument(documentId, content, searchTags);
    }

    public DocumentSearchResultWrapper searchDocument(SearchCriteria searchCriteria)
            throws GwtServiceException {
        return DocumentManager.searchDocumentByTags(searchCriteria);
    }

}
