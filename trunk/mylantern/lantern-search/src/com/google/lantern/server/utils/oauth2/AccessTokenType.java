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
package com.google.lantern.server.utils.oauth2;

/**
 *
 * @author Arjun Satyapal
 */
public enum AccessTokenType {
    BEARER("Bearer");
    
    private String tokenType;
    
    private AccessTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    
    public String get() {
        return tokenType;
    }
    
    public static AccessTokenType getAccessTokenTypeByString(String typeString) {
        for (AccessTokenType currType : AccessTokenType.values()) {
            if (currType.get().equals(typeString)) {
                return currType;
            }
        }
        
        throw new IllegalArgumentException("unknown typeString : "+ typeString);
    }
}
