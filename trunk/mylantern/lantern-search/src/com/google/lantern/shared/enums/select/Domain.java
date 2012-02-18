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
package com.google.lantern.shared.enums.select;


/**
 * 
 * @author Arjun Satyapal
 */
public enum Domain implements HelperEnum<Domain>{
    Physics,
    Chemistry,
    Maths,
    Biology,
    History,
    Other;

    public static SelectEnumHelper<Domain> helper = new SelectEnumHelper<Domain>(
            Domain.class);

    public static SelectEnumHelper<Domain> getHelper() {
        return helper;
    }

    public String getValue() {
        return name();
    }

    public Domain[] getValues() {
        return values();
    }

    public String getPublicName() {
        return helper.getPublicName(this);
    }
}
