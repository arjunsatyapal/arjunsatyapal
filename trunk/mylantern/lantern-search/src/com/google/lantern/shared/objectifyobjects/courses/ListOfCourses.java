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
package com.google.lantern.shared.objectifyobjects.courses;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

/**
 *
 * @author Arjun Satyapal
 */
public class ListOfCourses implements IsSerializable {
    private String cursor;
    private List<CourseEntity> listOfCourses;
    
    // Default constructor for GWT.
    protected ListOfCourses() {
    }
    
    public ListOfCourses(String cursor, List<CourseEntity> listOfCourses) {
        this.cursor = cursor;
        this.listOfCourses = checkNotNull(listOfCourses);
    }

    public String getCursor() {
        return cursor;
    }

    public List<CourseEntity> getListOfCourses() {
        return listOfCourses;
    }
}
