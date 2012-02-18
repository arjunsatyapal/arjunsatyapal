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
package com.google.lantern.server.persistence.search;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexManager;
import com.google.appengine.api.search.IndexManagerFactory;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.SearchResult;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * @author Arjun Satyapal
 */
public class FullTextSearchIndexHelper {
    private static final Logger logger = Logger.getLogger(FullTextSearchIndexHelper.class.getName());

    private static final String INDEX_NAME = "content-index";

    public static Index getIndex() {
        IndexManager indexManager = IndexManagerFactory.newIndexManager();
        Index index = indexManager.getIndex(IndexSpec.newBuilder().setName(INDEX_NAME));
        return index;
    }

    public static void updateIndex(Document doc) {
        try {
            Index index = FullTextSearchIndexHelper.getIndex();
            index.deleteDocument(doc.getId());
            index.add(doc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static void deleteFromSearchIndex(Collection<SearchResult> searchResults) {
        if (searchResults.size() <= 0) {
            return;
        }

        List<String> docIds = Lists.newArrayListWithCapacity(searchResults.size());
        for (SearchResult currResult : searchResults) {
            docIds.add(currResult.getDocument().getId());
        }

        FullTextSearchIndexHelper.getIndex().deleteDocuments(docIds);
        logger.info("Successfully deleted following docIds from Search Index : "
                + Iterables.toString(docIds));
    }
}
