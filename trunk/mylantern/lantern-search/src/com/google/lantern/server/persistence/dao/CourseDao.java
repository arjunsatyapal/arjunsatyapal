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
package com.google.lantern.server.persistence.dao;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.lantern.shared.exceptions.unchecked.UserNotLoggedInException;


import com.google.lantern.server.persistence.utils.ObjectifyUtils;

import com.google.lantern.server.persistence.search.FullTextSearchHelper;
import com.google.lantern.server.persistence.search.FullTextSearchIndexHelper;


import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.appengine.api.search.Document;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.lantern.server.persistence.utils.QueryUtils;
import com.google.lantern.shared.objectifyobjects.courses.CourseEntity;
import com.google.lantern.shared.objectifyobjects.courses.ListOfCourses;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * @author Arjun Satyapal
 */
public class CourseDao extends DAOBase {
    private static final Logger logger = Logger.getLogger(CourseDao.class.getName());
    static {
        ObjectifyService.register(CourseEntity.class);
    }

    public static Long createNewCourse(CourseEntity newCourseEntity) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        ofy.put(newCourseEntity);
        return checkNotNull(newCourseEntity.getId());
    }

    public static CourseEntity getCourse(Long courseId) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        return ofy.get(new Key<CourseEntity>(CourseEntity.class, courseId));
    }

    public static void updateCourse(CourseEntity updatedCourseEntity)
            throws UserNotLoggedInException {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        ofy.put(updatedCourseEntity);

        // Now making it available for search.
        // TODO(arjuns) : Add field in entity to show that it is available for search.
        Document doc =
                FullTextSearchHelper.toAppEngineSearchDoc(updatedCourseEntity.getId(),
                        updatedCourseEntity.getContent());
        try {
            FullTextSearchIndexHelper.getIndex().add(doc);
            logger.info("Successfully indexed document " + doc.getId());
        } catch (Exception e) {
            logger.severe("Failed to index a document" + Throwables.getStackTraceAsString(e));
        }
    }

    public static ListOfCourses getListOfCourses(final String cursorStr) {
        Objectify ofy = ObjectifyUtils.getObjectifyService();
        Query<CourseEntity> query = ofy.query(CourseEntity.class);

        if (cursorStr != null) {
            query.startCursor(Cursor.fromWebSafeString(cursorStr));
        }

        List<CourseEntity> listOfCourses = Lists.newArrayList();
        String newCursorStr = null;
        QueryResultIterator<CourseEntity> iterator = query.iterator();
        while (iterator.hasNext()) {
            CourseEntity course = iterator.next();
            listOfCourses.add(course);

            if (listOfCourses.size() >= QueryUtils.PAGE_SIZE) {
                newCursorStr = iterator.getCursor().toWebSafeString();
                break;
            }
        }

        return new ListOfCourses(newCursorStr, listOfCourses);
    }

    public static ListOfCourses searchCourses(String searchString) {
        String searchQuery = FullTextSearchHelper.HTML_FIELD_NAME + ":" + searchString;

//        SearchParams params = new SearchParams(searchQuery, QueryUtils.PAGE_SIZE);
//        List<Document> docs = FullTextSearchHelper.findAll(params);
//
//        StringBuilder strBuilder = new StringBuilder();
//        for (Document currDoc : docs) {
//            strBuilder.append(currDoc.getId()).append(" ");
//        }
//
//        logger.severe(strBuilder.toString());

        return null;
    }
}
