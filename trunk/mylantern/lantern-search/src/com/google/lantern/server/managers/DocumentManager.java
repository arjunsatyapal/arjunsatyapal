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
package com.google.lantern.server.managers;

import com.google.common.collect.Iterables;
import com.google.lantern.server.persistence.dao.DocumentDao;
import com.google.lantern.server.persistence.search.FullTextSearchHelper;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.enums.select.DocumentType;
import com.google.lantern.shared.exceptions.checked.common.GwtServerSideException;
import com.google.lantern.shared.exceptions.checked.common.GwtServiceException;
import com.google.lantern.shared.exceptions.unchecked.AdminOnlyAccessException;
import com.google.lantern.shared.objectifyobjects.DocumentObjectInterface;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper;
import com.googlecode.objectify.Key;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 
 * @author Arjun Satyapal
 */
public class DocumentManager {
    private static final Logger logger = Logger.getLogger(DocumentManager.class.getName());

    public static DocumentEntity createDocument(DocumentType type, String title, String content,
            SearchTags searchTags) throws GwtServiceException {
        try {
            ManagerInterface<? extends DocumentObjectInterface> manager =
                    ManagerProvider.getManager(type);

            DocumentEntity documentEntity = manager.createDocument(title, content, searchTags);

            DocumentDao.createDocument(documentEntity);

            logger.info("Successfully saved Type[" + type + "], Title[" + title
                    + "], with DcoumentId["
                    + documentEntity.getDocumentId() + "].");

            return documentEntity;
        } catch (GwtServiceException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDocument(Long documentId) throws GwtServiceException,
            AdminOnlyAccessException {
        try {
            AppEngineUtils.validateAdminAccessOnly();

            // First delete from Sites, and then do the local cleanup. Thsi is because we will be
            // listening to Sites feed. So even if page is deleted later, we can clean up the
            // datastore
            // later.
            DocumentEntity documentEntity = DocumentDao.getDocumentById(documentId);
            ManagerInterface<DocumentObjectInterface> manager =
                    ManagerProvider.getManager(documentEntity.getType());
            manager.deleteDocument(documentEntity);

            // Now deleting from Datastore.
            DocumentDao.deleteDocument(documentId);
        } catch (GwtServiceException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static DocumentEntity getDocument(Long documentId) throws GwtServiceException {
        DocumentEntity documentEntity = DocumentDao.getDocumentById(documentId);
        try {
            ManagerInterface<DocumentObjectInterface> manager =
                    ManagerProvider.getManager(documentEntity.getType());
            return manager.getDocument(documentEntity);
        } catch (GwtServiceException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteAll() throws GwtServiceException {
        try {
            for (DocumentType currType : DocumentType.values()) {
                ManagerInterface<DocumentObjectInterface> manager =
                        ManagerProvider.getManager(currType);

                manager.deleteAll();

                // TODO(arjuns) : Fix this to get other types.
                // First delete documents from Search Index.
                Map<String, Key<DocumentEntity>> map =
                        DocumentDao.getIdsForDocumentsReferringSites();
                FullTextSearchHelper.deleteDocuments(map.keySet());
                String listOfDocuemntIds = Iterables.toString(map.keySet());
                logger.info("Following documentIds[having sites] were successfully deleted from " +
                        "searchIndex : " + listOfDocuemntIds);

                // Now delete all the Documents which refer to sites.
                DocumentDao.deleteDocuments(map.values());
                logger.info("Following documentIds[having sites] were successfully deleted from " +
                        "dataStore : " + listOfDocuemntIds);

            }
            return true;
        } catch (GwtServiceException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static DocumentEntity updateDocument(Long documentId, String content,
            SearchTags searchTags) throws GwtServiceException {
        try {
            DocumentEntity documentEntity = DocumentDao.getDocumentById(documentId);

            ManagerInterface<DocumentObjectInterface> manager =
                    ManagerProvider.getManager(documentEntity.getType());

            // TODO(arjuns) : Fix this with generic object.
            manager.updateDocument(documentEntity, content);

            documentEntity.updateSearchTags(searchTags);
            if (documentEntity.isDirty()) {
                DocumentDao.updateDocumentEntity(documentEntity);
            }

            return documentEntity;
        } catch (GwtServiceException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static DocumentSearchResultWrapper searchDocumentByTags(SearchCriteria searchCriteria)
            throws GwtServerSideException {
        return DocumentDao.searchDocument(searchCriteria);
    }
}
