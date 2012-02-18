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
package com.google.lantern.shared.dto;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Strings;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.lantern.shared.objectifyobjects.SearchTags;

/**
 *
 * @author Arjun Satyapal
 */
public class SearchCriteria implements IsSerializable {
    private String searchString;
    private SearchTags searchTags;
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("SearchString[").append(searchString).append("],");
        
        if (searchTags != null) {
            builder.append(searchTags.toString());
        }
        
        return builder.toString();
    }

    public SearchCriteria() {
        this("", new SearchTags());
    }
    
    public SearchCriteria(String searchString, SearchTags searchTags) {
        this.searchString = checkNotNull(searchString);
        this.searchTags = checkNotNull(searchTags);
    }
    
    public boolean hasSearchString() {
        return !Strings.isNullOrEmpty(searchString);
    }
    
    public String getSearchString() {
        return searchString;
    }
    
    public SearchCriteria setSearchString(String searchString) {
        this.searchString = searchString;
        return this;
    }

    public boolean hasSearchTags() {
        return searchTags.hasTags();
    }
    
    public SearchTags getSearchTags() {
        return searchTags;
    }
    public SearchCriteria setSearchTags(SearchTags searchTags) {
        this.searchTags = searchTags;
        return this;
    }
}
