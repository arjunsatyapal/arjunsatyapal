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

import static com.arjunsatyapal.rome.utils.CnxAtomCategoryUtils.getCnxCollectionCategoryEle;
import static com.arjunsatyapal.rome.utils.CnxAtomCategoryUtils.getCnxModuleCategoryEle;
import static com.arjunsatyapal.rome.utils.CnxAtomCategoryUtils.getCnxResourceCategoryEle;

import com.arjunsatyapal.rome.atompubimpl.CnxAtomService;
import com.arjunsatyapal.rome.enums.CnxAtomPubConstants;
import com.sun.syndication.feed.atom.Category;
import com.sun.syndication.propono.atom.common.Categories;
import com.sun.syndication.propono.atom.common.Collection;

import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Arjun Satyapal
 */
public class CnxAtomCollectionUtils {
    // Utility class.
    private CnxAtomCollectionUtils() {
    }

    public static Collection getCollectionForCnxResource(CnxAtomService service) {
        return getCnxCollection(CnxAtomPubConstants.COLLECTION_RESOURCE_NAME,
                service.getConstants().getCollectionResourcesAbsPath(),
                getCnxResourceCategoryEle(service));
    }
    
    public static Collection getCollectionForCnxModule(CnxAtomService service) {
        return getCnxCollection(CnxAtomPubConstants.COLLECTION_MODULE_NAME,
                service.getConstants().getCollectionModulesAbsPath(),
                getCnxModuleCategoryEle(service));
    }

    public static Collection getCollectionForCnxCollection(CnxAtomService service) {
        return getCnxCollection(CnxAtomPubConstants.COLLECTION_CNX_COLLECTION_NAME,
                service.getConstants().getCollectionCnxCollectionsAbsPath(),
                getCnxCollectionCategoryEle(service));
    }

    private static Collection getCnxCollection(String collectionName,
            String collectionPath, Category category) {
        Collection collection = new Collection(collectionName,
                MediaType.TEXT_PLAIN, collectionPath);

        Categories categories = new Categories();
        categories.addCategory(category);
        collection.addCategories(categories);

        return collection;
    }
}
