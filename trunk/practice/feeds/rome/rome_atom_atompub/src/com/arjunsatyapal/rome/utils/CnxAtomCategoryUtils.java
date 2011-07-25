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

import static com.arjunsatyapal.rome.enums.CnxAtomPubConstants.COLLECTION_CNX_COLLECTION_REL_PATH;
import static com.arjunsatyapal.rome.enums.CnxAtomPubConstants.COLLECTION_MODULE_REL_PATH;
import static com.arjunsatyapal.rome.enums.CnxAtomPubConstants.COLLECTION_RESOURCE_REL_PATH;

import com.arjunsatyapal.rome.atompubimpl.CnxAtomService;
import com.sun.syndication.feed.atom.Category;

/**
 * Utility class for AtomCategories.
 * 
 * @author Arjun Satyapal
 */
public class CnxAtomCategoryUtils {
    // Utility class.
    private CnxAtomCategoryUtils() {
    }
    
    public static Category getCnxResourceCategoryEle(CnxAtomService service) {
        return getCnxCategoryEle(service, COLLECTION_RESOURCE_REL_PATH,
                COLLECTION_RESOURCE_REL_PATH);
    }
    
    public static Category getCnxModuleCategoryEle(CnxAtomService service) {
        return getCnxCategoryEle(service, COLLECTION_MODULE_REL_PATH,
                COLLECTION_MODULE_REL_PATH);
    }
    
    public static Category getCnxCollectionCategoryEle(CnxAtomService service) {
        return getCnxCategoryEle(service, COLLECTION_CNX_COLLECTION_REL_PATH,
                COLLECTION_CNX_COLLECTION_REL_PATH);
    }
    
    public static Category getCnxCategoryEle(CnxAtomService service, String label, String term) {
        Category category = new Category();
        category.setLabel(label);
        category.setTerm(term);
        category.setScheme(service.getConstants().getSchemeAbsUrl());
        
        return category;
    }
}
