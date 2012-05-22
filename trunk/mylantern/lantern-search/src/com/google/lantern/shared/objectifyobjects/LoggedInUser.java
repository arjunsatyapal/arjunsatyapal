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
package com.google.lantern.shared.objectifyobjects;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author Arjun Satyapal
 */
public class LoggedInUser implements IsSerializable {
    String email;
    
    // Required for GWT Serialization.
    @SuppressWarnings("unused")
    private LoggedInUser() {
    }
    
    public LoggedInUser(String email) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(email), "email cannot be empty/null");
        this.email = this.getEmail();
    }
    
    public String getEmail() {
        return email;
    }
}