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
public enum UsStates implements HelperEnum<UsStates> {
    // TODO(arjuns) : Add test to ensure that all have different public names.
    // Special Values.
    Alabama,
    Alaska,
    American_Samoa,
    Arizona,
    Arkansas,
    California,
    Colorado,
    Connecticut,
    Delaware,
    District_of_Columbia,
    Florida,
    Georgia,
    Guam,
    Hawaii,
    Idaho,
    Illinois,
    Indiana,
    Iowa,
    Kansas,
    Kentucky,
    Louisiana,
    Maine,
    Maryland,
    Massachusetts,
    Michigan,
    Minnesota,
    Mississippi,
    Missouri,
    Montana,
    Nebraska,
    Nevada,
    New_Hampshire,
    New_Jersey,
    New_Mexico,
    New_York,
    North_Carolina,
    North_Dakota,
    Northern_Marianas_Islands,
    Ohio,
    Oklahoma,
    Oregon,
    Pennsylvania,
    Puerto_Rico,
    Rhode_Island,
    South_Carolina,
    South_Dakota,
    Tennessee,
    Texas,
    Utah,
    Vermont,
    Virginia,
    Virgin_Islands,
    Washington,
    West_Virginia,
    Wisconsin,
    Wyoming;
    
    public static SelectEnumHelper<UsStates> helper = new SelectEnumHelper<UsStates>(
            UsStates.class);

    public static SelectEnumHelper<UsStates> getHelper() {
        return helper;
    }

    public String getValue() {
        return name();
    }

    public UsStates[] getValues() {
        return values();
    }

    public String getPublicName() {
        return helper.getPublicName(this);
    }
}
