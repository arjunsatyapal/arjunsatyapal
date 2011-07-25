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

import com.arjunsatyapal.rome.enums.ServletPaths;
import com.sun.syndication.feed.atom.Category;

/**
 * Utility class for AtomCategories.
 * 
 * @author Arjun Satyapal
 */
public class AtomCategoryUtils {
    public static Category getCnxResourceCategoryEle() {
        return getCnxCategoryEle(ServletPaths.AP_COLLECTION_RESOURCE_REL_PATH,
                ServletPaths.AP_COLLECTION_RESOURCE_ABS_PATH,
                ServletPaths.AP_COLLECTION_RESOURCE_REL_PATH);
    }
    
    public static Category getCnxModuleCategoryEle() {
        return getCnxCategoryEle(ServletPaths.AP_COLLECTION_MODULE_REL_PATH,
                ServletPaths.AP_COLLECTION_MODULE_ABS_PATH,
                ServletPaths.AP_COLLECTION_MODULE_REL_PATH);
    }
    
    public static Category getCnxCollectionCategoryEle() {
        return getCnxCategoryEle(ServletPaths.AP_COLLECTION_CNX_COLLECTION_REL_PATH,
                ServletPaths.AP_COLLECTION_CNX_COLLECTION_ABS_PATH,
                ServletPaths.AP_COLLECTION_CNX_COLLECTION_REL_PATH);
    }
    
    public static Category getCnxCategoryEle(String label, String schemePath, String term) {
        Category category = new Category();
        category.setLabel(label);
        category.setScheme(schemePath);
        category.setTerm(term);
        
        return category;
    }
}
