/*
 * Copyright (c) 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.lantern.server.utils.appengine;

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;

import com.google.lantern.shared.exceptions.unchecked.AdminOnlyAccessException;


import java.util.Properties;

import com.google.api.client.extensions.appengine.http.urlfetch.UrlFetchTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.lantern.server.persistence.utils.QueryUtils;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Utilities for Google App Engine applications.
 * 
 * @author Yaniv Inbar
 */
public class AppEngineUtils {
    private final static Logger logger = Logger.getLogger(AppEngineUtils.class.getName());

    private static final DatastoreService datastoreService =
            DatastoreServiceFactory.getDatastoreService();

    /** Persistence manager factory instance. */
    private static final PersistenceManagerFactory PM_FACTORY = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");

    /** Returns the global instance of the HTTP transport. */
    public static HttpTransport getHttpTransport() {
        return new UrlFetchTransport();
    }

    /** Returns the global instance of the JSON factory. */
    public static JsonFactory getJsonFactory() {
        return new JacksonFactory();
    }

    /** Returns the Persistence Manager factory. */
    public static PersistenceManagerFactory getPersistenceManagerFactory() {
        return PM_FACTORY;
    }

    public static boolean isDevServer() {
        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Development) {
            return true;
        }

        return false;
    }

    /**
     * Returns User object for the Logged-in user.
     * 
     * @throws UserNotLoggedInException if user is not Logged-in.
     */
    public static final User getUser() throws UserNotLoggedInException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user == null) {
            throw new UserNotLoggedInException("User is not logged in.");
        }

        return user;
    }

    public static final String getUserId() throws UserNotLoggedInException {
        return getUser().getUserId();
    }
    
    public static final String getUserEmail() throws UserNotLoggedInException {
        return getUser().getEmail();
    }

    public static long getCurrenTimeInMillis() {
        return System.currentTimeMillis();
    }

    public static void printAllMetadata() {
        StringBuilder builder = new StringBuilder();

        List<String> listOfName = gatherMetadata(Query.KIND_METADATA_KIND);
        builder.append(Query.KIND_METADATA_KIND).append(" : ")
                .append(Iterables.toString(listOfName)).append("\n");

        listOfName = gatherMetadata(Query.NAMESPACE_METADATA_KIND);
        builder.append(Query.NAMESPACE_METADATA_KIND).append(" : ")
                .append(Iterables.toString(listOfName)).append("\n");

        listOfName = gatherMetadata(Query.PROPERTY_METADATA_KIND);
        builder.append(Query.PROPERTY_METADATA_KIND).append(" : ")
                .append(Iterables.toString(listOfName)).append("\n");

        System.out.println(builder.toString());
    }

    private static List<String> gatherMetadata(String kind) {
        List<String> listOfNames = Lists.newArrayList();

        Query query = new Query(kind).setKeysOnly();

        for (Entity e : datastoreService.prepare(query).asIterable()) {
            StringBuilder nameBuilder = new StringBuilder();
            if (e.getKey().getId() != 0) {
                continue;
            } else {
                if (e.getKey().getParent() != null) {
                    nameBuilder.append(e.getParent().getName()).append(".");
                }

                nameBuilder.append(e.getKey().getName());
            }

            listOfNames.add(nameBuilder.toString());
        }

        return listOfNames;
    }

    public static void deleteAllKinds() {
        List<String> listOfKinds = gatherMetadata(Query.KIND_METADATA_KIND);
        for(String currKind : listOfKinds) {
            if (currKind.startsWith("__")) {
                logger.info("Ignoring kind[" + currKind + "], assuming it to be a system defined.");
                continue;
            }
            deleteKind(currKind);
        }
    }

    private static void deleteKind(String currKind) {
        Query query = new Query(currKind).setKeysOnly();
        
        QueryResultWrapper resultWrapper = null;
        String cursor = null;
        
        do {
            if (resultWrapper != null) {
                cursor = resultWrapper.getCursor();
            }

            resultWrapper = searchEntities(cursor, query);
            
            logger.info("For Kind[" + currKind + "], found ["
                    + resultWrapper.getResults().size() + "] entries.");
            
            deleteEntities(resultWrapper.getResults());

            if (resultWrapper.hasCursor()) {
                cursor = resultWrapper.getCursor();
            } else {
                resultWrapper = null;
            }
        } while (resultWrapper == null);
    }
    
    
    protected static void deleteEntities(QueryResultList<Entity> queryResults) {
        if (queryResults.size() <= 0) {
            return;
        }

        List<Key> keys = Lists.newArrayListWithCapacity(queryResults.size());
        for (Entity currEntity : queryResults) {
            keys.add(currEntity.getKey());
        }
        
        datastoreService.delete(keys);
        logger.info("Successfully deleted following docIds from Search Index : "
                + Iterables.toString(keys));
    }

    
    public static QueryResultWrapper searchEntities(String cursor, Query query) {
        Preconditions.checkNotNull(query);

        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(QueryUtils.PAGE_SIZE);

        if (!Strings.isNullOrEmpty(cursor)) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(cursor));
        }

        PreparedQuery pq = datastoreService.prepare(query);
        QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);

        return new QueryResultWrapper(results.getCursor().toWebSafeString(), results);
    }
    
    public static boolean isJunitTesting() {
        Properties systemProperties = System.getProperties();
        
        String value = (String) systemProperties.get("junit-testing");
        
        if (value != null && value.equals("false")) {
            return false;
        }
        
        return true;
    }
    
    // TODO(arjuns) : Addtests.
    public static boolean isUserAdmin() {
        return UserServiceFactory.getUserService().isUserAdmin();
    }
    
    public static void validateAdminAccessOnly() {
        if (!isUserAdmin()) {
            throw new AdminOnlyAccessException();
        }
    }
}
