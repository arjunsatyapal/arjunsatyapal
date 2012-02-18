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


import com.google.common.collect.Iterables;

import com.google.lantern.shared.enums.select.Domain;
import com.google.lantern.shared.enums.select.Grades;
import com.google.lantern.shared.enums.select.UsStates;
import com.google.lantern.shared.objectifyobjects.documents.DocumentEntity;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import java.util.List;

/**
 * 
 * @author Arjun Satyapal
 */
public class SGDocumentRecord extends ListGridRecord {
    public static final String ATTR_COURSE_ID = "DocumentId";
    public static final String COL_COURSE_ID = "Id";
    
    public static final String ATTR_COURSE_TITLE = "courseTitle";
    public static final String COL_COURSE_TITLE = "Title";
    
    public static final String ATTR_DOMAIN = "domains";
    public static final String COL_DOMAIN = "Domains";
    
    public static final String ATTR_US_STATES = "usStates";
    public static final String COL_US_STATES = "US States";
    
    public static final String ATTR_GRADES = "grades";
    public static final String COL_GRADES = "Grades";

    private Long documentId;
    private String title;
    private List<Domain> domains;
    private List<UsStates> usStates;
    private List<Grades> grades;

    public SGDocumentRecord(Long id, String title, List<Domain> domains, List<UsStates> usStates,
            List<Grades> grades) {
        setCourseId(id);
        setCourseTitle(title);
        setDomains(domains);
        setUsStates(usStates);
        setGrades(grades);
    }

    private void setCourseId(Long courseId) {
        this.documentId = courseId;
        setAttribute(ATTR_COURSE_ID, courseId);
    }

    private void setCourseTitle(String courseTitle) {
        this.title = courseTitle;
        setAttribute(ATTR_COURSE_TITLE, courseTitle);
    }

    private void setDomains(List<Domain> domains) {
        this.domains = domains;
        setAttribute(ATTR_DOMAIN, Iterables.toString(domains));
    }

    private void setUsStates(List<UsStates> states) {
        this.usStates = states;
        setAttribute(ATTR_US_STATES, Iterables.toString(states));
    }

    private void setGrades(List<Grades> grades) {
        this.grades = grades;
        setAttribute(ATTR_GRADES, Iterables.toString(grades));
    }

    public static SGDocumentRecord[] fromDocumentEntities(List<DocumentEntity> documentList) {
        if (documentList.size() <= 0) {
            return null;
        }

        SGDocumentRecord[] records = new SGDocumentRecord[documentList.size()];
        for (int counter = 0; counter < documentList.size(); counter++) {
            DocumentEntity currDocument = documentList.get(counter);

            records[counter] = new SGDocumentRecord(
                    currDocument.getDocumentId(), currDocument.getSitesPage().getTitle(),
                    currDocument.getSearchTags().getDomains(),
                    currDocument.getSearchTags().getUsStates(),
                    currDocument.getSearchTags().getGrades());
        }

        return records;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public String getTitle() {
        return title;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public List<UsStates> getUsStates() {
        return usStates;
    }

    public List<Grades> getGrades() {
        return grades;
    }
}
