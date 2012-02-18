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
package com.google.lantern.client.events;

/**
 *
 * @author Arjun Satyapal
 */
public enum HistoryEvent {
    HOME("home"),
    AUTHOR_HOME("author"),
    NOT_FOUND("404");
    
    private String name;
    
    public String getName() {
        return name;
    }
    
    private HistoryEvent(String name) {
        this.name = name;
    }
    
    
    public static HistoryEvent getHistoryEvent(String name) {
        for (HistoryEvent currEvent : HistoryEvent.values()) {
            if (currEvent.getName().equalsIgnoreCase(name)) {
                return currEvent;
            }
        }
        
        return NOT_FOUND;
    }
}
