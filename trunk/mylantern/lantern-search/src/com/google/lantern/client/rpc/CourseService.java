package com.google.lantern.client.rpc;

import com.google.lantern.shared.objectifyobjects.courses.ListOfCourses;

import com.google.lantern.shared.objectifyobjects.courses.CourseEntity;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.lantern.shared.ServletPaths;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath(ServletPaths.GwtServices.COURSE_SERVICE_SERVLET)
public interface CourseService extends RemoteService {
    Long createNewCourse(final CourseEntity newCourseEntity);
    
    CourseEntity getCourse(final Long courseId);
    
    void updateCourse(final CourseEntity updatedCourseEntity);
    
    ListOfCourses getListOfCourses(final String cursorStr);
    
    ListOfCourses searchCourses(final String searchString);
}
