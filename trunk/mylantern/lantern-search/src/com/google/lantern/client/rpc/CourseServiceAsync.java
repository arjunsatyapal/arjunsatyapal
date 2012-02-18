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
package com.google.lantern.client.rpc;

import com.google.lantern.shared.objectifyobjects.courses.ListOfCourses;

import com.google.lantern.shared.objectifyobjects.courses.CourseEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Arjun Satyapal
 */
public interface CourseServiceAsync {
    void createNewCourse(CourseEntity newCourseEntity, AsyncCallback<Long> callback);

    void getCourse(Long courseId, AsyncCallback<CourseEntity> callback);

    void updateCourse(CourseEntity updatedCourseEntity, AsyncCallback<Void> callback);

    void getListOfCourses(String cursorStr, AsyncCallback<ListOfCourses> callback);

    void searchCourses(String searchString, AsyncCallback<ListOfCourses> callback);
}
