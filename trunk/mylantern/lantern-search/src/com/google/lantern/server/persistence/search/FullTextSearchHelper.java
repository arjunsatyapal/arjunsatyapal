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

import static com.google.lantern.server.persistence.search.FullTextSearchIndexHelper.getIndex;

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.SearchRequest;
import com.google.appengine.api.search.SearchResponse;
import com.google.lantern.server.persistence.utils.QueryUtils;
import com.google.lantern.server.utils.appengine.AppEngineUtils;
import com.google.lantern.shared.dto.SearchCriteria;
import com.google.lantern.shared.enums.select.HelperEnum;
import com.google.lantern.shared.objectifyobjects.SearchTags;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * @author Arjun Satyapal
 */
public class FullTextSearchHelper {
    private static final Logger logger = Logger.getLogger(FullTextSearchHelper.class.getName());
    
    public static final String HTML_FIELD_NAME = "htmlContent";
    public static final String AUTHOR_FIELD_NAME = "author";
    public static final String PUBLISHED_DATE_FIELD_NAME = "published";
    public static final String FILTER_DOMAIN = "domain";
    public static final String FILTER_US_STATES = "us_states";
    public static final String FILTER_GRADES = "grades";

    public static Document toAppengineSearchDoc(DocumentEntity entity)
            throws UserNotLoggedInException {
        Document.Builder builder = Document.newBuilder();

        builder.setId(entity.getDocumentId().toString());

        builder.addField(Field.newBuilder()
                .setName(HTML_FIELD_NAME)
                .setHTML(entity.getContent()));

        builder.addField(Field.newBuilder()
                .setName(AUTHOR_FIELD_NAME)
                .setText(AppEngineUtils.getUser().getEmail()));

        // TODO(arjuns) : Get this value from document entity.
        builder.addField(
                Field.newBuilder()
                        .setName(PUBLISHED_DATE_FIELD_NAME)
                        .setDate(Field.date(new Date())));

        // Now storing additional search tags provided by Author.

        SearchTags searchTags = entity.getSearchTags();

        if (searchTags.hasDomains()) {
            builder.addField(Field.newBuilder().setName(FILTER_DOMAIN)
                    .setText(getSearchTagsForIndexing(searchTags.getDomains())));
        }

        if (searchTags.hasUsStates()) {
            builder.addField(Field.newBuilder().setName(FILTER_US_STATES)
                    .setText(getSearchTagsForIndexing(searchTags.getUsStates())));
        }

        if (searchTags.hasGrades()) {
            builder.addField(Field.newBuilder().setName(FILTER_GRADES)
                    .setText(getSearchTagsForIndexing(searchTags.getGrades())));
        }

        Document response = builder.build();
        return response;
    }

    // TODO(arjuns) : Full text search api for Appengine some how is not able to look for
    // underscores. So have to do this hack.
    private static <T extends Enum<T>, E extends HelperEnum<T>> String getSearchTagsForIndexing(
            List<E> list) {
        StringBuilder strBuilder = new StringBuilder();
        boolean isFirst = true;
        for (E currValue : list) {
            if (!isFirst) {
                strBuilder.append(", ");
            }

            String publicName = currValue.getPublicName();
            strBuilder.append("\"" + publicName + "\"");
            isFirst = false;
        }

        return strBuilder.toString();
    }

    @Deprecated
    public static Document toAppEngineSearchDoc(Long documentId, String html)
            throws UserNotLoggedInException {
        // TODO(arjuns) : Add title.
        Document.Builder builder = Document.newBuilder();

        builder.setId(documentId.toString());

        builder.addField(Field.newBuilder()
                .setName(HTML_FIELD_NAME)
                .setHTML(html));

        builder.addField(Field.newBuilder()
                .setName(AUTHOR_FIELD_NAME)
                .setText(AppEngineUtils.getUser().getEmail()));
        builder.addField(Field.newBuilder()
                .setName(PUBLISHED_DATE_FIELD_NAME)
                .setDate(Field.date(new Date())));
        return builder.build();
    }

