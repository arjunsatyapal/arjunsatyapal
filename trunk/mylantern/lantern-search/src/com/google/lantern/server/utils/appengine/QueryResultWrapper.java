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
package com.google.lantern.server.utils.appengine;

import com.google.common.base.Preconditions;

import com.google.appengine.api.datastore.Entity;

import com.google.appengine.api.datastore.QueryResultList;

/**
 *
 * @author Arjun Satyapal
 */
public class QueryResultWrapper {
    String cursor;
    QueryResultList<Entity> results;
    
    public QueryResultWrapper(String cursor, QueryResultList<Entity> results) {
        this.cursor = cursor;
        this.results = Preconditions.checkNotNull(results);
    }

    public boolean hasCursor() {
        return cursor != null;
    }
    
    public String getCursor() {
        return cursor;
    }

    public QueryResultList<Entity> getResults() {
        return results;
    }
}
