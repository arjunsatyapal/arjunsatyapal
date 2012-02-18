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
public enum Grades implements HelperEnum<Grades> { 
    GRADE_1,
    GRADE_2,
    GRADE_3,
    GRADE_4,
    GRADE_5,
    GRADE_6,
    GRADE_7,
    GRADE_8,
    GRADE_9,
    GRADE_10,
    GRADE_11,
    GRADE_12,
    First_Year,
    Second_Year,
    Third_Year,
    Final_Year;

    public static SelectEnumHelper<Grades> helper = new SelectEnumHelper<Grades>(
            Grades.class);

    public static SelectEnumHelper<Grades> getHelper() {
        return helper;
    }

    public String getValue() {
        return name();
    }

    public Grades[] getValues() {
        return values();
    }

    public String getPublicName() {
        return helper.getPublicName(this);
    }
}