    public static FullTextSearchResultWrapper searchByCriteria(SearchCriteria searchCriteria) {
        SearchRequest.Builder searchReqBuilder = SearchRequest.newBuilder()
                .setLimit(QueryUtils.PAGE_SIZE);

        // TODO(arjuns) : Add support for cursor.
        // if (!Strings.isNullOrEmpty(cursor)) {
        // searchReqBuilder.setCursor(cursor);
        // } else {

        String query = queryBuilderFromSearchCriteria(searchCriteria);
        logger.info("Query = " + query);

        // Preconditions.checkArgument(Strings.isNullOrEmpty(cursor));
        
        searchReqBuilder.setQuery(query.trim());
        // }

        SearchRequest searchRequest = searchReqBuilder.build();
        SearchResponse searchResponse = FullTextSearchIndexHelper.getIndex().search(searchRequest);

        return new FullTextSearchResultWrapper(searchResponse.getCursor(),
                searchResponse.getResults());
    }

    /**
     * @param searchCriteria
     * @return
     */
    static String queryBuilderFromSearchCriteria(SearchCriteria searchCriteria) {
        StringBuilder queryBuilder = new StringBuilder();

        if (searchCriteria.hasSearchString()) {
            queryBuilder.append(searchCriteria.getSearchString());
        }

        if (searchCriteria.hasSearchString() && searchCriteria.hasSearchTags()) {
            queryBuilder.append(" AND ");
        }

        if (searchCriteria.hasSearchTags()) {
            SearchTags searchTags = searchCriteria.getSearchTags();

            boolean isFilterAdded = false;
            if (searchTags.hasDomains()) {
                applyFilter(queryBuilder, FILTER_DOMAIN, searchTags.getDomains());
                isFilterAdded = true;
            }

            if (searchTags.hasUsStates()) {
                if (isFilterAdded) {
                    queryBuilder.append(" AND ");
                    isFilterAdded = false;
                }

                applyFilter(queryBuilder, FILTER_US_STATES, searchTags.getUsStates());
                isFilterAdded = true;
            }

            if (searchTags.hasGrades()) {
                if (isFilterAdded) {
                    queryBuilder.append(" AND ");
                    isFilterAdded = false;
                }

                applyFilter(queryBuilder, FILTER_GRADES, searchTags.getGrades());
            }
        }

        if (searchCriteria.hasSearchString() && searchCriteria.hasSearchTags()) {
            queryBuilder.append("");
        }
        return queryBuilder.toString();
    }

    /**
     * TODO(arjuns) : Due to the hack done for indexing, we have to follow same thing while
     * retrieving the document.
     */
    private static <T extends Enum<T>, E extends HelperEnum<T>> void applyFilter(
            StringBuilder queryBuilder,
            String filterName, List<E> values) {
        boolean isFirst = true;
        for (E currValue : values) {
            if (!isFirst) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append(filterName).append(":\"")
                    .append(currValue.getPublicName()).append("\"");
            isFirst = false;
        }
    }
    
    public static void deleteDocument(Long documentId) {
        getIndex().deleteDocument(Long.toString(documentId));
    }
    
    public static void deleteDocuments(Iterable<String> documentIds) {
        getIndex().deleteDocuments(documentIds);
    }

    // /**
    // * @param queryBuilder
    // * @param searchTags
    // */
    // private static <E extends Enum<E>> void addFilters(StringBuilder queryBuilder,
    // String filterName, List<E> values) {
    // boolean isFirst = true;
    // // queryBuilder.append("\"");
    // for (E currValue : values) {
    // if (!isFirst) {
    // queryBuilder.append(" AND ");
    // }
    // queryBuilder.append(filterName).append(":\"").append(currValue).append("\"");
    // isFirst = false;
    // }
    // // queryBuilder.append("\"");
    // }

    // public static List<Document> findAll(SearchParams params) {
    // SearchRequest.Builder builder = SearchRequest.newBuilder()
    // .setQuery(params.query)
    // .setLimit(params.pageSize);
    // // .setCursorType(SearchRequest.CursorType.SINGLE);
    //
    // if (params.getCursor() != null && !params.getCursor().isEmpty()) {
    // builder.setCursor(params.getCursor());
    // }
    //
    // SearchResponse response = SearchIndexHelper.getIndex().search(builder.build());
    //
    // List<Document> matched = new ArrayList<Document>();
    // for (SearchResult result : response) {
    // matched.add(result.getDocument());
    // }
    // params.setCursor(response.getCursor());
    // return matched;
    // }

    // public static class SearchParams {
    // public final String query;
    // public final int pageSize;
    // private String cursor;
    //
    // public SearchParams(String query, int pageSize) {
    // this.query = query;
    // this.pageSize = pageSize;
    // }
    //
    // public void setCursor(String cursor) {
    // this.cursor = cursor;
    // }
    //
    // public String getCursor() {
    // return cursor;
    // }
    // }
}
