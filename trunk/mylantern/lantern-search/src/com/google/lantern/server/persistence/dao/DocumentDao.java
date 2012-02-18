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
package com.google.lantern.server.persistence.dao;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.SearchResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.lantern.server.persistence.search.FullTextSearchHelper;
import com.google.lantern.server.persistence.search.FullTextSearchIndexHelper;
import com.google.lantern.server.persistence.search.FullTextSearchResultWrapper;
import com.google.lantern.server.persistence.utils.ObjectifyUtils;
import com.google.lantern.server.persistence.utils.QueryUtils;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.exceptions.checked.client.document.DocumentNotFoundException;
import com.google.lantern.shared.exceptions.checked.common.GwtServerSideException;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.SitesPage;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper;
import com.google.lantern.shared.objectifyobjects.documents.DocumentSearchResultWrapper.CursorType;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Arjun Satyapal
 */
public class DocumentDao extends DAOBase {
    static {
        ObjectifyService.register(SearchTags.class);
        ObjectifyService.register(SitesPage.class);
        ObjectifyService.register(DocumentEntity.class);
    }

    public static Long createDocument(final DocumentEntity newDocumentEntity)
            throws UserNotLoggedInException {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        ofy.put(newDocumentEntity);

        updateSearchIndex(newDocumentEntity);
        return checkNotNull(newDocumentEntity.getDocumentId());
    }

    public static void updateDocumentEntity(DocumentEntity docEntity)
            throws UserNotLoggedInException {
        checkNotNull(docEntity);
        checkNotNull(docEntity.getDocumentId());

        Objectify ofy = ObjectifyUtils.getObjectifyService();
        ofy.put(docEntity);

        updateSearchIndex(docEntity);
    }

    public static DocumentEntity getDocumentById(Long id) throws DocumentNotFoundException {
        Objectify ofy = ObjectifyUtils.getObjectifyService();

        Key<DocumentEntity> key = new Key<DocumentEntity>(DocumentEntity.class, id);
        DocumentEntity entity = null;

        try {
            entity = ofy.get(key);
        } catch (NotFoundException e) {
            throw new DocumentNotFoundException("Document with Id[" + id
                    + "], does not exist in datastore.");
        }

        return entity;
    }

    public static void deleteDocument(Long documentId) {
        AppEngineUtils.validateAdminAccessOnly();

        // First delete from search Index.
        FullTextSearchHelper.deleteDocument(documentId);

        Objectify ofy = ObjectifyUtils.getObjectifyService();
        ofy.delete(new Key<DocumentEntity>(DocumentEntity.class, documentId));
    }

    public static DocumentSearchResultWrapper searchDocument(SearchCriteria searchCriteria)
            throws GwtServerSideException {
        if (searchCriteria.hasSearchString()) {
            return searchByText(searchCriteria);
        }

        return searchByTags(searchCriteria.getSearchTags());
    }

    private static DocumentSearchResultWrapper searchByText(SearchCriteria searchCriteria) {
        FullTextSearchResultWrapper resultWrapper =
                FullTextSearchHelper.searchByCriteria(searchCriteria);

        List<Key<DocumentEntity>> listOfDocEntityKeys = Lists.newArrayList();
        for (SearchResult result : resultWrapper.getSearchResults()) {
            listOfDocEntityKeys.add(getDatastoreKeyFromSearchDocument(result.getDocument()));
        }

        return new DocumentSearchResultWrapper(resultWrapper.getCursor(), CursorType.FTS,
                getListOfDocuments(listOfDocEntityKeys));
    }

    private static DocumentSearchResultWrapper searchByTags(SearchTags searchTags) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();

        Query<DocumentEntity> query =
                ofy.query(DocumentEntity.class);

        if (searchTags.hasDomains()) {
            query.filter("searchTags.domains in ", searchTags.getDomains());
        }

        if (searchTags.hasUsStates()) {
            query.filter("searchTags.usStates in ", searchTags.getUsStates());
        }

        if (searchTags.hasGrades()) {
            query.filter("searchTags.grades in ", searchTags.getGrades());
        }

        // TODO(arjuns) : Add cursor support later.
        List<DocumentEntity> results = query.list();

        return new DocumentSearchResultWrapper(null, CursorType.DataStore, results);
    }

    protected static List<DocumentEntity> getListOfDocuments(List<Key<DocumentEntity>> listOfKeys) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();

        Map<Key<DocumentEntity>, DocumentEntity> map = ofy.get(listOfKeys);

        return Lists.newArrayList(map.values());
    }

    // TODO(arjuns) : Add support for cursor.
    public static Map<String, Key<DocumentEntity>> getIdsForDocumentsReferringSites() {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        QueryResultIterable<Key<DocumentEntity>> result =
                ofy.query(DocumentEntity.class).fetchKeys();

        Map<String, Key<DocumentEntity>> map = Maps.newIdentityHashMap();
        for (Key<DocumentEntity> curr : result) {
            map.put(Long.toString(curr.getId()), curr);
        }

        return map;
    }

    public static void deleteDocuments(Iterable<Key<DocumentEntity>> iterable) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();

        ofy.delete(iterable);
    }

    protected static void updateSearchIndex(final DocumentEntity docEntity)
            throws UserNotLoggedInException {
        // Now making it available for search.
        Document aeSearchDoc = FullTextSearchHelper.toAppengineSearchDoc(docEntity);
        FullTextSearchIndexHelper.updateIndex(aeSearchDoc);
    }

    @Deprecated
    public static List<DocumentEntity> getDocumentsByEntityKeys(
            List<Key<DocumentEntity>> listOfDocumentKeys) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        Map<Key<DocumentEntity>, DocumentEntity> documents = ofy.get(listOfDocumentKeys);
        return Lists.newArrayList(documents.values());
    }

    @Deprecated
    public static DocumentSearchResultWrapper getByCreatorEmail(final String cretaorEmail,
            final String cursorStr) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        Query<DocumentEntity> query =
                ofy.query(DocumentEntity.class).filter("creatorEmail", cretaorEmail);

        if (cursorStr != null) {
            query.startCursor(Cursor.fromWebSafeString(cursorStr));
        }

        List<DocumentEntity> listOfDocuments = Lists.newArrayList();
        String newCursorStr = null;
        QueryResultIterator<DocumentEntity> iterator = query.iterator();
        while (iterator.hasNext()) {
            DocumentEntity course = iterator.next();
            listOfDocuments.add(course);

            if (listOfDocuments.size() >= QueryUtils.PAGE_SIZE) {
                newCursorStr = iterator.getCursor().toWebSafeString();
                break;
            }
        }

        return new DocumentSearchResultWrapper(newCursorStr, CursorType.DataStore, listOfDocuments);
    }

    @Deprecated
    protected static Key<DocumentEntity> getDatastoreKeyFromSearchDocument(Document doc) {
        Long datastoreId = Long.parseLong(doc.getId());
        return new Key<DocumentEntity>(DocumentEntity.class, datastoreId);
    }

}
