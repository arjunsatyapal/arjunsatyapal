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
package com.google.lantern.client.smartgwtentity;

import com.google.lantern.shared.objectifyobjects.courses.CourseEntity;

import java.util.List;

import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 *
 * @author Arjun Satyapal
 */
public class SGCourseRecord extends ListGridRecord {
    public static final String ATTR_COURSE_ID="courseId";
    public static final String ATTR_COURSE_TITLE = "courseTitle";
    
    private Long courseId;
    private String courseTitle;
    
    public SGCourseRecord(Long id, String title) {
        setCourseId(id);
        setCourseTitle(title);
    }

    private void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
        setAttribute(ATTR_COURSE_TITLE, courseTitle);
        
    }

    private void setCourseId(Long courseId) {
        this.courseId = courseId;
        setAttribute(ATTR_COURSE_ID, courseId);
    }
    
    public static SGCourseRecord[] fromCourseEntities(List<CourseEntity> courseList) {
        if (courseList.size() <= 0) {
            return null;
        }
        
        SGCourseRecord[] records = new SGCourseRecord[courseList.size()];
        for (int counter=0; counter < courseList.size(); counter++) {
            CourseEntity currCourse = courseList.get(counter);
            
            records[counter] = new SGCourseRecord(currCourse.getId(), currCourse.getTitle());
        }
        
        return records;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return courseTitle;
    }
}
