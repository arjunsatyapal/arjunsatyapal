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
package com.google.lantern.server.gwtservices;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lantern.client.rpc.CourseService;
import com.google.lantern.server.persistence.dao.CourseDao;
import com.google.lantern.shared.objectifyobjects.courses.CourseEntity;
import com.google.lantern.shared.objectifyobjects.courses.ListOfCourses;

/**
 * 
 * @author Arjun Satyapal
 */
@SuppressWarnings("serial")
public class CourseServiceImpl extends RemoteServiceServlet implements CourseService {
    public Long createNewCourse(CourseEntity newCourseEntity) {
        return CourseDao.createNewCourse(newCourseEntity);
    }

    public CourseEntity getCourse(Long courseId) {
        return CourseDao.getCourse(courseId);
    }

    public void updateCourse(final CourseEntity updatedCourseEntity)  {
//        CourseDao.updateCourse(updatedCourseEntity);
    }

    public ListOfCourses getListOfCourses(final String cursorStr) {
        return CourseDao.getListOfCourses(cursorStr);
    }

    public ListOfCourses searchCourses(final String searchString) {
        return CourseDao.searchCourses(searchString);
    }
}
