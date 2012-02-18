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

import com.google.lantern.server.persistence.entities.ParentSitesPageEntity;
import com.google.lantern.server.persistence.utils.ObjectifyUtils;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

/**
 * 
 * @author Arjun Satyapal
 */
public class SitesDao {
    static {
        ObjectifyService.register(ParentSitesPageEntity.class);
    }

    public static ParentSitesPageEntity updateParentPage(ParentSitesPageEntity entity) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        ofy.put(entity);

        checkNotNull(entity.getId());

        return entity;
    }

    public static ParentSitesPageEntity getParentPageEntityByTitle(String title) {
        
        // TODO(arjuns) : Add layer of cache.
        Objectify ofy = ObjectifyUtils.getObjectifyService();

        // TODO(arjuns) : Add check for having more then one entry for the parent. At present
        // returning the first value that was fetched from datastore.
        ParentSitesPageEntity result = 
                ofy.query(ParentSitesPageEntity.class).filter("title", title).get();

        return result;
    }
}
