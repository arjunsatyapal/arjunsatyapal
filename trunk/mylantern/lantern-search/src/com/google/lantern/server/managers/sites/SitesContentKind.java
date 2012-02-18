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
package com.google.lantern.server.managers.sites;


/**
 *
 * @author Arjun Satyapal
 */
public enum SitesContentKind {
    ANNOUNCEMENT("announcement"), 
    ANNOUNCEMNTS_PAGE("announcementspage"),
    COMMENT("comment"),
    FILE_CABINET("filecabinet"),
    LIST_ITEM("listitem"),
    LIST_PAGE("listpage"),
    WEB_PAGE("webpage"),
    ATTACHMENT("attachment"),
    WEB_ATTACHMENT("webattachment"),
    
    ALL("all");
    
    private String kind;
    
    private SitesContentKind(String kind) {
        this.kind = kind;
    }
    
    public String get() {
        return kind;
    }
}
