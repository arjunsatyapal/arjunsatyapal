/*
 * Copyright 2011 Google Inc.
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
package com.arjunsatyapal.rome.utils;

import com.arjunsatyapal.rome.enums.CnxAtomPubConstants;
import com.sun.syndication.feed.atom.Category;
import com.sun.syndication.propono.atom.common.Categories;
import com.sun.syndication.propono.atom.common.Collection;

import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Arjun Satyapal
 */
public class AtomCollectionUtils {
    // Utility class.
    private AtomCollectionUtils() {
    }

    public static Collection getCnxResourceCollection() {
        Collection collection = new Collection(
                CnxAtomPubConstants.COLLECTION_RESOURCE_NAME, MediaType.TEXT_PLAIN,
                CnxAtomPubConstants.COLLECTION_RESOURCE_ABS_PATH);

        Category resourceCategory = AtomCategoryUtils
                .getCnxResourceCategoryEle();
        Categories categories = new Categories();
        categories.addCategory(resourceCategory);

        collection.addCategories(categories);

        return collection;
    }
}